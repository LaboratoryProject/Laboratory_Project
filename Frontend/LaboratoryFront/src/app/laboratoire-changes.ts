
import { Laboratoire } from "./laboratoire.service"

export interface LaboratoireChanges extends Partial<Laboratoire> {
contactLaboratoire?: {
numTel?: string;
fax?: string;
email?: string;
};
adresse?: {
numVoie?: string;
nomVoie?: string;
codePostal?: string;
ville?: string;
commune?: string;
};
}
