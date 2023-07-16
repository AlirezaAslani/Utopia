package utopia.model;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utopia.entities.TblConfig;
import utopia.enumeration.LogEnum;
import utopia.session.IConfigImplLocal;
import utopia.session.IConfigImplRemote;

@Stateless(name = "configFacade")
public class ConfigFacade extends AbstractFacade<TblConfig> implements IConfigImplLocal, IConfigImplRemote {

	private static final Logger log = LoggerFactory.getLogger(ConfigFacade.class.getSimpleName());

	@PersistenceContext(unitName = "UtopiaEJBModule")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public ConfigFacade() {
		super(TblConfig.class);
	}

	@Override
	public TblConfig find(TblConfig entity) {
		super.find(entity).getCnfId();
		return null;
	}

	@Override
	public TblConfig findByCnfType(String cnfType, int status) {
		TblConfig results = null;
		try {
			String query = "SELECT u FROM TblConfig u WHERE u.cnfType= :configType and u.cnfState = :configState";
			em.getEntityManagerFactory();
			TypedQuery<TblConfig> typedTblUserQuery = em.createQuery(query, TblConfig.class);
			typedTblUserQuery.setParameter("configState", BigDecimal.valueOf(status));
			typedTblUserQuery.setParameter("configType", cnfType);
			results = typedTblUserQuery.getSingleResult();
		} catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}

		return results;
	}

}