import AllForUser.RegUser;
import AllForUser.User;
import AllForUser.UserSteps;
import PageObject.*;
import driver.Links;
import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class AccountPageTest {

    private PersonalAccountPage personalAccountPage;
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private static User userLog;
    private static User userReg;
    private static UserSteps userSteps;
    private RegUser regUser;
    private boolean userCreated;
    private WebDriverWait webDriverWait;



    @Before
    public void setUp() {


        driver = WebDriverCreator.createWebDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        userCreated = false;
        userLog = new User("Reg@mail.ru", "Reg1233");
        userReg = new User("Reg@mail.ru", "Reg1233", "Reg");
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        userCreated = false;
    }

    @Test
    @DisplayName("Check account profile for authorized user")
    public void checkAccountProfile() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickAccountProfileButton();
        loginPage.waitRegistrationButtonDisplayed();
        loginPage.clickRegistrationButton();
        registrationPage.registration(userReg.getName(), userReg.getEmail(), userReg.getPassword());
        loginPage.waitRegistrationButtonDisplayed();
        loginPage.clickRegistrationButton();
        mainPage.clickAccountProfileButton();
        loginPage.loginUser(userLog.getEmail(), userLog.getPassword());
        loginPage.clickEnterButton();
        mainPage.clickAccountProfileButton();
        personalAccountPage.waitProfileTab();
        String expected = "Профиль";
        String actual = personalAccountPage.getProfileTabText();
        assertEquals(expected, actual);
        userCreated = true;
    }

    @Test
    @DisplayName("Open constructor from account profile")
    public void openConstructorFromProfile() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickAccountProfileButton();
        personalAccountPage.clickConstructorLink();
        String expected = "Соберите бургер";
        String actual = mainPage.getMainTextConstructor();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Open main page from account profile")
    public void openMainPageFromAccount() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickAccountProfileButton();
        personalAccountPage.clickLogoLink();
        String expected = "Соберите бургер";
        String actual = mainPage.getMainTextConstructor();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Logout from account profile")
    public void logoutFromAccount() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickAccountProfileButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(userReg.getName(), userReg.getEmail(), userReg.getPassword());
        loginPage.waitRegistrationButtonDisplayed();
        loginPage.clickRegistrationButton();
        mainPage.clickAccountProfileButton();
        loginPage.loginUser(userLog.getEmail(), userLog.getPassword());
        loginPage.clickEnterButton();
        mainPage.clickAccountProfileButton();
        personalAccountPage.waitLogoutButton();
        personalAccountPage.clickLogoutButton();
        loginPage.waitEntranceDisplayed();
        String expected = "Вход";
        String actual = loginPage.getEntranceText();
        assertEquals(expected, actual);
        userCreated = true;
    }

    @After
    public void tearDown() {
        if (userCreated)
            {
            String accessToken = UserSteps.getAccessToken(userReg);
            if (accessToken != null) {UserSteps.deleteUser(userReg, regUser, accessToken);
                driver.quit();
            }
        }
    }
}
