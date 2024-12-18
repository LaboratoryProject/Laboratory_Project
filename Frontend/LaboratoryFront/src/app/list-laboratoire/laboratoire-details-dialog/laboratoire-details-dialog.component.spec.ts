import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LaboratoireDetailsDialogComponent } from './laboratoire-details-dialog.component';

describe('LaboratoireDetailsDialogComponent', () => {
  let component: LaboratoireDetailsDialogComponent;
  let fixture: ComponentFixture<LaboratoireDetailsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LaboratoireDetailsDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaboratoireDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
