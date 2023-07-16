package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum RoleMemberEnum {
    USER("ENT_USER"),GROUP("ENT_GROUP"),ROLE("ENT_ROLE");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private RoleMemberEnum(String value) {
        this.value = value;
    }

    static {
        for (RoleMemberEnum pageType : RoleMemberEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static RoleMemberEnum valueOf(int statusEnum) {
        return (RoleMemberEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
