package models;

public class Grades {

	private int id;
	GradingFormat gradingFormat;
	GradeScore passingGrade;
	double recievedGrade;

	public Grades() {
		super();
	}

	public Grades(int id, GradingFormat gradingFormat, GradeScore passingGrade, double recievedGrade) {
		this.id = id;
		this.gradingFormat = gradingFormat;
		this.passingGrade = passingGrade;
		this.recievedGrade = recievedGrade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public String toString() {
		return "Grades [id=" + id + ", gradingFormat=" + gradingFormat + ", passingGrade=" + passingGrade
				+ ", recievedGrade=" + recievedGrade + "]";
	}

	
}
