import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { Source, SourceService } from '../../shared';

@Injectable()
export class SourceModalService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private sourceService: SourceService
    ) {}

    open(component: Component, id?: any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.sourceService.find(id).subscribe((source) => this.sourceModalRef(component, source));
        } else {
            return this.sourceModalRef(component, new Source());
        }
    }

    sourceModalRef(component: Component, source: Source): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.source = source;
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
