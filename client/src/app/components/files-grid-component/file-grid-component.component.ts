import { Component, OnInit } from '@angular/core';
import { FileService } from '../../services/file-service.service';
import { ColDef, GridOptions } from 'ag-grid/main';
import { GridDataSource } from '../../common/grid-data-source';
import 'rxjs/add/operator/concatAll';
import 'rxjs/add/operator/toArray';
import { FileColumnDef } from './file-column-def';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Observable } from 'rxjs/Observable';

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
    dataSource: GridDataSource;
    columnDefs: ColDef[];

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
        this.route.paramMap.switchMap((params: ParamMap) => {
            const submissionId: string = params.get('id');
            this.dataSource = new GridDataSource(submissionId, this.fileService);
            return this.loadColumns(submissionId);
        })
        .subscribe(columns => this.columnDefs = columns);
    }

    loadColumns(submissionId: string): Observable<FileColumnDef[]> {
        return this.fileService.getMetadata(submissionId)
            .map(columnDefs => columnDefs.map(p => new FileColumnDef(p.name, p.id)));
    }

    onGridReady(params) {
        params.api.sizeColumnsToFit();
        this.gridOptions.api.setDatasource(this.dataSource);
    }
}

