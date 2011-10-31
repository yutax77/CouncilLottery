package com.yutax77;

public class Pair<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> implements Comparable<Pair<T1, T2>>{
	private T1 left;
	private T2 right;
	
	private Pair(T1 left, T2 right) {
		this.left = left;
		this.right = right;
	}
	
	public static<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> Pair<T1, T2> of(T1 left, T2 right) {
		return new Pair<T1, T2>(left, right);
	}
	
	public T1 getLeft() {
		return left;
	}
	
	public T2 getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pair))
			return false;
		
		@SuppressWarnings("unchecked")
		Pair<T1, T2> other = (Pair<T1, T2>) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	@Override
	public int compareTo(Pair<T1, T2> o) {
		int result = this.left.compareTo(o.left);
		if(result != 0) return result;
		
		result = this.right.compareTo(o.right);
		return result;
	}
}
