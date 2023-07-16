package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {
    ACTIVE(1),
    DEACTIVE(0);

    private int value;
    private static Map<Object, Object> map = new HashMap<>();

    private StatusEnum(int value) {
        this.value = value;
    }

    static {
        for (StatusEnum pageType : StatusEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static StatusEnum valueOf(int statusEnum) {
        return (StatusEnum) map.get(statusEnum);
    }

    public int getValue() {
        return value;
    }
}
