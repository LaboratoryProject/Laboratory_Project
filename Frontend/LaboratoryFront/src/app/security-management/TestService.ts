import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TestService {
  constructor(private http: HttpClient, private keycloakService: KeycloakService) {}

  private url = 'http://localhost:8082/';

  sayHello(): Observable<string> {
    return new Observable((observer) => {
      this.keycloakService.isLoggedIn().then((loggedIn) => {
        if (loggedIn) {
          this.http.get<string>(this.url + '/all').subscribe(
            (response) => {
              observer.next(response);
            },
            (error) => {
              observer.error(error);
            }
          );
        } else {
          console.log('User is not logged in. Redirecting to login...');
          // Optionally, redirect to login here
          this.keycloakService.login();
        }
      });
    });
  }
}
