package com.opty.cfp.service.dto;

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
	
	public SourceDTO(){
		
	}
	
	public SourceDTO(Long id, String name, boolean activated){
		this.id = id;
		this.name = name;
		this.setActivated(activated);
	}
	
	public SourceDTO(Source source){
		this(source.getId(), source.getName(), source.isActivated());
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
