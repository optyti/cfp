package com.opty.cfp.service.dto;

import java.time.Instant;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.opty.cfp.domain.Bill;

public class BillDTO {
	
private Long id;
	
	@Size(max = 50)
	@NotBlank
	private String name;
	
	private boolean activated = false;
	
	private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;
    
    private Instant lastModifiedDate;
    
    private BillDTO billMaster;
    
    public BillDTO(){
		
	}
	
	public BillDTO(Long id, String name, boolean activated, String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate){
		this.id = id;
		this.name = name;
		this.activated = activated;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.setLastModifiedDate(lastModifiedDate);
	}
	
	public BillDTO(Bill bill){
		this(bill.getId(), bill.getName(), bill.getActivated(), bill.getCreatedBy(), 
				bill.getCreatedDate(), bill.getLastModifiedBy(), bill.getLastModifiedDate());
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the activated
	 */
	public boolean getActivated() {
		return activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Instant getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the billMaster
	 */
	public BillDTO getBillMaster() {
		return billMaster;
	}

	/**
	 * @param billMaster the billMaster to set
	 */
	public void setBillMaster(BillDTO billMaster) {
		this.billMaster = billMaster;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
