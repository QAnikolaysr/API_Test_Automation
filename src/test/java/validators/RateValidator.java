package validators;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RateValidator {
    public void validateSchema() {
        given()
                .log().all()
                .when()
                .get("https://kurs/onliner.by/sdapi/kurs/api/bestrate?currency=RUB&tupe=nbrb")
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/rate-schema.json"));
    }
}
