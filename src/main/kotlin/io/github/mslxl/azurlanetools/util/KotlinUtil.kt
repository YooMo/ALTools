package io.github.mslxl.azurlanetools.util

import java.io.File
import javax.imageio.ImageIO

fun Any?.unit() =Unit

fun <C> C.print():C{
    print(this)
    return this
}

fun <C> C.println():C{
    println(this)
    return this
}

inline fun Boolean.isTrue(block:()->Unit): Boolean{
    if (this) block.invoke()
    return this
}

inline fun Boolean.isFalse(block:()->Unit): Boolean{
    if (!this) block.invoke()
    return this
}

inline fun Any?.isNull(block: () -> Unit):Any?{
    (this==null).isTrue(block)
    return this
}

inline fun Any?.isNotNull(block: () -> Unit):Any{
    (this!=null).isTrue(block)
    return this!!
}
inline fun Any.isEquals(other:Any,block: () -> Unit):Any{
    (this == other).isTrue(block)
    return this
}

inline fun Any.isNotEquals(other:Any,block: () -> Unit):Any{
    (this == other).isFalse(block)
    return this
}

fun Any?.isNull() = this==null
fun Any?.isNotNull() = this!=null

inline fun tryDoUntilTrue(allowException:Boolean = false, block: (times:Int) -> Boolean){
    var times = 1
    var ret:Boolean = false
    var exceptionHappen:Boolean
    do {
        exceptionHappen = false
        try {
            ret = block.invoke(times)
            times++
        }catch (e:Exception){
            e.printStackTrace()
            exceptionHappen = true
        }

    }while (!ret&&if (allowException) true else !exceptionHappen)
}

fun commands(vararg command:String):List<String>{
    return ArrayList<String>().apply {
        command.forEach {
            this.add(it)
        }
    }
}
inline fun loop(block: () -> Unit){
    while (true){
        block.invoke()
    }
}
inline fun loop(times: Int,block: () -> Unit){
    if(times > 0){
        for (i in 1..times){
            block.invoke()
        }
    }

}
fun image(path:String) = ImageIO.read(File(path))
fun image(path:File) = ImageIO.read(path)