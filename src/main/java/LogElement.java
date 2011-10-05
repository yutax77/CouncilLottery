import java.util.List;

public class LogElement {
	private String chairman;
	private String secretary;
	private List<String> snack;
	
	public LogElement(String chairman, String secretary, List<String> snack) {
		this.chairman = chairman;
		this.secretary = secretary;
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

}