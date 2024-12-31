import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component'; // Importez le composant Home
import { AppComponent } from './app.component';
import { MainContentComponent } from './main-content/main-content.component';
import { AddLaboratoireComponent } from './add-laboratoire/add-laboratoire.component';



import { LaboratoireListComponent } from './list-laboratoire/list-laboratoire.component';
import { AnalyseListeComponent } from './analyse-liste/analyse-liste.component';
import { ListDossiersComponent } from './list-dossiers/list-dossiers.component';
import { AjouterPatientComponent } from './ajouter-patient/ajouter-patient.component';
import { AjouterDossierComponent } from './ajouter-dossier/ajouter-dossier.component';
import { OptionsAnalyseComponent } from './options-analyse/options-analyse.component';
import { ListPatientsComponent } from './list-patients/list-patients.component';
import { PatientModificationDialogComponent } from './list-patients/patient-modification-dialog/patient-modification-dialog.component';
import { RechercherUtilisateurComponent } from './rechercher-utilisateur/rechercher-utilisateur.component';
import { AjouterUtilisateurComponent } from './ajouter-utilisateur/ajouter-utilisateur.component';
import { ModifierUtilisateurComponent } from './modifier-utilisateur/modifier-utilisateur.component';
import { ListeAdminComponent } from './liste-admin/liste-admin.component';
import { ModifierAdminDialogComponent } from './liste-admin/modifier-admin-dialog/modifier-admin-dialog.component';
import { ChatComponent } from './chat/chat.component';
import { EpreuveListeComponent } from './Analyse/Epreuve/epreuve-liste/epreuve-liste.component';
import { ConsulterAnalyseComponent } from './consulter-analyse/consulter-analyse.component';
import { ModifierAnalyseComponent } from './modifier-analyse/modifier-analyse.component';
import { ModifierEpreuveComponent } from './modifier-epreuve/modifier-epreuve.component';
import { AjouterAnalyseComponent } from './ajouter-analyse/ajouter-analyse.component';
import { AjouterEpreuveComponent } from './ajouter-epreuve/ajouter-epreuve.component';
import { TestAnalyseListeComponent } from './Analyse/test-analyse-liste/test-analyse-liste.component';
import { TestAnalyseFormComponent } from './Analyse/test-analyse-form/test-analyse-form.component';



const routes: Routes = [
  // Route par défaut qui charge AppComponent
  // Route par défaut : MainContentComponent sera affiché
  { path: '', component: MainContentComponent },  // Composant par défaut (Page d'accueil)



  // Route pour accéder à la page principale (AppComponent)
  { path: 'home', component: HomeComponent },   // Pour accéder à HomeComponent via "/home"
  { path: 'add', component: AddLaboratoireComponent },

  // Si l'utilisateur va sur "/page", affiche le AppComponent
  { path: 'page', component: AppComponent }, // Route pour AppComponent (Page principale)
  { path: 'list', component: LaboratoireListComponent },
  // Redirection pour les routes non trouvées
  { path: 'list-analyse', component: AnalyseListeComponent },
  { path: 'list-dossiers', component: ListDossiersComponent },
  { path: 'add-patient', component: AjouterPatientComponent },
  { path: 'add-dossier', component: AjouterDossierComponent },
  { path: 'options-analyse', component: OptionsAnalyseComponent },
  {path: 'rechercher-utilisateur', component: RechercherUtilisateurComponent },
  {path: 'ajouter-utilisateur', component: AjouterUtilisateurComponent },
  {path: 'modifier-utilisateur', component: ModifierUtilisateurComponent },
  {path: 'list-admin', component: ListeAdminComponent },
  { path: 'list-patients', component: ListPatientsComponent },
  { path: 'modifier-patient', component: PatientModificationDialogComponent },
  { path: 'modifier-utilisateur/:id', component: ModifierAdminDialogComponent },
  { path: 'chat', component:  ChatComponent },

  { path: 'list-analyse', component: AnalyseListeComponent },
  { path: 'list-epreuve', component: EpreuveListeComponent },
  { path: 'consulter-analyse', component:  ConsulterAnalyseComponent},
  { path: 'modifier-analyse', component: ModifierAnalyseComponent },
  { path: 'modifier-epreuve', component: ModifierEpreuveComponent },
  { path: 'consulter-epreuve', component:  AjouterEpreuveComponent},
  { path: 'ajouter-analyse', component: AjouterAnalyseComponent },
  { path: 'ajouter-epreuve', component: AjouterEpreuveComponent },
  { path: 'listTestAnalyse', component: TestAnalyseListeComponent },
  { path: 'TestAnalyseForm', component: TestAnalyseFormComponent },


  { path: '**', redirectTo: '' },  // Si l'URL est incorrecte, redirige vers l'accueil
  // Rediriger vers la route par défaut pour toute route invalide


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
