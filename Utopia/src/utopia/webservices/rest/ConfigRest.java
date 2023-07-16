package utopia.webservices.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utopia.entities.TblConfig;
import utopia.entities.TblUser;
import utopia.enumeration.ExecutionStatusEnum;
import utopia.service.AuthenticationService;
import utopia.service.ConfigService;
import utopia.service.IConfigService;
import utopia.service.IUserService;
import utopia.service.UserService;
import utopia.session.IConfigImplLocal;
import utopia.session.IUserImplLocal;

@Path("/config")
@Stateless
public class ConfigRest {

	@EJB(name = "config")
	private IConfigImplLocal configFacade;

	private IConfigService configService;

	@PostConstruct
	public void init() {
		configService = new ConfigService(configFacade);
	}

	// Specifies that the method processes HTTP POST requests and return all users
	// object from DB
	@Path("/lstconfigs")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String lstConfigs() throws ParseException {

		configService = new ConfigService(configFacade);

		List<TblConfig> sList = configService.getConfigList();

		JSONObject main = new JSONObject();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		for (TblConfig c : sList) {
			JSONObject outputJsonObj = new JSONObject();
			// CNF_ID, CNF_DATE, CNF_TYPE, CNF_NAME, CNF_STATE, CNF_DESCRIPTION
			
			outputJsonObj.put("CNF_DATE", simpleDateFormat.format(c.getCnfDate()));
			outputJsonObj.put("CNF_TYPE", c.getCnfType());
			outputJsonObj.put("CNF_NAME", c.getCnfName());
			outputJsonObj.put("CNF_STATE", c.getCnfState());
			outputJsonObj.put("CNF_DESCRIPTION", c.getCnfDescription());

			main.put(c.getCnfType(), outputJsonObj);
		}

		return main.toString();
	}

	// Specifies that the method processes HTTP POST requests and edit users list
	// object from DB
	@POST
	@Path("/addconfigs")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addConfigs(String body) throws ParseException {
		configService = new ConfigService(configFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, Object> objectMap = (Map<String, Object>) obj;

		Map<String, HashMap<String, String>> configListResult = null;
		StringBuffer result = null;

		HashMap<String, HashMap<String, String>> configList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> config : objectMap.entrySet()) {
			configList.put(config.getKey(), (HashMap<String, String>) config.getValue());
		}

		try {
			result = configService.insertConfigs(configService, configList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		if (result.length() > 0)
			return result.toString();
		else
			return ExecutionStatusEnum.SUCCESSFUL.toString();

	}

	// Specifies that the method processes HTTP POST requests and edit users list
	// object from DB
	@POST
	@Path("/editconfigs")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String editConfigs(String body) throws ParseException {
		configService = new ConfigService(configFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, Object> objectMap = (Map<String, Object>) obj;

		Map<String, HashMap<String, String>> configListResult = null;
		StringBuffer result = null;

		HashMap<String, HashMap<String, String>> configList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> config : objectMap.entrySet()) {
			configList.put(config.getKey(), (HashMap<String, String>) config.getValue());
		}

		try {
			result = configService.updateConfigs(configService, configList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		if (result.length() > 0)
			return result.toString();
		else
			return ExecutionStatusEnum.SUCCESSFUL.toString();
	}

	// Specifies that the method processes HTTP POST requests and edit users list
	// object from DB
	@POST
	@Path("/removeconfigs")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String removeConfigs(String body) throws ParseException {
		configService = new ConfigService(configFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, Object> objectMap = (Map<String, Object>) obj;

		Map<String, HashMap<String, String>> configListResult = null;
		StringBuffer result = null;

		HashMap<String, HashMap<String, String>> configList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> config : objectMap.entrySet()) {
			configList.put(config.getKey(), (HashMap<String, String>) config.getValue());
		}

		try {
			result = configService.delateConfigs(configService, configList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		if (result.length() > 0)
			return result.toString();
		else
			return ExecutionStatusEnum.SUCCESSFUL.toString();
	}
}
