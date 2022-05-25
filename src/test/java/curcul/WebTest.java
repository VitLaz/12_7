package curcul;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import curcul.page.WebPege;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebTest {
    public static final String REPOSITORY = "vonoelv/hw6_parametrizedTest";
    public static final String ISSUE_NUMBER = "#1";

    @Test
    public void testIssueSelenide() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Selenide.open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(linkText(REPOSITORY)).click();
        $(partialLinkText("Issues")).click();
        $(withText(ISSUE_NUMBER)).shouldBe(Condition.visible);

    }
    @Test
    public void testIssueLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            Selenide.open("https://github.com");
        });
        step("Ищем репозиторий", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Кликаем на таб Issues", () -> {
            $(partialLinkText("Issues")).click();
        });

        step("Проверяем что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText(ISSUE_NUMBER)).shouldBe(Condition.visible);
        });
    }

    @Test
    public void testIssueSteps() {

        WebPege webPege = new WebPege();
        webPege.openMainPage()
                .searchForRepository(REPOSITORY)
                .clickOnRepositoryLink(REPOSITORY)
                .openIssuesTab()
                .shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
