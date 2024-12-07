import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddAnalyseDialogComponent } from './add-analyse-dialog/add-analyse-dialog.component';

@Component({
  selector: 'app-analyse-dashboard',
  templateUrl: './analyse-dashboard.component.html',
  styleUrls: ['./analyse-dashboard.component.css'],
})
export class AnalyseDashboardComponent {
  constructor(private dialog: MatDialog) {}

  openAddAnalyseDialog(): void {
    this.dialog.open(AddAnalyseDialogComponent, {
      width: '400px',
    });
  }
}
