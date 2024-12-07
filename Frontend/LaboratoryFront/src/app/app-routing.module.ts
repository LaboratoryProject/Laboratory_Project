import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Importation des composants
import { AppComponent } from './app.component';
import { MainContentComponent } from './main-content/main-content.component';
import { AddLaboratoireComponent } from './add-laboratoire/add-laboratoire.component';
import { LaboratoireListComponent } from './list-laboratoire/list-laboratoire.component';
import { AjouterUtilisateurComponent } from './ajouter-utilisateur/ajouter-utilisateur.component';
import { SupprimerUtilisateurComponent } from './supprimer-utilisateur/supprimer-utilisateur.component';
import { ModifierUtilisateurComponent } from './modifier-utilisateur/modifier-utilisateur.component';
import { RechercherUtilisateurComponent } from './rechercher-utilisateur/rechercher-utilisateur.component';
import { AddAnalyseDialogComponent } from './add-analyse-dialog/add-analyse-dialog.component';
import { AnalyseDashboardComponent } from './analyse-dashboard/analyse-dashboard.component';
import { CreatePatientComponent } from './create-patient/create-patient.component';
import { PatientDossiersComponent } from './patient-dossiers/patient-dossiers.component';
import { HomeComponent } from './home/home.component';

// Définition des routes
const routes: Routes = [
  { path: '', component: MainContentComponent },  // Composant par défaut
  { path: 'ajouter-utilisateur', component: AjouterUtilisateurComponent },
  { path: 'supprimer-utilisateur', component: SupprimerUtilisateurComponent },
  { path: 'modifier-utilisateur', component: ModifierUtilisateurComponent },
  { path: 'rechercher-utilisateur', component: RechercherUtilisateurComponent },
  { path: 'home', component: HomeComponent },
  { path: 'add', component: AddLaboratoireComponent },
  { path: 'list', component: LaboratoireListComponent },

  // Routes pour les nouveaux composants
  { path: 'add-analyse', component: AddAnalyseDialogComponent },  // Route pour AddAnalyseDialog
  { path: 'analyse-dashboard', component: AnalyseDashboardComponent },  // Route pour AnalyseDashboard
  { path: 'create-patient', component: CreatePatientComponent },  // Route pour CreatePatient
  { path: 'patient-dossiers', component: PatientDossiersComponent },  // Route pour PatientDossiers

  { path: 'page', component: AppComponent },
  { path: '**', redirectTo: '' },  // Redirige vers l'accueil pour les routes invalides
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
