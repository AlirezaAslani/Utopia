package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum ConfigObjEnum {
	Type("CNF_TYPE"),
    Name("CNF_NAME"),
    Status("CNF_STATE"),
    Descptition("CNF_DESCRIPTION"),;

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private ConfigObjEnum(String value) {
        this.value = value;
    }

    static {
        for (ConfigObjEnum pageType : ConfigObjEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static ConfigObjEnum valueOf(int statusEnum) {
        return (ConfigObjEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
