import { ColDef } from 'ag-grid';

export class FileColumnDef implements ColDef {
    headerName?: string;
    field?: string;
    filterParams?: any;

    constructor(columnName: string, columnId: string) {
        this.headerName = columnName;
        this.field = columnId;
        this.filterParams = {debounceMs: 1000, filterOptions: ['equals', 'contains'], defaultOption: 'equals'};
    }
}
