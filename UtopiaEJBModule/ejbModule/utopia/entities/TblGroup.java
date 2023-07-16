package utopia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_GROUP database table.
 * 
 */
@Entity
@Table(name="TBL_GROUP")
@NamedQuery(name="TblGroup.findAll", query="SELECT t FROM TblGroup t")
public class TblGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="GROUP_ID")
	private long groupId;

	@Column(name="GROUP_DESCRIPTION")
	private String groupDescription;

	@Column(name="GROUP_NAME")
	private String groupName;
	
	@Column(name="GROUP_STATUS")
	private Integer groupStatus;
	
	@Column(name="GROUP_DISPLAYNAME")
	private String groupDisplayname;

	//bi-directional many-to-one association to TblUsergroup
	@OneToMany(mappedBy="tblGroup")
	private List<TblUserGroup> tblUsergroups;
	
	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="GROUP_CREATED_BY")
	private TblUser groupCreatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GROUP_CREATED_DATE")
	private Date groupCreatedDate;
	
	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="GROUP_UPDATED_BY")
	private TblUser groupUpdatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GROUP_UPDATED_DATE")
	private Date groupUpdatedDate;

	public TblGroup() {
	}

	
	// getter and setter methods
	public long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupDescription() {
		return this.groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Integer getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(Integer groupStatus) {
		this.groupStatus = groupStatus;
	}

	public List<TblUserGroup> getTblUsergroups() {
		return this.tblUsergroups;
	}

	public void setTblUsergroups(List<TblUserGroup> tblUsergroups) {
		this.tblUsergroups = tblUsergroups;
	}

	public TblUserGroup addTblUsergroup(TblUserGroup tblUsergroup) {
		getTblUsergroups().add(tblUsergroup);
		tblUsergroup.setTblGroup(this);

		return tblUsergroup;
	}

	public TblUserGroup removeTblUsergroup(TblUserGroup tblUsergroup) {
		getTblUsergroups().remove(tblUsergroup);
		tblUsergroup.setTblGroup(null);

		return tblUsergroup;
	}

	public TblUser getGroupCreatedBy() {
		return groupCreatedBy;
	}

	public void setGroupCreatedBy(TblUser groupCreatedBy) {
		this.groupCreatedBy = groupCreatedBy;
	}

	public Date getGroupCreatedDate() {
		return groupCreatedDate;
	}

	public void setGroupCreatedDate(Date groupCreatedDate) {
		this.groupCreatedDate = groupCreatedDate;
	}

	public TblUser getGroupUpdatedBy() {
		return groupUpdatedBy;
	}

	public void setGroupUpdatedBy(TblUser groupUpdatedBy) {
		this.groupUpdatedBy = groupUpdatedBy;
	}

	public Date getGroupUpdatedDate() {
		return groupUpdatedDate;
	}

	public void setGroupUpdatedDate(Date groupUpdatedDate) {
		this.groupUpdatedDate = groupUpdatedDate;
	}

	public String getGroupDisplayname() {
		return groupDisplayname;
	}

	public void setGroupDisplayname(String groupDisplayname) {
		this.groupDisplayname = groupDisplayname;
	}
	
	
	
}