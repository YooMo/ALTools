package io.github.mslxl.azurlanetools

import io.github.mslxl.azurlanetools.config.Config
import io.github.mslxl.azurlanetools.operatioer.Run
import io.github.mslxl.azurlanetools.operatioer.Setting
import io.github.mslxl.azurlanetools.util.isFalse
import io.github.mslxl.azurlanetools.util.isNull
import io.github.mslxl.azurlanetools.util.tryDoUntilTrue
import java.io.File
import java.util.*


object AzurLaneTools {

    @JvmStatic
    fun main(args: Array<String>) {
        println("AzurLane Tools by mslxl")
        println("Init...")

        Config.adb_path.isNull {
            tryDoUntilTrue {
                times->
                (times==1).isFalse {
                    println("File was not exists")
                }
                print("Please enter adb tools path:")
                val path = readLine()
                val file = File(path)
                if (file.exists()){
                    Config.adb_path = file.absolutePath
                }
                file.exists()
            }
        }
        Config.store()

        println("Please use '${Config.adb_path}' connect your phone")
        print("Press [Enter] to continue...")
        readLine()
        println("Config fill was created.")
        var input:Int = 0
        tryDoUntilTrue {
            println("Please enter your operation you want:")
            println("\t0. Run")
            println("\t1. Setting")
            println("\t3. Exit")
            print("Your choice:")
            input = Scanner(System.`in`).nextInt()
            !(input<0||input > 2)
        }
        when(input){
            0->Run()
            1->Setting()
        }
    }

}