package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois obelix = new Gaulois("Obélix", 25);
		try {
//			etal.libererEtal();
//			etal.acheterProduit(1, null);
//			etal.acheterProduit(-1, obelix);
			etal.acheterProduit(2, obelix);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
	}

}
