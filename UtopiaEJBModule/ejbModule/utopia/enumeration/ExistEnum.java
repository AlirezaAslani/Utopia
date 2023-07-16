package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum ExistEnum {
    EXIST("exist"),
    NOEXIST("noexist");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private ExistEnum(String value) {
        this.value = value;
    }

    static {
        for (ExistEnum pageType : ExistEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static ExistEnum valueOf(int statusEnum) {
        return (ExistEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
