import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TestAnalyseService } from '../test-analyse-service.service';
import { TestAnalyse } from '../test-analyse.model';

import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-test-analyse-form',
  templateUrl: './test-analyse-form.component.html',
  imports: [
    ReactiveFormsModule,
    MatCardModule,     // Importing MatCardModule
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  styleUrls: ['./test-analyse-form.component.css']
})
export class TestAnalyseFormComponent implements OnInit {
  testAnalyseForm!: FormGroup;  // Utilisation du modificateur `!` pour indiquer que cette propriété sera initialisée dans `ngOnInit`

  constructor(private fb: FormBuilder, private AnalyseService: TestAnalyseService) { }

  ngOnInit(): void {
    // Initialisation du formulaire avec les validations
    this.testAnalyseForm = this.fb.group({
      name: ['', Validators.required],
      minValue: [null, [Validators.required, Validators.min(0)]],
      maxValue: [null, [Validators.required, Validators.min(0)]],
      unite: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.testAnalyseForm.valid) {
      const newTestAnalyse: TestAnalyse = this.testAnalyseForm.value;
      this.AnalyseService.createTestAnalyse(newTestAnalyse).subscribe((response: any) => {
        console.log('Test Analyse ajouté avec succès', response);
        // Vous pouvez réinitialiser le formulaire ou naviguer vers une autre page
        this.testAnalyseForm.reset();
      });
    }
  }
}