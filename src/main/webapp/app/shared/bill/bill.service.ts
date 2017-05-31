import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Bill } from './bill.model';
import { ResponseWrapper } from '../model/response-wrapper.model';
import { createRequestOption } from '../model/request-util';

@Injectable()
export class BillService {
    private resourceUrlCreate = 'bill/createBill';
    private resourceUrlUpdate = 'bill/updateBill';
    private resourceUrlFind = 'bill/findBill';
    private resourceUrlQuery = 'bill/allBills';
    private resourceUrlDelete = 'bill/deleteBill';

    constructor(private http: Http) { }

    create(bill: Bill): Observable<Response> {
        return this.http.post(this.resourceUrlCreate, bill);
    }

    update(bill: Bill): Observable<Response> {
        return this.http.put(this.resourceUrlUpdate, bill);
    }

    find(id: any): Observable<Bill> {
        return this.http.get(`${this.resourceUrlFind}/${id}`).map((res: Response) => res.json());
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrlQuery, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: any): Observable<Response> {
        return this.http.delete(`${this.resourceUrlDelete}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse);
    }
}
