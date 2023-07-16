package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum GroupMemberObjEnum {
    Member_Type("MEMBER_TYPE"),
    User_Member("USER"),
    Group_Member("GROUP"),
    Member("MEMBER");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private GroupMemberObjEnum(String value) {
        this.value = value;
    }

    static {
        for (GroupMemberObjEnum pageType : GroupMemberObjEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static GroupMemberObjEnum valueOf(int statusEnum) {
        return (GroupMemberObjEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
