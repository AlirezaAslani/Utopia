package utopia.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utopia.entities.TblGroup;
import utopia.entities.TblUser;
import utopia.entities.TblUserGroup;
import utopia.enumeration.StatusEnum;

public interface IUserGroupService {
	
	public TblUserGroup getUserGroupObj(TblGroup group, TblUser user, StatusEnum status, String description, TblUser userGroupCreatedBy, Date createdDate, TblUser userGroupUpdatedBy, Date updatedDate);
	public int addGroupMember(IConfigService configService, Map<String, HashMap<String,String>> userNameList) throws Exception;
	public List<TblUserGroup> getGroupsOfUser(String userName);
	public int removeGroupMember(IConfigService configService, Map<String, HashMap<String,String>> userNameList) throws Exception;
	public List<TblUserGroup> getUsersOfGroup(String groupName);



}
