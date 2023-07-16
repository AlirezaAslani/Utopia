package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum PermissionMaskEnum {
	READ(1),
	TRAVERSE(0),
	CHANGE(4),
	DELETE(8),
	ASSIGN(16),
	OWNERSHIP(32),
	PUBLISHER(2048),
	SCHEDULERPUBLISHER(4096),
	PUBLISHERREPORT(8192);

    private int value;
    private static Map<Object, Object> map = new HashMap<>();

    private PermissionMaskEnum(int value) {
        this.value = value;
    }

    static {
        for (PermissionMaskEnum pageType : PermissionMaskEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static PermissionMaskEnum valueOf(int statusEnum) {
        return (PermissionMaskEnum) map.get(statusEnum);
    }

    public int getValue() {
        return value;
    }
}
