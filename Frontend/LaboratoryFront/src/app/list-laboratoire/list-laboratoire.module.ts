import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LaboratoireListComponent } from './list-laboratoire.component';
import { MatCardModule } from '@angular/material/card';
import { MatDialogActions, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { LaboratoireEditDialogComponent } from './laboratoire-edit-dialog/laboratoire-edit-dialog.component';
import { LaboratoireDetailsDialogComponent } from './laboratoire-details-dialog/laboratoire-details-dialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { SideBarSuperAdminComponent } from '../side-bar-super-admin/side-bar-super-admin.component';

 // Import your component

@NgModule({
  declarations: [
     
    
  
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    HttpClientModule,
    MatToolbarModule,
    MatSelectModule,
    BrowserAnimationsModule,
    SideBarSuperAdminComponent

   // ,  // Add CommonModule here
  ],

  exports: [// Export if you need to use it in other modules
  ]
})
export class ListLaboratoireModule { }
