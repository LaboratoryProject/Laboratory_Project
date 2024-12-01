import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // Rend ce service disponible partout dans l'application
})
export class UtilisateurService {
  private baseUrl = 'http://localhost:8085/utilisateurs'; // URL de votre API backend

  constructor(private http: HttpClient) {}

  // Ajouter un utilisateur
  createUtilisateur(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, data);
  }

  // Récupérer tous les utilisateurs
  getAllUtilisateurs(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  // Récupérer un utilisateur par ID
  getUtilisateurById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // Mettre à jour un utilisateur
  updateUtilisateur(id: number, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, data);
  }

  // Supprimer un utilisateur
  deleteUtilisateur(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
