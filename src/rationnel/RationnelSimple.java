package rationnel;

import types.Rationnel;
import util.Outils;

public class RationnelSimple implements Rationnel {

	private int _num, _den;
	
	/**
	   * initialiser un rationnel avec numerateur et d�nominateur
	   * @param num : valeur du num�rateur
	   * @param den : valeur du d�nominateur
	   * @pre den != 0
	   * @post fraction irr�ductible et d�nominateur > 0
	   */
	public RationnelSimple(int num, int den) {
		assert den != 0 : "*** PR�-CONDITION NON V�RIFI�E : den doit �tre != 0"; 
		this._den = den;
		this._num = num;
	}
	
	@Override
	public boolean equals(Rationnel r) {
		return r.getNumerateur() == this._num && r.getDenominateur() == this._den; 
	}

	@Override
	public Rationnel somme(Rationnel r) {
		int somNum, somDen, pgcd;
		somNum = this._num*r.getDenominateur() + this._den*r.getNumerateur();
		somDen = this._den*r.getDenominateur();
		
		pgcd = Outils.pgcd(somNum, somDen);
		if(pgcd > 1) {
			somNum = somNum / pgcd;
			somDen = somDen / pgcd;
		}
		
		return new RationnelSimple(somNum, somDen);
	}
	
	@Override
	public Rationnel inverse() {
		assert this._num != 0 : "*** PR�-CONDITION NON V�RIFI�E *** : this.num doit �tre != 0"; 
		
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
