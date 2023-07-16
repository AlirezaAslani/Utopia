package utopia.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_USER database table.
 * 
 */
@Entity
@Table(name="TBL_USER")
@NamedQuery(name="TblUser.findAll", query="SELECT t FROM TblUser t")
public class TblUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private long userId;

	@Column(name="USER_DESCRIPTION")
	private String userDescription;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	@Column(name="USER_STATUS")
	private Integer userStatus;

	@Column(name="USER_USERNAME")
	private String userUsername;
	
	@Column(name="USER_DISPLAYNAME")
	private String userDisplayname;
	
	@Column(name="USER_BRANCH_CODE")
	private String userBranchCode;
	
	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to TblUsergroup
	@OneToMany(mappedBy="tblUser")
	private List<TblUserGroup> tblUsergroups;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_CREATED_BY")
	private TblUser userCreatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_CREATED_DATE")
	private Date userCreatedDate;
	
	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_UPDATED_BY")
	private TblUser userUpdatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_UPDATED_DATE")
	private Date userUpdatedDate;


	public TblUser() {
	}

	
	// getter and setter methods
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserDescription() {
		return this.userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public List<TblUserGroup> getTblUsergroups() {
		return this.tblUsergroups;
	}

	public void setTblUsergroups(List<TblUserGroup> tblUsergroups) {
		this.tblUsergroups = tblUsergroups;
	}

	public TblUserGroup addTblUsergroup(TblUserGroup tblUsergroup) {
		getTblUsergroups().add(tblUsergroup);
		tblUsergroup.setTblUser(this);

		return tblUsergroup;
	}

	public TblUserGroup removeTblUsergroup(TblUserGroup tblUsergroup) {
		getTblUsergroups().remove(tblUsergroup);
		tblUsergroup.setTblUser(null);

		return tblUsergroup;
	}

	public TblUser getUserCreatedBy() {
		return userCreatedBy;
	}

	public void setUserCreatedBy(TblUser userCreatedBy) {
		this.userCreatedBy = userCreatedBy;
	}

	public Date getUserCreatedDate() {
		return userCreatedDate;
	}

	public void setUserCreatedDate(Date userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}

	public TblUser getUserUpdatedBy() {
		return userUpdatedBy;
	}

	public void setUserUpdatedBy(TblUser userUpdatedBy) {
		this.userUpdatedBy = userUpdatedBy;
	}

	public Date getUserUpdatedDate() {
		return userUpdatedDate;
	}

	public void setUserUpdatedDate(Date userUpdatedDate) {
		this.userUpdatedDate = userUpdatedDate;
	}

	public String getUserDisplayname() {
		return userDisplayname;
	}

	public void setUserDisplayname(String userDisplayname) {
		this.userDisplayname = userDisplayname;
	}

	public String getUserBranchCode() {
		return userBranchCode;
	}

	public void setUserBranchCode(String userBranchCode) {
		this.userBranchCode = userBranchCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}