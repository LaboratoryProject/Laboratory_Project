import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EpreuveService {
  private baseUrl = 'http://localhost:8086/api/epreuves'; // URL de base pour l'API des épreuves

  constructor(private http: HttpClient) {}

  // Créer une nouvelle épreuve
  createEpreuve(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, data).pipe(
      tap((newEpreuve) => console.log('Épreuve créée:', newEpreuve)),
      catchError((error) => {
        console.error('Erreur lors de la création de l\'épreuve:', error);
        return throwError(error);
      })
    );
  }

  // Récupérer toutes les épreuves
  getAllEpreuves(): Observable<any[]> {
    console.log('Récupération des épreuves depuis URL:', this.baseUrl);
    return this.http.get<any[]>(`${this.baseUrl}`).pipe(
      tap((data) => console.log('Épreuves récupérées:', data)),
      catchError((error) => {
        console.error('Erreur lors de la récupération des épreuves:', error);
        return throwError(error);
      })
    );
  }

  // Récupérer une épreuve par ID
  getEpreuveById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`).pipe(
      tap((data) => console.log('Épreuve récupérée:', data)),
      catchError((error) => {
        console.error('Erreur lors de la récupération de l\'épreuve:', error);
        return throwError(error);
      })
    );
  }

  // Mettre à jour une épreuve
  updateEpreuve(id: number, epreuve: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, epreuve).pipe(
      tap((updatedEpreuve) => console.log('Épreuve mise à jour:', updatedEpreuve)),
      catchError((error) => {
        console.error('Erreur lors de la mise à jour de l\'épreuve:', error);
        return throwError(error);
      })
    );
  }

  // Supprimer une épreuve
  deleteEpreuve(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`).pipe(
      tap(() => console.log('Épreuve supprimée:', id)),
      catchError((error) => {
        console.error('Erreur lors de la suppression de l\'épreuve:', error);
        return throwError(error);
      })
    );
  }

  // Récupérer les épreuves associées à une analyse
  getEpreuvesByAnalyse(analyseId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/analyse/${analyseId}`).pipe(
      tap((data) => console.log('Épreuves récupérées pour l\'analyse:', data)),
      catchError((error) => {
        console.error('Erreur lors de la récupération des épreuves associées:', error);
        return throwError(error);
      })
    );
  }

  // Rechercher des épreuves par nom
  searchEpreuves(nom: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/search?nom=${nom}`).pipe(
      tap((data) => console.log('Épreuves trouvées:', data)),
      catchError((error) => {
        console.error('Erreur lors de la recherche des épreuves:', error);
        return throwError(error);
      })
    );
  }
}
