export class Laboratoire {
  id?: number;
  nom: string;
  logo: string;
  nrc: string;
  active: boolean;
  dateActivation: Date;

  constructor(nom: string, logo: string, nrc: string, active: boolean, dateActivation: Date, id?: number) {
    this.id = id;
    this.nom = nom;
    this.logo = logo;
    this.nrc = nrc;
    this.active = active;
    this.dateActivation = dateActivation;
  }
}
