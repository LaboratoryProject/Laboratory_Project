import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 // Import your component

@NgModule({
  declarations: [],
  imports: [
    CommonModule, 
    FormsModule, // ,  // Add CommonModule here
  ],
  exports: [// Export if you need to use it in other modules
  ]
})
export class ModifierLaboratoireModule { }
