import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { Bill, BillService } from '../../shared';

@Injectable()
export class BillModalService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private billService: BillService
    ) {}

    open(component: Component, id?: any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.billService.find(id).subscribe((bill) => this.billModalRef(component, bill));
        } else {
            return this.billModalRef(component, new Bill());
        }
    }

    billModalRef(component: Component, bill: Bill): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.bill = bill;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
