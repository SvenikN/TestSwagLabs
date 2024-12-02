package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private final String ADD_TO_CART_PATTERN = "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";
    private final By TITLE = (By.cssSelector("[class=title]"));
    private final By PRODUCT = (By.xpath("//*[text()='Products']"));

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Отображение названия страницы Продукты")
    public boolean titleDisplayed() {
        driver.findElement(PRODUCT).isDisplayed();
        return true;
    }

    @Step("Добавление одного товара в корзину со страницы Продукты")
    public void addToCart(String product) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    @Step("Открытие корзины по значку корзина")
    public void openCart() {
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    }

    @Step("Получение названия добавленного продукта")
    public ArrayList<String> getProductNames() {
        List<WebElement> allProductsName = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product: allProductsName) {
            names.add(product.getText());
        }
        return names;
    }

    @Step("Загрузка страницы Продукты")
    public void isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Add to cart']")));
    }

    @Step("Получение текста кнопки")
    public ArrayList<String> getButtonNames() {
        List<WebElement> allButtonName = driver.findElements(By.cssSelector(".btn"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement button: allButtonName) {
            names.add(button.getText());
        }
        return names;
    }

    @Step("Добавление всех товаров в корзину")
    public ArrayList<String> addToCart() {
        List<WebElement> allButtons = driver.findElements(By.xpath("//*[text()='Add to cart']"));
        for (WebElement button: allButtons) {
            button.click();
        }
        return null;
    }
}
