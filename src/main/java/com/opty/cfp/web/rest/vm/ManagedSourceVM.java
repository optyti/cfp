package com.opty.cfp.web.rest.vm;

import java.time.Instant;

import com.opty.cfp.service.dto.SourceDTO;

/**
 * View Model extending the SourceDTO, which is meant to be used in the source management UI.
 */
public class ManagedSourceVM extends SourceDTO{

    public ManagedSourceVM() {
    }

    public ManagedSourceVM(Long id, String name, boolean activated, String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate) {

        super(id, name, activated, createdBy, createdDate, lastModifiedBy, lastModifiedDate);

    }

}
