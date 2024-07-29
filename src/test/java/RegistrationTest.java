import AllForUser.RegUser;
import AllForUser.User;
import AllForUser.UserSteps;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.PersonalAccountPage;
import PageObject.RegistrationPage;
import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private User userCreate;
    private User userIncorrectPass;
    private User userLogin;
    private String incorrectPassword;
    private boolean userCreated;
    private String accessToken;
   private RegUser regUser;
    private WebDriver driver;


    @Before
    public void setUp() {

        driver = WebDriverCreator.createWebDriver();
        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        userCreate = new User("Reg@mail.ru","Reg1233", "Reg");
        userIncorrectPass = new User ("Reg@mail.ru","Reg", "Reg");
        userLogin = new User ("Reg@mail.ru","Reg");
        userCreated = false;
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
    }

    @Test
    @DisplayName("Register user happy path")
    public void registrationTest() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickAccountProfileButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(userCreate.getName(), userCreate.getEmail(), userCreate.getPassword());
        loginPage.waitEnterButtonDisplayed();
        String expected = "Вход";
        String actual = loginPage.getEntranceText();
        assertEquals(expected, actual);
        userCreated = true;
            }

    @Test
    @DisplayName("Register with incorrect password")
    public void registrationNegative() {
        mainPage.open();
        mainPage.waitLoginButton();
        mainPage.clickAccountProfileButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(userIncorrectPass.getName(), userIncorrectPass.getEmail(), userIncorrectPass.getPassword());
        String expected = "Некорректный пароль";
        String actual = registrationPage.getErrorMessage();
        assertEquals(expected, actual);
        userCreated = true;
    }


    @After
    public void tearDown() {
        if (userCreated) {
            String accessToken = UserSteps.getAccessToken(userCreate);
            if (accessToken != null)
            {
                UserSteps.deleteUser(userCreate, regUser, accessToken);
                driver.quit();
            }
        }

    }

}

