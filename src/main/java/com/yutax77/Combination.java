package com.yutax77;

import org.apache.commons.lang3.tuple.Pair;

public class Combination implements Comparable<Combination>{
	private double score;
	private Person chairman;
	private Person secretary;
	private Pair<Person, Person> snacPair;
	
	private Combination(CombinationBuilder builder) {
		this.score = builder.score;
		this.chairman = builder.chairman;
		this.secretary = builder.secretary;
		this.snacPair = builder.snackPair;
	}

	public void addChairman(double score, Person chairman) {
		this.score = score;
		this.chairman = chairman;
	}
	
	public int compareTo(Combination o) {
		int result = -(int)(this.score - o.score);
		if(result != 0) 
			return result;
		
		//議長名の辞書順
		result = chairman.compareTo(o.chairman);
		if(result != 0) 
			return result;
		
		//書記名の辞書順
		result = secretary.compareTo(o.secretary);
		if(result != 0) 
			return result;
		
		//おやつ係
		result = snacPair.compareTo(o.snacPair);
		if(result != 0) 
			return result;
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chairman == null) ? 0 : chairman.hashCode());
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((secretary == null) ? 0 : secretary.hashCode());
		result = prime * result + ((snacPair == null) ? 0 : snacPair.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Combination))
			return false;
		Combination other = (Combination) obj;
		if (chairman == null) {
			if (other.chairman != null)
				return false;
		} else if (!chairman.equals(other.chairman))
			return false;
		if (Double.doubleToLongBits(score) != Double
				.doubleToLongBits(other.score))
			return false;
		if (secretary == null) {
			if (other.secretary != null)
				return false;
		} else if (!secretary.equals(other.secretary))
			return false;
		if (snacPair == null) {
			if (other.snacPair != null)
				return false;
		} else if (!snacPair.equals(other.snacPair))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Combination [score=").append(score)
				.append(", chairman=").append(chairman).append(", secretary=")
				.append(secretary).append(", snacPair=").append(snacPair)
				.append("]");
		return builder.toString();
	}

	public static class CombinationBuilder {
		private double score;
		private Person chairman;
		private Person secretary;
		private Pair<Person, Person> snackPair;
		
		public CombinationBuilder chairman(double score, Person chairman) {
			//TODO 重複指定のチェック
			this.score += score;
			this.chairman = chairman;
			return this;
		}
		
		public CombinationBuilder secretary(double score, Person secretary) {
			//TODO 重複指定のチェック
			this.score += score;
			this.secretary = secretary;
			return this;
		}
		
		public CombinationBuilder snack(double score, Pair<Person, Person> snackPair) {
			this.score += score;
			this.snackPair = snackPair;
			return this;
		}
		
		public Combination build() {
			return new Combination(this);
		}
	}
}
