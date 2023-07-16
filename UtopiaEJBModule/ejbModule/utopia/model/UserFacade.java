package utopia.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utopia.entities.TblUser;
import utopia.enumeration.LogEnum;
import utopia.enumeration.StatusEnum;
import utopia.session.IUserImplLocal;
import utopia.session.IUserImplRemote;

@Stateless(name = "userFacade")
public class UserFacade extends AbstractFacade<TblUser> implements IUserImplLocal, IUserImplRemote {

	private static final Logger log = LoggerFactory.getLogger(UserFacade.class.getSimpleName());

	@PersistenceContext(unitName = "UtopiaEJBModule")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public UserFacade() {
		super(TblUser.class);
	}

	@Override
	public TblUser find(TblUser entity) {
		super.find(entity).getUserId();
		return null;
	}

	@Override
	public TblUser findByUserName(String userName) {
		String query = "SELECT u FROM TblUser u WHERE u.userUsername= :userUsername";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		typedTblUserQuery.setParameter("userUsername", userName);
		TblUser results = null;
		try {
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

	@Override
	public List<TblUser> findByStatus(StatusEnum status) {
		String query = "SELECT u FROM TblUser u WHERE u.userStatus= :userStatus";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		typedTblUserQuery.setParameter("userStatus", status.getValue());
		List<TblUser> results = null;
		try {
			results = typedTblUserQuery.getResultList();
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
	public List<TblUser> usersList(int first, int max) {
		String query = "SELECT u FROM TblUser u order by u.userId DESC";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class).setFirstResult(first)
				.setMaxResults(max);
		List<TblUser> results = null;
		try {
			results = typedTblUserQuery.getResultList();
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
	public int usersCount() {
		String query = "SELECT u FROM TblUser u";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		List<TblUser> results = null;
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
	public List<TblUser> filterByUserName(String userName) {
		String query = "SELECT u FROM TblUser u WHERE u.userUsername LIKE :userUsername";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		typedTblUserQuery.setParameter("userUsername", "%" + userName + "%");
		List<TblUser> results = null;
		try {
			results = typedTblUserQuery.getResultList();
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
	public List<TblUser> filterByUserStatus(String status) {
		String query = "SELECT u FROM TblUser u WHERE u.userStatus LIKE :userStatus";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		typedTblUserQuery.setParameter("userStatus", Integer.valueOf(status));
		List<TblUser> results = null;
		try {
			results = typedTblUserQuery.getResultList();
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
	public List<TblUser> filterByUserDescription(String userDescription) {
		String query = "SELECT u FROM TblUser u WHERE u.userDescription LIKE :userDescription";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		typedTblUserQuery.setParameter("userDescription", "%" + userDescription + "%");
		List<TblUser> results = null;
		try {
			results = typedTblUserQuery.getResultList();
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
	public List<TblUser> filterByUserBranchCode(String userBranch) {
		String query = "SELECT u FROM TblUser u WHERE u.userBranchCode LIKE :userBranchCode";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		typedTblUserQuery.setParameter("userBranchCode", "%" + userBranch + "%");
		List<TblUser> results = null;
		try {
			results = typedTblUserQuery.getResultList();
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
	public List<TblUser> filterByUserDisplayname(String UserDisplayname) {
		String query = "SELECT u FROM TblUser u WHERE u.userDisplayname LIKE :userDisplayname";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		typedTblUserQuery.setParameter("userDisplayname", "%" + UserDisplayname + "%");
		List<TblUser> results = null;
		try {
			results = typedTblUserQuery.getResultList();
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
	public List<TblUser> filterByUsrName(String userName) {
		String query = "SELECT u FROM TblUser u WHERE u.userName LIKE :userName";
		em.getEntityManagerFactory();
		TypedQuery<TblUser> typedTblUserQuery = em.createQuery(query, TblUser.class);
		typedTblUserQuery.setParameter("userName", "%" + userName + "%");
		List<TblUser> results = null;
		try {
			results = typedTblUserQuery.getResultList();
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