package util;

public class Outils
{
  /**
   * Calculer le pgcd de deux entiers selon l'algorithme d'Euclide
   * @param a : entier > 0
   * @param b : entier > 0
   * @return  : pgcd de a et b
   */
  public static int pgcd(int a, int b) {
    if (b == 0) { return a; }
    else	{ return pgcd(b, a % b); }
  }
}
