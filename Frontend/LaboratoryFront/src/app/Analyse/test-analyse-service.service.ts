import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TestAnalyse } from './test-analyse.model';

@Injectable({
  providedIn: 'root'
})
export class TestAnalyseService {
  private apiUrl = 'http://localhost:8082/api/test-analyses';  // Remplacez par l'URL de votre backend

  constructor(private http: HttpClient) { }

  createTestAnalyse(testAnalyse: TestAnalyse): Observable<TestAnalyse> {
    return this.http.post<TestAnalyse>(this.apiUrl, testAnalyse);
  }


   // Récupérer tous les tests d'analyse
   getAllTestAnalyses(): Observable<TestAnalyse[]> {
    return this.http.get<TestAnalyse[]>(this.apiUrl);
  }

  // Récupérer un test d'analyse par son ID
  getTestAnalyseById(id: number): Observable<TestAnalyse> {
    return this.http.get<TestAnalyse>(`${this.apiUrl}/${id}`);
  }

  

  // Modifier un test d'analyse existant
  updateTestAnalyse(id: number, testAnalyse: TestAnalyse): Observable<TestAnalyse> {
    return this.http.put<TestAnalyse>(`${this.apiUrl}/${id}`, testAnalyse);
  }

  // Supprimer un test d'analyse
  deleteTestAnalyse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  
}