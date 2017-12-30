package daoObjects;

public enum GradingFormat {
	LETTER_GRADE(0, "Letter Grade"), PRESENTATION(1, "Presentation");

	private int id;
	private String gradingFormat;

	private GradingFormat(int id, String gradingFormat) {
		this.id = id;
		this.gradingFormat = gradingFormat;
	}

	public int getId() {
		return id;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public static GradingFormat getById(int id) {
		for (GradingFormat v : values()) {
			if (v.getId() == id) {
				return v;
			}
		}
		return null;
	}

	public static GradingFormat getByGradingFormat(String gradingFormat) {
		for (GradingFormat v : values()) {
			if (v.getGradingFormat().equals(gradingFormat)) {
				return v;
			}
		}
		return null;
	}

}
