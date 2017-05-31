import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { BillModalService } from './bills-modal.service';
import { JhiLanguageHelper, Bill, BillService } from '../../shared';

@Component({
    selector: 'jhi-bill-mgmt-dialog',
    templateUrl: './configuration-bills-dialog.component.html'
})
export class BillMgmtDialogComponent implements OnInit {

    bill: Bill;
    isSaving: Boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private languageHelper: JhiLanguageHelper,
        private billService: BillService,
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
        if (this.bill.id !== null) {
            this.billService.update(this.bill).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
        } else {
            this.billService.create(this.bill).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
        }
    }

    private onSaveSuccess(result) {
        this.eventManager.broadcast({ name: 'billListModification', content: 'OK' });
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-bill-dialog',
    template: ''
})
export class BillDialogComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private billModalService: BillModalService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.billModalService.open(BillMgmtDialogComponent, params['id']);
            } else {
                this.modalRef = this.billModalService.open(BillMgmtDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
