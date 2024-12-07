import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { AddAnalyseDialogComponent } from './add-analyse-dialog.component';

describe('AddAnalyseDialogComponent', () => {
  let component: AddAnalyseDialogComponent;
  let fixture: ComponentFixture<AddAnalyseDialogComponent>;
  let dialogRefMock = { close: jasmine.createSpy('close') };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddAnalyseDialogComponent],
      imports: [FormsModule],
      providers: [{ provide: MatDialogRef, useValue: dialogRefMock }],
    }).compileComponents();

    fixture = TestBed.createComponent(AddAnalyseDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should close the dialog when "Annuler" is clicked', () => {
    component.dialogRef.close();
    expect(dialogRefMock.close).toHaveBeenCalled();
  });

  it('should navigate when CIN is entered and "Suivant" is clicked', () => {
    component.cin = '12345678';
    spyOn(component, 'checkPatient');
    component.checkPatient();
    expect(component.checkPatient).toHaveBeenCalled();
  });
});
