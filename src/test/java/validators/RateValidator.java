package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;
import static org.testng.Assert.assertTrue;

public class RateValidator {
    public void validateSchema() {
        given()
                .log().all()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=RUB&tupe=nbrb")
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/rate_schema.json"));
    }

    public void validateRagex(final String responseBody) {
        String regex = "\"scale\":\\s*(?:[1-9]|[1-9]\\d{1,3}|10000)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);
        assertTrue(matcher.find(), "Msg");
    }

    public void validateHeaders() {
        given()
                .log().all()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=RUB&tupe=nbrb")
                .then()
                .header("Content-Type", containsString("application/json"));
    }
    public void validateKeys() {
        given()
                .log().all()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=RUB&tupe=nbrb")
                .then()
                .body("$", hasKey("amount"))
                .body("$", hasKey("grow"))
                .body("$", hasKey("delta"));
    }
}
