package utopia.session;

import java.util.List;
import javax.ejb.Remote;
import utopia.entities.TblConfig;

@Remote
public interface IConfigImplRemote {
	public List<TblConfig> findAll();

	public void create(TblConfig entity);

	public void remove(TblConfig entity);

	public TblConfig find(TblConfig entity);

	public TblConfig edit(TblConfig entity);

	public TblConfig findByCnfType(String cnfType, int status);

}
