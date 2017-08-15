package io.github.mslxl.azurlanetools.util

import io.github.mslxl.azurlanetools.config.Config
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import kotlin.concurrent.thread

object Phone {
    fun exc(command: List<String>){
        val process = Runtime.getRuntime().exec("adb shell")
        process.outputStream.bufferedWriter().run {
            command.forEach {
                this.write(it)
                this.newLine()
                this.flush()
            }
            this.write("exit")
            this.newLine()
            this.flush()
            this.close()
        }
    }
    fun exc(command:String):String{
        return Console.exc("adb shell $command")
    }

    fun tap(x:Int,y:Int){
        println("Tap $x $y")
        exc("input tap $x $y")
    }
    fun tap(rect:Rectangle){
        if (!rect.exists()) error("Can not tap not exists rect!")
        val x = Random().nextInt(rect.width)
        val y = Random().nextInt(rect.height)
        tap(x+rect.x,y+rect.y)
    }
    private lateinit var screen:BufferedImage
    fun screenshot(new:Boolean = true,delOnExit:Boolean = true): BufferedImage {

/*        if (!new){
            return screen
        }*/
        println("Screenshot...")
        val id = System.currentTimeMillis()
        var file = File("screencap/$id.png")
        if (!file.parentFile.exists()){
            file.parentFile.mkdirs()
        }
        Phone.exc("mkdir /mnt/sdcard/screencap/")
        Phone.exc("screencap -p /mnt/sdcard/screencap/$id.png").println()
        Console.exc("adb pull /mnt/sdcard/screencap/$id.png ${file.absolutePath}").println()
        Phone.exc("rm /mnt/sdcard/screencap/$id.png")
        if (delOnExit){
            file.deleteOnExit()
        }
        var img = ImageIO.read(file)
        loop(Config.rotate_times){
            img = img.rotate()
        }
        screen = img
        return img
    }
    fun waitFor(image:BufferedImage,frequency:Long=5000,timeOut:Long=30000){
        var time = System.currentTimeMillis()
        val sTime = time
        loop {
            val timeN = System.currentTimeMillis() - time
            if (frequency > timeN){
                Thread.sleep(frequency - timeN)
            }
            if (canFindOnScreen(image)){
                return
            }
            time = System.currentTimeMillis()
            if (time - sTime >= timeOut){
                error("Time out")
            }
            println("Wait...")
        }
    }
    fun searchOnScreen(image:BufferedImage,new: Boolean = true):Rectangle{
        val screenshot = screenshot(new)
        return searchRectInsideImage(screenshot,image)
    }
    fun canFindOnScreen(image:BufferedImage,newScreencap: Boolean = true):Boolean= searchOnScreen(image,newScreencap).let {
        (x, y, width, height) ->
        (height !=-1)and(width != -1)and(x != -1)and(y != -1)
    }
}