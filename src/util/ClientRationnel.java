package util;

import java.util.Scanner;

import rationnel.RationnelCouple;
import rationnel.RationnelSimple;
import types.Rationnel;

public class ClientRationnel {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Rationnel r = lireRationnel(input), rPrec = new RationnelSimple(0);
		String strAffichage;
		int iVerif;
		
		while(r.valeur() != 0) {
			// affichage du dernier rationnel lu
			System.out.println("Le rationnel lu est : " + r.toString());		
			
			// affichage de l'inverse du rationnel
			System.out.println("L'inverse du rationnel est : " + r.inverse());

			// affichage de la valeur réelle du rationnel
			System.out.println("La valeur réelle du rationnel est : " + r.valeur());

			// comparaison avec le précédent (compareTo)
			iVerif = r.compareTo(rPrec);
			strAffichage = iVerif < 0 ? "inférieur" : (iVerif > 0 ? "supérieur" : "égal");
			System.out.println("\n(compareTo) Le rationnel est " + strAffichage + " au précédent.");

			// comparaison avec le précédent (equals)
			strAffichage = r.equals(rPrec) ? "est" : "n'est pas";
			System.out.println("(equals) Le rationnel " + strAffichage + " égal au précédent.");
			
			// calcul & affiche de la somme du rationnel avec le précédent (0 à la 1ère itération)
			strAffichage = "\nSomme avec le précédent : ";
			strAffichage += r.somme(rPrec);
			//strAffichage += rPrec.getNumerateur() != 0 ? r.somme(rPrec) : 0;
			System.out.println(strAffichage);			
			
			rPrec = r;
			r = lireRationnel(input); 
		}

	}
	
	/**
	 * Demande la saisie d'une rationnel et renvoie le rationnel lu
	 * @param input : Scanner 
	 * @return RationnelSimple le rationnel lu
	 */
	static Rationnel lireRationnel(Scanner input) {
		System.out.print("\nSaisir le numérateur : ");
		int num = input.nextInt();
		System.out.print("Saisir le dénominateur : ");
		int den = input.nextInt();
		return makeRationnel(num, den);		
	}

	/**
	 * Crée et renvoie une instance de RationnelSimple initialisée avec les paramètres de la fonction
	 * @param num : int numérateur
	 * @param den : int dénominateur
	 * @return Rationnel : instance de RationnelSimple
	 * @pre den != 0
	 */
	static Rationnel makeRationnel(int num, int den) {
		assert den != 0 : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : den doit être != 0";
		//return new RationnelSimple(num, den);
		return new RationnelCouple(num, den);
	}
	
	/**
	 * Affiche (fraction et valeur) les nb premiers éléments d’un tableau de rationnels ; le tableau 
	 * est supposé créé et initialisé avant l’appel et (bien sûr) 0 ≤ nb ≤ lesRationnels.length
	 * @param lesRationnels
	 * @param nb
	 */
	static void afficher (Rationnel [ ] lesRationnels , int nb) {
		assert nb < lesRationnels.length : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : nb doit être inférieur à la taille du tableau";
		for(int i=0 ; i<nb ; i++) {
			System.out.println(lesRationnels[i].toString()+" = "+lesRationnels[i].valeur());
		}
	}
}
