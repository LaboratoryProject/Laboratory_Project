import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LaboratoireService {
  private baseUrl = 'http://localhost:8081/api/laboratoires'; // Remplacez par votre URL

  constructor(private http: HttpClient) {}

  createLaboratoire(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/complet`, data);
  }
  
}

