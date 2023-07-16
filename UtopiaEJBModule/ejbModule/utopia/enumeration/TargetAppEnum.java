package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum TargetAppEnum {
    OBI("obi");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private TargetAppEnum(String value) {
        this.value = value;
    }

    static {
        for (TargetAppEnum pageType : TargetAppEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static TargetAppEnum valueOf(int statusEnum) {
        return (TargetAppEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
