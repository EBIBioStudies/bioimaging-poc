import { IDatasource, IGetRowsParams } from 'ag-grid';
import { FileService } from '../services/file-service.service';

/**
 * Implementation of grid data source
 */
export class GridDataSource implements IDatasource {

    constructor(private submissionId: string, private fileService: FileService) {
    }

    public getRows(params: IGetRowsParams) {
        console.log('params: ' + params.startRow);

        this.fileService.getFiles(this.submissionId, params).subscribe(
            data => params.successCallback(data.files, data.totalElements));
    }
}
