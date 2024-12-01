import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinDialogComponent } from './cin-dialog.component';

describe('CinDialogComponent', () => {
  let component: CinDialogComponent;
  let fixture: ComponentFixture<CinDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CinDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CinDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
