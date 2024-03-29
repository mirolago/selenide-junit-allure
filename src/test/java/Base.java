import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;

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
        Configuration.baseUrl = testUrl;
//        Configuration.proxyEnabled = true;
//        Configuration.fileDownload = FileDownloadMode.PROXY;
        //        Configuration.proxyEnabled = true;
//        Configuration.proxyHost = "0.0.0.0";
//        Configuration.fileDownload = FileDownloadMode.PROXY;
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    protected byte[] attachScreenshot(String screenshotName) throws IOException {
        return Shutterbug.shootPage(WebDriverRunner.getWebDriver(), Capture.FULL).withName(screenshotName).getBytes();
    }

    @AfterAll
    public static void quitWebDriver() {
        if (Boolean.parseBoolean(System.getProperty("useSeleniumGrid", "true"))) {
            Selenide.closeWebDriver();
        }
    }

    public static String returnPdfContent(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        String text = null;
        if(!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(document);
        }
        document.close();
        return text;
    }

    @BeforeEach
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true).includeSelenideSteps(false));
    }
}
