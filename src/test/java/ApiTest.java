import org.testng.annotations.Test;
import validators.RateValidator;

public class ApiTest {
    private final RateValidator validator = new RateValidator();

    @Test
    public void checkOnliner() {
        validator.validateSchema();
    }
}
