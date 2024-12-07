import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaboratoireListComponent } from './list-laboratoire.component';

describe('ListLaboratoireComponent', () => {
  let component: LaboratoireListComponent;
  let fixture: ComponentFixture<LaboratoireListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LaboratoireListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaboratoireListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
