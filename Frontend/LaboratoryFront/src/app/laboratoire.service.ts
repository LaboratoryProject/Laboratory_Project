import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';

// Define Laboratoire interface to type the data
export interface Laboratoire {
  id: number;
  nom: string;
  logo: string | null; // If the logo is nullable
  nrc: string;
  active: boolean;
  dateActivation: string;
  numTel: string;
  fax: string;
  email: string;
  numVoie: string;
  nomVoie: string;
  codePostal: string;
  ville: string;
  commune: string;
}

@Injectable({
  providedIn: 'root',
})
export class LaboratoireService {
  private baseUrl = 'http://localhost:8081/api/laboratoires'; // Remplacez par votre URL

  constructor(private http: HttpClient) {}

  createLaboratoire(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/complet`, data);
  }
   // Get all laboratoires
   getAllLaboratoires(): Observable<any[]> {
    console.log('Fetching laboratoires from URL:', this.baseUrl);
    return this.http.get<any[]>(`${this.baseUrl}`).pipe( tap(data => console.log('Received laboratoires:', data)),
      catchError(error => {
        console.error('Error fetching laboratoires:', error);
        return throwError(error);
      })
    );
  }
  deleteLaboratoire(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }
  
  getLaboratoireById(id: number): Observable<Laboratoire> {
    return this.http.get<Laboratoire>(`${this.baseUrl}/laboratoire/${id}`);
  }

 showDetails(id: number): Observable<Laboratoire> {
   return this.http.get<Laboratoire>(`${this.baseUrl}/laboratoire/{id}`);
 }
 updateLaboratoire(id: number, laboratoire: Laboratoire): Observable<Laboratoire> {
  return this.http.put<Laboratoire>(`${this.baseUrl}/update/${id}`, laboratoire).pipe(
    tap(updatedLab => console.log('Laboratoire updated:', updatedLab)),
    catchError(error => {
      console.error('Error updating laboratoire:', error);
      return throwError(error);
    })
  );
}
updateLaboratoireParcellement(
  id: number, 
  laboratoire: Partial<Laboratoire>, 
  logoFile?: File | null
): Observable<Laboratoire> {
  const formData = new FormData();
  
  // Convert laboratoire data to JSON string
  formData.append('updates', JSON.stringify(laboratoire));
  
  // Add logo file if present
  if (logoFile) {
    formData.append('logoFile', logoFile);
  }

  // Use HttpClient to send a PATCH request
  return this.http.patch<Laboratoire>(
    `${this.baseUrl}/update-partiel/${id}`, 
    formData
  );
}}
