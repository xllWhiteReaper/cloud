import { Routes } from '@angular/router';
import { FilesComponent } from './files.component';
import { TestComponent } from './test/test.component';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: FilesComponent,
  },
  {
    path: 'test',
    component: TestComponent,
  },
];
