import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ModifierUtilisateurComponent } from './modifier-utilisateur.component';
import { UtilisateurService } from '../utilisateur.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';

describe('ModifierUtilisateurComponent', () => {
  let component: ModifierUtilisateurComponent;
  let fixture: ComponentFixture<ModifierUtilisateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModifierUtilisateurComponent],
      imports: [HttpClientTestingModule, FormsModule],
      providers: [UtilisateurService]
    }).compileComponents();

    fixture = TestBed.createComponent(ModifierUtilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with default values', () => {
    expect(component.idUtilisateur).toBe(0);
    expect(component.utilisateur).toBeNull();
    expect(component.message).toBe('');
  });
});
