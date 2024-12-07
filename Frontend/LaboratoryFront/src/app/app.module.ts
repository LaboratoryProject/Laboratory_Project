import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
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
import { AddAnalyseDialogComponent } from './add-analyse-dialog/add-analyse-dialog.component';
import { AnalyseDashboardComponent } from './analyse-dashboard/analyse-dashboard.component';
import { CreatePatientComponent } from './create-patient/create-patient.component';
import { PatientDossiersComponent } from './patient-dossiers/patient-dossiers.component';

// Services
import { PatientService } from './services/patient.service';
import { AnalyseService } from './services/analyse.service';
import { LaboratoireService } from './services/laboratoire.service';

// Models
import { Analyse } from './models/analyse.model';
import { Patient } from './models/patient.model';
import { Laboratoire } from './models/laboratoire.model';

// Angular Material modules
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

// Other modules
import { ListLaboratoireModule } from './list-laboratoire/list-laboratoire.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MainContentComponent,
    AddLaboratoireComponent,
    AjouterUtilisateurComponent,
    SupprimerUtilisateurComponent,
    ModifierUtilisateurComponent,
    RechercherUtilisateurComponent,
    AddAnalyseDialogComponent,
    AnalyseDashboardComponent,
    CreatePatientComponent,
    PatientDossiersComponent,
    SidebarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    ListLaboratoireModule,  // Other module
  ],
  providers: [
    PatientService,
    AnalyseService,
    LaboratoireService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
