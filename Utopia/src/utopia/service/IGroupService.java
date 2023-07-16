package utopia.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utopia.entities.TblGroup;
import utopia.entities.TblUser;
import utopia.enumeration.StatusEnum;


public interface IGroupService {
	
	public Integer groupsCount();
	public List<TblGroup> getgroupObjList() throws Exception;
	public List<TblGroup> getgroupObjList(int first, int max) throws Exception;
	public TblGroup findByGroupName(String groupName);
	public StringBuffer insertGroups(IConfigService configService, Map<String, HashMap<String,String>> groupNameList) throws Exception;
	public StringBuffer updateGroups(IConfigService configService, Map<String, HashMap<String,String>> groupNameList) throws Exception;
	public StringBuffer delateGroups(IConfigService configService, Map<String, HashMap<String,String>> userNameList) throws Exception;
	public List<TblGroup> filterByGroupName(String groupName);
	public TblGroup getGroupObj(String groupName, String groupDescription, StatusEnum status, TblUser groupCreatedBy, Date createdDate, TblUser groupUpdatedBy, Date updatedDate);
	


	

}
