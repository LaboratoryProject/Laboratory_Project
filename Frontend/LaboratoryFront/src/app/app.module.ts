import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
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

// Angular Material modules
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LaboratoireListComponent } from './list-laboratoire/list-laboratoire.component';
import { ListLaboratoireModule } from './list-laboratoire/list-laboratoire.module';
import { HttpClientModule } from '@angular/common/http';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { AnalyseListeComponent } from './analyse-liste/analyse-liste.component';
import { ListDossiersComponent } from './list-dossiers/list-dossiers.component';
import { PatientDialogComponent } from './list-dossiers/patient-dialog/patient-dialog.component';
import { AjouterPatientComponent } from './ajouter-patient/ajouter-patient.component';
import { AjouterDossierComponent } from './ajouter-dossier/ajouter-dossier.component';
import { OptionsAnalyseComponent } from './options-analyse/options-analyse.component';
import { ListPatientsComponent } from './list-patients/list-patients.component';
import { PatientModificationDialogComponent } from './list-patients/patient-modification-dialog/patient-modification-dialog.component';
import { SideBarSuperAdminComponent } from './side-bar-super-admin/side-bar-super-admin.component';
import { AjouterUtilisateurComponent } from './ajouter-utilisateur/ajouter-utilisateur.component';

import { initializeKeycloak } from './security-management/keycloak-init';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { TokenRefreshService } from './security-management/TokenRefreshService';
import { UserService } from './security-management/user.service';
import { ModifierUtilisateurComponent } from './modifier-utilisateur/modifier-utilisateur.component';
import { RechercherUtilisateurComponent } from './rechercher-utilisateur/rechercher-utilisateur.component';
import { ListeAdminComponent } from './liste-admin/liste-admin.component';
import { ModifierAdminDialogComponent } from './liste-admin/modifier-admin-dialog/modifier-admin-dialog.component';
import { ChatComponent } from './chat/chat.component';
import { AjouterAnalyseComponent } from './ajouter-analyse/ajouter-analyse.component';
import { AjouterEpreuveComponent } from './ajouter-epreuve/ajouter-epreuve.component';
import { ConsulterAnalyseComponent } from './consulter-analyse/consulter-analyse.component';
import {EpreuveListeComponent} from './epreuve-liste/epreuve-liste.component';
import { ModifierAnalyseComponent } from './modifier-analyse/modifier-analyse.component';
import { ModifierEpreuveComponent } from './modifier-epreuve/modifier-epreuve.component';
import { TestAnalyseListeComponent } from './test-analyse-liste/test-analyse-liste.component';


@NgModule({
  declarations: [
    AppComponent, // Only non-standalone components should be declared here
    HomeComponent,
    MainContentComponent,

   
  ],

    
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
    HttpClientModule,
    
    OptionsAnalyseComponent,
    
    AjouterPatientComponent,
    AjouterDossierComponent,
    ReactiveFormsModule, 

    // Import standalone components
    SidebarComponent,
    AddLaboratoireComponent,
    SidebarComponent,
    AddLaboratoireComponent,
    BrowserModule,
    AppRoutingModule,
    CommonModule,  
    LaboratoireListComponent,
    HttpClientModule ,
    MatCardModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatToolbarModule,
    MatSelectModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    AnalyseListeComponent,
    ListDossiersComponent,
    PatientDialogComponent,
    PatientModificationDialogComponent,
    SideBarSuperAdminComponent,
    KeycloakAngularModule,
    AjouterUtilisateurComponent,
    ModifierUtilisateurComponent,
    RechercherUtilisateurComponent,
    ListeAdminComponent,
    ModifierAdminDialogComponent,
    ChatComponent,
    AjouterAnalyseComponent,
    AjouterEpreuveComponent,
    ConsulterAnalyseComponent,
    EpreuveListeComponent,
    ModifierAnalyseComponent,
    ModifierEpreuveComponent,
    TestAnalyseListeComponent,
  ],
  providers: [
    provideClientHydration(withEventReplay()),
    provideAnimationsAsync(),
    TokenRefreshService,
    UserService,
    {provide: 'APP_INITIALIZER',
    useFactory: initializeKeycloak,
    multi: true,
    deps: [KeycloakService],} // Injectez KeycloakService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
