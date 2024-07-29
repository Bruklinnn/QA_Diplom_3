package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static driver.Links.FORGOT_PASSWORD_PAGE;


public class ForgotPasswordPage {
    private final WebDriver driver;
    private static final String FORGOT_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void open() {
        driver.get(FORGOT_PASSWORD_PAGE);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Open forgot password page")
    public void openForgotPasswordPage(){
        driver.get(FORGOT_PASSWORD_PAGE);
    }

}

