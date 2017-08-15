import io.github.mslxl.azurlanetools.util.*
import io.kotlintest.matchers.shouldNot
import io.kotlintest.matchers.shouldNotBe
import org.junit.Test
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

class ImageMatch{


    @Test
    fun test(){
        val stime = System.currentTimeMillis()
        val resourceAsStream1 = ImageMatch::class.java.classLoader.getResourceAsStream("image/1502710567211.png")
        val s = ImageIO.read(resourceAsStream1)
        val random = true
        val x = if (random) Random().nextInt(s.width-250) else 131
        val y = if (random) Random().nextInt(s.height-250) else 846
        val bufferedImage = BufferedImage(200, 200, s.type)

        for (sx in 0..199){
            for (sy in 0..199){
                val rgb = s.getRGB(x+sx,y+sy)
                bufferedImage.setRGB(sx,sy,rgb)
            }
        }


        val result = searchRectInsideImage(s,bufferedImage)
        ImageIO.write(bufferedImage,"png",File("testResult.png"))
        println("Image source is x=$x y=$y")

        result.height shouldNotBe -1
        result.width shouldNotBe -1
        result.x shouldNotBe -1
        result.y shouldNotBe -1

        println("Image found! rect is x=${result.x} y=${result.y} w=${result.width} h=${result.height}")
        println("It cost ${System.currentTimeMillis() - stime}ms")
    }

    @Test
    fun testR(){
        val file = File("DU2TDM14B1007925")
        file.listFiles().forEach {
            if (it.name.endsWith(".png")){
                var image = ImageIO.read(it)
                loop(3){
                    image = image.rotate()
                }
                ImageIO.write(image,"png",it)
            }
        }
    }
}