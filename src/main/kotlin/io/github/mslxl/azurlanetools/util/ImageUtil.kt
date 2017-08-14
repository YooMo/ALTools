package io.github.mslxl.azurlanetools.util

import java.awt.Color
import java.awt.image.BufferedImage

fun BufferedImage.getColor(x:Int,y:Int)=Color(this.getRGB(x, y))