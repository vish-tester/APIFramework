package pojoRevise;

public class RequestType {
	private String requestSubType;
	private RequestDetails requestDetails;
	
	public RequestType() {
		
	}
	
	public RequestType(String requestSubType, RequestDetails requestDetails) {
		this.requestSubType = requestSubType;
		this.requestDetails = requestDetails;
	}
	public String getRequestSubType() {
		return requestSubType;
	}
	public void setRequestSubType(String requestSubType) {
		this.requestSubType = requestSubType;
	}
	public RequestDetails getRequestDetails() {
		return requestDetails;
	}
	public void setRequestDetails(RequestDetails requestDetails) {
		this.requestDetails = requestDetails;
	}
	
	
	

}
