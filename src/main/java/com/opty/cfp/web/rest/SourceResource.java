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
import com.opty.cfp.domain.Source;
import com.opty.cfp.repository.SourceRepository;
import com.opty.cfp.security.AuthoritiesConstants;
import com.opty.cfp.service.MailService;
import com.opty.cfp.service.SourceService;
import com.opty.cfp.service.dto.SourceDTO;
import com.opty.cfp.web.rest.util.HeaderUtil;
import com.opty.cfp.web.rest.util.PaginationUtil;
import com.opty.cfp.web.rest.vm.ManagedSourceVM;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/source")
public class SourceResource {

    private final Logger log = LoggerFactory.getLogger(SourceResource.class);

    private static final String ENTITY_NAME = "Source";

    private final SourceRepository sourceRepository;

    @SuppressWarnings("unused")
	private final MailService mailService;

    private final SourceService sourceService;

    public SourceResource(SourceRepository sourceRepository, MailService mailService,
            SourceService sourceService) {

        this.sourceRepository = sourceRepository;
        this.mailService = mailService;
        this.sourceService = sourceService;
    }

    @PostMapping("/createSource")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity createSource(@Valid @RequestBody ManagedSourceVM managedSourceVM) throws URISyntaxException {
        log.debug("REST request to save Source : {}", managedSourceVM);

        if (managedSourceVM.getId() != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new source cannot already have an ID"))
                .body(null);
        // Lowercase the user login before comparing with database
        } else if (sourceRepository.findOneByName(managedSourceVM.getName().toLowerCase()).isPresent()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "nameexists", "Name already in use"))
                .body(null);
        } else {
            Source newSource = sourceService.createSource(managedSourceVM);
//            mailService.sendCreationEmail(newSource);
            return ResponseEntity.created(new URI("/api/sources/" + newSource.getName()))
                .headers(HeaderUtil.createAlert( "configurationSource.created", newSource.getName()))
                .body(newSource);
        }
    }
    
    @GetMapping("/getSource")
    @Timed
    public ResponseEntity<SourceDTO> getSource(@PathVariable Long id) {
        log.debug("REST request to get Source: {}", id);
        System.out.println(id);
        return ResponseUtil.wrapOrNotFound(
            sourceService.getSourceById(id)
                .map(SourceDTO::new));
    }

    @PutMapping("/updateSource")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<SourceDTO> updateSource(@Valid @RequestBody ManagedSourceVM managedSourceVM) {
        log.debug("REST request to update Source : {}", managedSourceVM);
        Optional<Source> existingSource = sourceRepository.findOneByName(managedSourceVM.getName());
        existingSource = sourceRepository.findOneByName(managedSourceVM.getName().toLowerCase());
        if (existingSource.isPresent() && (!existingSource.get().getId().equals(managedSourceVM.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "sourceexists", "Source already in use")).body(null);
        }
        Optional<SourceDTO> updatedSource = sourceService.updateSource(managedSourceVM);

        return ResponseUtil.wrapOrNotFound(updatedSource,
            HeaderUtil.createAlert("configurationSource.updated", managedSourceVM.getName()));
    }

    @GetMapping("/getAllSources")
    @Timed
    public ResponseEntity<List<SourceDTO>> getAllSources(@ApiParam Pageable pageable) {
        final Page<SourceDTO> page = sourceService.getAllManagedSources(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSource")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        log.debug("REST request to delete Source: {}", id);
        sourceService.deleteSource(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "configurationSource.deleted", id.toString())).build();
    }
}