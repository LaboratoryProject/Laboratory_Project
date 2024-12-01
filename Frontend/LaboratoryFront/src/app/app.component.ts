import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CinDialogComponent } from './cin-dialog/cin-dialog.component'; // Import du composant de dialogue


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'LaboratoryFront';
  constructor(public dialog: MatDialog) {}

  // Fonction pour ouvrir la boîte de dialogue
  openDialog() {
    this.dialog.open(CinDialogComponent, {
      width: '500px',  // Largeur du dialogue
      height: 'auto',  // Hauteur automatique
      maxWidth: '90vw', // Largeur maximale (par rapport à la largeur de la fenêtre)
    });
  }
}