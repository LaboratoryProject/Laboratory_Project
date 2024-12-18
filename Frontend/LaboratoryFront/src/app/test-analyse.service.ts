import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestAnalyseService {

  // Définition directe de l'URL de l'API
  private apiUrl = 'http://localhost:8082/api/analyse/test-analyses';

  constructor(private http: HttpClient) { }

  // Ajouter un TestAnalyse
  ajouterTestAnalyse(testAnalyse: any): Observable<any> {
    return this.http.post(this.apiUrl, testAnalyse);
  }

  // Récupérer tous les TestAnalyses
  getAllTestAnalyses(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  // Récupérer un TestAnalyse par son ID
  getTestAnalyseById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // Mettre à jour un TestAnalyse
  updateTestAnalyse(id: number, testAnalyse: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, testAnalyse);
  }

  // Supprimer un TestAnalyse
  supprimerTestAnalyse(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
