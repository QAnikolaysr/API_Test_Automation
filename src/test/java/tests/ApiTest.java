package tests;

import org.testng.annotations.Test;
import steps.RateStep;
import validators.RateValidator;

public class ApiTest {
    private final RateValidator validator = new RateValidator();
    private final RateStep step = new RateStep();

    String response = step.getOnlinerResponse();

    @Test
    public void  checkOnliner(){
        validator.validateSchema();
    }

    @Test
    public void checkOnlinerRegex() {
        validator.validateRagex(response);
    }

    @Test
    public void checkOnlinerHeader() {
        validator.validateHeaders();
    }

    @Test
    public void checkOnlinerKeys() {
        validator.validateKeys();
    }
}

