package utopia.service;

import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import utopia.entities.TblUser;
import utopia.enumeration.StatusEnum;

public interface IUserService {

	public int usersCount();

	public List<TblUser> getUserList();

	public List<String> getUserNameList();

	public void insertUserList(List<TblUser> lst);

	public TblUser findByUserName(String userNameList);

	public List<TblUser> getUserList(int first, int max);

	public List<TblUser> filterByUserName(String userName);

	public HashMap<String, List> getAllUser(List<String> userNameList);

	public int insertUsers(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception;

	public int updateUsers(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception;

	public int delateUsers(IConfigService configService, Map<String, HashMap<String, String>> userNameList)
			throws Exception;

	public TblUser getUserObj(String userName, String password, StatusEnum status, String userDescription,
			TblUser userCreatedBy, Date createdDate, TblUser userUpdatedBy, Date updatedDate);

}
