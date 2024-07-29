package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static driver.Links.waitOfVisibleElement;

public class LoginPage {

    private final WebDriver driver;
    private static final String LOGIN_URL = " https://stellarburgers.nomoreparties.site/login";
    private final By email = By.xpath(".//label[text() = 'Email']/parent::div//input");
    private final By password = By.xpath(".//label[text() = 'Пароль']/parent::div//input");
    private final By enterButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By entrance = By.xpath(".//h2[text() = 'Вход']");
    private final By registration =By.xpath(".//a[text() = 'Зарегистрироваться']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void open() {
        driver.get(LOGIN_URL);
    }

    @Step("Input user email")
    public void setEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    @Step("Input user password")
    public void setPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }
    @Step("Click login button")
    public void clickRegistrationButton() {
        driver.findElement(registration).click();
    }

    @Step("Click login button")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Login user")
    public void loginUser(String userEmail, String userPassword) {
        setEmail(userEmail);
        setPassword(userPassword);
    }

    @Step("Wait for display entrance")
    public void waitEntranceDisplayed() {
        waitOfVisibleElement(driver, entrance);
    }


    @Step("Wait for display entrance")
    public void waitEnterButtonDisplayed() {
        waitOfVisibleElement(driver, enterButton);
    }

    @Step("Wait registration button")
    public void waitRegistrationButtonDisplayed() {
        waitOfVisibleElement(driver, registration);
    }

    @Step("Get text of entrance")
    public String getEntranceText() {
        return driver.findElement(entrance).getText();
    }

}
