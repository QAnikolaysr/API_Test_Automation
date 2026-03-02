package tests;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.DataProvider;

public class ApiOnlinerTest {
    @DataProvider(name = "currencyProvider")
    public Object[][] currencyProvider() {
        return new Object[][] {
                {"RUB"},
                {"EUR"},
                {"USD"}
        };
    }

    @Test(dataProvider = "currencyProvider")
    public void checkRates(String currency) {
        String url = "https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=" + currency + "&tupe=nbrb";

        String responseBody = given()
                .log().all()
        .when()
                .get(url)
        .then()
                .log().all()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("$", hasKey("amount"))
                //.body("scale", equalTo(1))
                //.body("amount", equalTo("3,7199"))
                .extract()
                .asString();
        System.out.println("###############" + responseBody);
        System.out.println("%%%%%%%%%%%%%%%" + responseBody.toUpperCase());
        String regex = "amount\"\\s*:\\s*\"\\d+,\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);
        assertTrue(matcher.find(),"ffff");
    }
}