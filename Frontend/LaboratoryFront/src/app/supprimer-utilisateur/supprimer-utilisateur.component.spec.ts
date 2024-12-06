import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SupprimerUtilisateurComponent } from './supprimer-utilisateur.component';
import { UtilisateurService } from '../utilisateur.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';

describe('SupprimerUtilisateurComponent', () => {
  let component: SupprimerUtilisateurComponent;
  let fixture: ComponentFixture<SupprimerUtilisateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SupprimerUtilisateurComponent],
      imports: [HttpClientTestingModule, FormsModule],
      providers: [UtilisateurService]
    }).compileComponents();

    fixture = TestBed.createComponent(SupprimerUtilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with default values', () => {
    expect(component.idUtilisateur).toBe(0);
    expect(component.message).toBe('');
  });
});
