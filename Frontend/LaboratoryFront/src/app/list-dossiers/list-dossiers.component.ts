import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PatientDialogComponent } from './patient-dialog/patient-dialog.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { DossierService } from '../services/dossier.service';
import { AjouterDossierComponent } from '../ajouter-dossier/ajouter-dossier.component';



interface Dossier {
id: number;
patientName: string;
patientIdentity: string;
dossierNumber: string;
}
@Component({
imports: [
    CommonModule,
    FormsModule, // Pour formulaires réactifs
  ],
selector: 'app-list-dossiers',
templateUrl: './list-dossiers.component.html',
styleUrl: './list-dossiers.component.css'
})
export class ListDossiersComponent {
dossiers: Dossier[] = [
{ id: 1, patientName: 'John Doe', patientIdentity: '123456789', dossierNumber: 'D001' },
{ id: 2, patientName: 'Jane Smith', patientIdentity: '987654321', dossierNumber: 'D002' },
{ id: 3, patientName: 'Bob Johnson', patientIdentity: '456789123', dossierNumber: 'D003' },
{ id: 4, patientName: 'Alice Williams', patientIdentity: '789123456', dossierNumber: 'D004' }
];
searchText: string = '';
constructor(private router: Router, private dialog: MatDialog) {}
navigateToDossier(dossier: Dossier) {
this.router.navigate(['/dossier', dossier.id]);
}
addDossier() {
// Implement logic to add a new dossier
console.log('Add a new dossier');
}
searchDossiers() {
// Implement logic to search dossiers based on the searchText
console.log('Search dossiers:', this.searchText);
}
openDialog(): void {
this.dialog.open(AjouterDossierComponent, {
width: '600px',
height:'700px',
data: { message: 'Hello from the dialog!' }
});
}
}
