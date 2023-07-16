package utopia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the TBL_USERGROUP database table.
 * 
 */
@Entity
@Table(name="TBL_USERGROUP")
@NamedQuery(name="TblUsergroup.findAll", query="SELECT t FROM TblUserGroup t")
public class TblUserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="USRGROUP_ID")
	private long usrgroupId;

	@Column(name="USRGROUP_DESCRIPTION")
	private String usrgroupDescription;

	//bi-directional many-to-one association to TblGroup
	@ManyToOne
	@JoinColumn(name="USRGROUP_GROUPID")
	private TblGroup tblGroup;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USRGROUP_USERID")
	private TblUser tblUser;
	
	@Column(name="USRGROUP_STATUS")
	private Integer status;
	
	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USRGROUP_CREATED_BY")
	private TblUser userGroupCreatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USRGROUP_CREATED_DATE")
	private Date userGroupCreatedDate;
	
	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USRGROUP_UPDATED_BY")
	private TblUser userGroupUpdatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USRGROUP_UPDATED_DATE")
	private Date userGroupUpdatedDate;


	public TblUserGroup() {
	}

	
	// getter and setter methods
	public long getUsrgroupId() {
		return this.usrgroupId;
	}

	public void setUsrgroupId(long usrgroupId) {
		this.usrgroupId = usrgroupId;
	}

	public String getUsrgroupDescription() {
		return this.usrgroupDescription;
	}

	public void setUsrgroupDescription(String usrgroupDescription) {
		this.usrgroupDescription = usrgroupDescription;
	}

	public TblGroup getTblGroup() {
		return this.tblGroup;
	}

	public void setTblGroup(TblGroup tblGroup) {
		this.tblGroup = tblGroup;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public TblUser getUserGroupCreatedBy() {
		return userGroupCreatedBy;
	}

	public void setUserGroupCreatedBy(TblUser userGroupCreatedBy) {
		this.userGroupCreatedBy = userGroupCreatedBy;
	}

	public Date getUserGroupCreatedDate() {
		return userGroupCreatedDate;
	}

	public void setUserGroupCreatedDate(Date userGroupCreatedDate) {
		this.userGroupCreatedDate = userGroupCreatedDate;
	}

	public TblUser getUserGroupUpdatedBy() {
		return userGroupUpdatedBy;
	}

	public void setUserGroupUpdatedBy(TblUser userGroupUpdatedBy) {
		this.userGroupUpdatedBy = userGroupUpdatedBy;
	}

	public Date getUserGroupUpdatedDate() {
		return userGroupUpdatedDate;
	}

	public void setUserGroupUpdatedDate(Date userGroupUpdatedDate) {
		this.userGroupUpdatedDate = userGroupUpdatedDate;
	}
}