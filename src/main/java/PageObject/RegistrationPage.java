package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;
    private static final String REG_URL = "https://stellarburgers.nomoreparties.site/register/";

    private final By LogInRegistrationPage = By.xpath(".//a[text() = 'Войти']"); /*Кнопка войти на странице регистрации*/
    private final By NameInput = By.xpath(".//label[text() = 'Имя']/parent::div//input"); /*Поле Имя*/
    private final By EmailInput = By.xpath(".//label[text() = 'Email']/parent::div//input"); /*Поле email*/
    private final By PasswordInput = By.xpath(".//label[text() = 'Пароль']/parent::div//input"); /*Поле пароль*/
    private final By RegInRegistrationPage = By.xpath(".//button[text() = 'Зарегистрироваться']"); /*Кнопка зарегестрироваться на странице регистрации*/
    private final By errorMessage = By.xpath(".//p[text()='Некорректный пароль']"); /*Некорректный пароль*/

    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(REG_URL);
    }

    @Step
            ("Click register button")
    public void clickLogInRegistrationPage() {
        driver.findElement(LogInRegistrationPage).click();
    }

    @Step
            ("Input user name")
    public void setName(String userName) {
        driver.findElement(NameInput).sendKeys(userName);
    }

    @Step
            ("Input user email")
    public void setEmail(String userEmail) {
        driver.findElement(EmailInput).sendKeys(userEmail);
    }

    @Step
            ("Input user password")
    public void setPassword(String userPassword) {
        driver.findElement(PasswordInput).sendKeys(userPassword);
    }

    @Step
            ("Click register button")
    public void clickRegisterButton() {
        driver.findElement(RegInRegistrationPage).click();
    }

    @Step
            ("Registration user")
    public void registration(String userName, String userEmail, String userPassword) {
        setName(userName);
        setEmail(userEmail);
        setPassword(userPassword);
        clickRegisterButton();
    }

    @Step
            ("Get error message")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
