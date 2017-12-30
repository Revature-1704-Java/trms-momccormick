package daoObjects;

public enum ReimbursementStatus {
	PENDING(0, "Pending"), GRADE_PENDING(1, "Grade Pending"), APPROVAL_PENDING(2, "Approval Pending"), AWARDED(3,
			"Awarded"), CANCELED(4, "Cancelled"), URGENT(5, "Urgent"), DENIED(6, "Denied");

	private int id;
	private String reimbursementStatus;

	private ReimbursementStatus(int id, String reimbursementStatus) {
		this.id = id;
		this.reimbursementStatus = reimbursementStatus;
	}

	public int getId() {
		return id;
	}

	public String getReimbursementStatus() {
		return reimbursementStatus;
	}

	public static ReimbursementStatus getById(int id) {
		for (ReimbursementStatus v : values()) {
			if (v.getId() == id) {
				return v;
			}
		}
		return null;
	}

	public static ReimbursementStatus getByReimbursementStatus(String reimbursementStatus) {
		for (ReimbursementStatus v : values()) {
			if (v.getReimbursementStatus().equals(reimbursementStatus)) {
				return v;
			}
		}
		return null;
	}

}
