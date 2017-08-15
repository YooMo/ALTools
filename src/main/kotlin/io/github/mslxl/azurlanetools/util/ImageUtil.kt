package io.github.mslxl.azurlanetools.util

import java.awt.Color
import java.awt.image.BufferedImage

fun BufferedImage.getColor(x: Int, y: Int) = Color(this.getRGB(x, y))
/**
 * 在 [screenshot] 中寻找 [image] 并返回位置
 * Search [image] inside [screenshot] and return its bounds
 */
fun searchRectInsideImage(screenshot: BufferedImage, image: BufferedImage): Rectangle {
    for (sy in 0..screenshot.height - 1) {
        for (sx in 0..screenshot.width - 1) {
            if (screenshot.getRGB(sx, sy) == image.getRGB(0, 0)) {
                var match = true
                inside@ for (x in 0..image.width - 1) {
                    for (y in 0..image.height - 1) {
                        if (image.getRGB(x, y) != screenshot.getRGB(sx + x, sy + y)) {
                            match = false
                            break@inside
                        }
                    }
                }
                if (match) {
                    return Rectangle(sx, sy, image.width, image.height)
                }
            }
        }
    }
    return Rectangle(-1, -1, -1, -1)
}

fun BufferedImage.rotate(): BufferedImage {
    println("Rotate the image...")
    val image = BufferedImage(height, width, type)
    for (tx in 0..width-1) {
        for (th in 0..height-1) {
            image.setRGB(height-th-1,tx, getRGB(tx, th))
        }
    }
    return image
}

data class Rectangle(
        val x: Int,
        val y: Int,
        val width: Int,
        val height: Int
) {
    fun exists() = (x != -1) and (y != -1) and (width != -1) and (height != -1)
}