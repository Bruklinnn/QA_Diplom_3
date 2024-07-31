
import PageObject.MainPage;
import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import static org.junit.Assert.assertTrue;

public class MainPageTest {

        private MainPage mainPage;
    private WebDriver driver;

        @Before
        public void setUp() {
            driver = WebDriverCreator.createWebDriver();
            mainPage = new MainPage(driver);
            driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        }

        @Test
        @DisplayName("Bun tab selected")
        public void bunTabSelected() {
            mainPage.open();
            mainPage.clickFillings();
            mainPage.clickBuns();
            assertTrue(mainPage.isBunsSelected());
        }

        @Test
        @DisplayName("Sauce tab selected")
        public void sauceTabSelected() {
            mainPage.open();
            mainPage.clickFillings();
            mainPage.clickSauces();
            assertTrue(mainPage.isSaucesSelected());
        }

        @Test
        @DisplayName("Filling tab selected")
        public void fillingTabSelected() {
            mainPage.open();
            mainPage.clickSauces();
            mainPage.clickFillings();
            assertTrue(mainPage.isFillingsSelected());
        }
        @After
    public void tearDown() {
            driver.quit();
        }

    }

