import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';

import { Bill, BillService } from '../../shared';

@Component({
    selector: 'jhi-bill-mgmt-detail',
    templateUrl: './configuration-bills-detail.component.html'
})
export class BillMgmtDetailComponent implements OnInit, OnDestroy {

    bill: Bill;
    private subscription: Subscription;

    constructor(
        private billService: BillService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
    }

    load(id) {
        this.billService.find(id).subscribe((bill) => {
            this.bill = bill;
        });
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
