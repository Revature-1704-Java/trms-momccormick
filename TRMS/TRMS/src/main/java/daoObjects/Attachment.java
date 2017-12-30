package daoObjects;

public class Attachment {

	private int id;
	Reimbursement reimbursement;
	String attachmentPath;

	public Attachment() {
		super();
	}

	public Attachment(int id, Reimbursement reimbursement, String attachmentPath) {
		this.id = id;
		this.reimbursement = reimbursement;
		this.attachmentPath = attachmentPath;
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

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", reimbursement=" + reimbursement + ", attachmentPath=" + attachmentPath + "]";
	}

	
}
