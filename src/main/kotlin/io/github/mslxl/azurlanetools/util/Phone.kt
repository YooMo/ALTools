package io.github.mslxl.azurlanetools.util

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
        return ImageIO.read(file)
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
        var rx = -1
        var ry = -1
        var rw = -1
        var rh = -1

        for (x in 0..image.width) x@{
            for (y in 0..image.height) y@{
                val color = image.getRGB(x,y)
                for (sx in 0..screenshot.width) sx@{
                    for (sy in 0..screenshot.height) sy@{
                        val sColor = screenshot.getRGB(sx,sy)
                        if ((color==sColor) and ((rx ==0 ) and (ry == 0))){
                            rx = sx
                            ry = sy
                        }
                    }
                }
            }
        }
        return Rectangle(rx,ry,rw,rh)
    }

    data class Rectangle(
            val x:Int,
            val y:Int,
            val width:Int,
            val height:Int
    )
}