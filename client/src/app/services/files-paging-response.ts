/**
 * Represent rest end point rest service response.
 */
export interface FilesPagingResponse {
    totalElements: number;
    totalPages: number;
    pageNumber: number;
    pageSize: number;
    files: any[];
}
