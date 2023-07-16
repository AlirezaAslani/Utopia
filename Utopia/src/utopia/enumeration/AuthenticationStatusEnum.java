package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum AuthenticationStatusEnum {
	UNSUCCESSFUL(0),
	SUCCESSFUL(1),
    LOCKED(2),
    DEACTIVE(3),
    NOTEXIST(-1),
    UNKNOWN(4);

    private int value;
    private static Map<Object, Object> map = new HashMap<>();

    private AuthenticationStatusEnum(int value) {
        this.value = value;
    }

    static {
        for (AuthenticationStatusEnum pageType : AuthenticationStatusEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static AuthenticationStatusEnum valueOf(int statusEnum) {
        return (AuthenticationStatusEnum) map.get(statusEnum);
    }

    public int getValue() {
        return value;
    }
}
