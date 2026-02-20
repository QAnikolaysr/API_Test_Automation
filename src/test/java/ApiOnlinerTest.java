import org.testng.annotations.Test;

import static com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.StringContains.containsString;

public class ApiOnlinerTest {
    public Object[][] currencyProvider() {
        };
}

@Test (dataProvider = "currencies")
public void checkRates(String currency) {
    String url = "https://kurs/onliner.by/sdapi/kurs/api/bestrate?currency=" + currency + "&tupe=nbrb";

    String responseBody = given()
            .log().all()
    .when()
            .get(url)
    .then()
            .log().all()
            .statusCode(200)
            .header("Content-Type", containsString("application/json"))
            .body("$",hasKey("amount"))
            .body("scale",equalTo(1))
            //body("amount", equalTo("3,7199"))
            .extract()
            .asString();
    System.out.println("###############" + responseBody);
    System.out.println("%%%%%%%%%%%%%%%" + responseBody.toUpperCase());
    String regex = "amount\"\\s*:\\s*\"\\d+,\\d{4}";
    Pattern pattern = Pattern.compile(regex);


}

void main() {
}