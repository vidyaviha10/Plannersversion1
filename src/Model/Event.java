package Model;

public class Event {
	
	int id;
	String  name;
	String venue;
	String date;
	int customerid;
	int tablesize;
	int totalTables;
	String customerName;
	int maxperson;
	
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getTablesize() {
		return tablesize;
	}
	public void setTablesize(int tablesize) {
		this.tablesize = tablesize;
	}
	public int getTotalTables() {
		return totalTables;
	}
	public void setTotalTables(int totalTables) {
		this.totalTables = totalTables;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getMaxperson() {
		return maxperson;
	}
	public void setMaxperson(int maxperson) {
		this.maxperson = maxperson;
	}
	
	

}
