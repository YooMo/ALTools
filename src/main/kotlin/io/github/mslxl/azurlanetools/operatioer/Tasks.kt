package io.github.mslxl.azurlanetools.operatioer

object Tasks {
    private val map = HashMap<String,Class<out Task>>()

    @JvmOverloads
    fun reg(title:String = "No title",clazz: Class<out Task>){
        map[title] = clazz
    }

    fun list() = map.keys
    fun run(title: String){
        val newInstance = map[title]!!.newInstance()
        val method = map[title]!!.getMethod("run")
        method.invoke(newInstance)
    }
}