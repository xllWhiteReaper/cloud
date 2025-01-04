import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TestComponent } from './test/test.component';
import { FilesComponent } from './files.component';
import { provideRouter } from '@angular/router';
import { routes } from './files.router';

@NgModule({
  declarations: [TestComponent, FilesComponent],
  providers: [provideRouter(routes)],
  imports: [CommonModule],
})
export class FilesModule {}
