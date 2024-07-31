package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static driver.Links.waitOfVisibleElement;

public class MainPage {
    private final WebDriver driver;
    private static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final By stellarBurgers = By.xpath(".//div[@class ='AppHeader_header__logo__2D0X2'])");
    private final By OrdersLent = By.xpath(".//a[@class ='AppHeader_header__link__3D_hX AppHeader_header__link_active__1IkJo']"); /*Лента заказов*/
    private final By personalAccount = By.xpath(".//p[text()='Личный Кабинет']"); /*Личный кабинет*/
    private final By constructor = By.xpath(".//p[text()='Конструктор']"); /*Конструктор*/
    private final By LogInMain = By.xpath(".//button[text() = 'Войти в аккаунт']"); /*Кнопка войти в аккаунт на главной*/
    private final By createOrderButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']"); /*Кнопка оформить заказ(появляется, когда залогинишься)*/
    private final By Buns = By.xpath(".//span[text() = 'Булки']/parent::div");; /*Булки*/
    private final By Sauces = By.xpath(".//span[text() = 'Соусы']"); /*Соусы*/
    private final By Fillings = By.xpath(".//span[text() = 'Начинки']/parent::div"); /*Начинки*/
    private final By activeTab = By.xpath(".//div[contains(@class, 'current')]");
    private final By mainTextConstructor = By.xpath(".//*[text() = 'Соберите бургер']");



    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void open() {
        driver.get(MAIN_URL);
    }

    @Step
            ("Login loginAccountButton")
    public void clickLoginAccountButton() {
        driver.findElement(LogInMain).click();
    }

    @Step
            ("Login accountProfileButton")
    public void clickAccountProfileButton() {
        driver.findElement(personalAccount).click();
    }
    @Step
            ("Login accountProfileButton")
    public void clickStellarBurgers() {
        driver.findElement(stellarBurgers).click();
    }

    @Step("Get create order button text")
    public String getCreateOrderButtonText() {
        return driver.findElement(createOrderButton).getText();
    }

    @Step
            ("Pick bun")
    public void clickBuns() {
        driver.findElement(Buns).click();
    }

    @Step
            ("Get buns text")
    public String getBunsText() {
        return driver.findElement(Buns).getText();
    }

    @Step
            ("Pick sauces")
    public void clickSauces() {
        driver.findElement(Sauces).click();
    }

    @Step
            ("Get sauces text")
    public String getSaucesText() {
        return driver.findElement(Sauces).getText();
    }

    @Step
            ("Pick fillings")
    public void clickFillings() {
        driver.findElement(Fillings).click();
    }

    @Step
            ("Get fillings text")
    public String getFillingsText() {
        return driver.findElement(Fillings).getText();
    }

    @Step
            ("Get active tab text")
    public String getActiveTabText() {
        return driver.findElement(activeTab).getText();
    }

    @Step
            ("Is bun tab selected?")
    public boolean isBunsSelected() {
        return getBunsText().equals(getActiveTabText());
    }

    @Step
            ("Is sauce tab selected?")
    public boolean isSaucesSelected() {
        return getSaucesText().equals(getActiveTabText());
    }

    @Step
            ("Is filling tab selected?")
    public boolean isFillingsSelected() {
        return getFillingsText().equals(getActiveTabText());
    }

    @Step
            ("Get main text")
    public String getMainTextConstructor() {
        return driver.findElement(mainTextConstructor).getText();
    }

    @Step
            ("Wait for visible of createOrderButton")
    public void waitCreateOrderButton() {
        waitOfVisibleElement(driver, createOrderButton);
    }
    @Step
            ("Wait for visible of createOrderButton")
    public void waitLoginButton() {
        waitOfVisibleElement(driver, LogInMain);
    }
}
