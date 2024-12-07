import { TestBed } from '@angular/core/testing';
import { PatientService } from './patient.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Patient } from './patient.model';

describe('PatientService', () => {
  let service: PatientService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PatientService]
    });
    service = TestBed.inject(PatientService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Vérifie si aucune requête HTTP en attente
  });

  // Test de la méthode createPatient()
  it('should create a patient', () => {
    const newPatient: Patient = {
      id: 1,
      cin: 'A123456',
      nom: 'John Doe',
      dateNaissance: '1990-01-01',
      adresse: '123 Rue Exemple',
      telephone: '0600000000'
    };

    service.createPatient(newPatient).subscribe((patient) => {
      expect(patient).toEqual(newPatient);
    });

    const req = httpMock.expectOne('/api/patient');
    expect(req.request.method).toBe('POST');
    req.flush(newPatient);
  });

  // Test de la méthode getAllPatients()
  it('should get all patients', () => {
    const mockPatients: Patient[] = [
      { id: 1, cin: 'A123456', nom: 'John Doe', dateNaissance: '1990-01-01', adresse: '123 Rue Exemple', telephone: '0600000000' },
      { id: 2, cin: 'B654321', nom: 'Jane Smith', dateNaissance: '1992-02-02', adresse: '456 Rue Exemple', telephone: '0600000001' }
    ];

    service.getAllPatients().subscribe((patients) => {
      expect(patients.length).toBe(2);
      expect(patients).toEqual(mockPatients);
    });

    const req = httpMock.expectOne('/api/patient');
    expect(req.request.method).toBe('GET');
    req.flush(mockPatients);
  });

  // Test de la méthode getPatientByCin()
  it('should get a patient by CIN', () => {
    const cin = 'A123456';
    const mockPatient: Patient = {
      id: 1,
      cin: 'A123456',
      nom: 'John Doe',
      dateNaissance: '1990-01-01',
      adresse: '123 Rue Exemple',
      telephone: '0600000000'
    };

    service.getPatientByCin(cin).subscribe((patient) => {
      expect(patient).toEqual(mockPatient);
    });

    const req = httpMock.expectOne(`/api/patient/${cin}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockPatient);
  });

  // Test de la méthode updatePatient()
  it('should update a patient', () => {
    const updatedPatient: Patient = {
      id: 1,
      cin: 'A123456',
      nom: 'John Doe Updated',
      dateNaissance: '1990-01-01',
      adresse: '123 Rue Exemple Updated',
      telephone: '0600000001'
    };

    service.updatePatient(1, updatedPatient).subscribe((patient) => {
      expect(patient).toEqual(updatedPatient);
    });

    const req = httpMock.expectOne(`/api/patient/1`);
    expect(req.request.method).toBe('PUT');
    req.flush(updatedPatient);
  });

  // Test de la méthode deletePatient()
  it('should delete a patient', () => {
    const id = 1;

    service.deletePatient(id).subscribe((response) => {
      expect(response).toBeNull();
    });

    const req = httpMock.expectOne(`/api/patient/${id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
