package daoObjects;

public enum GradeLetter {
	A(0, 'A', 0.9, 1.0), B(1, 'B', 0.8, 0.9), C(2, 'C', 0.7, 0.8), D(3, 'D', 0.6, 0.7), F(4, 'F', 0.0, 0.6);

	private int id;
	private char gradeLetter;
	private double minPercent;
	private double maxPercent;

	private GradeLetter(int id, char gradeLetter, double minPercent, double maxPercent) {
		this.id = id;
		this.gradeLetter = gradeLetter;
		this.minPercent = minPercent;
		this.maxPercent = maxPercent;
	}

	public int getId() {
		return id;
	}

	public char getGradeLetter() {
		return gradeLetter;
	}

	public double getMinPercent() {
		return minPercent;
	}

	public double getMaxPercent() {
		return maxPercent;
	}

	public static GradeLetter getById(int id) {
		for (GradeLetter v : values()) {
			if (v.getId() == id) {
				return v;
			}
		}
		return null;
	}

	public static GradeLetter getByGradeLetter(char gradeLetter) {
		for (GradeLetter v : values()) {
			if (v.getGradeLetter() == gradeLetter) {
				return v;
			}
		}
		return null;
	}

	public static GradeLetter getByMinPercent(double minPercent) {
		for (GradeLetter v : values()) {
			if (v.getMinPercent() == minPercent) {
				return v;
			}
		}
		return null;
	}

	public static GradeLetter getByMaxPercent(double maxPercent) {
		for (GradeLetter v : values()) {
			if (v.getMaxPercent() == maxPercent) {
				return v;
			}
		}
		return null;
	}

}
