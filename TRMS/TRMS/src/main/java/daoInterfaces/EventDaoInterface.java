package daoInterfaces;

import daoObjects.Event;
import daoObjects.Reimbursement;

public interface EventDaoInterface extends BaseDaoInterface<Event> {
	
	Event getForReimbursement(Reimbursement reimbursement);
	
	Event getForReimbursementId(int reimbursementId);
}
