import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserStateService } from './userState.service';

export interface User {
  id: number;
  keycloakId: string;
  email: string;
  fkIdLaboratoire: number;
  nomComplet: string;
  profession: string;
  numTel: string;
  signature: string;
  role: string;  // Ou tu peux le définir en tant qu'énumération si tu préfères
  password?: string;
  nrc:string;  // Peut-être ne pas exposer le mot de passe, donc optionnel
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8085/utilisateurs'; // Remplacer par l'URL de votre API

  constructor(private http: HttpClient, private userStateService: UserStateService) {}

  getUserById(id: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/keycloak/${id}`);
  }

  fetchAndStoreUser(id: string): void {
    this.getUserById(id).subscribe((user) => {
      this.userStateService.setUser(user);
    });
  }

  // Récupérer tous les utilisateurs
  getUsers(): Observable<User[]> {
    const token = localStorage.getItem('access_token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<User[]>(this.baseUrl, { headers });
  }

  // Ajouter un utilisateur avec FormData
  createUtilisateur(data: any): Observable<any> {
    const body = {
      nomComplet: data.nomComplet,
      email: data.email,
      profession: data.profession,
      numTel: data.numTel,
      role: data.role,
      fkIdLaboratoire: data.fkIdLaboratoire,
      password: data.password,
      signature: data.signature,
      nrc: data.nrc, // Signature en base64
    };
  
    const token = localStorage.getItem('access_token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',  // Utilisation de JSON pour les données
    });
  
    return this.http.post<any>(this.baseUrl, body, { headers });
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
