import { KeycloakService } from 'keycloak-angular';

export function initializeKeycloak(keycloak: KeycloakService) {
  return (): Promise<any> => 
    keycloak.init({
      config: {
        url: 'http://localhost:9090',  // L'URL du serveur Keycloak
        realm: 'Laboratory-realm',  // Le nom de votre realm
        clientId: 'Angular',  // L'ID du client configuré dans Keycloak
      },
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: true,  // Vérification de l'iframe de connexion
        checkLoginIframeInterval: 30,  // Intervalle pour vérifier la connexion
      },
      enableBearerInterceptor: true,  // Active l'intercepteur pour ajouter le token aux requêtes
      bearerPrefix: 'Bearer',  // Le préfixe pour le token
    }).then(() => {
      console.log('Keycloak initialized');
    }).catch((error: any) => {
      console.error('Keycloak initialization failed', error);
    });
}
