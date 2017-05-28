package com.opty.cfp.web.rest.vm;

import com.opty.cfp.service.dto.SourceDTO;

/**
 * View Model extending the SourceDTO, which is meant to be used in the source management UI.
 */
public class ManagedSourceVM extends SourceDTO{

    public ManagedSourceVM() {
    }

    public ManagedSourceVM(Long id, String name, boolean activated) {

        super(id, name, activated);

    }

}
