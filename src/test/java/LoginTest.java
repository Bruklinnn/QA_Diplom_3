import AllForUser.RegUser;
import AllForUser.User;
import AllForUser.UserSteps;
import PageObject.*;
import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private static User userLogin;
    private static User userReg;
    private RegUser regUser;
    private boolean userCreated;


    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        userCreated = false;
        userLogin = new User("Reg@mail.ru", "Reg1233");
        userReg = new User("Reg@mail.ru", "Reg1233", "Reg");
        UserSteps.createUser(userReg, regUser);
        driver.manage().timeouts().implicitlyWait(Duration.of(15, ChronoUnit.SECONDS));

    }

    @Test
    @DisplayName("Login user loginAccountButton")
    public void UserLoginAccountButton() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickLoginAccountButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        loginPage.waitEnterButtonDisplayed();
        loginPage.clickEnterButton();
        mainPage.waitCreateOrderButton();
        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();
        assertEquals(expected, actual);
        userCreated = true;
    }

    @Test
    @DisplayName("Login user accountProfileButton")
    public void loginUserAccountProfileButton() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickAccountProfileButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        loginPage.waitEnterButtonDisplayed();
        loginPage.clickEnterButton();
        mainPage.waitCreateOrderButton();
        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();
        assertEquals(expected, actual);
        userCreated = true;
    }

    @Test
    @DisplayName("Login user RegistrationPage")
    public void loginUserRegistrationPage() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickLoginAccountButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        loginPage.waitEnterButtonDisplayed();
        loginPage.clickEnterButton();
        mainPage.waitCreateOrderButton();
        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
        userCreated = true;
    }

    @Test
    @DisplayName("Login user ForgotPasswordPage")
    public void loginForgotPasswordPage() {
        forgotPasswordPage.openForgotPasswordPage();
        forgotPasswordPage.clickLoginButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        loginPage.waitEnterButtonDisplayed();
        loginPage.clickEnterButton();
        mainPage.waitCreateOrderButton();
        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
        userCreated = true;
    }

    @After

    public void tearDown() {
        if (userCreated)
            driver.quit();{
            String accessToken = UserSteps.getAccessToken(userLogin);
            if (accessToken != null) UserSteps.deleteUser(userLogin, regUser, accessToken);
        }
        driver.quit();
    }
    }
