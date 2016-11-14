package util;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import types.Rationnel;
import rationnel.RationnelCouple;

public class TestUnitaireRationnelCouple
{

  void appelFonctionInsertion(Rationnel r, Rationnel[ ] tr, int N)
  {
    util.ClientRationnel.insererRationnel(r, tr, N);
  }

  @Test(timeout=20)
  // test constructeur � un param�tre
  public void test_Constructeur1() {
    System.out.print("\nconstructeur � un param�tre : ");
    Rationnel r1 = new RationnelCouple(3);
    Assert.assertTrue(r1.getNumerateur() == 3);
    Assert.assertTrue(r1.getDenominateur() == 1);
    System.out.println("test r�ussi");
  }

  // test constructeur � 2 param�tres : cas d'un d�nominateur nul
  @Test(timeout=20, expected = AssertionError.class)
  public void test_Constructeur2_EchecAssertion() {
    System.out.print("\nconstructeur � 2 param�tres : assertion d�nominateur non nul");
    new RationnelCouple(3, 0);
  }

  @Test(timeout=20)
  // test constructeur � 2 param�tres : divers cas
  public void test_Constructeur2() {
    System.out.print("\nconstructeur � 2 param�tres : v�rifier simplifications : ");
    // simplification non n�cessaire
    Rationnel r1 = new RationnelCouple(3, 1);
    Assert.assertTrue(r1.getNumerateur() == 3);
    Assert.assertTrue(r1.getDenominateur() == 1);
    // simplification obligatoire
    Rationnel r2 = new RationnelCouple(6, 2);
    Assert.assertTrue(r2.getNumerateur() == 3);
    Assert.assertTrue(r2.getDenominateur() == 1);
    // simplification et changement signe
    Rationnel r3 = new RationnelCouple(-21, -7);
    Assert.assertTrue(r3.getNumerateur() == 3);
    Assert.assertTrue(r3.getDenominateur() == 1);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  // test constructeur de copie
  public void test_Constructeur3() {
    System.out.print("\nconstructeur de copie : v�rifier �galit� original/copie : ");
    Rationnel r1 = new RationnelCouple(-21, -7);
    Rationnel r2 = new RationnelCouple(r1);
    Assert.assertTrue(r1.getNumerateur() == r2.getNumerateur() &&
		      r1.getDenominateur() == r2.getDenominateur());
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void testGetNumerateur() {
    System.out.print("\nv�rifier getNumerateur : ");
    Rationnel r1 = new RationnelCouple(-21, -4);
    Assert.assertTrue(r1.getNumerateur() == 21);
    Rationnel r2 = new RationnelCouple(-21, -7);
    Assert.assertTrue(r2.getNumerateur() == 3);
    Rationnel r3 = new RationnelCouple(-5);
    Assert.assertTrue(r3.getNumerateur() == -5);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void testGetDenominateur() {
    System.out.print("\nv�rifier getDenominateur : ");
    Rationnel r1 = new RationnelCouple(-21, -4);
    Assert.assertTrue(r1.getDenominateur() == 4);
    Rationnel r2 = new RationnelCouple(-21, -7);
    Assert.assertTrue(r2.getDenominateur() == 1);
    Rationnel r3 = new RationnelCouple(-5);
    Assert.assertTrue(r3.getDenominateur() == 1);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  // test de la m�thode equals
  public void testEqualsRationnel() {
    System.out.print("\nTester �galit� : ");
    Rationnel r1 = new RationnelCouple(-21, -7);
    Rationnel r2 = new RationnelCouple(r1);
  
    Assert.assertTrue(r1.equals(r2));
    Assert.assertTrue(r2.equals(r1));
  
    Rationnel r3 = new RationnelCouple(3);
    Assert.assertTrue(r1.equals(r3));
    Assert.assertTrue(r3.equals(r1));
  
    Rationnel r4 = new RationnelCouple(4);
    Assert.assertTrue(! r1.equals(r4));
    Assert.assertTrue(! r4.equals(r3));
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  // test de la somme de 2 rationnels
  public void testSomme() {
    System.out.print("\nTester somme : ");
    Rationnel r1 = new RationnelCouple(-21, -7);
    Rationnel r3 = new RationnelCouple(3);
    Rationnel somme = r1.somme(r3);
    Assert.assertTrue(somme.getNumerateur() == 6 && somme.getDenominateur() == 1);
  
    Rationnel r2 = new RationnelCouple(-3);
    somme = r2.somme(r1);
    Assert.assertTrue(somme.getNumerateur() == 0 && somme.getDenominateur() == 1);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20, expected = AssertionError.class)
  // inversion d'une fraction de valeur 0
  public void testInverseEchecAssert() {
    System.out.print("\nTester inverse : assertion ...");
    Rationnel zero = new RationnelCouple(0);
    Rationnel inverse = zero.inverse();
  }

  @Test(timeout=20)
  // inversion d'une fraction non nulle
  public void testInverse() {
    System.out.print("\nTester inverse : ");
    Rationnel r1 = new RationnelCouple(-21, -4);
    Rationnel inverse = r1.inverse();
    Assert.assertTrue(inverse.getNumerateur() == r1.getDenominateur() && 
		      inverse.getDenominateur() == r1.getNumerateur());
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  // valeur d'une fraction
  public void testValeur() {
    System.out.print("\nTester valeur d'une fraction : ");
    Rationnel r1 = new RationnelCouple(-21, -4);
    double val_r1 = r1.valeur();
    Assert.assertTrue(val_r1 == 5.25);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void testCompareTo() {
    System.out.print("\nTester compareTo : ");
    Rationnel r1 = new RationnelCouple(-21, -4);
    Assert.assertTrue(r1.compareTo(r1) == 0);
    Rationnel r2 = new RationnelCouple(-21, -7);
    Assert.assertTrue(r1.compareTo(r2) > 0);
    Assert.assertTrue(r2.compareTo(r1) < 0);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void inserer_tableau_vide() {
    System.out.print("\ninsertion dans un tableau vide              : ");
    // insertion dans un tableau vide
    Rationnel [] resu = new Rationnel[]{new RationnelCouple(1, 2)};

    Rationnel [] lesRationnels = new Rationnel[1];
    Rationnel r1 = new RationnelCouple(1, 2);
    appelFonctionInsertion(r1, lesRationnels, 0);
    tester_egalite(lesRationnels, resu, 1);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void inserer_tete_tableau_1elt() {
    System.out.print("\ninsertion en t�te d'un tableau d'un �l�ment : ");
    Rationnel [] resu = new Rationnel[]{new RationnelCouple(1, 3),
					new RationnelCouple(1, 2)};

    Rationnel [] lesRationnels = new Rationnel[2];
    lesRationnels[0] = new RationnelCouple(1, 2);
    appelFonctionInsertion(new RationnelCouple(1, 3), lesRationnels, 1);
    tester_egalite(lesRationnels, resu, 2);
    
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void inserer_fin_tableau_1elt() {
    System.out.print("\ninsertion en fin  d'un tableau d'un �l�ment : ");
    Rationnel [] resu = new Rationnel[]{new RationnelCouple(1, 3),
					new RationnelCouple(1, 2)};

    Rationnel [] lesRationnels = new Rationnel[2];
    lesRationnels[0] = new RationnelCouple(1, 3);
    appelFonctionInsertion(new RationnelCouple(1, 2), lesRationnels, 1);
    tester_egalite(lesRationnels, resu, 2);
    
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void inserer_tete() {
    System.out.print("\ninsertion en t�te                           : ");
    int [] numerateurs   = {
      15,14,13,12,11,10,9,8,7,6,
    };
    int [] denominateurs = {
      5,5,5,5,5,5,5,5,5,5,
    };
    Rationnel [] resu = new Rationnel[numerateurs.length];
    for (int i = 0 ; i < numerateurs.length;  ++i) {
      resu[resu.length - i - 1] = new RationnelCouple(numerateurs[i], denominateurs[i]);
    }

    // ins�rer
    Rationnel [] lesRationnels = new Rationnel[numerateurs.length];
    for (int i = 0; i < numerateurs.length; ++i) {
      appelFonctionInsertion(new RationnelCouple(numerateurs[i], denominateurs[i]),
				       lesRationnels, i);
    }
    tester_egalite(lesRationnels, resu, numerateurs.length);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void inserer_fin() {
    System.out.print("\ninsertion en fin                            : ");
    int [] numerateurs   = {
      15,14,13,12,11,10,9,8,7,6,
    };
    int [] denominateurs = {
      5,5,5,5,5,5,5,5,5,5,
    };
    Rationnel [] resu = new Rationnel[numerateurs.length];
    for (int i = 0 ; i < numerateurs.length;  ++i) {
      resu[resu.length - i - 1] = new RationnelCouple(numerateurs[i], denominateurs[i]);
    }

    // ins�rer
    Rationnel [] lesRationnels = new Rationnel[numerateurs.length];
    for (int i = numerateurs.length - 1; i >= 0; --i) {
      appelFonctionInsertion(new RationnelCouple(numerateurs[i], denominateurs[i]),
				       lesRationnels, lesRationnels.length - i - 1);
    }
    tester_egalite(lesRationnels, resu, numerateurs.length);
    System.out.println("test r�ussi");
  }

  @Test(timeout=20)
  public void inserer_partout() {
    System.out.print("\ninsertion partout                           : ");
    int [] numerateurs   = {
      15,3,-5,10,11,-6,-8,7,13,14,
    };
    int [] denominateurs = {
      5,5,5,5,5,5,5,5,5,5,
    };
    Rationnel [] resu = new Rationnel[numerateurs.length];
    int [] numerateurs_tris = Arrays.copyOf(numerateurs, numerateurs.length);
    Arrays.sort(numerateurs_tris);
    for (int i = 0 ; i < numerateurs_tris.length;  ++i) {
      resu[i] = new RationnelCouple(numerateurs_tris[i], denominateurs[i]);
    }

    // ins�rer
    Rationnel [] lesRationnels = new Rationnel[numerateurs.length];
    for (int i = 0 ; i < numerateurs.length;  ++i) {
      appelFonctionInsertion(new RationnelCouple(numerateurs[i], denominateurs[i]),
				       lesRationnels, i);
    }
    tester_egalite(lesRationnels, resu, numerateurs.length);
    System.out.println("test r�ussi");
  }

  // v�rifier l'�galit� des nb premiers �l�ments de deux tableaux
  void tester_egalite(Rationnel [] t1, Rationnel [] t2, int nb) {
    for(int i = 0; i < nb; ++i) {
      Assert.assertTrue(t1[i].equals(t2[i]));
    }
  }
}