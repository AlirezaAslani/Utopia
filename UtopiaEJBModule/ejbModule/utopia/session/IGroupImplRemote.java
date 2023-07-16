package utopia.session;

import java.util.List;
import javax.ejb.Remote;
import utopia.entities.TblGroup;
import utopia.enumeration.StatusEnum;

@Remote
public interface IGroupImplRemote {
	public int groupsCount();

	public List<TblGroup> findAll();

	public void create(TblGroup entity);

	public void remove(TblGroup entity);

	public TblGroup edit(TblGroup entity);

	public TblGroup find(TblGroup entity);

	public TblGroup findByGroupName(String groupName);

	public List<TblGroup> groupsList(int first, int max);

	public List<TblGroup> findByStatus(StatusEnum status);

	public List<TblGroup> filterByGroupStatus(String status);

	public List<TblGroup> filterByGroupName(String groupName);

	public List<TblGroup> filterByGroupDescription(String groupDescription);

	public List<TblGroup> filterByGroupDisplayname(String groupDisplayname);
}
