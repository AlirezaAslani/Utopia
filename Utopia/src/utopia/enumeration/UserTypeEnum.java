package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum UserTypeEnum {
	 	NORNALUSER(0),
	    ADMIN(1);

	    private int value;
	    private static Map<Object, Object> map = new HashMap<>();

	    private UserTypeEnum(int value) {
	        this.value = value;
	    }

	    static {
	        for (UserTypeEnum pageType : UserTypeEnum.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static UserTypeEnum valueOf(int statusEnum) {
	        return (UserTypeEnum) map.get(statusEnum);
	    }

	    public int getValue() {
	        return value;
	    }
}
