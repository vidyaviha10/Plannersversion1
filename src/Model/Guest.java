package Model;

public class Guest {
	public int id;
public String name;
public String email;
public String phone;
public Double weight;
public String description;
public int eventId;
public String toSit;
public String nottoSit;

public Guest() {
	super();
}
public Guest(int id, String name, Double weight, String toSit, String nottoSit) {
	super();
	this.id = id;
	this.name = name;
	this.weight = weight;
	this.toSit = toSit;
	this.nottoSit = nottoSit;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Double getWeight() {
	return weight;
}
public void setWeight(Double weight) {
	this.weight = weight;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getEventId() {
	return eventId;
}
public void setEventId(int eventId) {
	this.eventId = eventId;
}
public String getToSit() {
	return toSit;
}
public void setToSit(String toSit) {
	this.toSit = toSit;
}
public String getNottoSit() {
	return nottoSit;
}
public void setNottoSit(String nottoSit) {
	this.nottoSit = nottoSit;
}


}
