package models;

public class ReimbursementNote {

	private int id;
	Reimbursement reimbursement;
	NoteReason noteReason;
	String note;

	public ReimbursementNote() {
		super();
	}

	public ReimbursementNote(int id, Reimbursement reimbursement, NoteReason noteReason, String note) {
		this.id = id;
		this.reimbursement = reimbursement;
		this.noteReason = noteReason;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reimbursement getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(Reimbursement reimbursement) {
		this.reimbursement = reimbursement;
	}

	public NoteReason getNoteReason() {
		return noteReason;
	}

	public void setNoteReason(NoteReason noteReason) {
		this.noteReason = noteReason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "ReimbursementNote [id=" + id + ", reimbursement=" + reimbursement + ", noteReason=" + noteReason
				+ ", note=" + note + "]";
	}

	
}
