import { Routes } from '@angular/router';

import {
    sourceMgmtRoute,
    sourceDialogRoute
} from './';

import { UserRouteAccessService } from '../shared';

const ADMIN_ROUTES = [
    ...sourceMgmtRoute
];

export const configurationState: Routes = [{
    path: '',
    data: {
        authorities: ['ROLE_ADMIN']
    },
    canActivate: [UserRouteAccessService],
    children: ADMIN_ROUTES
},
    ...sourceDialogRoute
];
