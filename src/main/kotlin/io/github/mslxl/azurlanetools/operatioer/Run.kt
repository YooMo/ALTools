package io.github.mslxl.azurlanetools.operatioer

import io.github.mslxl.azurlanetools.util.AzurLane
import io.github.mslxl.azurlanetools.util.Console
import io.github.mslxl.azurlanetools.util.Phone
import io.github.mslxl.azurlanetools.util.commands

class Run {
    init {
        println("Restart game...")

        AzurLane.stop()


        println("Wait for start...")

        AzurLane.start()


        Thread.sleep(30 * 1000)
        Phone.tap(200,100)
    }
}