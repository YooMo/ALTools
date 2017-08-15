package io.github.mslxl.azurlanetools.operatioer

import io.github.mslxl.azurlanetools.config.Config
import io.github.mslxl.azurlanetools.config.Resources
import io.github.mslxl.azurlanetools.util.AzurLane
import io.github.mslxl.azurlanetools.util.Console
import io.github.mslxl.azurlanetools.util.Phone
import io.github.mslxl.azurlanetools.util.commands

object Run {
    operator fun invoke(){
        println(Config.PHONE_DIR.name)
        AzurLane.restart()
        readLine()
    }
}