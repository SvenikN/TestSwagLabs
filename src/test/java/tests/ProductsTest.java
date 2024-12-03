package tests;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest{

    @DataProvider()
    public Object[][] catalog() {
        return new Object[][]{
                {"Sauce Labs Backpack", "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", "$29.99"},
                {"Sauce Labs Bike Light", "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.", "$15.99"},
                {"Sauce Labs Fleece Jacket", "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.", "$49.99"},
                {"Sauce Labs Onesie", "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.", "$7.99"},
                {"Test.allTheThings() T-Shirt (Red)", "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.", "$15.99"}
        };
    }

    @DataProvider()
    public Object[][] productName() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"},
                {"Sauce Labs Onesie"},
                {"Test.allTheThings() T-Shirt (Red)"}
        };
    }

    @Epic("Модуль страницы Продуктов")
    @Feature("TNS-2")
    @Story("TNS-2.1")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Nikitina Svetlana srg.svt@gmail.com")
    @Test(description = "Смена статуса кнопки Добавить в корзину/Удалить")
    public void buttonStatus() {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.isOpened();
        productsPage.addToCart();
        ArrayList<String> namesButton = productsPage.getButtonNames();
        List<String> remove = List.of("Remove", "Remove", "Remove", "Remove", "Remove", "Remove");
        assertEquals(namesButton, remove, "The status of the button has not changed");
    }

    @Epic("Модуль страницы Продуктов")
    @Feature("TNS-2")
    @Story("TNS-2.2")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Nikitina Svetlana srg.svt@gmail.com")
    @Test(dataProvider = "productName", description = "Добавление товара в корзину")
    public void addProductsInCart(String name) {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.isOpened();
        productsPage.addToCart(name);
        productsPage.openCart();
        assertTrue(productsPage.getProductNames().contains(name), "The product has not been added to the cart");
    }

    @Epic("Модуль страницы Продуктов")
    @Feature("TNS-2")
    @Story("TNS-2.3")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Nikitina Svetlana srg.svt@gmail.com")
    @Test(dataProvider = "catalog", description = "Описание продукта")
    public void catalogLists(String name, String description, String price) {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.isOpened();
        assertEquals(description, productsPage.cartProductDesc(name), "The description does not match");
        assertEquals(price, productsPage.cartProductPrice(name), "The price does not match");
    }
}



