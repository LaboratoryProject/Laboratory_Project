import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LaboratoireListComponent } from './list-laboratoire.component';
import { FormsModule } from '@angular/forms';
 // Import your component

@NgModule({
  declarations: [],
  imports: [
     CommonModule,
     FormsModule,
   ],
  exports: [// Export if you need to use it in other modules
  ]
})
export class ListLaboratoireModule { }
