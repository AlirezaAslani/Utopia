package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum LogEnum {
	NoResultException("No Result Exception"),UnexpectedError("Unexpected Error");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private LogEnum(String value) {
        this.value = value;
    }

    static {
        for (LogEnum pageType : LogEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static LogEnum valueOf(int statusEnum) {
        return (LogEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
