package rationnel;

import java.util.Scanner;

import types.Rationnel;

public class ClientRationnel {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Rationnel r = lireRationnel(input), rPrec = new RationnelSimple(0,1);
		String strAffichage;
		int iVerif;
		
		while(r.valeur() != 0) {
			// affichage du dernier rationnel lu
			System.out.println("Le rationnel lu est : " + r.toString());
			
			// calcul & affiche de la somme du rationnel avec le pr�c�dent (0 � la 1�re it�ration)
			strAffichage = "Somme avec le pr�c�dent : ";
			strAffichage += rPrec.getNumerateur() != 0 ? r.somme(rPrec) : 0;
			System.out.println(strAffichage);
			
			// affichage de l'inverse du rationnel
			System.out.println("L'inverse du rationnel est : " + r.inverse());

			// affichage de la valeur r�elle du rationnel
			System.out.println("La valeur r�elle du rationnel est : " + r.valeur());

			// comparaison avec le pr�c�dent (compareTo)
			iVerif = r.compareTo(rPrec);
			strAffichage = iVerif < 0 ? "inf�rieur" : (iVerif > 0 ? "sup�rieur" : "�gal");
			System.out.println("(compareTo) Le rationnel est " + strAffichage + " au pr�c�dent.");

			// comparaison avec le pr�c�dent (�quals)
			strAffichage = r.equals(rPrec) ? "est" : "n'est pas";
			System.out.println("(equals) Le rationnel " + strAffichage + " �gal au pr�c�dent.");
			
			rPrec = r;
			r = lireRationnel(input); 
		}

	}
	
	static Rationnel lireRationnel(Scanner input) {
		System.out.print("\nSaisir le num�rateur : ");
		int num = input.nextInt();
		System.out.print("Saisir le d�nominateur : ");
		int den = input.nextInt();
		return new RationnelSimple(num, den);		
	}

}
