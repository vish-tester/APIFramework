package pojoRevise;

import java.util.List;

public class RequestDetails {

	private String name;
	private int hours;
	private List<RequestDates> requestDates;
	
	public RequestDetails() {
		
	}
	
	public RequestDetails(String name, int hours, List<RequestDates> requestDates) {
		this.name = name;
		this.hours = hours;
		this.requestDates = requestDates;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public List<RequestDates> getRequestDates() {
		return requestDates;
	}

	public void setRequestDates(List<RequestDates> requestDates) {
		this.requestDates = requestDates;
	}
	
	
}
