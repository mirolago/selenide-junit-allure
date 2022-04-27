import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Test1 extends Base {

    @Test
    public void testCase01() {
        open(testUrl);
    }
}
