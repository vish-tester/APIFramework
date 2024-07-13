package pojoForInput;

public class Booking {

	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private String additionalneeds;
	private bookingDates bookingdates;
	
	public Booking()
	{
		
	}
	
	public Booking (String fname, String lname, int tprice, boolean dpaid, String aneeds, bookingDates bdates) {
		
		setFirstname(fname);
		setLastname(lname);
		setTotalprice(tprice);
		setDepositpaid(dpaid);
		setAdditionalneeds(aneeds);
		setBookingdates(bdates);
		
	}
	
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public boolean isDepositpaid() {
		return depositpaid;
	}
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	public String getAdditionalneeds() {
		return additionalneeds;
	}
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}
	public bookingDates getBookingdates() {
		return bookingdates;
	}
	public void setBookingdates(bookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}
	
	
	
	
	
}
