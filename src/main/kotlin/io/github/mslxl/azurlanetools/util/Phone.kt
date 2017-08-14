package io.github.mslxl.azurlanetools.util

import io.github.mslxl.azurlanetools.config.Config
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
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
    fun exc(command:String):String=Console.exc("adb shell $command")

    fun tap(x:Int,y:Int){
        exc("input tap $x $y")
    }

    fun screenshot(delOnExit:Boolean = true): BufferedImage {
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
            img.rotate()
        }
        return img
    }
    fun waitFor(image:BufferedImage,frequency:Long,timeOut:Long){
        var time = System.currentTimeMillis()
        loop {
            val timeN = System.currentTimeMillis() - time

            if (frequency > timeN){
                Thread.sleep(frequency - timeN)
            }



            time = System.currentTimeMillis()
        }
    }
    fun searchOnScreen(image:BufferedImage):Rectangle{
        val screenshot = screenshot()
        return searchRectInsideImage(screenshot,image)
    }

}