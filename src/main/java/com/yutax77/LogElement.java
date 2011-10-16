package com.yutax77;

import java.util.List;
import java.util.Map;

public class LogElement {
	private String chairman;
	private String secretary;
	private List<String> snack;
	
	public LogElement(String chairman, String secretary, List<String> snack) {
		this.chairman = chairman;
		this.secretary = secretary;
		this.snack = snack;
	}

	public LogElement() {}
	
	public String getChairman() {
		return chairman;
	}

	public void setChairman(String chairman) {
		this.chairman = chairman;
	}

	public String getSecretary() {
		return secretary;
	}

	public void setSecretary(String secretary) {
		this.secretary = secretary;
	}

	public List<String> getSnack() {
		return snack;
	}

	public void setSnack(List<String> snack) {
		this.snack = snack;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chairman == null) ? 0 : chairman.hashCode());
		result = prime * result
				+ ((secretary == null) ? 0 : secretary.hashCode());
		result = prime * result + ((snack == null) ? 0 : snack.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LogElement))
			return false;
		LogElement other = (LogElement) obj;
		if (chairman == null) {
			if (other.chairman != null)
				return false;
		} else if (!chairman.equals(other.chairman))
			return false;
		if (secretary == null) {
			if (other.secretary != null)
				return false;
		} else if (!secretary.equals(other.secretary))
			return false;
		if (snack == null) {
			if (other.snack != null)
				return false;
		} else if (!snack.equals(other.snack))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogElement [chairman=").append(chairman)
				.append(", secretary=").append(secretary).append(", snack=")
				.append(snack).append("]");
		return builder.toString();
	}

	public void addChairmanCount(Map<Person, ExpCount> result, int no) {
		Person person = new Person(chairman);
		
		//TODO 書き方に工夫の余地あり？
		ExpCount expCount = result.get(person);
		if(expCount == null) {
			result.put(person, new ExpCount(1, no));
		}
		else {
			result.put(person, expCount.update(no));
		}
	}

}
