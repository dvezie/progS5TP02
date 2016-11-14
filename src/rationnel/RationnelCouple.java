package rationnel;

import types.Rationnel;
import util.Couple;
import util.Outils;

public class RationnelCouple implements Rationnel {

	private Couple<Integer> _couple;
	
	/**
	   * initialiser un rationnel � partir d'un entier : nb/1
	   * @param num : valeur du num�rateur
	   */
	public RationnelCouple(int num) {
		this._couple.setFirst(num);
		this._couple.setSecond(1);
	}
	
	/**
	   * initialiser un rationnel avec numerateur et d�nominateur
	   * @param num : valeur du num�rateur
	   * @param den : valeur du d�nominateur
	   * @pre den != 0
	   * @post fraction irr�ductible et d�nominateur > 0
	   */
	public RationnelCouple(int num, int den) {
		assert den != 0 : "*** PR�-CONDITION NON V�RIFI�E : den doit �tre != 0"; 
		
		this._couple.setFirst(num);
		this._couple.setSecond(den);
		
		this.simplifier();
	}
	
	/**
	   * initialiser un rationnel � partir d'un autre
	   * @param r : rationnel � dupliquer
	   */
	public RationnelCouple(Rationnel r) {
		//r = this.simplifier(r);
		this._couple.setSecond(r.getDenominateur());
		this._couple.setFirst(r.getNumerateur());
		this.simplifier();
	}
	
	public void simplifier() {
		//////////On simplifie la fraction //////////
		// Gestion des signes
		if ((this._couple.getFirst()<0 && this._couple.getSecond()<0)||(this._couple.getFirst()>=0 && this._couple.getSecond()<0)) {
			this._couple.setFirst(-this._couple.getFirst());
			this._couple.setSecond(-this._couple.getSecond());
		}
		// Si la fraction est n�gative alors on change le signe de 
		// celle-ci le temps de calculer le pgcd
		int pgcd = 0;
		if(this._couple.getFirst() < 0) {
			pgcd = Outils.pgcd(-this._couple.getFirst(), this._couple.getSecond());
		} else if(this._couple.getFirst() > 0) {
			pgcd = Outils.pgcd(this._couple.getFirst(), this._couple.getSecond());			
		}		
		// Gestion des valeurs
		if(pgcd > 1) {
			this._couple.setFirst(this._couple.getFirst() / pgcd);
			this._couple.setSecond(this._couple.getSecond() / pgcd);
		}
	}
	
	@Override
	public boolean equals(Rationnel r) {
		return r.getNumerateur() == this._couple.getFirst() && r.getDenominateur() == this._couple.getSecond(); 
	}

	@Override
	public Rationnel somme(Rationnel r) {
		int somNum, somDen;
		somNum = this._couple.getFirst()*r.getDenominateur() + this._couple.getSecond()*r.getNumerateur();
		somDen = this._couple.getSecond()*r.getDenominateur();
		
		//return this.simplifier(somNum, somDen);
		return new RationnelSimple(somNum, somDen);
	}
	
	@Override
	public Rationnel inverse() {
		assert this._couple.getFirst() != 0 : "*** PR�-CONDITION NON V�RIFI�E *** : this.num doit �tre != 0"; 
		
		return new RationnelSimple(this._couple.getSecond(), this._couple.getFirst());
	}

	@Override
	public double valeur() {
		return (double)this._couple.getFirst() / this._couple.getSecond();
	}
	
	@Override
	public String toString() {
		return this._couple.getFirst() + "/" + this._couple.getSecond();
	}

	@Override
	public int getNumerateur() {
		return this._couple.getFirst();
	}

	@Override
	public int getDenominateur() {
		return this._couple.getSecond();
	}

	@Override
	public int compareTo(Rationnel autre) {
		if(this._couple.getFirst()*autre.getDenominateur() == this._couple.getSecond()*autre.getNumerateur()) {
			return 0;
		} else if(this._couple.getFirst()*autre.getDenominateur() > this._couple.getSecond()*autre.getNumerateur()) {
			return 1;
		} else {
			return -1;
		}
	}

}
