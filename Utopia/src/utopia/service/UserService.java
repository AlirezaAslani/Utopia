package utopia.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import utopia.utils.DateUtils;
import utopia.entities.TblUser;
import utopia.enumeration.ExistEnum;
import utopia.session.IUserImplLocal;
import utopia.enumeration.StatusEnum;
import utopia.enumeration.UserObjEnum;
import utopia.enumeration.SystemUserEnum;
import utopia.utils.EncoderUtils;
import utopia.enumeration.ExecutionStatusEnum;

public class UserService implements IUserService {

	private IUserImplLocal userEJB;

	@Override
	public TblUser getUserObj(String userName, String password, StatusEnum status, String userDescription,
			TblUser userCreatedBy, Date createdDate, TblUser userUpdatedBy, Date updatedDate) {
		TblUser user = new TblUser();
		user.setUserUsername(userName);
		user.setUserPassword(password);
		user.setUserStatus(status.getValue());
		user.setUserDescription(userDescription);
		user.setUserCreatedBy(userCreatedBy);
		user.setUserCreatedDate(createdDate);
		user.setUserUpdatedBy(userUpdatedBy);
		user.setUserUpdatedDate(updatedDate);
		return user;
	}

	public UserService(IUserImplLocal userEJB) {
		super();
		this.userEJB = userEJB;
	}

	@Override
	public List<String> getUserNameList() {
		List<String> lstName = new ArrayList<String>();
		for (TblUser user : userEJB.findAll()) {
			lstName.add(user.getUserUsername());
		}
		return lstName;
	}

	@Override
	public void insertUserList(List<TblUser> lst) {
		for (TblUser user : lst) {
			userEJB.create(user);
		}

	}

	@Override
	public TblUser findByUserName(String userName) {
		return userEJB.findByUserName(userName);
	}

	@Override
	public List<TblUser> getUserList() {
		return userEJB.findAll();
	}

	@Override
	public List<TblUser> getUserList(int first, int max) {
		return userEJB.usersList(first, max);
	}

	@Override
	public int usersCount() {
		return userEJB.usersCount();
	}

	@Override
	public HashMap<String, List> getAllUser(List<String> userNameList) {
		List<TblUser> userList = new ArrayList<TblUser>();
		List<String> noExistUserList = new ArrayList<String>();
		HashMap<String, List> resultMap = new HashMap<String, List>();
		for (String user : userNameList) {
			TblUser userObj = userEJB.findByUserName(user);
			if (userObj != null)
				userList.add(userObj);
			else
				noExistUserList.add(user);
		}
		resultMap.put(ExistEnum.EXIST.getValue(), userList);
		resultMap.put(ExistEnum.NOEXIST.getValue(), noExistUserList);

		return resultMap;

	}

	@Override
	public int insertUsers(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception {

		List<String> result = new ArrayList<String>();
		StringBuffer failUsers = new StringBuffer();

		Date date = DateUtils.getCalendarDate();
		TblUser systemUser = findByUserName(SystemUserEnum.SystemUser.getValue());

		for (Map.Entry<String, HashMap<String, String>> user : userNameList.entrySet()) {

			if (user.getKey() != null) {
				TblUser existUser = userEJB.findByUserName(user.getKey());
				if (existUser == null) {
					HashMap<String, String> userValue = user.getValue();

					TblUser userObj = getUserObj(user.getKey(), null, StatusEnum.ACTIVE, null, null, null, null, null);

					userObj.setUserUsername(user.getKey());
					if (userValue.get(UserObjEnum.Password.getValue()) != null
							&& !userValue.get(UserObjEnum.Password.getValue()).isEmpty())
						userObj.setUserPassword(EncoderUtils.encoder(userValue.get(UserObjEnum.Password.getValue())));
					if (userValue.get(UserObjEnum.Descptition.getValue()) != null
							&& !userValue.get(UserObjEnum.Descptition.getValue()).isEmpty())
						userObj.setUserDescription(userValue.get(UserObjEnum.Descptition.getValue()));
					if (userValue.get(UserObjEnum.Status.getValue()) != null
							&& !userValue.get(UserObjEnum.Status.getValue()).isEmpty())
						if (userValue.get(UserObjEnum.Status.getValue()).equals("0"))
							userObj.setUserStatus(0);
					if (userValue.get(UserObjEnum.DisplayName.getValue()) != null
							&& !userValue.get(UserObjEnum.DisplayName.getValue()).isEmpty())
						userObj.setUserDisplayname(userValue.get(UserObjEnum.DisplayName.getValue()));
					if (userValue.get(UserObjEnum.UserBranchCode.getValue()) != null
							&& !userValue.get(UserObjEnum.UserBranchCode.getValue()).isEmpty())
						userObj.setUserBranchCode(userValue.get(UserObjEnum.UserBranchCode.getValue()));
					if (userValue.get(UserObjEnum.User_name.getValue()) != null
							&& !userValue.get(UserObjEnum.User_name.getValue()).isEmpty())
						userObj.setUserName(userValue.get(UserObjEnum.User_name.getValue()));

					userObj.setUserCreatedDate(date);
					userObj.setUserCreatedBy(systemUser);
					userObj.setUserUpdatedBy(systemUser);
					userObj.setUserUpdatedDate(date);

					userEJB.create(userObj);
					return ExecutionStatusEnum.SUCCESSFUL.getValue();

				} else {
					return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
				}
			}

		}
		return ExecutionStatusEnum.UNSUCCESSFUL.getValue();

	}

	@Override
	public int updateUsers(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception {

		List<String> result = new ArrayList<String>();
		StringBuffer failUsers = new StringBuffer();
		for (Map.Entry<String, HashMap<String, String>> user : userNameList.entrySet()) {
			TblUser userObj = null;
			userObj = userEJB.findByUserName(user.getKey());
			if (!userObj.equals(null)) {

				HashMap<String, String> userValue = user.getValue();
				if (userValue.get(UserObjEnum.Descptition.getValue()) != null
						&& !userValue.get(UserObjEnum.Descptition.getValue()).isEmpty())
					userObj.setUserDescription(userValue.get(UserObjEnum.Descptition.getValue()));
				if (userValue.get(UserObjEnum.DisplayName.getValue()) != null
						&& !userValue.get(UserObjEnum.DisplayName.getValue()).isEmpty())
					userObj.setUserDisplayname(userValue.get(UserObjEnum.DisplayName.getValue()));
				if (userValue.get(UserObjEnum.Status.getValue()) != null
						&& !userValue.get(UserObjEnum.Status.getValue()).isEmpty())
					userObj.setUserStatus(Integer.valueOf(userValue.get(UserObjEnum.Status.getValue())));
				if (userValue.get(UserObjEnum.UserBranchCode.getValue()) != null
						&& !userValue.get(UserObjEnum.UserBranchCode.getValue()).isEmpty())
					userObj.setUserBranchCode(userValue.get(UserObjEnum.UserBranchCode.getValue()));
				if (userValue.get(UserObjEnum.User_name.getValue()) != null
						&& !userValue.get(UserObjEnum.User_name.getValue()).isEmpty())
					userObj.setUserName(userValue.get(UserObjEnum.User_name.getValue()));

				userEJB.edit(userObj);
				return ExecutionStatusEnum.SUCCESSFUL.getValue();

			} else {
				return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
			}

		}
		return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
	}

	@Override
	public int delateUsers(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception {

		List<String> result = new ArrayList<String>();
		StringBuffer failUsers = new StringBuffer();
		for (Map.Entry<String, HashMap<String, String>> user : userNameList.entrySet()) {
			TblUser userObj = null;
			userObj = userEJB.findByUserName(user.getKey());
			if (!userObj.equals(null)) {

				userEJB.remove(userObj);
				return ExecutionStatusEnum.SUCCESSFUL.getValue();

			} else {
				return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
			}

		}
		return ExecutionStatusEnum.UNSUCCESSFUL.getValue();
	}

	@Override
	public List<TblUser> filterByUserName(String userName) {
		// TODO Auto-generated method stub
		return userEJB.filterByUserName(userName);
	}

}
