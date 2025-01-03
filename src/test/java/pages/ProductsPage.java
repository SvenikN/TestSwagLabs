package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage{

    private static final String ADD_TO_CART_PATTERN = "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";
    private static final String DESCRIPTION_PRODUCT_PATTERN = "//div[text()='%s']//following::*[@class='inventory_item_desc']";
    private static final String PRICE_PRODUCT_PATTERN = "//div[text()='%s']/ancestor::*[@class='inventory_item_description']/child::*[@class='pricebar']/child::*[@class='inventory_item_price']";
    private final By title = (By.cssSelector("[class=title]"));
    private final By product = (By.xpath("//*[text()='Products']"));
    private final By button = (By.xpath("//*[text()='Add to cart']"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение названия страницы")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Отображение названия страницы Продукты")
    public boolean titleDisplayed() {
        driver.findElement(product).isDisplayed();
        return true;
    }

    @Step("Добавление одного товара в корзину со страницы Продукты")
    public ProductsPage addToCart(String product) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
        return this;
    }

    @Step("Открытие корзины по значку корзина")
    public ProductsPage openCart() {
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        return this;
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
    public ProductsPage isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(button));
        return this;
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
        List<WebElement> allButtons = driver.findElements(button);
        for (WebElement button: allButtons) {
            button.click();
        }
        return null;
    }

    @Step("Получение описания товара")
    public String cartProductDesc(String name) {
        String description = String.format(DESCRIPTION_PRODUCT_PATTERN, name);
        return driver.findElement(By.xpath(description)).getText();
    }

    @Step("Получение цены товара")
    public String cartProductPrice(String name) {
        String price = String.format(PRICE_PRODUCT_PATTERN, name);
        return driver.findElement(By.xpath(price)).getText();
    }
}
