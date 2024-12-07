export class Patient {
  id: number;
  nom: string;
  prenom: string;
  cin: string; // CIN pour identifier le patient
  dateNaissance: string;
  adresse: string;

  constructor(id: number, nom: string, prenom: string, cin: string, dateNaissance: string, adresse: string) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.cin = cin;
    this.dateNaissance = dateNaissance;
    this.adresse = adresse;
  }
}
