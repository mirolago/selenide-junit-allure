import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class DarcaKrviTest extends Base {

    @Test
    @Description("Test case 01")
    void testCase01() throws IOException {
        open(testUrl);
        attachScreenshot("Main screen");
        clickNext("English", 1);
        $("#vyska").setValue("180");
        $("#vaha").setValue("180");
        clickRadio("pohlavie_MUZ");
        clickNext("Next", 2);
        clickRadio("otazka1_ANO");
        clickRadio("otazka2_ANO");
        clickRadio("otazka3_ANO");
        clickRadio("otazka4_ANO");
        clickRadio("otazka5_ANO");
        clickRadio("otazka6_NIE");
        clickRadio("otazka7_ANO");
        clickRadio("otazka8_ANO");
        clickNext("Next", 3);
        clickRadio("otazka9aNie");
        clickRadio("otazka9bNie");
        clickRadio("otazka9cNie");
        clickRadio("otazka9dNie");
        clickRadio("otazka9eNie");
        clickRadio("otazka9fNie");
        clickRadio("otazka9gNie");
        clickRadio("otazka9hNie");
        clickRadio("otazka9iNie");
        clickRadio("otazka9jNie");
        clickRadio("otazka9kNie");
        clickRadio("otazka9lNie");
        clickRadio("otazka9mNie");
        clickRadio("otazka10Nie");
        clickRadio("otazka11Nie");
        clickRadio("otazka12Nie");
        clickNext("Next", 4);
        clickRadio("otazka13aNie");
        clickRadio("otazka13bNie");
        clickRadio("otazka13cNie");
        clickRadio("otazka14Nie");
        clickRadio("otazka15Nie");
        clickRadio("otazka16aNie");
        clickRadio("otazka16bNie");
        clickRadio("otazka16cNie");
        clickRadio("otazka17Nie");
        clickRadio("otazka18Nie");
        clickRadio("otazka19Nie");
        clickRadio("otazka20Nie");
        clickRadio("otazka21Nie");
        clickRadio("otazka22aNie");
        clickRadio("otazka22bNie");
        clickRadio("otazka22cNie");
        clickRadio("otazka23Nie");
        clickRadio("otazka24Nie");
        clickNext("Next", 5);
        $("#miestoPodpisu").setValue("Lozorno");
        $("#datumPodpisu").setValue("01012022");
        clickRadio("teplota37Nie");
        clickRadio("pobytZahranicieNie");
        clickRadio("kontaktZahranicieNie");
        clickRadio("priznakOchoreniaNie");
        clickRadio("prekonalCovidNie");
        $$("input.button").findBy(value("Next")).click();
        $(byText("Thank you for your blood donation")).shouldBe(visible);
        switchTo().window(1);
        $(byText("Your questionnaie is being prepared. Please wait...")).shouldBe(visible);
        switchTo().window(0);
        attachScreenshot("Step 6");
        File file = new File(WebDriverRunner.getBrowserDownloadsFolder() + "/dotaznik.pdf");
        Assertions.assertTrue(returnPdfContent(file).contains("Questionnaire for Donors of Blood, Plasma and Blood Cells"));
    }

    private void clickNext(String buttonValue, int step) throws IOException {
        clickNext(buttonValue, "Step " + step + " out of 5", "Step " + step);
    }

    private void clickNext(String buttonValue, String textToBeVisible, String screenshotName) throws IOException {
        $$("input.button").findBy(value(buttonValue)).click();
        $(byText(textToBeVisible)).shouldBe(visible);
        attachScreenshot(screenshotName);
    }

    protected void clickRadio(String elementId) {
        $("#" + elementId).click();
    }
}
