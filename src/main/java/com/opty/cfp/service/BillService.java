package com.opty.cfp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opty.cfp.config.Constants;
import com.opty.cfp.domain.Bill;
import com.opty.cfp.domain.Source;
import com.opty.cfp.repository.BillRepository;
import com.opty.cfp.security.SecurityUtils;
import com.opty.cfp.service.dto.BillDTO;
import com.opty.cfp.service.dto.SourceDTO;

/**
 * Service class for managing sources.
 */
@Service
@Transactional
public class BillService {

    private final Logger log = LoggerFactory.getLogger(BillService.class);

    private final BillRepository billRepository;
    


    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill createBill(String name) {

    	Bill newBill = new Bill();
    	newBill.setName(name);;
    	newBill.setActivated(true);
    	billRepository.save(newBill);
        log.debug("Created Information for Bill: {}", newBill);
        return newBill;
    }

    public Bill createBill(BillDTO billDTO) {
    	Bill bill = new Bill();
    	bill.setName(billDTO.getName());
    	bill.setActivated(true);
    	billRepository.save(bill);
        log.debug("Created Information for Bill: {}", bill);
        return bill;
    }

    public void updateBill(String name) {
    	billRepository.findOneByName(SecurityUtils.getCurrentUserLogin()).ifPresent(bill -> {
    		bill.setName(name);
            log.debug("Changed Information for Bill: {}", bill);
        });
    }

    public Optional<BillDTO> updateBill(BillDTO billDTO) {
        return Optional.of(billRepository
            .findOne(billDTO.getId()))
            .map(bill -> {
                bill.setName(billDTO.getName());
                bill.setActivated(billDTO.getActivated());
                log.debug("Changed Information for Bill: {}", bill);
                return bill;
            })
            .map(BillDTO::new);
    }

    public void deleteSource(Long id) {
        billRepository.getBillById(id).ifPresent(bill -> {
            billRepository.delete(bill);
            log.debug("Deleted Bill: {}", bill);
        });
    }
    
    @Transactional(readOnly = true)
    public Optional<Bill> getBillById(Long id) {
        return billRepository.getBillById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Bill> getBillByName(String name) {
        return billRepository.findOneByName(name);
    }

    @Transactional(readOnly = true)
    public Page<BillDTO> getAllManagedBill(Pageable pageable) {
        return billRepository.findAllByNameNot(pageable, Constants.ANONYMOUS_USER).map(BillDTO::new);
    }

}
