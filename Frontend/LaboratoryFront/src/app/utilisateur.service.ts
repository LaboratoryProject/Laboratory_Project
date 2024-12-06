import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // Rend ce service disponible partout dans l'application
})
export class UtilisateurService {
  private baseUrl = 'http://localhost:8085/utilisateurs';

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

  // Récupérer des utilisateurs par rôle
  getUtilisateursByRole(role: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/role/${role}`);
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
