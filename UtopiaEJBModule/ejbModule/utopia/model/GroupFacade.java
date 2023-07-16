package utopia.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import utopia.entities.TblGroup;
import utopia.enumeration.LogEnum;
import utopia.enumeration.StatusEnum;
import utopia.session.IGroupImplLocal;
import utopia.session.IGroupImplRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless(name = "groupFacade")
public class GroupFacade extends AbstractFacade<TblGroup> implements IGroupImplLocal, IGroupImplRemote {

	private static final Logger log = LoggerFactory.getLogger(GroupFacade.class.getSimpleName());

	@PersistenceContext(unitName = "UtopiaEJBModule")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public GroupFacade() {
		super(TblGroup.class);
	}

	@Override
	public TblGroup find(TblGroup entity) {
		super.find(entity).getGroupId();
		return null;
	}

	@Override
	public TblGroup findByGroupName(String groupName) {
		String query = "SELECT u FROM TblGroup u WHERE u.groupName = :groupName";
		TypedQuery<TblGroup> typedQuery = em.createQuery(query, TblGroup.class);
		typedQuery.setParameter("groupName", groupName);
		TblGroup results = null;
		try {
			results = typedQuery.getSingleResult();
		} catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<TblGroup> findByStatus(StatusEnum status) {
		String query = "SELECT u FROM TblGroup u WHERE u.groupStatus = :groupStatus";
		TypedQuery<TblGroup> typedQuery = em.createQuery(query, TblGroup.class);
		typedQuery.setParameter("groupStatus", status.getValue());
		List<TblGroup> results = null;
		try {
			results = typedQuery.getResultList();
		} catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<TblGroup> groupsList(int first, int max) {
		String query = "SELECT u FROM TblGroup u order by u.groupId DESC";
		TypedQuery<TblGroup> typedQuery = em.createQuery(query, TblGroup.class).setFirstResult(first)
				.setMaxResults(max);
		List<TblGroup> results = null;
		try {
			results = typedQuery.getResultList();
		} catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public int groupsCount() {
		String query = "SELECT u FROM TblGroup u";
		em.getEntityManagerFactory();
		TypedQuery<TblGroup> typedTblUserQuery = em.createQuery(query, TblGroup.class);
		List<TblGroup> results = null;
		try {
			results = typedTblUserQuery.getResultList();
		} catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}

		return results.size();
	}

	@Override
	public List<TblGroup> filterByGroupName(String groupName) {
		String query = "SELECT u FROM TblGroup u WHERE u.groupName LIKE :groupName";
		TypedQuery<TblGroup> typedQuery = em.createQuery(query, TblGroup.class);
		typedQuery.setParameter("groupName", "%" + groupName + "%");
		List<TblGroup> results = null;
		try {
			results = typedQuery.getResultList();
		} catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<TblGroup> filterByGroupStatus(String status) {
		String query = "SELECT u FROM TblGroup u WHERE u.groupStatus = :groupStatus";
		TypedQuery<TblGroup> typedQuery = em.createQuery(query, TblGroup.class);
		typedQuery.setParameter("groupStatus", Integer.valueOf(status));
		List<TblGroup> results = null;
		try {
			results = typedQuery.getResultList();
		} catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<TblGroup> filterByGroupDescription(String groupDescription) {
		String query = "SELECT u FROM TblGroup u WHERE u.groupDescription LIKE :groupDescription";
		TypedQuery<TblGroup> typedQuery = em.createQuery(query, TblGroup.class);
		typedQuery.setParameter("groupDescription", "%" + groupDescription + "%");
		List<TblGroup> results = null;
		try {
			results = typedQuery.getResultList();
		} catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<TblGroup> filterByGroupDisplayname(String groupDisplayname) {
		String query = "SELECT u FROM TblGroup u WHERE u.groupDisplayname LIKE :groupDisplayname";
		TypedQuery<TblGroup> typedQuery = em.createQuery(query, TblGroup.class);
		typedQuery.setParameter("groupDisplayname", "%" + groupDisplayname + "%");
		List<TblGroup> results = null;
		try {
			results = typedQuery.getResultList();
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