import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../models/patient.model';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private baseUrl = 'http://localhost:8072/patients'; // URL de l'API pour les patients

  constructor(private http: HttpClient) {}

  // Récupérer tous les patients
  getAllPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>(`${this.baseUrl}`);
  }

  // Récupérer un patient par ID
  getPatientById(id: number): Observable<Patient> {
    return this.http.get<Patient>(`${this.baseUrl}/${id}`);
  }

  // Ajouter un patient
  createPatient(data: Patient): Observable<Patient> {
    return this.http.post<Patient>(`${this.baseUrl}`, data);
  }

  // Mettre à jour un patient
  updatePatient(id: number, data: Patient): Observable<Patient> {
    return this.http.put<Patient>(`${this.baseUrl}/${id}`, data);
  }

  // Supprimer un patient
  deletePatient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
