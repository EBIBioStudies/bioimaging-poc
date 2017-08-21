import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AgGridModule } from 'ag-grid-angular/main';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { FileGridComponent } from './components/files-grid-component/file-grid-component.component';
import { FileService } from './services/file-service.service';

@NgModule({
    declarations: [AppComponent, FileGridComponent],
    imports: [BrowserModule, HttpModule, AgGridModule.withComponents([])],
    providers: [FileService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
