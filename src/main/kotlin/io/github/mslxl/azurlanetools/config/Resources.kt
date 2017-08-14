package io.github.mslxl.azurlanetools.config

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Resources {
    private val map = HashMap<String,BufferedImage>()
    operator fun get(pathName:String):BufferedImage{
        if (!map.containsKey(pathName)){
            val file = File(Config.PHONE_DIR,pathName)
            if (!file.exists()) error("$pathName in ${Config.PHONE_DIR.name} was not found")
            map[pathName] = ImageIO.read(file)
        }
        return map[pathName]!!
    }
}