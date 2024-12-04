package tests;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DataProvider()
    public Object[][] loginDataError() {
        return new Object[][] {
            {"standard_user", "secret", "Epic sadface: Username and password do not match any user in this service"},
            {"standard","secret_sauce","Epic sadface: Username and password do not match any user in this service"},
            {"", "secret_sauce", "Epic sadface: Username is required"},
            {"standard_user","","Epic sadface: Password is required"},
            {"", "", "Epic sadface: Username is required"},
            {" ", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
            {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
            {"@#@$","secret_sauce", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
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