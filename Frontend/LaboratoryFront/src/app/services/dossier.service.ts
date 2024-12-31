import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class DossierService {
  private apiUrl = 'http://localhost:8090/api/dossier'; // API URL for Dossier

  constructor(private http: HttpClient) {}

  // Helper method to create headers with token
  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('access_token');

    if (!token) {
      console.error('Authentication token not found in localStorage.');
      throw new Error('Authentication token is required.');
    }

    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  // Fetch all dossiers
  getAllDossiers(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error('Error fetching dossiers:', error);
        return throwError(() => error);
      })
    );
  }

  // Add a new dossier
  createDossier(dossier: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, dossier, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error('Error creating dossier:', error);
        return throwError(() => error);
      })
    );
  }

  // Update a dossier
  updateDossier(numDossier: number, updatedDossier: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${numDossier}`, updatedDossier, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error('Error updating dossier:', error);
        return throwError(() => error);
      })
    );
  }

  // Delete a dossier
  deleteDossier(numDossier: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${numDossier}`, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error('Error deleting dossier:', error);
        return throwError(() => error);
      })
    );
  }
}
