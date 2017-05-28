package com.opty.cfp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opty.cfp.config.Constants;
import com.opty.cfp.domain.Source;
import com.opty.cfp.domain.User;
import com.opty.cfp.repository.SourceRepository;
import com.opty.cfp.security.SecurityUtils;
import com.opty.cfp.service.dto.SourceDTO;

/**
 * Service class for managing sources.
 */
@Service
@Transactional
public class SourceService {

    private final Logger log = LoggerFactory.getLogger(SourceService.class);

    private final SourceRepository sourceRepository;
    


    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public Source createSource(String name) {

        Source newSource = new Source();
        newSource.setName(name);;
        newSource.setActivated(true);
        sourceRepository.save(newSource);
        log.debug("Created Information for Source: {}", newSource);
        return newSource;
    }

    public Source createSource(SourceDTO sourceDTO) {
        Source source = new Source();
        source.setName(sourceDTO.getName());
        source.setActivated(true);
        sourceRepository.save(source);
        log.debug("Created Information for Source: {}", source);
        return source;
    }

    public void updateSource(String name) {
        sourceRepository.findOneByName(SecurityUtils.getCurrentUserLogin()).ifPresent(source -> {
            source.setName(name);
            log.debug("Changed Information for Source: {}", source);
        });
    }

    public Optional<SourceDTO> updateSource(SourceDTO sourceDTO) {
        return Optional.of(sourceRepository
            .findOne(sourceDTO.getId()))
            .map(source -> {
                source.setName(sourceDTO.getName());
                source.setActivated(sourceDTO.isActivated());
                log.debug("Changed Information for Source: {}", source);
                return source;
            })
            .map(SourceDTO::new);
    }

    public void deleteSource(Long id) {
        sourceRepository.getSourceById(id).ifPresent(source -> {
            sourceRepository.delete(source);
            log.debug("Deleted Source: {}", source);
        });
    }
    
    @Transactional(readOnly = true)
    public Optional<Source> getSourceById(Long id) {
        return sourceRepository.getSourceById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Source> getSourceByName(String name) {
        return sourceRepository.findOneByName(name);
    }

    @Transactional(readOnly = true)
    public Page<SourceDTO> getAllManagedSources(Pageable pageable) {
        return sourceRepository.findAllByNameNot(pageable, Constants.ANONYMOUS_USER).map(SourceDTO::new);
    }

}
