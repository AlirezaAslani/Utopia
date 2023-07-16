package utopia.session;

import java.util.List;
import javax.ejb.Local;
import utopia.entities.TblGroup;
import utopia.entities.TblUser;
import utopia.entities.TblUserGroup;

@Local
public interface IUserGroupImplLocal {

	public List<TblUserGroup> findAll();

	public void create(TblUserGroup entity);

	public void remove(TblUserGroup entity);

	public TblUserGroup edit(TblUserGroup entity);

	public List<TblUserGroup> findByGroupID(TblGroup tblGroup);

	public List<TblUserGroup> findByUserID(TblUser userMemeberOfGroup);

	public TblUserGroup findByUserMemeberOfGroup(TblGroup tblGroup, TblUser userMemeberOfGroup);

}
