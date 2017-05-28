import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Source, SourceService } from '../../shared';
import { SourceModalService } from './source-modal.service';

@Component({
    selector: 'jhi-source-mgmt-delete-dialog',
    templateUrl: './configuration-sources-delete-dialog.component.html'
})
export class SourceMgmtDeleteDialogComponent {

    source: Source;

    constructor(
        private sourceService: SourceService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id) {
        this.sourceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({ name: 'sourceListModification',
                content: 'Deleted a source'});
            this.activeModal.dismiss(true);
        });
    }

}

@Component({
    selector: 'jhi-source-delete-dialog',
    template: ''
})
export class SourceDeleteDialogComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sourceModalService: SourceModalService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.sourceModalService.open(SourceMgmtDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
