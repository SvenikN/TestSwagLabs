package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class YourCartTest extends BaseTest{

    @Epic("Модуль страницы Корзина")
    @Feature("TNS-3")
    @Story("TNS-3.1")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Nikitina Svetlana srg.svt@gmail.com")
    @Description("Проверка удаления товара из корзины")
    @Test(description = "Удаление товара из корзины")
    public void deleteProductIsCart() {
        loginPage
                .open()
                .login(user, password);
        productsPage
                .isOpened()
                .addToCart("Sauce Labs Bolt T-Shirt")
                .openCart();
        assertTrue(yourCartPage.deleteProductIsCart(), "Product delete");
    }
}
