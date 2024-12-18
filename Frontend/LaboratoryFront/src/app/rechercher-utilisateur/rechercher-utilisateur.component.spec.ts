import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RechercherUtilisateurComponent } from './rechercher-utilisateur.component';

describe('RechercherUtilisateurComponent', () => {
  let component: RechercherUtilisateurComponent;
  let fixture: ComponentFixture<RechercherUtilisateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RechercherUtilisateurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RechercherUtilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
