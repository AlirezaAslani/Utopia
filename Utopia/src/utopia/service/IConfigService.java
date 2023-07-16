package utopia.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utopia.entities.TblConfig;
import utopia.entities.TblGroup;
import utopia.entities.TblUser;
import utopia.enumeration.StatusEnum;

public interface IConfigService {
	
	public HashMap<String, String> getConfigMap();
	public TblConfig findByConfigtype(String configType);
	public TblConfig getConfigObj(Date createdDate,String type, String name, String status, String desc);
	public List<TblConfig> getConfigList();
	public StringBuffer insertConfigs(IConfigService configService, Map<String, HashMap<String,String>> configNameList) throws Exception;
	public StringBuffer updateConfigs(IConfigService configService, Map<String, HashMap<String,String>> configNameList) throws Exception;
	public StringBuffer delateConfigs(IConfigService configService, Map<String, HashMap<String,String>> configNameList) throws Exception;

}
