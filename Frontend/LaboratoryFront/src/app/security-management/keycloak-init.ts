import { KeycloakService } from 'keycloak-angular';

/**
 * Initialize Keycloak for the Angular application.
 * @param keycloak Instance of KeycloakService
 * @returns A function that returns a Promise, ensuring proper initialization.
 */
export function initializeKeycloak(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    // Ensure the code only runs in the browser environment
    if (typeof window !== 'undefined' && window.document) {
      return keycloak.init({
        config: {
          url: 'http://localhost:9090/auth', // URL of your Keycloak server (include /auth if applicable)
          realm: 'Laboratory-realm',        // Name of your Keycloak realm
          clientId: 'Angular',              // Client ID configured in Keycloak
        },
        initOptions: {
          onLoad: 'login-required',         // Automatically log in if the user isn't logged in
          checkLoginIframe: true,           // Enable iframe login checks
          checkLoginIframeInterval: 30,     // Interval in seconds for login checks
        },
        enableBearerInterceptor: true,      // Add the token automatically to API calls
        bearerPrefix: 'Bearer',             // Token prefix for Authorization headers
      })
        .then(() => {
          console.log('Keycloak initialized successfully.');
        })
        .catch((error: any) => {
          console.error('Keycloak initialization failed:', error.message);
          console.error('Stack trace:', error.stack);
          return Promise.reject(error); // Reject the promise for failure handling
        });
    } else {
      // Skip Keycloak initialization for server-side rendering
      console.log('Keycloak initialization skipped: Server-side rendering detected.');
      return Promise.resolve();
    }
  };
}
