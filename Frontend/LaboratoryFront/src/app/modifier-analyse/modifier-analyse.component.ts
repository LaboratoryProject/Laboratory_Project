import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AnalyseService } from '../analyse.service';

@Component({
  selector: 'app-modifier-analyse',
  templateUrl: './modifier-analyse.component.html',
  styleUrls: ['./modifier-analyse.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
})
export class ModifierAnalyseComponent {
  analyseForm!: FormGroup; // Utilisez "!" pour indiquer qu'il sera initialisé plus tard

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private analyseService: AnalyseService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.fetchAnalyse(+id);
    }
    this.initForm();
  }

  initForm(): void {
    this.analyseForm = this.fb.group({
      nomAnalyse: [''],
      descriptionAnalyse: [''],
      laboratoire: [''],
      epreuve: [''],
      statutAnalyse: [''],
      dateAnalyse: [''],
      coutAnalyse: [''],
    });
  }

  fetchAnalyse(id: number): void {
    this.analyseService.getAnalyseById(id).subscribe((data) => {
      this.analyseForm.patchValue(data);
    });
  }

  onSubmit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.analyseService.updateAnalyse(+id, this.analyseForm.value).subscribe(() => {
        alert('Analyse modifiée avec succès');
        this.router.navigate(['/liste-analyses']);
      });
    }
  }
revenir(): void {
    this.router.navigate(['/liste-analyses']);  // Remplacez par la route souhaitée si nécessaire
  }
}

