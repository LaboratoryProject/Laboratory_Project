import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { jwtDecode } from 'jwt-decode';
import { UserStateService } from './userState.service'; 

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private keycloakService: KeycloakService, private userStateService: UserStateService) { }

  async getUserIdFromToken(): Promise<string | null> {
    try {
      const token = await this.keycloakService.getToken();
      const decodedToken: any = jwtDecode(token);
      return decodedToken.sub || null;  // Retourne l'ID utilisateur du token
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }
  

  async initializeUserId(): Promise<void> {
    try {
      const token = await this.keycloakService.getToken();
      const decodedToken: any = jwtDecode(token);
      const userId = decodedToken.sub || null;
      
      // Stocke l'ID utilisateur dans un service d'état utilisateur
      this.userStateService.setUserId(userId);
    } catch (error) {
      console.error('Error decoding token:', error);
    }
  }
}
