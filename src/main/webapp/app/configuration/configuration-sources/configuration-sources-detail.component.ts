import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';

import { Source, SourceService } from '../../shared';

@Component({
    selector: 'jhi-source-mgmt-detail',
    templateUrl: './configuration-sources-detail.component.html'
})
export class SourceMgmtDetailComponent implements OnInit, OnDestroy {

    source: Source;
    private subscription: Subscription;

    constructor(
        private sourceService: SourceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
    }

    load(id) {
        this.sourceService.find(id).subscribe((source) => {
            this.source = source;
        });
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
