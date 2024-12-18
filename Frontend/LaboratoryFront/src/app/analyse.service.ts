import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AnalyseService {
  private baseUrl = 'http://localhost:8082/api/analyse';

  constructor(private http: HttpClient) {}

  // Créer une nouvelle analyse
  createAnalyse(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, data).pipe(
      tap((newAnalyse) => console.log('Analyse créée:', newAnalyse)),
      catchError((error) => {
        console.error('Erreur lors de la création de l\'analyse:', error);
        return throwError(error);
      })
    );
  }

  // Récupérer toutes les analyses
  getAllAnalyses(): Observable<any[]> {
    console.log('Récupération des analyses depuis URL:', this.baseUrl);
    return this.http.get<any[]>(`${this.baseUrl}`).pipe(
      tap((data) => console.log('Analyses récupérées:', data)),
      catchError((error) => {
        console.error('Erreur lors de la récupération des analyses:', error);
        return throwError(error);
      })
    );
  }

  // Récupérer une analyse par ID
  getAnalyseById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`).pipe(
      tap((data) => console.log('Analyse récupérée:', data)),
      catchError((error) => {
        console.error('Erreur lors de la récupération de l\'analyse:', error);
        return throwError(error);
      })
    );
  }

  // Mettre à jour une analyse
  updateAnalyse(id: number, analyse: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, analyse).pipe(
      tap((updatedAnalyse) => console.log('Analyse mise à jour:', updatedAnalyse)),
      catchError((error) => {
        console.error('Erreur lors de la mise à jour de l\'analyse:', error);
        return throwError(error);
      })
    );
  }

  // Supprimer une analyse
  deleteAnalyse(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`).pipe(
      tap(() => console.log('Analyse supprimée:', id)),
      catchError((error) => {
        console.error('Erreur lors de la suppression de l\'analyse:', error);
        return throwError(error);
      })
    );
  }

  // Récupérer les analyses par laboratoire
  getAnalysesByLaboratoire(laboratoireId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/laboratoire/${laboratoireId}`).pipe(
      tap((data) => console.log('Analyses récupérées par laboratoire:', data)),
      catchError((error) => {
        console.error('Erreur lors de la récupération des analyses par laboratoire:', error);
        return throwError(error);
      })
    );
  }
}
