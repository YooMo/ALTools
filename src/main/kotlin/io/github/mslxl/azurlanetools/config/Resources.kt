package io.github.mslxl.azurlanetools.config


import com.google.gson.Gson
import io.github.mslxl.azurlanetools.util.Rectangle
import io.github.mslxl.azurlanetools.util.isFalse
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Resources private constructor(val dir: File) {
    companion object {
        private val map = HashMap<String,BufferedImage>()
        private val groupMap = HashMap<String,Resources>()
        operator fun get(pathName:String):BufferedImage{
            if (!map.containsKey(pathName)){
                val file = File(Config.PHONE_DIR,pathName)
                if (!file.exists()) error("$pathName in ${Config.PHONE_DIR.name} was not found")
                map[pathName] = ImageIO.read(file)
            }
            return map[pathName]!!
        }
        operator fun invoke(groupName:String):Resources{
            if (!groupMap.containsKey(groupName)){
                groupMap[groupName] = Resources(
                        File(Config.PHONE_DIR,groupName).apply {
                            if (!exists())
                            mkdirs()
                        }
                )
            }
            return groupMap[groupName]!!
        }
        fun getLocation(pathName: String):Rectangle{
            File(Config.PHONE_DIR,pathName+".json").run {
                exists().isFalse {
                    error("$pathName was not found!")
                }
                return Gson().fromJson<Rectangle>(inputStream().bufferedReader().readText(),Rectangle::class.java)
            }
        }
    }
    operator fun get(pathName:String):BufferedImage{
        if (!map.containsKey(pathName)){
            val file = File(dir,pathName)
            if (!file.exists()) error("$pathName in ${Config.PHONE_DIR.name}/${dir.name} was not found")
            map[pathName] = ImageIO.read(file)
        }
        return map[pathName]!!
    }
    fun getLocation(pathName: String):Rectangle{
        File(dir,pathName+".json").run {
            exists().isFalse {
                error("$pathName was not found!")
            }
            return Gson().fromJson<Rectangle>(inputStream().bufferedReader().readText(),Rectangle::class.java)
        }
    }
}