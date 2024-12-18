import { Component, Inject, ViewChild, ElementRef, OnInit, AfterViewInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { UtilisateurService } from '../../utilisateur.service';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { SideBarSuperAdminComponent } from '../../side-bar-super-admin/side-bar-super-admin.component';

@Component({
  selector: 'app-modifier-admin-dialog',
  templateUrl: './modifier-admin-dialog.component.html',
  standalone:true,
  imports:[CommonModule,
        FormsModule,
        MatDialogModule,
        MatCardModule,
        MatDialogModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        HttpClientModule,
        MatToolbarModule,
        MatSelectModule,],
  styleUrls: ['./modifier-admin-dialog.component.css']
})
export class ModifierAdminDialogComponent implements OnInit, AfterViewInit {
  @ViewChild('utilisateurForm') utilisateurForm!: NgForm;
  @ViewChild('signatureCanvas', { static: false }) signatureCanvas!: ElementRef<HTMLCanvasElement>;

  utilisateur: any;
  private isDrawing = false;
  private lastX = 0;
  private lastY = 0;

  constructor(
    public dialogRef: MatDialogRef<ModifierAdminDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private utilisateurService: UtilisateurService
  ) {}

  ngOnInit() {
    // Deep clone the data to avoid direct mutation
    this.utilisateur = { ...this.data };
  }

  ngAfterViewInit() {
    this.setupSignatureCanvas();
  }

  private setupSignatureCanvas() {
    if (!this.signatureCanvas) return;

    const canvas = this.signatureCanvas.nativeElement;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    // Set white background
    ctx.fillStyle = 'white';
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // Add event listeners
    canvas.addEventListener('mousedown', this.startDrawing.bind(this));
    canvas.addEventListener('mousemove', this.draw.bind(this));
    canvas.addEventListener('mouseup', this.stopDrawing.bind(this));
    canvas.addEventListener('mouseout', this.stopDrawing.bind(this));
  }

  private startDrawing(e: MouseEvent) {
    const canvas = this.signatureCanvas.nativeElement;
    const rect = canvas.getBoundingClientRect();
    this.isDrawing = true;
    [this.lastX, this.lastY] = [
      e.clientX - rect.left,
      e.clientY - rect.top
    ];
  }

  private draw(e: MouseEvent) {
    if (!this.isDrawing) return;

    const canvas = this.signatureCanvas.nativeElement;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    const rect = canvas.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;

    ctx.beginPath();
    ctx.moveTo(this.lastX, this.lastY);
    ctx.lineTo(x, y);
    ctx.stroke();

    [this.lastX, this.lastY] = [x, y];
  }

  private stopDrawing() {
    this.isDrawing = false;
  }

  onSubmit() {
    if (this.utilisateurForm.valid) {
      // Save signature before submitting
      this.savePad();

      this.utilisateurService.modifyUtilisateur(this.utilisateur).subscribe({
        next: (updatedUtilisateur) => {
          this.dialogRef.close(updatedUtilisateur);
        },
        error: (error) => {
          console.error('Error modifying utilisateur', error);
          // Consider adding user-friendly error handling
        }
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  clearPad() {
    const canvas = this.signatureCanvas.nativeElement;
    const ctx = canvas.getContext('2d');
    if (ctx) {
      ctx.fillStyle = 'white';
      ctx.fillRect(0, 0, canvas.width, canvas.height);
      this.utilisateur.signature = null;
    }
  }

  savePad() {
    const canvas = this.signatureCanvas.nativeElement;
    const dataURL = canvas.toDataURL();
    this.utilisateur.signature = dataURL;
  }
}