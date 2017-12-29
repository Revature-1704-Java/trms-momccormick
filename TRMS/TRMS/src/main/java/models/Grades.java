package models;

public class Grades {
	GradingFormat gradingFormat;
	GradeScore passingGrade;
	double recievedGrade;
	
	public Grades(GradingFormat gradingFormat, GradeScore passingGrade, double recievedGrade) {
		super();
		this.gradingFormat = gradingFormat;
		this.passingGrade = passingGrade;
		this.recievedGrade = recievedGrade;
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public GradeScore getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(GradeScore passingGrade) {
		this.passingGrade = passingGrade;
	}

	public double getRecievedGrade() {
		return recievedGrade;
	}

	public void setRecievedGrade(double recievedGrade) {
		this.recievedGrade = recievedGrade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
		result = prime * result + ((passingGrade == null) ? 0 : passingGrade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(recievedGrade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grades other = (Grades) obj;
		if (gradingFormat != other.gradingFormat)
			return false;
		if (passingGrade != other.passingGrade)
			return false;
		if (Double.doubleToLongBits(recievedGrade) != Double.doubleToLongBits(other.recievedGrade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grades [gradingFormat=" + gradingFormat + ", passingGrade=" + passingGrade + ", recievedGrade="
				+ recievedGrade + "]";
	}
	
}
