import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RechercherUtilisateurComponent } from './rechercher-utilisateur.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { UtilisateurService } from '../utilisateur.service';

describe('RechercherUtilisateurComponent', () => {
  let component: RechercherUtilisateurComponent;
  let fixture: ComponentFixture<RechercherUtilisateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RechercherUtilisateurComponent],
      imports: [HttpClientTestingModule, FormsModule],
      providers: [UtilisateurService]
    }).compileComponents();

    fixture = TestBed.createComponent(RechercherUtilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with default values', () => {
    expect(component.idUtilisateur).toBe(0);
    expect(component.role).toBe('');
    expect(component.utilisateur).toBeNull();
    expect(component.utilisateurs).toEqual([]);
    expect(component.message).toBe('');
  });
});
