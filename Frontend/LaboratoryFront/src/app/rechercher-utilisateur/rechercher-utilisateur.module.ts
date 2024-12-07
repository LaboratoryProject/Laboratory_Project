import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RechercherUtilisateurComponent } from './rechercher-utilisateur.component';


@NgModule({
  declarations: [
    RechercherUtilisateurComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
  ],
  exports: [
    RechercherUtilisateurComponent
  ]
})
export class RechercherUtilisateurModule { }