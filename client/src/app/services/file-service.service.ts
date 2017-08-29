import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { IGetRowsParams } from 'ag-grid';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { FileProperty } from './file-meta-response';
import { FilesPagingResponse } from './files-paging-response';

/**
 * Helps to obtain file information for a given submission.
 */
@Injectable()
export class FileService {
    private static readonly BASE_URL: string = environment.apiUrl +  'submissions/submissionId/files?';
    private static readonly META_URL: string = environment.apiUrl + 'submissions/submissionId/meta';

    constructor(private http: Http) {
    }

    /**
     * Obtain paging file information for the given rows parameters
     *
     * @param {IGetRowsParams} params query params as start row and end row.
     * @returns {Observable<FilesPagingResponse>} an observable object so user can subscribe to changes.
     */
    getFiles(submissionId: string, params: IGetRowsParams): Observable<FilesPagingResponse> {
        const pageSize: number = params.endRow - params.startRow;
        const page: number = (pageSize / 100) - 1;

        let requestUrl = `${FileService.BASE_URL.replace('submissionId', submissionId)}page=${page}&size=${pageSize}`;

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

    /**
     * Obtain the list of file properties to display in the user interface.
     *
     * @param {string} submissionId the id of the submission.
     * @returns {Observable<FileProperty[]>} the list of file properties to display in the user interface.
     */
    getMetadata(submissionId: string): Observable<FileProperty[]> {
        const url: string = FileService.META_URL.replace('submissionId', submissionId);
        const data: Observable<FileProperty[]> = this.http.get(url).map(res => res.json());
        return data;
    }

}
