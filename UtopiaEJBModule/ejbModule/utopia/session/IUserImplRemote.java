package utopia.session;

import java.util.List;
import javax.ejb.Remote;

import utopia.entities.TblUser;
import utopia.enumeration.StatusEnum;

@Remote
public interface IUserImplRemote {
	public int usersCount();

	public List<TblUser> findAll();

	public void create(TblUser entity);

	public void remove(TblUser entity);

	public TblUser find(TblUser entity);

	public TblUser edit(TblUser entity);

	public TblUser findByUserName(String userName);

	public List<TblUser> usersList(int first, int max);

	public List<TblUser> findByStatus(StatusEnum status);

	public List<TblUser> filterByUserName(String userName);

	public List<TblUser> filterByUserStatus(String status);

	public List<TblUser> filterByUsrName(String userName);

	public List<TblUser> filterByUserBranchCode(String userBranch);

	public List<TblUser> filterByUserDescription(String userDescription);

	public List<TblUser> filterByUserDisplayname(String userDisplayname);

}
