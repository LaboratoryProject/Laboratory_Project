import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component'; // Importez le composant Home
import { AppComponent } from './app.component';
import { MainContentComponent } from './main-content/main-content.component';
import { AddLaboratoireComponent } from './add-laboratoire/add-laboratoire.component';
import { AjouterUtilisateurComponent } from './ajouter-utilisateur/ajouter-utilisateur.component';
import { SupprimerUtilisateurComponent } from './supprimer-utilisateur/supprimer-utilisateur.component';
import { ModifierUtilisateurComponent } from './modifier-utilisateur/modifier-utilisateur.component';
import { RechercherUtilisateurComponent } from './rechercher-utilisateur/rechercher-utilisateur.component';



const routes: Routes = [
  // Route par défaut qui charge AppComponent
  // Route par défaut : MainContentComponent sera affiché
  { path: '', component: MainContentComponent },  // Composant par défaut (Page d'accueil)

  { path: 'ajouter-utilisateur', component: AjouterUtilisateurComponent },
  { path: 'supprimer-utilisateur', component: SupprimerUtilisateurComponent },
   { path: 'modifier-utilisateur', component: ModifierUtilisateurComponent },
   { path: 'rechercher-utilisateur', component: RechercherUtilisateurComponent },

  // Route pour accéder à la page principale (AppComponent)
  { path: 'home', component: HomeComponent },   // Pour accéder à HomeComponent via "/home"
  { path: 'add', component: AddLaboratoireComponent },
  // Si l'utilisateur va sur "/page", affiche le AppComponent
  { path: 'page', component: AppComponent }, // Route pour AppComponent (Page principale)

  // Redirection pour les routes non trouvées
  { path: '**', redirectTo: '' },  // Si l'URL est incorrecte, redirige vers l'accueil
  // Rediriger vers la route par défaut pour toute route invalide


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
