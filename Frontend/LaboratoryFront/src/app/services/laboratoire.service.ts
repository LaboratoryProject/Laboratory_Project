import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';

// Define Laboratoire interface to type the data
export interface Laboratoire {
  id: number;
  nom: string;
  logo: string | null; // If the logo is nullable
  nrc: string;
  active: boolean;
  dateActivation: string;
  numTel: string;
  fax: string;
  email: string;
  numVoie: string;
  nomVoie: string;
  codePostal: string;
  ville: string;
  commune: string;
}

@Injectable({
  providedIn: 'root',
})
export class LaboratoireService {
  private baseUrl = 'http://localhost:8081/api/laboratoires'; // Replace with your actual URL

  constructor(private http: HttpClient) {}

  createLaboratoire(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/complet`, data); // Fixed string interpolation
  }

  // Get all laboratoires
  getAllLaboratoires(): Observable<any[]> {
    console.log('Fetching laboratoires from URL:', this.baseUrl);
    return this.http.get<any[]>(`${this.baseUrl}`).pipe(
      tap((data) => console.log('Received laboratoires:', data)),
      catchError((error) => {
        console.error('Error fetching laboratoires:', error);
        return throwError(error);
      })
    );
  }

  deleteLaboratoire(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`); // Fixed string interpolation
  }

  showDetails(id: number): Observable<Laboratoire> {
    return this.http.get<Laboratoire>(`${this.baseUrl}/${id}`); // Fixed string interpolation
  }
}
