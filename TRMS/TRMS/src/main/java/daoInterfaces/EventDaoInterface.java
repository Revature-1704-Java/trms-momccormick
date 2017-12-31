package daoInterfaces;

import java.util.List;

import daoObjects.Event;
import daoObjects.EventType;

public interface EventDaoInterface extends BaseDaoInterface<Event> {

	List<Event> getAll();

	List<Event> getAllOfType(EventType eventType);

}
