package utopia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utopia.entities.TblConfig;
import utopia.enumeration.ConfigEnum;
import utopia.enumeration.ConfigObjEnum;
import utopia.session.IConfigImplLocal;
import utopia.utils.DateUtils;

public class ConfigService implements IConfigService{
	
	private IConfigImplLocal configEJB;
	
	public ConfigService(IConfigImplLocal configEJB) {
		super();
		this.configEJB = configEJB;
	}

	@Override
	public HashMap<String, String> getConfigMap() {
		HashMap<String, String> configMap = new HashMap<String, String>();

		TblConfig hostNameConfig = configEJB.findByCnfType(ConfigEnum.BIIP.toString(), 1);
		TblConfig portStringConfig = configEJB.findByCnfType(ConfigEnum.BIPORT.toString(), 1);
		TblConfig biPortConfig = configEJB.findByCnfType(ConfigEnum.BIDASHBOARDPORT.toString(), 1);
		TblConfig usernameConfig = configEJB.findByCnfType(ConfigEnum.BIADMIN.toString(), 1);
		TblConfig passwordConfig = configEJB.findByCnfType(ConfigEnum.BIADMINPASS.toString(), 1);
		TblConfig protocol = configEJB.findByCnfType(ConfigEnum.PROTOCOL.toString(), 1);
		TblConfig scheduler = configEJB.findByCnfType(ConfigEnum.SCHEDULER.toString(), 1);
		TblConfig webService = configEJB.findByCnfType(ConfigEnum.WBSERVICE.toString(), 1);

		configMap.put(ConfigEnum.BIIP.toString(), hostNameConfig.getCnfDescription());
		configMap.put(ConfigEnum.BIPORT.toString(), portStringConfig.getCnfDescription());
		configMap.put(ConfigEnum.BIDASHBOARDPORT.toString(), biPortConfig.getCnfDescription());
		configMap.put(ConfigEnum.BIADMIN.toString(), usernameConfig.getCnfDescription());
		configMap.put(ConfigEnum.BIADMINPASS.toString(), passwordConfig.getCnfDescription());
		configMap.put(ConfigEnum.PROTOCOL.toString(), protocol.getCnfDescription());
		configMap.put(ConfigEnum.SCHEDULER.toString(), scheduler.getCnfDescription());
		configMap.put(ConfigEnum.WBSERVICE.toString(), webService.getCnfDescription());

		return configMap;
	}

	
	
	@Override
	public TblConfig getConfigObj(Date createdDate, String type, String name, String status, String desc) {
		TblConfig tblConfig = new TblConfig();
		tblConfig.setCnfDate(createdDate);
		tblConfig.setCnfDescription(desc);
		tblConfig.setCnfName(name);
		tblConfig.setCnfState(BigDecimal.valueOf(Double.valueOf(status)));
		tblConfig.setCnfType(type);
		return tblConfig;
	}
	
	

	@Override
	public TblConfig findByConfigtype(String configType) {
		// TODO Auto-generated method stub
		return  configEJB.findByCnfType(configType, 1);
	}
	
	

	@Override
	public List<TblConfig> getConfigList() {
		// TODO Auto-generated method stub
		return configEJB.findAll();
	}

	@Override
	public StringBuffer insertConfigs(IConfigService configService, Map<String, HashMap<String, String>> configNameList)
			throws Exception {


		List<String> result = new ArrayList<String>();
		StringBuffer failConfig = new StringBuffer();

		Date date = DateUtils.getCalendarDate();


		for (Map.Entry<String, HashMap<String, String>> config : configNameList.entrySet()) { 
			TblConfig existConfigTypeActive = findByConfigtype(config.getKey());
			HashMap<String, String> configValue = config.getValue();
			if (existConfigTypeActive == null ) {
					TblConfig configObj = getConfigObj(date,configValue.get(ConfigObjEnum.Type.getValue()),configValue.get(ConfigObjEnum.Name.getValue()),configValue.get(ConfigObjEnum.Status.getValue()),configValue.get(ConfigObjEnum.Descptition.getValue()));
					configEJB.create(configObj);
				}else {
					failConfig.append(config.getKey() + ";");
				}
	
		}
		return failConfig;
	}

	@Override
	public StringBuffer updateConfigs(IConfigService configService, Map<String, HashMap<String, String>> configNameList)
			throws Exception {

		List<String> result = new ArrayList<String>();
		StringBuffer failConfig = new StringBuffer();

		Date date = DateUtils.getCalendarDate();


		for (Map.Entry<String, HashMap<String, String>> config : configNameList.entrySet()) { 
			TblConfig existConfigTypeActive = findByConfigtype(config.getKey());
			HashMap<String, String> configValue = config.getValue();
			if (existConfigTypeActive != null ) {
					existConfigTypeActive.setCnfDescription(configValue.get(ConfigObjEnum.Descptition.getValue()));
					
					configEJB.edit(existConfigTypeActive);
				}else {
					failConfig.append(config.getKey() + ";");
				}
	
		}
		return failConfig;
	}

	@Override
	public StringBuffer delateConfigs(IConfigService configService, Map<String, HashMap<String, String>> configNameList)
			throws Exception {
		List<String> result = new ArrayList<String>();
		StringBuffer failConfig = new StringBuffer();

		Date date = DateUtils.getCalendarDate();


		for (Map.Entry<String, HashMap<String, String>> config : configNameList.entrySet()) { 
			TblConfig existConfigTypeActive = findByConfigtype(config.getKey());
			HashMap<String, String> configValue = config.getValue();
			if (existConfigTypeActive != null ) {
					configEJB.remove(existConfigTypeActive);
				}else {
					failConfig.append(config.getKey() + ";");
				}
	
		}
		return failConfig;
	}

	



}
