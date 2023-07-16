package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum UserObjEnum {
    Descptition("USER_DESCRIPTION"),
    Status("USER_STATUS"),
    Password("USER_PASSWORD"),
    OLD_Password("OLD_USER_PASSWORD"),
	Username("USER_USERNAME"),
	DisplayName("USER_DISPLAYNAME"),
	UserBranchCode("USER_BRANCH_CODE"),
	User_name("USER_NAME");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private UserObjEnum(String value) {
        this.value = value;
    }

    static {
        for (UserObjEnum pageType : UserObjEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static UserObjEnum valueOf(int statusEnum) {
        return (UserObjEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
