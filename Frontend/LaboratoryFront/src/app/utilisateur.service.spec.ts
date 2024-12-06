import { TestBed } from '@angular/core/testing';
import { UtilisateurService } from './utilisateur.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('UtilisateurService', () => {
  let service: UtilisateurService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UtilisateurService],
    });
    service = TestBed.inject(UtilisateurService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call createUtilisateur and return the result', () => {
    const mockData = { nomComplet: 'Test User', email: 'test@example.com' };

    service.createUtilisateur(mockData).subscribe((response) => {
      expect(response).toEqual(mockData);
    });

    const req = httpMock.expectOne('http://localhost:8085/utilisateurs');
    expect(req.request.method).toBe('POST');
    req.flush(mockData);
  });

  it('should call getUtilisateurById and return the result', () => {
    const mockData = { id: 1, nomComplet: 'Test User', email: 'test@example.com' };

    service.getUtilisateurById(1).subscribe((response) => {
      expect(response).toEqual(mockData);
    });

    const req = httpMock.expectOne('http://localhost:8085/utilisateurs/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockData);
  });

  it('should call getUtilisateursByRole and return the result', () => {
    const mockData = [
      { id: 1, nomComplet: 'Admin User', role: 'ADMIN' },
      { id: 2, nomComplet: 'Technicien User', role: 'TECHNICIEN' },
    ];

    service.getUtilisateursByRole('ADMIN').subscribe((response) => {
      expect(response).toEqual(mockData);
    });

    const req = httpMock.expectOne('http://localhost:8085/utilisateurs/role/ADMIN');
    expect(req.request.method).toBe('GET');
    req.flush(mockData);
  });

  afterEach(() => {
    httpMock.verify();
  });
});
