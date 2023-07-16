package utopia.session;

import java.util.List;
import javax.ejb.Local;
import utopia.entities.TblConfig;

@Local
public interface IConfigImplLocal {
	public List<TblConfig> findAll();

	public void remove(TblConfig entity);

	public void create(TblConfig entity);

	public TblConfig edit(TblConfig entity);

	public TblConfig find(TblConfig entity);

	public TblConfig findByCnfType(String cnfType, int status);

}
