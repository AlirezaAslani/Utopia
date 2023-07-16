package utopia.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum WebserviceResultEnum {
	CreateFolder_Done("CreateFolder_Done"),
	CreateFolder_Failed("CreateFolder_Failed"),
	RemoveFolder_Done("RemoveFolder_Done"),
	RemoveFolder_Failed("RemoveFolder_Failed"),
	UpdateCatalogACL_Done("UpdateCatalogACL_Done"),
	UpdateCatalogACL_Failed("UpdateCatalogACL_Failed"),
	UpdateGlobalPriviledgeACL_Done("UpdateGlobalPriviledgeACL_Done"),
	UpdateGlobalPriviledgeACL_Failed("UpdateGlobalPriviledgeACL_Failed");

    private String value;
    private static Map<Object, Object> map = new HashMap<>();

    private WebserviceResultEnum(String value) {
        this.value = value;
    }

    static {
        for (WebserviceResultEnum pageType : WebserviceResultEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static WebserviceResultEnum valueOf(int statusEnum) {
        return (WebserviceResultEnum) map.get(statusEnum);
    }

    public String getValue() {
        return value;
    }
}
