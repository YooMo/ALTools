package io.github.mslxl.azurlanetools.config

import io.github.mslxl.azurlanetools.util.isNull
import java.util.*
import java.util.Properties

class Properties: java.util.Properties {
    constructor() : super()
    constructor(defaults: Properties?) : super(defaults)

    override fun getOrDefault(key: Any, defaultValue: Any): Any {
        val property = super.getOrDefault(key, null)
        if (property.isNull()){
            super.put(key,defaultValue)
            return defaultValue
        }else{
            return property
        }
    }

    override fun getProperty(key: String, defaultValue: String): String {
        val property =  super.getProperty(key, null)
        if (property.isNull()){
            super.put(key,defaultValue)
            return defaultValue
        }else{
            return property
        }
    }
}