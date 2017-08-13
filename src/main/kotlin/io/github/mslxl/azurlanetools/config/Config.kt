package io.github.mslxl.azurlanetools.config

import io.github.mslxl.azurlanetools.util.unit
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

object Config{
    private val FILE = File("config.properties")
    private val properties = Properties().apply {
        FileInputStream(FILE).run {
            this@apply.load(this)
            this.close()
        }
    }

    fun store(){
        FileOutputStream(FILE).run {
            properties.store(this,"AzurLane Tools config file")
            this.flush()
            this.close()
        }
    }


    var adb_path:String
        get() = properties.getProperty("adb_path",null)
        set(value) = properties.put("adb_path",value).unit()

}