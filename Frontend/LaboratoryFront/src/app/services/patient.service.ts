import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private apiUrl = 'http://localhost:8072/api/patients'; // URL de l'API backend

  constructor(private http: HttpClient) {}

  // Ajouter un patient
  ajouterPatient(patient: any): Observable<any> {
    return this.http.post(this.apiUrl, patient);
  }

  // Lister tous les patients
  listerPatients(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getPatientById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // Modifier les données d'un patient
  modifierPatient(id: number, updatedData: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, updatedData);
  }

  

  // Supprimer un patient
  supprimerPatient(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
