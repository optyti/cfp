import { Routes } from '@angular/router';

import {
    sourceMgmtRoute,
    sourceDialogRoute,
    billMgmtRoute,
    billDialogRoute
} from './';

import { UserRouteAccessService } from '../shared';

const ADMIN_ROUTES = [
    ...sourceMgmtRoute,
    ...billMgmtRoute
];

export const configurationState: Routes = [{
    path: '',
    data: {
        authorities: ['ROLE_ADMIN']
    },
    canActivate: [UserRouteAccessService],
    children: ADMIN_ROUTES
},
    ...sourceDialogRoute,
    ...billDialogRoute
];
