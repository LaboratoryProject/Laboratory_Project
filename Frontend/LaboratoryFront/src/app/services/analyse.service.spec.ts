import { TestBed } from '@angular/core/testing';
import { AnalyseService } from './analyse.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Analyse } from './analyse.model';

describe('AnalyseService', () => {
  let service: AnalyseService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AnalyseService]
    });
    service = TestBed.inject(AnalyseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Vérifie si aucune requête HTTP en attente
  });

  // Test de la méthode createAnalyse()
  it('should create an analyse', () => {
    const newAnalyse: Analyse = {
      id: 1,
      fkIdLaboratoire: 101,
      nom: 'Analyse Sang',
      description: 'Test de sang'
    };

    service.createAnalyse(newAnalyse).subscribe((analyse) => {
      expect(analyse).toEqual(newAnalyse);
    });

    const req = httpMock.expectOne('/api/analyse');
    expect(req.request.method).toBe('POST');
    req.flush(newAnalyse);
  });

  // Test de la méthode getAllAnalyses()
  it('should get all analyses', () => {
    const mockAnalyses: Analyse[] = [
      { id: 1, fkIdLaboratoire: 101, nom: 'Analyse Urine', description: 'Test d\'urine' },
      { id: 2, fkIdLaboratoire: 102, nom: 'Analyse Sang', description: 'Test de sang' }
    ];

    service.getAllAnalyses().subscribe((analyses) => {
      expect(analyses.length).toBe(2);
      expect(analyses).toEqual(mockAnalyses);
    });

    const req = httpMock.expectOne('/api/analyse');
    expect(req.request.method).toBe('GET');
    req.flush(mockAnalyses);
  });

  // Test de la méthode deleteAnalyse()
  it('should delete an analyse', () => {
    const id = 1;

    service.deleteAnalyse(id).subscribe((response) => {
      expect(response).toBeNull();
    });

    const req = httpMock.expectOne(`/api/analyse/${id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
