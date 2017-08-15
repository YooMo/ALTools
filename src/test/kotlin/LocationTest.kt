import io.github.mslxl.azurlanetools.config.Resources
import io.github.mslxl.azurlanetools.util.println
import io.kotlintest.matchers.shouldBe
import org.junit.Test

class LocationTest {
    @Test
    fun test(){
        val location = Resources.getLocation("3-4")
        location.println().exists() shouldBe true
    }
}