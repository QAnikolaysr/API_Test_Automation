package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter


public enum Currency {
    USD("USD"),
    EUR("EUR"),
    RUB("RUB");

    private final String code;
}
