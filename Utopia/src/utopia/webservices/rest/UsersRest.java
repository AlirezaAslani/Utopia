package utopia.webservices.rest;

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import utopia.entities.TblUser;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import utopia.service.UserService;
import utopia.service.IUserService;
import utopia.service.ConfigService;
import utopia.entities.TblUserGroup;
import utopia.enumeration.UserObjEnum;
import utopia.service.IConfigService;
import utopia.session.IUserImplLocal;
import javax.annotation.PostConstruct;
import utopia.session.IGroupImplLocal;
import utopia.session.IConfigImplLocal;
import utopia.service.UserGroupService;
import utopia.service.IUserGroupService;
import org.json.simple.parser.JSONParser;
import utopia.session.IUserGroupImplLocal;
import org.json.simple.parser.ParseException;
import utopia.enumeration.ExecutionStatusEnum;

@Path("/user")
@Stateless
public class UsersRest {

	@EJB(name = "config")
	private IConfigImplLocal configFacade;

	@EJB(name = "user")
	private IUserImplLocal userFacade;

	@EJB(name = "group")
	private IGroupImplLocal groupFacade;

	@EJB(name = "ug")
	private IUserGroupImplLocal ugFacade;

	private IUserService userService;
	private IConfigService configService;
	private IUserGroupService ugService;

	@PostConstruct
	public void init() {
		userService = new UserService(userFacade);
		configService = new ConfigService(configFacade);
		ugService = new UserGroupService(ugFacade, userFacade, groupFacade);
	}

	// Specifies that the method processes HTTP POST requests and return all users
	// object from DB
	@Path("/userscount")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String usersCount() throws ParseException {
		userService = new UserService(userFacade);
		configService = new ConfigService(configFacade);

		int count = userService.usersCount();
		JSONObject main = new JSONObject();

		main.put("Count", count);
		return main.toString();
	}

	// Specifies that the method processes HTTP POST requests and return all users
	// object from DB
	@Path("/lstusers")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String lstUsers(String body) throws ParseException {
		userService = new UserService(userFacade);
		configService = new ConfigService(configFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		String first = objectMap.get("first");
		String max = objectMap.get("max");

		List<TblUser> sList = new ArrayList<TblUser>();
		sList = userService.getUserList(Integer.parseInt(first), Integer.parseInt(max));
		JSONObject main = new JSONObject();

		for (TblUser u : sList) {
			JSONObject outputJsonObj = new JSONObject();
			// USER_ID, USER_USERNAME, USER_PASSWORD, USER_DESCRIPTION, USER_STATUS
			outputJsonObj.put("USER_ID", u.getUserId());
			outputJsonObj.put("USER_USERNAME", u.getUserUsername());
			outputJsonObj.put("USER_DESCRIPTION", u.getUserDescription());
			outputJsonObj.put("USER_STATUS", u.getUserStatus());
			outputJsonObj.put("USER_DISPLAYNAME", u.getUserDisplayname());
			outputJsonObj.put("USER_BRANCH_CODE", u.getUserBranchCode());
			outputJsonObj.put("USER_NAME", u.getUserName());

			main.put(u.getUserUsername(), outputJsonObj);
		}

		return main.toString();
	}

	// Specifies that the method processes HTTP POST requests and edit users list
	// object from DB
	@POST
	@Path("/addusers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int addUsers(String body) throws ParseException {
		userService = new UserService(userFacade);
		configService = new ConfigService(configFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, Object> objectMap = (Map<String, Object>) obj;

		Map<String, HashMap<String, String>> userNameListResult = null;
		int result = ExecutionStatusEnum.UNSUCCESSFUL.getValue();

		HashMap<String, HashMap<String, String>> userNameList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> user : objectMap.entrySet()) {
			userNameList.put(user.getKey(), (HashMap<String, String>) user.getValue());
		}

		try {
			result = userService.insertUsers(configService, userNameList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return result;

	}

	// Specifies that the method processes HTTP POST requests and edit users list
	// object from DB
	@POST
	@Path("/editusers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int editUsers(String body) throws ParseException {
		userService = new UserService(userFacade);
		configService = new ConfigService(configFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);

		Map<String, Object> objectMap = (Map<String, Object>) obj;

		int result = ExecutionStatusEnum.UNSUCCESSFUL.getValue();
		HashMap<String, HashMap<String, String>> userNameList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> user : objectMap.entrySet()) {
			userNameList.put(user.getKey(), (HashMap<String, String>) user.getValue());
		}

		try {
			result = userService.updateUsers(configService, userNameList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	// Specifies that the method processes HTTP POST requests and edit users list
	// object from DB
	@POST
	@Path("/removeusers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int removeUsers(String body) throws ParseException {
		userService = new UserService(userFacade);
		configService = new ConfigService(configFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);

		Map<String, Object> objectMap = (Map<String, Object>) obj;

		int result = ExecutionStatusEnum.UNSUCCESSFUL.getValue();
		;

		HashMap<String, HashMap<String, String>> userNameList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> user : objectMap.entrySet()) {
			userNameList.put(user.getKey(), (HashMap<String, String>) user.getValue());
		}

		try {
			result = userService.delateUsers(configService, userNameList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return result;

	}

	// Specifies that the method processes HTTP POST requests and return all users
	// object from DB
	@Path("/lstgroupsofuser")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String lstGroupsOfUser(String body) throws ParseException {
		userService = new UserService(userFacade);
		configService = new ConfigService(configFacade);
		ugService = new UserGroupService(ugFacade, userFacade, groupFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		String userName = objectMap.get(UserObjEnum.Username.getValue());

		List<TblUserGroup> sList = new ArrayList<TblUserGroup>();
		sList = ugService.getGroupsOfUser(userName);
		JSONObject main = new JSONObject();

		for (TblUserGroup ug : sList) {
			JSONObject outputJsonObj = new JSONObject();
			// USER_ID, USER_USERNAME, USER_PASSWORD, USER_DESCRIPTION, USER_STATUS
			if (ug.getTblGroup() != null) {
				outputJsonObj.put("GROUP_NAME", ug.getTblGroup().getGroupName());
				main.put(ug.getTblGroup().getGroupId(), outputJsonObj);
			}

		}

		return main.toString();
	}

	@Path("/finduser")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String findUser(String body) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		String userName = objectMap.get(UserObjEnum.Username.getValue());

		TblUser user = userService.findByUserName(userName);
		JSONObject main = new JSONObject();

		JSONObject outputJsonObj = new JSONObject();

		if (user != null) {

			outputJsonObj.put("USER_ID", user.getUserId());
			outputJsonObj.put("USER_USERNAME", user.getUserUsername());
			outputJsonObj.put("USER_DESCRIPTION", user.getUserDescription());
			outputJsonObj.put("USER_STATUS", user.getUserStatus());
			outputJsonObj.put("USER_DISPLAYNAME", user.getUserDisplayname());
			outputJsonObj.put("USER_BRANCH_CODE", user.getUserBranchCode());
			outputJsonObj.put("USER_NAME", user.getUserName());

			main.put(user.getUserUsername(), outputJsonObj);
		}

		return main.toString();
	}

	@Path("/filterbyusername")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String filterByUserName(String body) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		String userName = objectMap.get(UserObjEnum.Username.getValue());

		List<TblUser> lstUser = userService.filterByUserName(userName);
		JSONObject main = new JSONObject();

		if (lstUser != null) {
			for (TblUser user : lstUser) {
				JSONObject outputJsonObj = new JSONObject();

				outputJsonObj.put("USER_ID", user.getUserId());
				outputJsonObj.put("USER_USERNAME", user.getUserUsername());
				outputJsonObj.put("USER_DESCRIPTION", user.getUserDescription());
				outputJsonObj.put("USER_STATUS", user.getUserStatus());
				outputJsonObj.put("USER_DISPLAYNAME", user.getUserDisplayname());
				outputJsonObj.put("USER_BRANCH_CODE", user.getUserBranchCode());
				outputJsonObj.put("USER_NAME", user.getUserName());

				main.put(user.getUserId(), outputJsonObj);
			}
		}

		return main.toString();
	}

}
