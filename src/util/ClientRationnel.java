package util;

import java.util.Scanner;

import rationnel.RationnelSimple;
import types.Rationnel;

public class ClientRationnel {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Rationnel nouveau = lireRationnel(input), precedent = new RationnelSimple(0);
		String strAffichage;
		int capacite = 5;
		Rationnel[] lesRationnels = new Rationnel[capacite];
		int iVerif;
		int nb = 0;
		
		while(nouveau.valeur() != 0) {
			// affichage du dernier rationnel lu
			System.out.println("Le rationnel lu est : " + nouveau.toString());		
			
			// affichage de l'inverse du rationnel
			System.out.println("L'inverse du rationnel est : " + nouveau.inverse());

			// affichage de la valeur réelle du rationnel
			System.out.println("La valeur réelle du rationnel est : " + nouveau.valeur());

			// comparaison avec le précédent (compareTo)
			iVerif = nouveau.compareTo(precedent);
			strAffichage = iVerif < 0 ? "inférieur" : (iVerif > 0 ? "supérieur" : "égal");
			System.out.println("\n(compareTo) Le rationnel est " + strAffichage + " au précédent.");

			// comparaison avec le précédent (equals)
			strAffichage = nouveau.equals(precedent) ? "est" : "n'est pas";
			System.out.println("(equals) Le rationnel " + strAffichage + " égal au précédent.");
			
			// calcul & affiche de la somme du rationnel avec le précédent (0 à la 1ère itération)
			strAffichage = "\nSomme avec le précédent : ";
			strAffichage += nouveau.somme(precedent);
			//strAffichage += precedent.getNumerateur() != 0 ? r.somme(precedent) : 0;
			System.out.println(strAffichage);						
			
			insererRationnel(nouveau, lesRationnels, nb);
			if(nb<capacite) {				
				nb++;
			} else {
				System.out.println("\n!!! Tableau plein !!!\n");
			}
			afficher(lesRationnels, nb);
			System.out.println("Somme des rationnels du tableau : "+sommeRationnels(lesRationnels,nb).toString());
			
			precedent = nouveau;		
			nouveau = lireRationnel(input); 
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
		return new RationnelSimple(num, den);
	}
	
	/**
	 * Affiche (fraction et valeur) les nb premiers éléments d’un tableau de rationnels ; le tableau 
	 * est supposé créé et initialisé avant l’appel et (bien sûr) 0 ≤ nb ≤ lesRationnels.length
	 * @param lesRationnels
	 * @param nb
	 */
	static void afficher (Rationnel [ ] lesRationnels , int nb) {
		assert nb <= lesRationnels.length : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : nb doit être inférieur à la taille du tableau";		
		for(int i=0 ; i<nb ; i++) {
			System.out.println(lesRationnels[i].toString()+" = "+lesRationnels[i].valeur());
		}
	}
	
	/**
	 * insérer le rationnel nouveau dans le tableau lesRationnels 
	 * @pre : tableau trié ( ordre croissant )
	 * @param nb : nombre d ’ éléments dans le tableau avant ajout 
	 * @pre : 0 ≤ nb < lesRationnels.length
	 * @post : tableau trié ( ordre croissant )   
	 */
	static void insererRationnel ( Rationnel nouveau , Rationnel [ ] lesRationnels , int nb ) {
		assert nb <= lesRationnels.length : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : nb doit être inférieur à la taille du tableau";
		if(nb == 0) {
			lesRationnels[0] = nouveau;	
		} else {
			for(int i=nb ; i>0 ; i--) {
				if(nouveau.valeur()>lesRationnels[i-1].valeur()) {
					if(i<lesRationnels.length) {
						lesRationnels[i] = nouveau;
					} else {
						lesRationnels[i-1] = nouveau;
					}		
					i=0;
				} else {
					if(i<lesRationnels.length) {
						lesRationnels[i] = lesRationnels[i-1];
						lesRationnels[i-1] = nouveau;
					} else {
						lesRationnels[i-1] = nouveau;
					}
					
				}
			}
		}				
	 }
	
	 /**
	  * renvoie somme des n premiers élem du tableau
	  * @param nb : nombre d ’ éléments dans le tableau 
	  * @pre : 0 ≤ nb < lesRationnels.length
	  * @return Rationnel : somme des rationnels 
	  */
	 static Rationnel sommeRationnels(Rationnel [ ] lesRationnels , int nb) {
		 assert nb <= lesRationnels.length : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : nb doit être inférieur à la taille du tableau";
		 Rationnel sommeR = new RationnelSimple(0);
		 for(int i=0 ; i<nb ; i++) {
			 sommeR = sommeR.somme(lesRationnels[i]);
		 }
		 return sommeR;
	 }
}
