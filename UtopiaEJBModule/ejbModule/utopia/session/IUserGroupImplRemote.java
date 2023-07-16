package utopia.session;

import java.util.List;
import javax.ejb.Remote;
import utopia.entities.TblUser;
import utopia.entities.TblUserGroup;
import utopia.entities.TblGroup;

@Remote
public interface IUserGroupImplRemote {

	public List<TblUserGroup> findAll();

	public void create(TblUserGroup entity);

	public void remove(TblUserGroup entity);

	public TblUserGroup edit(TblUserGroup entity);

	public List<TblUserGroup> findByGroupID(TblGroup tblGroup);

	public TblUserGroup findByUserMemeberOfGroup(TblGroup tblGroup, TblUser userMemeberOfGroup);

	public List<TblUserGroup> findByUserID(TblUser userMemeberOfGroup);

}
