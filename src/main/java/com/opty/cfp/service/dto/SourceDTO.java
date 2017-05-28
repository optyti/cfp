package com.opty.cfp.service.dto;

import java.time.Instant;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.opty.cfp.domain.Source;

/**
 * A DTO representing an source.
 */
public class SourceDTO {
	
	private Long id;
	
	@Size(max = 50)
	@NotBlank
	private String name;
	
	private boolean activated = false;
	
	private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

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

	private Instant lastModifiedDate;
	
	public SourceDTO(){
		
	}
	
	public SourceDTO(Long id, String name, boolean activated, String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate){
		this.id = id;
		this.name = name;
		this.activated = activated;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate= lastModifiedDate;
	}
	
	public SourceDTO(Source source){
		this(source.getId(), source.getName(), source.getActivated(), source.getCreatedBy(), 
				source.getCreatedDate(), source.getLastModifiedBy(), source.getLastModifiedDate());
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
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
}
