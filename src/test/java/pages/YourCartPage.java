package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourCartPage extends BasePage{

    private static final By REMOVE = (By.xpath("//*[text()='Remove']"));

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Удаление товара из корзины")
    public boolean deleteProductIsCart() {
        driver.findElement(REMOVE).click();
        int name = driver.findElements(By.xpath("//*[text()='Sauce Labs Bolt T-Shirt']")).size();
        if (name == 0) {
            return true;
        } else {
            return false;
        }
    }
}
