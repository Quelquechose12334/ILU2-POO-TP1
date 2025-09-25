package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		if (marche.trouverEtalLibre() == -1) {
			return "Il n'y a plus d'étals libres pour installer ce vendeur.";
		}
		return "";
	}
	
	public static class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (!etals[indiceEtal].isEtalOccupe()) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}
		
		public int trouverEtalLibre() {
			int i = 0;
			while (i < etals.length) {
				if (etals[i].isEtalOccupe()) {
					i++;
				} else {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			int[] tab = new int[etals.length];
			int j = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					tab[j] = i;
					j++;
				}
			}
			Etal[] etalsTrouves = new Etal[j];
			for (int i = 0; i < j; i++) {
				etalsTrouves[i] = etals[tab[i]];
			}
			return etalsTrouves;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			int i = 0;
			while (i < etals.length) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				} else {
					i++;
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int i = 0;
			int nbEtalsVides = 0;
			while (i < etals.length) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					nbEtalsVides++;
				}
				i++;
			}
			chaine.append("Il reste " + nbEtalsVides + " étals non utilisés dans le marché.");
			return chaine.toString();
		}
	}
}