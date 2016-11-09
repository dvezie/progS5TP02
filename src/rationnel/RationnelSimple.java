package rationnel;

import types.Rationnel;
import util.Outils;

public class RationnelSimple implements Rationnel {

	private int _num, _den;
	
	/**
	   * initialiser un rationnel à partir d'un entier : nb/1
	   * @param num : valeur du numérateur
	   */
	public RationnelSimple(int num) {
		this._num = num;
		this._den = 1;
	}
	
	/**
	   * initialiser un rationnel avec numerateur et dénominateur
	   * @param num : valeur du numérateur
	   * @param den : valeur du dénominateur
	   * @pre den != 0
	   * @post fraction irréductible et dénominateur > 0
	   */
	public RationnelSimple(int num, int den) {
		assert den != 0 : "*** PRÉ-CONDITION NON VÉRIFIÉE : den doit être != 0"; 
		
//		////////// On simplifie la fraction //////////
//		// Gestion des signes
//		if ((num<0 && den<0)||(num>=0 && den<0)) {
//			num = -num;
//			den = -den;
//		}
//		// Gestion des valeurs
//		int pgcd = Outils.pgcd(num, den);
//		if(pgcd > 1) {
//			num = num / pgcd;
//			den = den / pgcd;
//		}
//		///////////////////////////////////////////////
		
		this._den = den;
		this._num = num;
		
		this.simplifier();
	}
	
	/**
	   * initialiser un rationnel à partir d'un autre
	   * @param r : rationnel à dupliquer
	   */
	public RationnelSimple(Rationnel r) {
		//r = this.simplifier(r);
		this._den = r.getDenominateur();
		this._num = r.getNumerateur();
		this.simplifier();
	}
	
	@Override
	public boolean equals(Rationnel r) {
		return r.getNumerateur() == this._num && r.getDenominateur() == this._den; 
	}

	@Override
	public Rationnel somme(Rationnel r) {
		int somNum, somDen;
		somNum = this._num*r.getDenominateur() + this._den*r.getNumerateur();
		somDen = this._den*r.getDenominateur();
		
		//return this.simplifier(somNum, somDen);
		return new RationnelSimple(somNum, somDen);
	}
	
	public void simplifier() {
		//////////On simplifie la fraction //////////
		// Gestion des signes
		if ((this._num<0 && this._den<0)||(this._num>=0 && this._den<0)) {
			this._num = -this._num;
			this._den = -this._den;
		}
		// Si la fraction est négative alors on change le signe de 
		// celle-ci le temps de calculer le pgcd
		int pgcd = 0;
		if(this._num < 0) {
			pgcd = Outils.pgcd(-this._num, this._den);
		} else if(this._num > 0) {
			pgcd = Outils.pgcd(this._num, this._den);			
		}		
		// Gestion des valeurs
		if(pgcd > 1) {
			this._num = this._num / pgcd;
			this._den = this._den / pgcd;
		}
		///////////////////////////////////////////////
	}
	
//	public Rationnel simplifier(int num, int den) {
//		
//		//////////On simplifie la fraction //////////
//		// Gestion des signes
//		if ((num<0 && den<0)||(num>=0 && den<0)) {
//			num = -num;
//			den = -den;
//		}
//		// Gestion des valeurs
//		int pgcd = Outils.pgcd(num, den);
//		if(pgcd > 1) {
//			num = num / pgcd;
//			den = den / pgcd;
//		}
//		///////////////////////////////////////////////
//		
//		return new RationnelSimple(num, den);
//	}
//	public Rationnel simplifier(Rationnel r) {
//		return this.simplifier(r.getNumerateur(), r.getDenominateur());
//	}
//	
//	public Rationnel simplifier() {
//		return this.simplifier(this.getNumerateur(), this.getDenominateur());
//	}
	
	@Override
	public Rationnel inverse() {
		assert this._num != 0 : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : this.num doit être != 0"; 
		
		return new RationnelSimple(this._den, this._num);
	}

	@Override
	public double valeur() {
		return (double)this._num / this._den;
	}
	
	@Override
	public String toString() {
		return this._num + "/" + this._den;
	}

	@Override
	public int getNumerateur() {
		return this._num;
	}

	@Override
	public int getDenominateur() {
		return this._den;
	}

	@Override
	public int compareTo(Rationnel autre) {
		if(this._num*autre.getDenominateur() == this._den*autre.getNumerateur()) {
			return 0;
		} else if(this._num*autre.getDenominateur() > this._den*autre.getNumerateur()) {
			return 1;
		} else {
			return -1;
		}
	}

}
