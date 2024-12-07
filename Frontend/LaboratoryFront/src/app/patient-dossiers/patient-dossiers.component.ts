import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../services/patient.service';

@Component({
  selector: 'app-patient-dossiers',
  templateUrl: './patient-dossiers.component.html',
  styleUrls: ['./patient-dossiers.component.css'],
})
export class PatientDossiersComponent implements OnInit {
  patientDossiers: any[] = [];
  cin: string = '';

  constructor(
    private route: ActivatedRoute,
    private patientService: PatientService
  ) {}

  ngOnInit(): void {
    this.cin = this.route.snapshot.paramMap.get('cin') || '';
    this.patientService.getDossiersByCin(this.cin).subscribe((data) => {
      this.patientDossiers = data;
    });
  }

  addAnalyse(dossierId: number): void {
    // Rediriger vers une interface pour ajouter une analyse
    console.log(`Ajouter une analyse pour le dossier ${dossierId}`);
  }
}
