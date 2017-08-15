package io.github.mslxl.azurlanetools

import io.github.mslxl.azurlanetools.operatioer.Tasks
import io.github.mslxl.azurlanetools.task.Attack3to4

fun initTasks(){
    Tasks.reg("3-4 loop",Attack3to4::class.java)
}