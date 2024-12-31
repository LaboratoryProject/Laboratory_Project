
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ajouter-dossier',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ajouter-dossier.component.html',
  styleUrls: ['./ajouter-dossier.component.css']
})
export class AjouterDossierComponent {
  dossierForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient,  private router: Router) {
      this.dossierForm = this.fb.group({
        numDossier: ['', Validators.required],
        fkEmailUtilisateur: ['', [Validators.required, Validators.email]],  // Updated to match the back-end field
        fkIdPatient: ['', Validators.required],  // Updated to match the back-end field
        date: ['', Validators.required],
      });
    }

 submitForm() {
     if (this.dossierForm.valid) {
       const dossierData = {
         numDossier: this.dossierForm.value.numDossier,
         fkEmailUtilisateur: this.dossierForm.value.fkEmailUtilisateur,
         fkIdPatient: this.dossierForm.value.fkIdPatient,
         date: this.dossierForm.value.date,
       };

       this.http.post('http://localhost:8090/api/dossier', dossierData)
         .subscribe(
           (response) => {
             console.log('Dossier created successfully:', response);
             this.router.navigate(['/list-dossiers']);
           },
           (error) => {
             console.error('Error creating dossier:', error);
             alert('An error occurred while creating the dossier');
           }
         );
     } else {
       alert('Please fill in all required fields');
     }
   }
 }


