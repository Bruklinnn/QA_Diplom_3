package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static driver.Links.waitOfVisibleElement;

public class PersonalAccountPage {
    private final WebDriver driver;
    private static final String ACCURL = "https://stellarburgers.nomoreparties.site/account/profile/";

    private static final By profileTab = By.xpath(".//*[text() = 'Профиль']");
    private final By logoutButton = By.xpath(".//button[text() = 'Выход']");
    private final By constructorLink = By.xpath(".//*[text() = 'Конструктор']");
    private final By logoLink = By.className("AppHeader_header__logo__2D0X2"); /*Кнопка восстановить в личном кабинете*/

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(ACCURL);
    }
    @Step("Get profileTab text")
    public String getProfileTabText() {
        return driver.findElement(profileTab).getText();
    }

    @Step("Click logoutButton")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Click constructorLink")
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    @Step("Click logoLink")
    public void clickLogoLink() {
        driver.findElement(logoLink).click();
    }

    @Step("Wait for visible of profileTab")
    public void waitProfileTab() {
        waitOfVisibleElement(driver, profileTab);
    }

    @Step("Wait for visible logoutButton")
    public void waitLogoutButton() {
        waitOfVisibleElement(driver, logoutButton);
    }
}