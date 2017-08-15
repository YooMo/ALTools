package io.github.mslxl.azurlanetools.operatioer

import io.github.mslxl.azurlanetools.config.Config
import io.github.mslxl.azurlanetools.config.Resources
import io.github.mslxl.azurlanetools.util.*
import java.util.*

object Run {
    operator fun invoke(){
        println(Config.PHONE_DIR.name)
        AzurLane.restart()
        println("Please choice task you want to exc: ")
        val list = Tasks.list()
        val a = ArrayList<String>()
        list.forEachIndexed{
            index,it->
            println("\t $index.$it")
            a.add(it)
        }
        val index = Scanner(System.`in`).nextInt()
        Tasks.run(a[index])
    }
}