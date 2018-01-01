package daoObjects;

public enum ReimbursementStatus {
	PENDING(1, "Pending"), GRADE_PENDING(2, "Grade Pending"), APPROVAL_PENDING(3, "Approval Pending"), AWARDED(4,
			"Awarded"), CANCELED(5, "Cancelled"), URGENT(6, "Urgent"), DENIED(7, "Denied");

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
