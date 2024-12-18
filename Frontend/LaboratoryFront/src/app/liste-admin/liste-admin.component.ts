import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SideBarSuperAdminComponent } from '../side-bar-super-admin/side-bar-super-admin.component';
import { ModifierAdminDialogComponent } from './modifier-admin-dialog/modifier-admin-dialog.component';


interface AdminUtilisateur {
  id: number;
  email: string;
  nomComplet: string;
  profession: string;
  numTel: string;
  laboratoireId: number;
  laboratoireNom: string;
  laboratoireNrc: string;
}

@Component({
  selector: 'app-liste-admin',
  templateUrl: './liste-admin.component.html',
  styleUrls: ['./liste-admin.component.css'],
  imports:[ CommonModule,
      MatCardModule,
      MatDialogModule,
      MatButtonModule,
      MatFormFieldModule,
      MatInputModule,
      MatIconModule,
      HttpClientModule,
      MatToolbarModule,
      MatSelectModule,
      SideBarSuperAdminComponent,]
})
export class ListeAdminComponent implements OnInit {
  adminUsers: AdminUtilisateur[] = [];
  loading = true;
  error: string | null = null;

  constructor(private http: HttpClient, private router: Router , private dialog: MatDialog) {}

  ngOnInit() {
    this.fetchAdminUsers();
  }

  fetchAdminUsers() {
    this.loading = true;
    this.http.get<AdminUtilisateur[]>('http://localhost:8085/utilisateurs/Admin')
      .subscribe({
        next: (users) => {
          this.adminUsers = users;
          this.loading = false;
        },
        error: (err) => {
          console.error('Erreur lors du chargement des administrateurs', err);
          this.error = 'Impossible de charger les administrateurs. Veuillez réessayer.';
          this.loading = false;
        }
      });
  }

  modifierAdministrateur(id: number) {
    const dialogRef = this.dialog.open(ModifierAdminDialogComponent, {
      data: { id }  // Pass the id to the dialog
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Handle any data returned from the dialog if necessary
      }
    });
  }

  navigateToAjouter() {
    this.router.navigate(['/ajouter-utilisateur']);
  }

  // Méthode pour supprimer un administrateur
  supprimerAdministrateur(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet administrateur ?')) {
      this.http.delete(`http://localhost:8085/utilisateurs/admin/${id}`)
        .subscribe({
          next: () => {
            this.fetchAdminUsers();
          },
          error: (err) => {
            console.error('Erreur lors de la suppression', err);
            this.error = 'Impossible de supprimer l\'administrateur. Veuillez réessayer.';
          }
        });
    }
  }

  getInitials(name: string): string {
    return name ? name.split(' ').map(n => n[0]).join('').toUpperCase() : '';
  }
}
