package tests;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DataProvider()
    public Object[][] loginDataError() {
        String user = PropertyReader.getProperty("TestSwagLabs.user");
        String userLocked = PropertyReader.getProperty("TestSwagLabs.userLocked");
        String password = PropertyReader.getProperty("TestSwagLabs.password");

        return new Object[][] {
            {user, "secret", "Epic sadface: Username and password do not match any user in this service"},
            {"standard", password,"Epic sadface: Username and password do not match any user in this service"},
            {"", password, "Epic sadface: Username is required"},
            {user, "","Epic sadface: Password is required"},
            {"", "", "Epic sadface: Username is required"},
            {" ", password, "Epic sadface: Username and password do not match any user in this service"},
            {userLocked, password, "Epic sadface: Sorry, this user has been locked out."},
            {"@#@$", password, "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @DataProvider()
    public Object[][] loginData() {
        String userProblem = PropertyReader.getProperty("TestSwagLabs.userProblem");
        String userGlitch = PropertyReader.getProperty("TestSwagLabs.userGlitch");
        String userError = PropertyReader.getProperty("TestSwagLabs.userError");
        String userVisual = PropertyReader.getProperty("TestSwagLabs.userVisual");
        String password = PropertyReader.getProperty("TestSwagLabs.password");

        return new Object[][]{
                {userProblem, password},
                {userGlitch, password},
                {userError, password},
                {userVisual, password}
        };
    }

    @Epic("Модуль страницы логина")
    @Feature("TNS-1")
    @Story("TNS-1.2")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Nikitina Svetlana srg.svt@gmail.com")
    @Description("Проверка входа в интернет магазин")
    @Test(dataProvider = "loginDataError", description = "Получение сообщения об ошибке")
    public void errorMessage(String user, String pass, String message) {
        loginPage
                .open()
                .login(user, pass);
        assertEquals(loginPage.getMessage(), message);
    }

    @Epic("Модуль страницы логина")
    @Feature("TNS-1")
    @Story("TNS-1.1")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Nikitina Svetlana srg.svt@gmail.com")
    @TmsLink("/TestsSwagLabs")
    @Description("Проверка входа в интернет магазин")
    @Test(dataProvider = "loginData", description = "Авторизация под верными данными")
    public void login(String user, String pass) {
        loginPage
                .open()
                .login(user, pass);
        assertTrue(productsPage.titleDisplayed(), "Title Product don't visible");
        assertEquals(productsPage.getTitle(), "Products");
    }
}
