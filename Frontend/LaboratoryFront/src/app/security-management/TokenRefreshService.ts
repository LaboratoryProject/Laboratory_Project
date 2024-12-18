import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class TokenRefreshService {

  constructor(private keycloak: KeycloakService) {
    this.scheduleTokenRefresh();
  }

  scheduleTokenRefresh() {
    setInterval(() => {
      this.keycloak.updateToken(30)  // Rafraîchit le token si il expire dans les 30 prochaines secondes
        .then((refreshed: any) => {
          if (refreshed) {
            console.log('Token refreshed');
          } else {
            console.warn('Token not refreshed, still valid');
          }
        })
        .catch(() => {
          console.error('Failed to refresh token');
        });
    }, 60000); // Rafraîchit toutes les minutes
  }
}
