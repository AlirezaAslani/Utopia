package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum SystemUserEnum {
    SystemUser("SYSTEM_USER");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private SystemUserEnum(String value) {
        this.value = value;
    }

    static {
        for (SystemUserEnum pageType : SystemUserEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static SystemUserEnum valueOf(int statusEnum) {
        return (SystemUserEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
