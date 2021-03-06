package daoObjects;

public enum EventType {
	UNIVERSITY_COURSE(1, "University Course", 0.80), SEMINAR(2, "Seminar", 0.60), CERTIFICATION_PREPARATION_CLASS(3,
			"Certification Preparation Class", 0.75), CERTIFICATION_EXAM(4, "Certification Exam",
					1.00), TECHNICAL_TRAINING(5, "Technical Training", 0.90), OTHER(6, "Other", 0.30);

	private int id;
	private String eventType;
	private double percentCovered;

	private EventType(int id, String eventType, double percentCovered) {
		this.id = id;
		this.eventType = eventType;
		this.percentCovered = percentCovered;
	}

	public int getId() {
		return id;
	}

	public String getEventType() {
		return eventType;
	}

	public double getPercentCovered() {
		return percentCovered;
	}

	public static EventType getById(int id) {
		for (EventType v : values()) {
			if (v.getId() == id) {
				return v;
			}
		}
		return null;
	}

	public static EventType getByEventType(String eventType) {
		for (EventType v : values()) {
			if (v.getEventType().equals(eventType)) {
				return v;
			}
		}
		return null;
	}

	public static EventType getByPercentCovered(double percentCovered) {
		for (EventType v : values()) {
			if (v.getPercentCovered() == percentCovered) {
				return v;
			}
		}
		return null;
	}

}
