package io.github.mslxl.azurlanetools.operatioer

import io.github.mslxl.azurlanetools.config.Resources
import io.github.mslxl.azurlanetools.util.AzurLane
import io.github.mslxl.azurlanetools.util.Console
import io.github.mslxl.azurlanetools.util.Phone
import io.github.mslxl.azurlanetools.util.commands

object Run {
    operator fun invoke(){
        println("Restart game...")
        AzurLane.stop()
        println("Wait for start...")
        AzurLane.start()
        Phone.waitFor(Resources["loginUI.png"],timeOut = 60 * 1000)
        Phone.tap(200,100)
        println("Init resources...")
        Thread.sleep(10 *1000)
        AzurLane.showMain()
    }
}