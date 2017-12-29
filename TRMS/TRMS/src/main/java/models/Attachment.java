package models;

public class Attachment {
	
	Reimbursement reimbursement;
	String attachmentPath;
	
	
	public Attachment(Reimbursement reimbursement, String attachmentPath) {
		super();
		this.reimbursement = reimbursement;
		this.attachmentPath = attachmentPath;
	}

	public Reimbursement getReimbursement() {
		return reimbursement;
	}


	public void setReimbursement(Reimbursement reimbursement) {
		this.reimbursement = reimbursement;
	}


	public String getAttachmentPath() {
		return attachmentPath;
	}


	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachmentPath == null) ? 0 : attachmentPath.hashCode());
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
		Attachment other = (Attachment) obj;
		if (attachmentPath == null) {
			if (other.attachmentPath != null)
				return false;
		} else if (!attachmentPath.equals(other.attachmentPath))
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
		return "Attachment [reimbursement=" + reimbursement + ", attachmentPath=" + attachmentPath + "]";
	}
	
}
