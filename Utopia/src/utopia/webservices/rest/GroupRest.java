package utopia.webservices.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utopia.entities.TblGroup;
import utopia.entities.TblUserGroup;
import utopia.enumeration.ExecutionStatusEnum;
import utopia.enumeration.GroupObjEnum;
import utopia.service.ConfigService;
import utopia.service.GroupService;
import utopia.service.IConfigService;
import utopia.service.IGroupService;
import utopia.service.IUserGroupService;
import utopia.service.IUserService;
import utopia.service.UserGroupService;
import utopia.service.UserService;
import utopia.session.IConfigImplLocal;
import utopia.session.IGroupImplLocal;
import utopia.session.IUserGroupImplLocal;
import utopia.session.IUserImplLocal;

@Path("/group")
@Stateless
public class GroupRest {

	@EJB(name = "user")
	private IUserImplLocal userFacade;

	@EJB(name = "group")
	private IGroupImplLocal groupFacade;

	@EJB(name = "config")
	private IConfigImplLocal configFacade;
	
	@EJB(name = "ug")
	private IUserGroupImplLocal ugFacade;

	private IUserService userService;
	private IGroupService groupService;
	private IConfigService configService;
	private IUserGroupService userGroupService;

	@PostConstruct
	public void init() {
		userService = new UserService(userFacade);
		groupService = new GroupService(groupFacade, userFacade);
		configService = new ConfigService(configFacade);
		userGroupService = new UserGroupService(ugFacade, userFacade, groupFacade);
	}
	
	
	@Path("/groupscount")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String groupsCount() throws ParseException {
	
		int count = groupService.groupsCount();
		JSONObject main = new JSONObject();


		main.put("Count", count);
		return main.toString();
	}

	
	@Path("/lstgroups")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String lstGroups(String body) throws ParseException {

		List<TblGroup> sList = new ArrayList<TblGroup>();
		
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(body);  
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		 String first = objectMap.get("first");
		 String max = objectMap.get("max");
		 
		try {
			sList = groupService.getgroupObjList(Integer.parseInt(first), Integer.parseInt(max));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject main = new JSONObject();

		for (TblGroup g : sList) {
			JSONObject outputJsonObj = new JSONObject();

			outputJsonObj.put(GroupObjEnum.Id.getValue(), g.getGroupId());

			outputJsonObj.put(GroupObjEnum.Descptition.getValue(), g.getGroupDescription());
			outputJsonObj.put(GroupObjEnum.Status.getValue(), g.getGroupStatus());

			main.put(g.getGroupName(), outputJsonObj);
		}

		return main.toString();
	}

	
	@POST
	@Path("/addgroups")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addGroups(String body) throws ParseException {
	
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(body);  
		Object obj = parser.parse(body);
		Map<String, Object> objectMap = (Map<String, Object>) obj;
		
		StringBuffer result = null;

		HashMap<String, HashMap<String, String>> groupNameList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> user : objectMap.entrySet()) {
			groupNameList.put(user.getKey(), (HashMap<String, String>) user.getValue());
		}

		try {
			result = groupService.insertGroups(configService, groupNameList);

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


	@POST
	@Path("/editgroups")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String editGrupes(String body) throws ParseException {
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(body);  
		Object obj = parser.parse(body);
		Map<String, Object> objectMap = (Map<String, Object>) obj;

		StringBuffer result = null;

		HashMap<String, HashMap<String, String>> groupNameList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> group : objectMap.entrySet()) {
			groupNameList.put(group.getKey(), (HashMap<String, String>) group.getValue());
		}

		try {
			result = groupService.updateGroups(configService, groupNameList);

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


	@POST
	@Path("/removegroups")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String removeGroups(String body) throws ParseException {
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(body);  
		Object obj = parser.parse(body);
		Map<String, Object> objectMap = (Map<String, Object>) obj;
		
		StringBuffer result = null;

		HashMap<String, HashMap<String, String>> userGroupList = new HashMap<String, HashMap<String, String>>();

		for (Map.Entry<String, Object> group : objectMap.entrySet()) {
			userGroupList.put(group.getKey(), (HashMap<String, String>) group.getValue());
		}

		try {
			result = groupService.delateGroups(configService, userGroupList);

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
	

	
	@Path("/lstusersofgroup")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String lstUsersOfgroup(String body) throws ParseException {
		userService = new UserService(userFacade);
		configService = new ConfigService(configFacade);
		userGroupService = new UserGroupService(ugFacade, userFacade, groupFacade);

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		String groupName = objectMap.get(GroupObjEnum.Name.getValue());

		List<TblUserGroup> sList = new ArrayList<TblUserGroup>();
		sList = userGroupService.getUsersOfGroup(groupName);
		JSONObject main = new JSONObject();

		for (TblUserGroup ug : sList) {
			JSONObject outputJsonObj = new JSONObject();
			
			if(ug.getTblUser() != null) {
				outputJsonObj.put("USER_NAME", ug.getTblUser().getUserUsername());
				main.put(ug.getTblGroup().getGroupName(), outputJsonObj);
			}

			
		}

		return main.toString();
	}
	
	@Path("/findgroup")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String findGroup(String body) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		String groupName = objectMap.get(GroupObjEnum.Name.getValue());
		

		
		
		TblGroup group = groupService.findByGroupName(groupName);
		JSONObject main = new JSONObject();

	
			JSONObject outputJsonObj = new JSONObject();
			
			if(group != null) {

				outputJsonObj.put(GroupObjEnum.Name.getValue(), group.getGroupName());
				outputJsonObj.put(GroupObjEnum.Descptition.getValue(), group.getGroupDescription());
				outputJsonObj.put(GroupObjEnum.Status.getValue(), group.getGroupStatus());
				outputJsonObj.put(GroupObjEnum.DisplayName.getValue(), group.getGroupDisplayname());

				main.put(group.getGroupId(), outputJsonObj);
			}
			
			
		

		return main.toString();
	}

	
	@Path("/filterbygroupname")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String filterByGroupName(String body) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(body);
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		String groupName = objectMap.get(GroupObjEnum.Name.getValue());
		

		
		
		List<TblGroup> lstGroup = groupService.filterByGroupName(groupName);
		JSONObject main = new JSONObject();

	
			
		for (TblGroup group : lstGroup) {
			if (group != null) {
				JSONObject outputJsonObj = new JSONObject();
				outputJsonObj.put(GroupObjEnum.Name.getValue(), group.getGroupName());
				outputJsonObj.put(GroupObjEnum.Descptition.getValue(), group.getGroupDescription());
				outputJsonObj.put(GroupObjEnum.Status.getValue(), group.getGroupStatus());
				outputJsonObj.put(GroupObjEnum.DisplayName.getValue(), group.getGroupDisplayname());
				
				main.put(group.getGroupId(), outputJsonObj);
			}
		}
			
			
		

		return main.toString();
	}


}
