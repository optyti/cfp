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
    SourceModalService
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
    ],
    entryComponents: [
        SourceMgmtDialogComponent,
        SourceMgmtDeleteDialogComponent,
    ],
    providers: [
        SourceResolvePagingParams,
        SourceResolve,
        SourceModalService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CfpConfigurationModule {}
