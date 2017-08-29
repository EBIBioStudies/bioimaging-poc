import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ColDef, GridOptions } from 'ag-grid/main';
import 'rxjs/add/operator/concatAll';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/toArray';
import { GridDataSource } from '../../common/grid-data-source';
import { FileService } from '../../services/file-service.service';
import { FileColumnDef } from './file-column-def';

/**
 * Files grid component declaration. Contains grid configuration options.
 *
 */
@Component({
    selector: 'app-my-grid-application',
    templateUrl: './file-grid-component.html'
})
export class FileGridComponent implements OnInit {
    gridOptions: GridOptions;
    columnDefs: ColDef[];
    dataSource: GridDataSource;

    constructor(
        private fileService: FileService,
        private route: ActivatedRoute) {

        this.gridOptions = <GridOptions>{
            enableServerSideSorting: true,
            enableServerSideFilter: true,
            enableColResize: true,
            floatingFilter: true,
            pagination: true,
            paginationPageSize: 50,
            rowModelType: 'infinite'
        };
    }

    ngOnInit(): void {
        this.route.paramMap.switchMap((params: ParamMap) =>
            this.initSubmissionData(params.get('id'))).subscribe(columns => {
                this.columnDefs = columns;
            });
    }

    onGridReady(params) {
        this.gridOptions.api.setDatasource(this.dataSource);
        this.gridOptions.api.sizeColumnsToFit();
    }


    private initSubmissionData(submissionId: string) {
        this.dataSource = new GridDataSource(submissionId, this.fileService);
        return this.fileService.getMetadata(submissionId).map(columnDefs => columnDefs.map(p => new FileColumnDef(p.name, p.id)));
    }
}

