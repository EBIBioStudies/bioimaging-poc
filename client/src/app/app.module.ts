import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { AgGridModule } from 'ag-grid-angular/main';
import { AppComponent } from './app.component';
import { FileGridComponent } from './components/files-grid-component/file-grid-component.component';
import { FileService } from './services/file-service.service';

const appRoutes: Routes = [
    {path: 'submissions/:id', component: FileGridComponent},
    {path: '', pathMatch: 'full', redirectTo: 'submissions/586283'}];

@NgModule({
    declarations: [AppComponent, FileGridComponent],
    imports: [
        RouterModule.forRoot(appRoutes, {enableTracing: true}),
        BrowserModule,
        HttpModule,
        AgGridModule.withComponents([])],
    providers: [FileService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
