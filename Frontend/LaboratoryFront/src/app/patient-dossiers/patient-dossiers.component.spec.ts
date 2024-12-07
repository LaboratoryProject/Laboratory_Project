import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { PatientDossiersComponent } from './patient-dossiers.component';
import { PatientService } from '../services/patient.service';

describe('PatientDossiersComponent', () => {
  let component: PatientDossiersComponent;
  let fixture: ComponentFixture<PatientDossiersComponent>;

  const mockPatientService = {
    getDossiersByCin: jasmine.createSpy('getDossiersByCin').and.returnValue(
      of([{ id: 1, dateCreation: '2024-12-01' }])
    ),
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PatientDossiersComponent],
      providers: [
        { provide: PatientService, useValue: mockPatientService },
        { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => '12345678' } } } },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientDossiersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch dossiers for the given CIN', () => {
    expect(mockPatientService.getDossiersByCin).toHaveBeenCalledWith('12345678');
    expect(component.patientDossiers.length).toBeGreaterThan(0);
  });
});
