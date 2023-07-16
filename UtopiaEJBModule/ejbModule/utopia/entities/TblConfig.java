package utopia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_CONFIG database table.
 * 
 */
@Entity
@Table(name="TBL_CONFIG")
@NamedQuery(name="TblConfig.findAll", query="SELECT t FROM TblConfig t")
public class TblConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="CNF_ID")
	private long cnfId;

	@Column(name="CNF_DATE")
	private Date cnfDate;

	@Column(name="CNF_DESCRIPTION")
	private String cnfDescription;

	@Column(name="CNF_NAME")
	private String cnfName;

	@Column(name="CNF_STATE")
	private BigDecimal cnfState;

	@Column(name="CNF_TYPE")
	private String cnfType;

	public TblConfig() {
	}

	
	// getter and setter methods
	public long getCnfId() {
		return this.cnfId;
	}

	public void setCnfId(long cnfId) {
		this.cnfId = cnfId;
	}

	public Date getCnfDate() {
		return this.cnfDate;
	}

	public void setCnfDate(Date cnfDate) {
		this.cnfDate = cnfDate;
	}

	public String getCnfDescription() {
		return this.cnfDescription;
	}

	public void setCnfDescription(String cnfDescription) {
		this.cnfDescription = cnfDescription;
	}

	public String getCnfName() {
		return this.cnfName;
	}

	public void setCnfName(String cnfName) {
		this.cnfName = cnfName;
	}

	public BigDecimal getCnfState() {
		return this.cnfState;
	}

	public void setCnfState(BigDecimal cnfState) {
		this.cnfState = cnfState;
	}

	public String getCnfType() {
		return this.cnfType;
	}

	public void setCnfType(String cnfType) {
		this.cnfType = cnfType;
	}

}