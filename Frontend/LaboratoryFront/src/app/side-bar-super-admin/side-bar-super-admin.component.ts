import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Router, RouterModule } from '@angular/router';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { MatDrawerMode } from '@angular/material/sidenav';
import { KeycloakService } from 'keycloak-angular'; // Import KeycloakService

@Component({
  selector: 'app-side-bar-super-admin',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatSidenavModule,
    MatListModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule
  ],
  template: `
    <mat-sidenav-container class="admin-layout">
      <mat-sidenav 
        #drawer 
        [mode]="sideNavMode"
        [opened]="isSidenavOpen"
        class="sidebar"
        [class.mobile-sidebar]="isMobile">
        <div class="sidebar-header">
          <h2>Super Admin</h2>
          <button 
            *ngIf="isMobile" 
            mat-icon-button 
            (click)="toggleSidenav()">
            <mat-icon>close</mat-icon>
          </button>
        </div>
        <mat-nav-list>
          <a mat-list-item routerLink="/home" routerLinkActive="active" (click)="closeSidenavIfMobile()">
            <mat-icon>dashboard</mat-icon>
            <span>Accueil</span>
          </a>
          <a mat-list-item routerLink="/list" routerLinkActive="active" (click)="closeSidenavIfMobile()">
            <mat-icon>science</mat-icon>
            <span>Laboratoires</span>
          </a>
          <a mat-list-item routerLink="/list-admin" routerLinkActive="active" (click)="closeSidenavIfMobile()">
            <mat-icon>people</mat-icon>
            <span>Administrateurs des laboratoires</span>
          </a>
        </mat-nav-list>
        <div class="sidebar-footer">
          <button mat-stroked-button color="warn" (click)="logout()">
            <mat-icon>logout</mat-icon>
            Déconnexion
          </button>
        </div>
      </mat-sidenav>
    </mat-sidenav-container>
  `,
  styles: [`
    .admin-layout {
      height: 100vh;
    }
    
    .sidebar {
      width: 330px;
      background-color: #ffffff;
      box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }
    
    .mobile-sidebar {
      width: 100%;
      max-width: 100%;
    }
    
    .sidebar-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #3f51b5;
      color: white;
      padding: 20px;
    }
    
    .sidebar-header h2 {
      margin: 0;
      font-weight: 300;
    }
    
    .sidebar-footer {
      position: absolute;
      bottom: 20px;
      width: 100%;
      padding: 0 20px;
    }
    
    .mat-list-item {
      transition: background-color 0.3s ease;
    }
    
    .mat-list-item:hover {
      background-color: rgba(63, 81, 181, 0.1);
    }
    
    .mat-list-item.active {
      color: #3f51b5;
      font-weight: bold;
      background-color: rgba(63, 81, 181, 0.1);
    }
    
    .content {
      padding: 20px;
      background-color: #f5f5f5;
      height: calc(100vh - 64px);
      overflow-y: auto;
    }
  `]
})
export class SideBarSuperAdminComponent {
  isMobile = false;
  isSidenavOpen = true;
  sideNavMode: MatDrawerMode = 'side';

  constructor(
    private breakpointObserver: BreakpointObserver,
    private keycloakService: KeycloakService,
    private router : Router,// Inject KeycloakService
  ) {
    this.breakpointObserver.observe([Breakpoints.Small, Breakpoints.XSmall])
      .subscribe(result => {
        this.isMobile = result.matches;
        
        if (this.isMobile) {
          this.sideNavMode = 'over';
          this.isSidenavOpen = false;
        } else {
          this.sideNavMode = 'side';
          this.isSidenavOpen = true;
        }
      });
  }

  toggleSidenav() {
    this.isSidenavOpen = !this.isSidenavOpen;
  }

  closeSidenavIfMobile() {
    if (this.isMobile) {
      this.isSidenavOpen = false;
    }
  }

  logout(): void {
    this.keycloakService
      .logout('http://localhost:9090/realms/Laboratory-realm/protocol/openid-connect/logout?client_id=Angular&post_logout_redirect_uri=http%3A%2F%2Flocalhost%3A4200%2Fhome')
      .then(() => {
        console.log('Logged out successfully');
      })
      .catch(error => {
        console.error('Error during logout:', error.response || error.message || error);
      });
  }
}