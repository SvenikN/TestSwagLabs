package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    public void CheckRate() {
        given()
                .log().all()
                .when()
                .get("https://www.saucedemo.com/")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void login() {
        String responsBody = given()
                .log().all()
                .when()
                .get("https://www.saucedemo.com/")
                .then()
                .log().all()
                .extract()
                .body().asString();
        System.out.println(responsBody + "!!!!!!");
    }
}
