package io.github.mslxl.azurlanetools.util

import io.github.mslxl.azurlanetools.config.Resources
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
    fun showMain(){
        println("Try to open main activity...")
        if (isSystemAnnounce(newScreencap = true)){
            Phone.tap(Phone.searchOnScreen(Resources["closeButton.png"],false))
            return
        }

        if (Phone.searchOnScreen(Resources["back_button_attack.png"],new = true).exists()){
            Phone.tap(Phone.searchOnScreen(Resources["back_button_attack.png"],false))
        }


        if (Phone.searchOnScreen(Resources["back_button_anchor.png"],true).exists()){
            Phone.tap(Phone.searchOnScreen(Resources["back_button_anchor.png"],false))
        }

    }

    fun isSystemAnnounce(newScreencap:Boolean = true): Boolean{
        println("Check whether the system announce...")
        return Phone.canFindOnScreen(Resources["systemAnnounce.png"],newScreencap)
    }

}