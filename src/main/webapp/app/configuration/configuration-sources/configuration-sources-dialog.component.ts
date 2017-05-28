import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { SourceModalService } from './source-modal.service';
import { JhiLanguageHelper, Source, SourceService } from '../../shared';

@Component({
    selector: 'jhi-source-mgmt-dialog',
    templateUrl: './configuration-sources-dialog.component.html'
})
export class SourceMgmtDialogComponent implements OnInit {

    source: Source;
    isSaving: Boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private languageHelper: JhiLanguageHelper,
        private sourceService: SourceService,
        private eventManager: EventManager
    ) {}

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.source.id !== null) {
            this.sourceService.update(this.source).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
        } else {
            this.sourceService.create(this.source).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
        }
    }

    private onSaveSuccess(result) {
        this.eventManager.broadcast({ name: 'sourceListModification', content: 'OK' });
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-source-dialog',
    template: ''
})
export class SourceDialogComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sourceModalService: SourceModalService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.sourceModalService.open(SourceMgmtDialogComponent, params['id']);
            } else {
                this.modalRef = this.sourceModalService.open(SourceMgmtDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
