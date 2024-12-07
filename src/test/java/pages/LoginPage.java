package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private static final By USERNAME_INPUT = By.xpath("//input[@placeholder='Username']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@placeholder='Password']");
    private static final By LOGIN_BUTTON = By.xpath("//input[@value='Login']");
    private final By errorMessage = By.xpath("//h3[text()]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Загрузка страницы Логина")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Вводим данные {user} и {password}")
    public LoginPage login(String user, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).submit();
        return this;
    }

    @Step("Получаем текст из сообщения об ошибке")
    public String getMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
