package io.github.mslxl.azurlanetools.config

import io.github.mslxl.azurlanetools.util.Phone
import io.github.mslxl.azurlanetools.util.isFalse
import io.github.mslxl.azurlanetools.util.unit
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object Config{
    private val FILE = File("config.properties").apply {
        this.exists().isFalse {
            createNewFile()
        }
    }
    val PHONE_DIR = File(Phone.exc("getprop ro.serialno")).apply {
            if (!exists())
                mkdirs()
    }

    private val properties = Properties().apply {
        FileInputStream(FILE).run {
            this@apply.load(this)
            this.close()
        }
    }
    private val phoneProperties = Properties().apply {
        FileInputStream(file("settings.properties")).run {
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
        FileOutputStream(file("settings.properties")).run {
            phoneProperties.store(this,"AzurLane Tools config file")
            this.flush()
            this.close()
        }
    }
    var rotate_times:Int
        get() = phoneProperties.getProperty("rotate_time","0").toInt()
        set(value) = phoneProperties.put("rotate_time",value).unit()

    var adb_path:String
        get() = properties.getProperty("adb_path","")
        set(value) = properties.put("adb_path",value).unit()


    private fun file(path:String) = File(PHONE_DIR,path).apply {
        if (!exists()) createNewFile()
    }

    init {
        sun.misc.SharedSecrets.getJavaLangAccess().registerShutdownHook(3 /* Shutdown hook invocation order */, true /* register even if shutdown in progress */) {
            store()
        }
    }
}