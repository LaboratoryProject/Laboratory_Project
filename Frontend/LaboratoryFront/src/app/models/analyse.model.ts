export class Analyse {
  id: number;
  fkIdLaboratoire: number;
  nom: string;
  description: string;

  constructor(id: number, fkIdLaboratoire: number, nom: string, description: string) {
    this.id = id;
    this.fkIdLaboratoire = fkIdLaboratoire;
    this.nom = nom;
    this.description = description;
  }
}
