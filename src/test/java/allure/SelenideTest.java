package allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    private static final String REPOSITORY = "allure-framework/allure2";

    @Feature("Issue в репозитории")
    @Story("Проверерки названия Issue")
    @Owner("p.barinova")
    @DisplayName("Проверка названия Issue в репозитории (Selenide)")
    @Test
    public void testCheckTitleIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").pressEnter();
        $(linkText("allure-framework/allure2")).click();
        $("#issues-tab").shouldHave(text("Issues"));
    }

    @Feature("Issue в репозитории")
    @Story("Проверерки названия Issue")
    @Owner("p.barinova")
    @DisplayName("Проверка названия Issue в репозитории (Lambda Step)")
    @Test
    public void testCheckTitleIssueWithLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть главную страницу", () -> {
            open("https://github.com");
        });
        step("Поиск репозитория" + " " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });
        step("Клик по ссылке репозитория" + " " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Проверка названия Issue в репозитории" + " " + REPOSITORY, () -> {
            $("#issues-tab").shouldHave(text("Issues"));
        });
    }

    @Feature("Issue в репозитории")
    @Story("Проверерки названия Issue")
    @Owner("p.barinova")
    @DisplayName("Проверка названия Issue в репозитории (Annotated Step)")
    @Test
    public void testCheckTitleIssueWithAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepository(REPOSITORY);
        steps.issueTitleCheck();
    }
}
