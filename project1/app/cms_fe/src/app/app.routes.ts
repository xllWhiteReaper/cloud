import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'files',
    loadChildren: () =>
      import('../files/files.module').then((c) => c.FilesModule),
  },
  {
    path: '**',
    redirectTo: '',
  },
];
