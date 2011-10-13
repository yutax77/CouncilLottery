public class ExpCount implements Comparable<ExpCount>{
	private int count;
	private int lastExpNo;
	
	public ExpCount(int count, int lastExpNo) {
		this.count = count;
		this.lastExpNo = lastExpNo;
	}

	public ExpCount update(int no) {
		return new ExpCount(count + 1, no);
	}

	public int getLastExpNo() {
		return lastExpNo;
	}
	
	public int getCount() {
		return count;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + lastExpNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ExpCount))
			return false;
		ExpCount other = (ExpCount) obj;
		if (count != other.count)
			return false;
		if (lastExpNo != other.lastExpNo)
			return false;
		return true;
	}

	public int compareTo(ExpCount arg0) {
		return this.count - arg0.count;
	}

}
