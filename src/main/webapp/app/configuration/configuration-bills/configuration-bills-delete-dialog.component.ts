import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Bill, BillService } from '../../shared';
import { BillModalService } from './bills-modal.service';

@Component({
    selector: 'jhi-bill-mgmt-delete-dialog',
    templateUrl: './configuration-bills-delete-dialog.component.html'
})
export class BillMgmtDeleteDialogComponent {

    bill: Bill;

    constructor(
        private billService: BillService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id) {
        this.billService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({ name: 'billListModification',
                content: 'Deleted a bill'});
            this.activeModal.dismiss(true);
        });
    }

}

@Component({
    selector: 'jhi-bill-delete-dialog',
    template: ''
})
export class BillDeleteDialogComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private billModalService: BillModalService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.billModalService.open(BillMgmtDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
