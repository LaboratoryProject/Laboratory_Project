import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { PatientService } from '../services/patient.service';
import { of, throwError } from 'rxjs';
import { CreatePatientComponent } from './create-patient.component';

describe('CreatePatientComponent', () => {
  let component: CreatePatientComponent;
  let fixture: ComponentFixture<CreatePatientComponent>;
  let mockPatientService: jasmine.SpyObj<PatientService>;

  beforeEach(async () => {
    mockPatientService = jasmine.createSpyObj('PatientService', ['addPatient']);
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, MatSnackBarModule],
      declarations: [CreatePatientComponent],
      providers: [{ provide: PatientService, useValue: mockPatientService }],
    }).compileComponents();

    fixture = TestBed.createComponent(CreatePatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call addPatient on valid form submission', () => {
    mockPatientService.addPatient.and.returnValue(of({}));
    component.patientForm.setValue({
      cin: 'AB123456',
      nom: 'Test',
      prenom: 'User',
      dateNaissance: '2000-01-01',
      telephone: '0612345678',
      adresse: 'Test Address',
    });

    component.onSubmit();

    expect(mockPatientService.addPatient).toHaveBeenCalledWith(component.patientForm.value);
  });

  it('should display an error on service failure', () => {
    mockPatientService.addPatient.and.returnValue(throwError(() => new Error('Service error')));
    component.patientForm.setValue({
      cin: 'AB123456',
      nom: 'Test',
      prenom: 'User',
      dateNaissance: '2000-01-01',
      telephone: '0612345678',
      adresse: 'Test Address',
    });

    component.onSubmit();

    expect(mockPatientService.addPatient).toHaveBeenCalled();
  });
});
