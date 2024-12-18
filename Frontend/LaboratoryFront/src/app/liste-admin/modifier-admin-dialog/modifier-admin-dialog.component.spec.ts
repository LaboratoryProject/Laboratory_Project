import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierAdminDialogComponent } from './modifier-admin-dialog.component';

describe('ModifierAdminDialogComponent', () => {
  let component: ModifierAdminDialogComponent;
  let fixture: ComponentFixture<ModifierAdminDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModifierAdminDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifierAdminDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
