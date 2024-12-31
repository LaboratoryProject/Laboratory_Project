import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root', // Makes this service available throughout the application
})
export class UtilisateurService {
  private baseUrl = 'http://localhost:8085/utilisateurs'; // API backend URL

  constructor(private http: HttpClient) {}

  // Helper method to create headers with token
  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('access_token');

    if (!token) {
      console.error('Authentication token not found in localStorage.');
      throw new Error('Authentication token is required.');
    }

    return new HttpHeaders({
      Authorization: `Bearer ${token}`,  // Correct string interpolation
      'Content-Type': 'application/json',
    });
  }

  // Add a user
  createUtilisateur(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, data, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error('Error creating user:', error);
        return throwError(() => error);
      })
    );
  }

  // Retrieve all users
  getAllUtilisateurs(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error('Error fetching users:', error);
        return throwError(() => error);
      })
    );
  }

  // Retrieve a user by ID
  getUtilisateurById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error(`Error fetching user with ID ${id}:`, error);  // Correct string interpolation
        return throwError(() => error);
      })
    );
  }

  // Update a user
  updateUtilisateur(id: number, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, data, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error(`Error updating user with ID ${id}:`, error);  // Correct string interpolation
        return throwError(() => error);
      })
    );
  }

  // Delete a user
  deleteUtilisateur(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error(`Error deleting user with ID ${id}:`, error);  // Correct string interpolation
        return throwError(() => error);
      })
    );
  }

  // Modify a user
  modifyUtilisateur(utilisateur: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/modifier`, utilisateur, { headers: this.getHeaders() }).pipe(
      catchError((error) => {
        console.error('Error modifying user:', error);
        return throwError(() => error);
      })
    );
  }
}
