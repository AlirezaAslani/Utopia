package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum ExecutionStatusEnum {
	UNSUCCESSFUL(0),
	SUCCESSFUL(1),
    LOCKED(2),
    DEACTIVE(3),
    UNKNOWN(4);

    private int value;
    private static Map<Object, Object> map = new HashMap<>();

    private ExecutionStatusEnum(int value) {
        this.value = value;
    }

    static {
        for (ExecutionStatusEnum pageType : ExecutionStatusEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static ExecutionStatusEnum valueOf(int statusEnum) {
        return (ExecutionStatusEnum) map.get(statusEnum);
    }

    public int getValue() {
        return value;
    }
}
