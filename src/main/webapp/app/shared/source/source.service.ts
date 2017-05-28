import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Source } from './source.model';
import { ResponseWrapper } from '../model/response-wrapper.model';
import { createRequestOption } from '../model/request-util';

@Injectable()
export class SourceService {
    private resourceUrlCreate = 'source/createSource';
    private resourceUrlUpdate = 'source/updateSource';
    private resourceUrlFind = 'source/getSource';
    private resourceUrlQuery = 'source/getAllSources';
    private resourceUrlDelete = 'source/deleteSource';

    constructor(private http: Http) { }

    create(source: Source): Observable<Response> {
        return this.http.post(this.resourceUrlCreate, source);
    }

    update(source: Source): Observable<Response> {
        return this.http.put(this.resourceUrlUpdate, source);
    }

    find(id: any): Observable<Source> {
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
