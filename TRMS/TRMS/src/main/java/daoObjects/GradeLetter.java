package daoObjects;

public enum GradeLetter {
	A(1, 'A', 90, 100), B(2, 'B', 80, 90), C(3, 'C', 70, 80), D(4, 'D', 60, 70), F(5, 'F', 0, 60);

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
