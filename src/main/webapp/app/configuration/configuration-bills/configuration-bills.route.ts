import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { PaginationUtil } from 'ng-jhipster';

import { BillMgmtComponent } from './configuration-bills.component';
import { BillMgmtDetailComponent } from './configuration-bills-detail.component';
import { BillDialogComponent } from './configuration-bills-dialog.component';
import { BillDeleteDialogComponent } from './configuration-bills-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class BillResolve implements CanActivate {

    constructor(private principal: Principal) { }

    canActivate() {
        return this.principal.identity().then((account) => this.principal.hasAnyAuthority(['ROLE_ADMIN']));
    }
}

@Injectable()
export class BillResolvePagingParams implements Resolve<any> {

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

export const billMgmtRoute: Routes = [
    {
        path: 'configuration-bills',
        component: BillMgmtComponent,
        resolve: {
            'pagingParams': BillResolvePagingParams
        },
        data: {
            pageTitle: 'configurartionBill.home.title'
        }
    },
    {
        path: 'configuration-bills/:id',
        component: BillMgmtDetailComponent,
        data: {
            pageTitle: 'configurartionBill.home.title'
        }
    }
];

export const billDialogRoute: Routes = [
    {
        path: 'configuration-bills-new',
        component: BillDialogComponent,
        outlet: 'popup'
    },
    {
        path: 'configuration-bills/:id/edit',
        component: BillDialogComponent,
        outlet: 'popup'
    },
    {
        path: 'configuration-bills/:id/delete',
        component: BillDeleteDialogComponent,
        outlet: 'popup'
    }
];
