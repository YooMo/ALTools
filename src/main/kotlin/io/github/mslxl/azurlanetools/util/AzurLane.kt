package io.github.mslxl.azurlanetools.util

import java.io.File
import java.util.*

object AzurLane {
    fun stop(){
        Phone.exc("am force-stop com.bilibili.azurlane")
        Thread.sleep(5 * 1000)
    }
    fun start(){
        Phone.exc("am start -n com.bilibili.azurlane/com.manjuu.azurlane.MainActivity")
    }

}