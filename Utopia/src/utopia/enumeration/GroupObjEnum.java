package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum GroupObjEnum {
    Id("GROUP_ID"),
    Name("GROUP_NAME"),
    Descptition("GROUP_DESCRIPTION"),
    Status("GROUP_STATUS"),
	DisplayName("GROUP_DISPLAYNAME");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private GroupObjEnum(String value) {
        this.value = value;
    }

    static {
        for (GroupObjEnum pageType : GroupObjEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static GroupObjEnum valueOf(int statusEnum) {
        return (GroupObjEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
