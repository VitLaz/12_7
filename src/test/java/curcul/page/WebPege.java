package curcul.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebPege {

    @Step
    public WebPege openMainPage() {
        Selenide.open("http://github.com");
        return this;
    }

    @Step("Ищем репозиторий {repository}")
    public WebPege searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
        return this;
    }

    @Step("Переходим по ссылке репозитория {repository}")
    public WebPege clickOnRepositoryLink(String repository) {
        $(linkText(repository)).click();
        return this;
    }

    @Step("Кликаем на таб Issues")
    public WebPege openIssuesTab() {
        $(partialLinkText("Issues")).click();
        return this;
    }

    @Step("Проверяем что существует Issue с номером {issueNumber}")
    public WebPege shouldSeeIssueWithNumber(String issueNumber) {
        $(withText(issueNumber)).shouldBe(Condition.visible);
        return this;
    }
}
