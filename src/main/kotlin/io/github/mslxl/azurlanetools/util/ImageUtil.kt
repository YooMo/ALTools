package io.github.mslxl.azurlanetools.util

import java.awt.Color
import java.awt.image.BufferedImage

fun BufferedImage.getColor(x:Int,y:Int)=Color(this.getRGB(x, y))
/**
 * 在 [screenshot] 中寻找 [image] 并返回位置
 * Search [image] inside [screenshot] and return its bounds
 */
fun searchRectInsideImage(screenshot:BufferedImage,image: BufferedImage):Rectangle{
    for (sy in 0..screenshot.height-1){
        for (sx in 0..screenshot.width-1){
            if (screenshot.getRGB(sx,sy)==image.getRGB(0,0)){
                var match = true
                inside@ for (x in 0..image.width-1) {
                    for (y in 0..image.height-1){
                        if (image.getRGB(x,y)!=screenshot.getRGB(sx+x,sy+y)){
                            match = false
                            break@inside
                        }
                    }
                }
                if (match){
                    return Rectangle(sx, sy, sx + image.width, sy + image.height)
                }
            }
        }
    }
    return Rectangle(-1, -1, -1, -1)
}

data class Rectangle(
        val x:Int,
        val y:Int,
        val width:Int,
        val height:Int
)