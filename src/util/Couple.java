package util;

public class Couple<T> {
	private T _elt1, _elt2;

	public T getFirst() {
		return _elt1;
	}

	public void setFirst(T elt1) {
		this._elt1 = elt1;
	}

	public T getSecond() {
		return _elt2;
	}

	public void setSecond(T elt2) {
		this._elt2 = elt2;
	}

	/**
	   * Initialise un couple à partir de 2 valeurs
	   * @param elt1 : valeur 1
	   * @param elt2 : valeur 2
	   */
	public Couple(T elt1, T elt2) {
		this._elt1 = elt1;
		this._elt2 = elt2;
	}
	
	/**
	 * Compare l'égalité d'un couple avec un autre passé en paramètre
	 * @param couple : couple avec lequel comparer
	 * @return boolean true si les couples sont égaux, false sinon
	 */
	public boolean equals(Couple<T> couple){
		return this._elt1.equals(couple.getFirst()) && this._elt2.equals(couple.getSecond());
	}
}
