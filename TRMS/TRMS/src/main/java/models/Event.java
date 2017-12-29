package models;

import java.sql.Date;

public class Event {

	private int id;
	String name;
	EventType eventType;
	String description;
	Date startDate;
	Date endDate;
	String time;
	String location;
	double cost;
	Grades grades;

	public Event() {
		super();
	}

	public Event(int id, String name, EventType eventType, String description, Date startDate, Date endDate,
			String time, String location, double cost, Grades grades) {
		this.id = id;
		this.name = name;
		this.eventType = eventType;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.location = location;
		this.cost = cost;
		this.grades = grades;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Grades getGrades() {
		return grades;
	}

	public void setGrades(Grades grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", eventType=" + eventType + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", time=" + time + ", location=" + location
				+ ", cost=" + cost + ", grades=" + grades + "]";
	}

	
}
