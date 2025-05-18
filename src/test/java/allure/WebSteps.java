package allure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открыть главную страницу")
    public void openPage() {
        open("https://github.com");
    }

    @Step("Поиск репозитория")
    public void searchForRepository(String repository) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repository);
        $("#query-builder-test").pressEnter();
    }

    @Step("Клик по ссылке репозитория")
    public void clickOnRepository(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Проверка названия Issue в репозитории")
    public void issueTitleCheck() {
        $("#issues-tab").shouldHave(text("Issues"));
    }
}
