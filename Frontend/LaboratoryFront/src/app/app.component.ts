import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CinDialogComponent } from './cin-dialog/cin-dialog.component'; // Import du composant de dialogue
import { UserStateService } from './security-management/userState.service';
import { KeycloakService } from 'keycloak-angular';
import { TokenService } from './security-management/TokenService';
import { User, UserService } from './security-management/user.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'LaboratoryFront';


  constructor(public dialog: MatDialog,private keycloakService: KeycloakService, private tokenService: TokenService, private userService: UserService,
    private userStateService: UserStateService) { }

  // Fonction pour ouvrir la boîte de dialogue
  openDialog() {
    this.dialog.open(CinDialogComponent, {
      width: '500px',  // Largeur du dialogue
      height: 'auto',  // Hauteur automatique
      maxWidth: '90vw', // Largeur maximale (par rapport à la largeur de la fenêtre)
    });
  }


  userId: string | null = null;
  user: User | null = null;

  
  async ngOnInit(): Promise<void> {
    try {
      // Attendez que Keycloak soit bien initialisé
      await this.keycloakService.init({
        config: {
          url: 'http://localhost:9090',
          realm: 'Laboratory-realm',
          clientId: 'Angular',
        },
        initOptions: {
          onLoad: 'login-required',
          checkLoginIframe: true,
        },
        enableBearerInterceptor: true,
        bearerPrefix: 'Bearer',
      });

      console.log('Keycloak initialized successfully');
    
    // Une fois initialisé, vous pouvez obtenir le token
    const token = await this.keycloakService.getToken();
    console.log('Token:', token);

    // Appel des autres méthodes après l'initialisation de Keycloak
    await this.displayToken();  // Afficher le token
    await this.fetchUserId();  // Récupérer l'ID de l'utilisateur
    this.tokenService.initializeUserId();  // Initialiser l'ID de l'utilisateur dans le service

    // Souscrire à l'observateur pour l'ID de l'utilisateur et charger les détails utilisateur
    this.userStateService.userId$.subscribe(id => {
      this.userId = id;
      if (this.userId) {
        this.loadUserDetails(this.userId);  // Charger les détails utilisateur par ID
        this.userService.getUserById(this.userId).subscribe(user => {
          // Stocker les données utilisateur dans l'état
          this.userStateService.setUser(user);
          console.log('User Data: from the state variables', user);
        });
      }
    });

  } catch (error) {
    console.error('Keycloak initialization failed', error);
  }
}

  private loadUserDetails(id: string): void {
    this.userService.getUserById(id).subscribe(
      (data: User) => {
        this.user = data;
        console.log('User data:', this.user);
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  private async displayToken(): Promise<void> {
    try {
      const token = await this.keycloakService.getToken();
      console.log('JWT Token:', token);

      
    } catch (error) {
      console.error('Error fetching token:', error);
    }
  }
  async fetchUserId(): Promise<void> {
    this.userId = await this.tokenService.getUserIdFromToken();
    console.log('User ID:', this.userId);
  }

  // Méthode de déconnexion
  logout(): void {
    this.keycloakService.logout().then(() => {
      console.log('Logged out successfully');
    }).catch(error => {
      console.error('Error during logout:', error);
    });
  }
  
}
