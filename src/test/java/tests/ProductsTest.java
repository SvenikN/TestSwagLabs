package tests;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest{

    @Epic("Модуль страницы Продуктов")
    @Feature("TNS-2")
    @Story("TNS-2.1")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Nikitina Svetlana srg.svt@gmail.com")
    @Test(description = "Смена статуса кнопки Добавить в корзину/Удалить")
    public void buttonStatus() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
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
    @Test(description = "Добавление товара в корзину")
    public void addProductsInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isOpened();
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        assertTrue(productsPage.getProductNames().contains("Sauce Labs Bolt T-Shirt"), "The product has not been added to the cart");
    }
}



