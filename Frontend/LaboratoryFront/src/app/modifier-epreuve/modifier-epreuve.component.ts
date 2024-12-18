import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EpreuveService } from '../epreuve.service';

@Component({
  selector: 'app-modifier-epreuve',
  templateUrl: './modifier-epreuve.component.html',
  styleUrls: ['./modifier-epreuve.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
  ],
})
export class ModifierEpreuveComponent implements OnInit {
  epreuveForm: FormGroup;
   epreuveId!: number;

  constructor(
    private fb: FormBuilder,
    private epreuveService: EpreuveService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // Définir le formulaire avec les champs nécessaires
    this.epreuveForm = this.fb.group({
      nomEpreuve: ['', Validators.required],
      fkIdAnalyse: [null, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {
    // Récupère l'ID de l'épreuve depuis les paramètres de la route
    this.epreuveId = +this.route.snapshot.paramMap.get('id')!;
    this.loadEpreuve();
  }

  loadEpreuve(): void {
    this.epreuveService.getEpreuveById(this.epreuveId).subscribe(
      (epreuve: { nomEpreuve: string; fkIdAnalyse: number }) => {
        // Charge les données de l'épreuve dans le formulaire
        this.epreuveForm.patchValue({
          nomEpreuve: epreuve.nomEpreuve,
          fkIdAnalyse: epreuve.fkIdAnalyse,
        });
      },
      (error) => {
        console.error('Erreur lors du chargement de l\'épreuve:', error);
        alert('L\'épreuve demandée n\'existe pas ou une erreur est survenue.');
        this.router.navigate(['/epreuves']);
      }
    );
  }

  onSubmit(): void {
    if (this.epreuveForm.valid) {
      this.epreuveService.updateEpreuve(this.epreuveId, this.epreuveForm.value).subscribe(
        () => {
          alert('Épreuve modifiée avec succès');
          this.router.navigate(['/epreuves']);
        },
        (error) => {
          console.error('Erreur lors de la modification de l\'épreuve:', error);
          alert('Erreur lors de la modification de l\'épreuve');
        }
      );
    } else {
      alert('Veuillez remplir tous les champs requis');
    }
  }

  revenir(): void {
    this.router.navigate(['/epreuves']);
  }
}
