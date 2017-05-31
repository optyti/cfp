package com.opty.cfp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.opty.cfp.domain.Bill;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findOneByName(String name);
    
    Page<Bill> findAllByNameNot(Pageable pageable, String name);
    
    Optional<Bill> getBillById(Long id);

}
