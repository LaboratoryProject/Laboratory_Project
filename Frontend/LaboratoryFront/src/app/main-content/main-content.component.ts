import { Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CinDialogComponent } from '../cin-dialog/cin-dialog.component';
import { MatDialog } from '@angular/material/dialog';
// Import du composant de dialogue


@Component({
  selector: 'app-main-content',
  standalone: false,
  
  templateUrl: './main-content.component.html',
  styleUrl: './main-content.component.css'
})
export class MainContentComponent {
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
