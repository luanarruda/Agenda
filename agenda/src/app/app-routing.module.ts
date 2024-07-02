import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContatosComponent } from './contatos/contatos.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path: 'contatos', component: ContatosComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
