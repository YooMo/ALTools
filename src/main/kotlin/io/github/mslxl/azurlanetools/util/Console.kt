package io.github.mslxl.azurlanetools.util

object Console {
    fun exc(command:String):String{
        val process = Runtime.getRuntime().exec(command)
        process.waitFor()
        return process.inputStream.bufferedReader().readText().trim()
    }
}