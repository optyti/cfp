package com.opty.cfp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.opty.cfp.domain.Source;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface SourceRepository extends JpaRepository<Source, Long> {

    Optional<Source> findOneByName(String name);
    
    Page<Source> findAllByNameNot(Pageable pageable, String name);
    
    Optional<Source> getSourceById(Long id);

}
