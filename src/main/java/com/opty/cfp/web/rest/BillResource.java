package com.opty.cfp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.opty.cfp.domain.Bill;
import com.opty.cfp.domain.Source;
import com.opty.cfp.repository.BillRepository;
import com.opty.cfp.security.AuthoritiesConstants;
import com.opty.cfp.service.BillService;
import com.opty.cfp.service.MailService;
import com.opty.cfp.service.dto.BillDTO;
import com.opty.cfp.service.dto.SourceDTO;
import com.opty.cfp.web.rest.util.HeaderUtil;
import com.opty.cfp.web.rest.util.PaginationUtil;
import com.opty.cfp.web.rest.vm.ManagedBillVM;
import com.opty.cfp.web.rest.vm.ManagedSourceVM;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/bill")
public class BillResource {

    private final Logger log = LoggerFactory.getLogger(BillResource.class);

    private static final String ENTITY_NAME = "Bill";

    private final BillRepository billRepository;

    @SuppressWarnings("unused")
	private final MailService mailService;

    private final BillService billService;

    public BillResource(BillRepository billRepository, MailService mailService,
            BillService billService) {

        this.billRepository = billRepository;
        this.mailService = mailService;
        this.billService = billService;
    }

    @PostMapping("/createBill")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity createSource(@Valid @RequestBody ManagedBillVM managedBillVM) throws URISyntaxException {
        log.debug("REST request to save Bill : {}", managedBillVM);

        if (managedBillVM.getId() != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new source cannot already have an ID"))
                .body(null);
        // Lowercase the user login before comparing with database
        } else if (billRepository.findOneByName(managedBillVM.getName().toLowerCase()).isPresent()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "fonteexists", "Name already in use"))
                .body(null);
        } else {
            Bill newBill = billService.createBill(managedBillVM);
//            mailService.sendCreationEmail(newBill);
            return ResponseEntity.created(new URI("/api/bills/" + newBill.getName()))
                .headers(HeaderUtil.createAlert( "configurationBill.created", newBill.getName()))
                .body(newBill);
        }
    }
    
    @GetMapping("/findBill/{id}")
    @Timed
    public ResponseEntity<BillDTO> getSource(@PathVariable Long id) {
        log.debug("REST request to get Bill: {}", id);
        return ResponseUtil.wrapOrNotFound(
            billService.getBillById(id)
                .map(BillDTO::new));
    }

    @PutMapping("/updateBill")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<BillDTO> updateBill(@Valid @RequestBody ManagedBillVM managedBillVM) {
        log.debug("REST request to update Bill : {}", managedBillVM);
        Optional<Bill> existingBill = billRepository.findOneByName(managedBillVM.getName());
        existingBill = billRepository.findOneByName(managedBillVM.getName().toLowerCase());
        if (existingBill.isPresent() && (!existingBill.get().getId().equals(managedBillVM.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "fonteexists", "Bill already in use")).body(null);
        }
        Optional<BillDTO> updatedBill = billService.updateBill(managedBillVM);

        return ResponseUtil.wrapOrNotFound(updatedBill,
            HeaderUtil.createAlert("configurationBill.updated", managedBillVM.getName()));
    }

    @GetMapping("/allBills")
    @Timed
    public ResponseEntity<List<BillDTO>> getAllBills(@ApiParam Pageable pageable) {
        final Page<BillDTO> page = billService.getAllManagedBill(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/bill/allBills");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBill/{id}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        log.debug("REST request to delete Bill: {}", id);
        billService.deleteSource(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "configurationBill.deleted", id.toString())).build();
    }
}