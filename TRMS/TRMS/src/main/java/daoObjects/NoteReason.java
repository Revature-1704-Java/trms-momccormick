package daoObjects;

public enum NoteReason {
	REIMBURSEMENT_DENIED(0, "Reimbursement Denied"), EXCESSIVE_AWARD(1, "Reimbursement Amount Exceeded");

	private int id;
	private String noteReason;

	private NoteReason(int id, String noteReason) {
		this.id = id;
		this.noteReason = noteReason;
	}

	public int getId() {
		return id;
	}

	public String getNoteReason() {
		return noteReason;
	}

	public static NoteReason getById(int id) {
		for (NoteReason v : values()) {
			if (v.getId() == id) {
				return v;
			}
		}
		return null;
	}

	public static NoteReason getByNoteReason(String noteReason) {
		for (NoteReason v : values()) {
			if (v.getNoteReason().equals(noteReason)) {
				return v;
			}
		}
		return null;
	}

}
