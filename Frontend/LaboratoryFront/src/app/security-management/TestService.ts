import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TestService {
  constructor(private http: HttpClient) {}
  private url = 'http://localhost:8081/';

  sayHello() {
    return this.http.get<string>(this.url + '/all');
  }
}
