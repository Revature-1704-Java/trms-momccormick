package daoObjects;

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
	GradingFormat gradingFormat;
	GradeLetter passingGrade;
	double recievedGrade;
	
	public Event(int id, String name, EventType eventType, String description, Date startDate, Date endDate,
			String time, String location, double cost, GradingFormat gradingFormat, GradeLetter passingGrade,
			double recievedGrade) {
		this.id = id;
		this.name = name;
		this.eventType = eventType;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.location = location;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.passingGrade = passingGrade;
		this.recievedGrade = recievedGrade;
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

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public GradeLetter getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(GradeLetter passingGrade) {
		this.passingGrade = passingGrade;
	}

	public double getRecievedGrade() {
		return recievedGrade;
	}

	public void setRecievedGrade(double recievedGrade) {
		this.recievedGrade = recievedGrade;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", eventType=" + eventType + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", time=" + time + ", location=" + location
				+ ", cost=" + cost + ", gradingFormat=" + gradingFormat + ", passingGrade=" + passingGrade
				+ ", recievedGrade=" + recievedGrade + "]";
	}
	
		
}
