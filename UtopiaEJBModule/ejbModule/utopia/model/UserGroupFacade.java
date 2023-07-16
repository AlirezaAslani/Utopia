package utopia.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utopia.entities.TblGroup;
import utopia.entities.TblUser;
import utopia.entities.TblUserGroup;
import utopia.enumeration.LogEnum;
import utopia.session.IUserGroupImplLocal;
import utopia.session.IUserGroupImplRemote;


@Stateless(name="userGroupFacade")
public class UserGroupFacade extends AbstractFacade<TblUserGroup> implements IUserGroupImplLocal,IUserGroupImplRemote {
	
	private static final Logger log = LoggerFactory.getLogger(UserGroupFacade.class.getSimpleName());

	@PersistenceContext(unitName = "UtopiaEJBModule")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public UserGroupFacade() {
		super(TblUserGroup.class);
	}
	
	@Override
    public List<TblUserGroup> findByGroupID(TblGroup tblGroup){
    	String query = "SELECT u FROM TblUserGroup u WHERE u.tblGroup.groupId = :groupId";
    	TypedQuery<TblUserGroup> typedQuery = em.createQuery(query , TblUserGroup.class);
    	typedQuery.setParameter("groupId", tblGroup.getGroupId());
    	List<TblUserGroup> results = typedQuery.getResultList();
    	return results;
    }
    
	@Override
    public TblUserGroup findByUserMemeberOfGroup(TblGroup tblGroup,TblUser userMemeberOfGroup){
    	String query = "SELECT u FROM TblUserGroup u WHERE u.tblGroup.groupId = :groupId and u.tblUser.userId = :userMemberId";
    	TypedQuery<TblUserGroup> typedQuery = em.createQuery(query , TblUserGroup.class);
    	typedQuery.setParameter("groupId", tblGroup.getGroupId());
    	typedQuery.setParameter("userMemberId", userMemeberOfGroup.getUserId());
    	TblUserGroup results = null;
    	try {
    		results = typedQuery.getSingleResult();
    	}catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		}catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}
    	return results;
    }
    
	@Override
    public List<TblUserGroup> findByUserID(TblUser userMemeberOfGroup){
    	String query = "SELECT u FROM TblUserGroup u WHERE u.tblUser.userId = :userMemberId";
    	TypedQuery<TblUserGroup> typedQuery = em.createQuery(query , TblUserGroup.class);
    	typedQuery.setParameter("userMemberId", userMemeberOfGroup.getUserId());
    	List<TblUserGroup> results = null;
    	try {
    		results = typedQuery.getResultList();
    	}catch (NoResultException e) {
			log.error(LogEnum.NoResultException.getValue());
			e.printStackTrace();
		}catch (Exception e) {
			log.error(LogEnum.UnexpectedError.getValue());
			e.printStackTrace();
		}
    	return results;
    }


}
