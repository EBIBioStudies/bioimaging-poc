import { Component } from '@angular/core';
import { FileService } from '../../services/file-service.service';
import { ColDef, GridOptions } from 'ag-grid/main';
import { GridDataSource } from '../../common/grid-data-source';
import 'rxjs/add/operator/concatAll';
import 'rxjs/add/operator/toArray';
import { FileColumnDef } from './file-column-def';

/**
 * Files grid component declaration. Contains grid configuration options.
 *
 */
@Component({
    selector: 'app-my-grid-application',
    templateUrl: './file-grid-component.html'
})
export class FileGridComponent {
    gridOptions: GridOptions;
    columnDefs: ColDef[];

    constructor(private fileService: FileService) {
        this.gridOptions = <GridOptions>{
            enableServerSideSorting: true,
            enableServerSideFilter: true,
            enableColResize: true,
            floatingFilter: true,
            pagination: true,
            paginationPageSize: 50,
            rowModelType: 'infinite'
        };

        this.columnDefs = this.fileService.getMetadata()
            .concatAll().map(prop => new FileColumnDef(prop.name, prop.id));
    }

    onGridReady(params) {
        params.api.sizeColumnsToFit();
        this.gridOptions.api.setDatasource(new GridDataSource(this.fileService));
    }
}

