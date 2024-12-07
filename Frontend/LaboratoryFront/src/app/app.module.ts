import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { MainContentComponent } from './main-content/main-content.component';

// Import standalone components
import { SidebarComponent } from './sidebar/sidebar.component';
import { AddLaboratoireComponent } from './add-laboratoire/add-laboratoire.component';
import { AjouterUtilisateurComponent } from './ajouter-utilisateur/ajouter-utilisateur.component';
import { SupprimerUtilisateurComponent } from './supprimer-utilisateur/supprimer-utilisateur.component';
import { ModifierUtilisateurComponent } from './modifier-utilisateur/modifier-utilisateur.component';
import { RechercherUtilisateurComponent } from './rechercher-utilisateur/rechercher-utilisateur.component';

// Angular Material modules
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LaboratoireListComponent } from './list-laboratoire/list-laboratoire.component';
import { ListLaboratoireModule } from './list-laboratoire/list-laboratoire.module';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

<<<<<<< HEAD
=======


>>>>>>> d7eb7f9e8985a72a66b1ac0e55bd95720a87ee2a
@NgModule({
  declarations: [
    AppComponent, // Only non-standalone components should be declared here
    HomeComponent,
<<<<<<< HEAD
    MainContentComponent
  ],
=======
    MainContentComponent,
    ],
>>>>>>> d7eb7f9e8985a72a66b1ac0e55bd95720a87ee2a
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatIconModule,
    MatFormFieldModule,
    CommonModule,
    FormsModule,
<<<<<<< HEAD
    HttpClientModule,

    // Import standalone components
    SidebarComponent,
    AddLaboratoireComponent,
    AjouterUtilisateurComponent,
    SupprimerUtilisateurComponent,
    ModifierUtilisateurComponent,
    RechercherUtilisateurComponent
=======
    SidebarComponent,
    AddLaboratoireComponent,
    BrowserModule,
    AppRoutingModule,
    ListLaboratoireModule,
    CommonModule,  
    LaboratoireListComponent,
    RechercherUtilisateurComponent,
    HttpClientModule ,
>>>>>>> d7eb7f9e8985a72a66b1ac0e55bd95720a87ee2a
  ],
  providers: [
    provideClientHydration(withEventReplay()),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
