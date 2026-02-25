package steps;

import static io.restassured.RestAssured.given;

public class RateStep {
    public String getOnlinerResponse() {
        return given()
                .log().all()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=RUB&tupe=nbrb")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .asString();
    }
}
