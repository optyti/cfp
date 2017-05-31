package com.opty.cfp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="tb_bill")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bill extends AbstractAuditingEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7948350660632793728L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
	private String name;
	
	@NotNull
	@Column(nullable = false)
	private boolean activated = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_master", insertable = true, updatable = true, nullable = true)
	private Bill billMaster;

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
	 * @return the billMaster
	 */
	public Bill getBillMaster() {
		return billMaster;
	}

	/**
	 * @param billMaster the billMaster to set
	 */
	public void setBillMaster(Bill billMaster) {
		this.billMaster = billMaster;
	}
	
	
	

}
