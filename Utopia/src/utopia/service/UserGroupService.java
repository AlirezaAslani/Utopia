package utopia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utopia.entities.TblGroup;
import utopia.entities.TblUser;
import utopia.entities.TblUserGroup;
import utopia.enumeration.ExecutionStatusEnum;
import utopia.enumeration.GroupMemberObjEnum;
import utopia.enumeration.StatusEnum;
import utopia.enumeration.SystemUserEnum;
import utopia.session.IGroupImplLocal;
import utopia.session.IUserGroupImplLocal;
import utopia.session.IUserImplLocal;

import utopia.utils.DateUtils;

public class UserGroupService implements IUserGroupService {

	private IUserGroupImplLocal userGroupEjb;
	private IUserService userService;
	private IGroupService groupService;

	public UserGroupService(IUserGroupImplLocal userGroupEjb, IUserImplLocal userEJB, IGroupImplLocal groupEJB) {
		super();
		this.userGroupEjb = userGroupEjb;
		this.userService = new UserService(userEJB);
		this.groupService = new GroupService(groupEJB, userEJB);
	}

	@Override
	public TblUserGroup getUserGroupObj(TblGroup group, TblUser user, StatusEnum status, String description,
			TblUser userGroupCreatedBy, Date createdDate, TblUser userGroupUpdatedBy, Date updatedDate) {
		TblUserGroup userGroup = new TblUserGroup();
		userGroup.setTblGroup(group);
		userGroup.setTblUser(user);
		userGroup.setStatus(status.getValue());
		userGroup.setUsrgroupDescription(description);
		userGroup.setUserGroupCreatedBy(userGroupCreatedBy);
		userGroup.setUserGroupCreatedDate(createdDate);
		userGroup.setUserGroupUpdatedBy(userGroupUpdatedBy);
		userGroup.setUserGroupUpdatedDate(updatedDate);
		return userGroup;
	}

	@Override
	public int addGroupMember(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception {

		TblUser userMember = null;

		List<String> result = new ArrayList<String>();
		StringBuffer failGroups = new StringBuffer();
		Date date = DateUtils.getCalendarDate();
		TblUser systemUser = userService.findByUserName(SystemUserEnum.SystemUser.getValue());
		for (Map.Entry<String, HashMap<String, String>> group : userNameList.entrySet()) {
			TblGroup groupObj = null;
			groupObj = groupService.findByGroupName(group.getKey());
			if (groupObj != null) {

				HashMap<String, String> groupValue = group.getValue();
				if (groupValue.get(GroupMemberObjEnum.Member_Type.getValue()) != null
						&& groupValue.get(GroupMemberObjEnum.Member_Type.getValue())
								.equals(GroupMemberObjEnum.User_Member.getValue())) {

					userMember = userService.findByUserName(groupValue.get(GroupMemberObjEnum.Member.getValue()));
					TblUserGroup userGroup = getUserGroupObj(groupObj, userMember, StatusEnum.ACTIVE, "", systemUser,
							date, systemUser, date);
					TblUserGroup userGroupExist = userGroupEjb.findByUserMemeberOfGroup(groupObj, userMember);
					if (userGroupExist == null) {
						System.out.println("add " + group.getKey() + "_" + userMember.getUserUsername());

						userGroupEjb.create(userGroup);
						return ExecutionStatusEnum.SUCCESSFUL.getValue();
					} else {
						return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
					}

				} else {
					return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
				}

			} else {
				return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
			}
		}
		return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
	}

	@Override
	public List<TblUserGroup> getGroupsOfUser(String userName) {
		// TODO Auto-generated method stub
		List<TblUserGroup> lstGroupsOfUser = new ArrayList<TblUserGroup>();
		if (userName != null) {
			TblUser user = userService.findByUserName(userName);
			lstGroupsOfUser = userGroupEjb.findByUserID(user);
		}

		return lstGroupsOfUser;
	}

	@Override
	public int removeGroupMember(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception {

		TblUser userMember = null;

		List<String> result = new ArrayList<String>();
		StringBuffer failGroups = new StringBuffer();
		Date date = DateUtils.getCalendarDate();
		TblUser systemUser = userService.findByUserName(SystemUserEnum.SystemUser.getValue());
		for (Map.Entry<String, HashMap<String, String>> group : userNameList.entrySet()) {

			TblGroup groupObj = groupService.findByGroupName(group.getKey());
			HashMap<String, String> groupValue = group.getValue();
			if (groupValue.get(GroupMemberObjEnum.Member_Type.getValue()) != null && groupValue
					.get(GroupMemberObjEnum.Member_Type.getValue()).equals(GroupMemberObjEnum.User_Member.getValue())) {

				userMember = userService.findByUserName(groupValue.get(GroupMemberObjEnum.Member.getValue()));
				TblUserGroup userGroup = userGroupEjb.findByUserMemeberOfGroup(groupObj, userMember);
				userGroupEjb.remove(userGroup);
			} else
				return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
		}

		return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
	}

	@Override
	public List<TblUserGroup> getUsersOfGroup(String groupName) {
		// TODO Auto-generated method stub
		List<TblUserGroup> lstGroupsOfUser = new ArrayList<TblUserGroup>();
		if (groupName != null) {
			TblGroup group = groupService.findByGroupName(groupName);
			lstGroupsOfUser = userGroupEjb.findByGroupID(group);
		}

		return lstGroupsOfUser;
	}

}
