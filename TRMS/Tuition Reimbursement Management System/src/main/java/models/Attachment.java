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
	
}
