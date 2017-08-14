import io.github.mslxl.azurlanetools.util.Console
import io.github.mslxl.azurlanetools.util.println
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import org.junit.Test

class ConsoleTest{
    @Test
    fun test(){
        Console.exc("echo test") shouldEqual "test"
    }
}