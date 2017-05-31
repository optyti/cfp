import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CfpSharedModule } from '../shared';
/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

import {
    configurationState,
    SourceMgmtComponent,
    SourceDialogComponent,
    SourceDeleteDialogComponent,
    SourceMgmtDetailComponent,
    SourceMgmtDialogComponent,
    SourceMgmtDeleteDialogComponent,
    SourceResolvePagingParams,
    SourceResolve,
    SourceModalService,
    BillMgmtComponent,
    BillDialogComponent,
    BillDeleteDialogComponent,
    BillMgmtDetailComponent,
    BillMgmtDialogComponent,
    BillMgmtDeleteDialogComponent,
    BillResolvePagingParams,
    BillResolve,
    BillModalService
} from './';

@NgModule({
    imports: [
        CfpSharedModule,
        RouterModule.forRoot(configurationState, { useHash: true }),
        /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    ],
    declarations: [
        SourceMgmtComponent,
        SourceDialogComponent,
        SourceDeleteDialogComponent,
        SourceMgmtDetailComponent,
        SourceMgmtDialogComponent,
        SourceMgmtDeleteDialogComponent,
        BillMgmtComponent,
        BillDialogComponent,
        BillDeleteDialogComponent,
        BillMgmtDetailComponent,
        BillMgmtDialogComponent,
        BillMgmtDeleteDialogComponent
    ],
    entryComponents: [
        SourceMgmtDialogComponent,
        SourceMgmtDeleteDialogComponent,
        BillMgmtDialogComponent,
        BillMgmtDeleteDialogComponent
    ],
    providers: [
        SourceResolvePagingParams,
        SourceResolve,
        SourceModalService,
        BillResolvePagingParams,
        BillResolve,
        BillModalService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CfpConfigurationModule {}
