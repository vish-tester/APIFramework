package pojoForInput;

public class bookingDates {

	private String checkin;
	private String checkout;
	
	public bookingDates() {
		
	}
	
	public bookingDates(String cin, String cout) {
		setcheckin(cin);
		setcheckout(cout);
	}
	public String getcheckin()
	{
		return checkin;
	}
	
	public void setcheckin(String checkin)
	{
		this.checkin = checkin;
	}
	
	public String getcheckout() {
		return checkout;
	}
	
	public void setcheckout(String checkout) {
		this.checkout = checkout;
	}
}
