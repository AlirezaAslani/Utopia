package utopia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utopia.entities.TblGroup;
import utopia.entities.TblUser;
import utopia.enumeration.ExistEnum;
import utopia.enumeration.GroupObjEnum;
import utopia.enumeration.StatusEnum;
import utopia.enumeration.SystemUserEnum;
import utopia.session.IGroupImplLocal;
import utopia.session.IUserImplLocal;
import utopia.utils.DateUtils;

public class GroupService implements IGroupService {

	private IGroupImplLocal groupEJB;
	private IUserService userService;

	public GroupService(IGroupImplLocal groupEJB, IUserImplLocal userEJB) {
		super();
		this.groupEJB = groupEJB;
		userService = new UserService(userEJB);
	}

	@Override
	public TblGroup getGroupObj(String groupName, String groupDescription, StatusEnum status, TblUser groupCreatedBy,
			Date createdDate, TblUser groupUpdatedBy, Date updatedDate) {
		TblGroup group = new TblGroup();
		group.setGroupName(groupName);
		group.setGroupDescription(groupDescription);
		group.setGroupStatus(status.getValue());
		group.setGroupCreatedBy(groupCreatedBy);
		group.setGroupCreatedDate(createdDate);
		group.setGroupUpdatedBy(groupUpdatedBy);
		group.setGroupUpdatedDate(updatedDate);
		return group;
	}

	@Override
	public TblGroup findByGroupName(String groupName) {
		// TODO Auto-generated method stub
		return groupEJB.findByGroupName(groupName);
	}
	
	@Override
	public Integer groupsCount() {
		// TODO Auto-generated method stub
		return groupEJB.groupsCount();
	}

	@Override
	public List<TblGroup> getgroupObjList() throws Exception {
		// TODO Auto-generated method stub
		return groupEJB.findAll();
	}

	@Override
	public List<TblGroup> getgroupObjList(int first, int max) throws Exception {
		// TODO Auto-generated method stub
		return groupEJB.groupsList(first, max);
	}

	@Override
	public StringBuffer insertGroups(IConfigService configService, Map<String, HashMap<String, String>> groupNameList)
			throws Exception {

		List<String> result = new ArrayList<String>();
		StringBuffer failGroup = new StringBuffer();

		Date date = DateUtils.getCalendarDate();
		TblUser systemUser = userService.findByUserName(SystemUserEnum.SystemUser.getValue());

		for (Map.Entry<String, HashMap<String, String>> group : groupNameList.entrySet()) {

			if (group.getKey() != null) {
				TblGroup existGroup = groupEJB.findByGroupName(group.getKey());
				if (existGroup == null) {
					HashMap<String, String> groupValue = group.getValue();

					TblGroup groupObj = getGroupObj(group.getKey(), null, StatusEnum.ACTIVE, systemUser, date,
							systemUser, date);

					groupObj.setGroupName(group.getKey());
					if (groupValue.get(GroupObjEnum.Descptition.getValue()) != null
							&& !groupValue.get(GroupObjEnum.Descptition.getValue()).isEmpty())
						groupObj.setGroupDescription(groupValue.get(GroupObjEnum.Descptition.getValue()));
					if (groupValue.get(GroupObjEnum.Status.getValue()) != null
							&& !groupValue.get(GroupObjEnum.Status.getValue()).isEmpty())
						if (groupValue.get(GroupObjEnum.Status.getValue()).equals("0"))
							groupObj.setGroupStatus(0);
					if (groupValue.get(GroupObjEnum.DisplayName.getValue()) != null
							&& !groupValue.get(GroupObjEnum.DisplayName.getValue()).isEmpty())
						groupObj.setGroupDisplayname(groupValue.get(GroupObjEnum.DisplayName.getValue()));

					groupObj.setGroupCreatedDate(date);
					groupObj.setGroupCreatedBy(systemUser);
					groupObj.setGroupUpdatedBy(systemUser);
					groupObj.setGroupUpdatedDate(date);

					groupEJB.create(groupObj);

				} else {
					failGroup.append(group.getKey() + ";");
				}
			}

		}
		return failGroup;
	}

	@Override
	public StringBuffer updateGroups(IConfigService configService, Map<String, HashMap<String, String>> groupNameList)
			throws Exception {

		List<String> result = new ArrayList<String>();
		StringBuffer failGroups = new StringBuffer();
		for (Map.Entry<String, HashMap<String, String>> group : groupNameList.entrySet()) {
			TblGroup groupObj = null;
			groupObj = groupEJB.findByGroupName(group.getKey());
			if (!groupObj.equals(null)) {

				HashMap<String, String> groupValue = group.getValue();
				if (groupValue.get(GroupObjEnum.Descptition.getValue()) != null
						&& !groupValue.get(GroupObjEnum.Descptition.getValue()).isEmpty())
					groupObj.setGroupDescription(groupValue.get(GroupObjEnum.Descptition.getValue()));
				if (groupValue.get(GroupObjEnum.DisplayName.getValue()) != null
						&& !groupValue.get(GroupObjEnum.DisplayName.getValue()).isEmpty())
					groupObj.setGroupDisplayname(groupValue.get(GroupObjEnum.DisplayName.getValue()));
				if (groupValue.get(GroupObjEnum.Status.getValue()) != null
						&& !groupValue.get(GroupObjEnum.Status.getValue()).isEmpty())
					groupObj.setGroupStatus(Integer.valueOf(groupValue.get(GroupObjEnum.Status.getValue())));

				// oracleService.updateGroupsDescription(group.getKey(),
				// groupValue.get(GroupObjEnum.Descptition.getValue()));
				groupEJB.edit(groupObj);

			} else {
				failGroups.append(group.getKey() + ";");
			}

		}
		return failGroups;
	}

	@Override
	public StringBuffer delateGroups(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception {

		List<String> result = new ArrayList<String>();
		StringBuffer failGroups = new StringBuffer();
		for (Map.Entry<String, HashMap<String, String>> group : userNameList.entrySet()) {
			TblGroup groupObj = null;
			groupObj = groupEJB.findByGroupName(group.getKey());
			if (!groupObj.equals(null)) {

				groupEJB.remove(groupObj);

			} else {
				failGroups.append(group.getKey() + ";");
			}

		}
		return failGroups;
	}

	@Override
	public List<TblGroup> filterByGroupName(String groupName) {
		// TODO Auto-generated method stub
		return groupEJB.filterByGroupName(groupName);
	}

}
