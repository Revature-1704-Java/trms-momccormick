package models;

public class ReimbursementNote {

	Reimbursement reimbursement;
	NoteReason noteReason;
	String note;
	public ReimbursementNote(Reimbursement reimbursement, NoteReason noteReason, String note) {
		super();
		this.reimbursement = reimbursement;
		this.noteReason = noteReason;
		this.note = note;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((noteReason == null) ? 0 : noteReason.hashCode());
		result = prime * result + ((reimbursement == null) ? 0 : reimbursement.hashCode());
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
		ReimbursementNote other = (ReimbursementNote) obj;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (noteReason != other.noteReason)
			return false;
		if (reimbursement == null) {
			if (other.reimbursement != null)
				return false;
		} else if (!reimbursement.equals(other.reimbursement))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementNote [reimbursement=" + reimbursement + ", noteReason=" + noteReason + ", note=" + note
				+ "]";
	}

}
