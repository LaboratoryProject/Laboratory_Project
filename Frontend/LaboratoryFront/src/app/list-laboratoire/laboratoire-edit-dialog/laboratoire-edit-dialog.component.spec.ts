import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaboratoireEditDialogComponent } from './laboratoire-edit-dialog.component';

describe('LaboratoireEditDialogComponent', () => {
  let component: LaboratoireEditDialogComponent;
  let fixture: ComponentFixture<LaboratoireEditDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LaboratoireEditDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaboratoireEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
