import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.codeborne.selenide.*;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Base {

    static String testUrl = System.getProperty("testUrl", "https://www.darcakrvi.sk/");
    static final int retryingTest = 3;

    static {
        String seleniumGridHub = System.getProperty("seleniumGridHub", "https://selenium4.astondev.sk");
        final boolean useSeleniumGrid = Boolean.parseBoolean(System.getProperty("useSeleniumGrid", "false"));

        //Configuration.assertionMode = AssertionMode.SOFT;
        //Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;
        //Configuration.browserSize = "1920x1920";
        Configuration.headless = false;
        Configuration.savePageSource = false;
        if (useSeleniumGrid) {
            Configuration.remote = seleniumGridHub;
        }
    }

    @Attachment(value = "{nazovScreenshotu}", type = "image/png")
    protected byte[] attachScreenshot(String nazovScreenshotu) throws IOException {
        return Shutterbug.shootPage(WebDriverRunner.getWebDriver(), Capture.FULL).withName(nazovScreenshotu).getBytes();
        //return Selenide.screenshot(OutputType.BYTES);
    }

}
