import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { IGetRowsParams } from 'ag-grid';
import { FilesPagingResponse } from './files-paging-response';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { FileProperty } from './file-meta-response';

/**
 * Helps to obtain file information for a given submission.
 */
@Injectable()
export class FileService {
    private static readonly BASE_URL: string = 'http://localhost:9876/submissions/586283/files?';
    private static readonly META_URL: string = 'http://localhost:9876/submissions/586283/meta';

    constructor(private http: Http) {
    }

    /**
     * Obtain paging file information for the given rows parameters
     *
     * @param {IGetRowsParams} params query params as start row and end row.
     * @returns {Observable<FilesPagingResponse>} an observable object so user can subscribe to changes.
     */
    getFiles(params: IGetRowsParams): Observable<FilesPagingResponse> {
        const pageSize: number = params.endRow - params.startRow;
        const page: number = (pageSize / 100) - 1;

        let requestUrl = `${FileService.BASE_URL}page=${page}&size=${pageSize}`;

        if (params.sortModel.length > 0) {
            requestUrl = `${requestUrl}&orderBy=${params.sortModel[0].colId}_${params.sortModel[0].sort}`;
        }

        const keys = Object.keys(params.filterModel);
        if (keys.length > 0) {
            requestUrl = `${requestUrl}&filterBy=`;

            for (const key of keys) {
                const filter = params.filterModel[key];
                requestUrl = `${requestUrl}${key}${filter.type}${filter.filter},`;
            }

            requestUrl.substring(0, requestUrl.length - 2);
        }

        return this.http.get(requestUrl).map(res => res.json());
    }

    getMetadata(): Observable<FileProperty[]> {
        return this.http.get(FileService.META_URL).map(res => res.json());
    }

}
