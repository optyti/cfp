import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { PaginationUtil } from 'ng-jhipster';

import { SourceMgmtComponent } from './configuration-sources.component';
import { SourceMgmtDetailComponent } from './configuration-sources-detail.component';
import { SourceDialogComponent } from './configuration-sources-dialog.component';
import { SourceDeleteDialogComponent } from './configuration-sources-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class SourceResolve implements CanActivate {

    constructor(private principal: Principal) { }

    canActivate() {
        return this.principal.identity().then((account) => this.principal.hasAnyAuthority(['ROLE_ADMIN']));
    }
}

@Injectable()
export class SourceResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: PaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
        };
    }
}

export const sourceMgmtRoute: Routes = [
    {
        path: 'configuration-sources',
        component: SourceMgmtComponent,
        resolve: {
            'pagingParams': SourceResolvePagingParams
        },
        data: {
            pageTitle: 'configurartionSource.home.title'
        }
    },
    {
        path: 'configuration-sources/:id',
        component: SourceMgmtDetailComponent,
        data: {
            pageTitle: 'configurartionSource.home.title'
        }
    }
];

export const sourceDialogRoute: Routes = [
    {
        path: 'configuration-sources-new',
        component: SourceDialogComponent,
        outlet: 'popup'
    },
    {
        path: 'configuration-sources/:id/edit',
        component: SourceDialogComponent,
        outlet: 'popup'
    },
    {
        path: 'configuration-sources/:id/delete',
        component: SourceDeleteDialogComponent,
        outlet: 'popup'
    }
];
