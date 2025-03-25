public class FlatBooking{
// it should contain information of applicant 
    static int ID = 1;
    private final int bookingID;
    private String appliedProjectName;
    private String flatType;
    private String bookingDate;

    public FlatBooking(String appliedProjectName, String flatType, String bookingDate){
        this.bookingID = ID++;
        this.appliedProjectName = appliedProjectName;
        this.flatType = flatType;
        this.bookingDate = bookingDate;
    }

    public void printFlatBooking() {
        System.out.println("----- Flat Booking Info -----");
        System.out.println("Booking ID: " + bookingID);
        System.out.println("Project: " + appliedProjectName);
        System.out.println("Flat Type: " + flatType);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("-----------------------------");
    }
    public int getBookingID() {
        return bookingID;
    }

    public String getFlatType() {
        return flatType;
    }

    public String getAppliedProjectName() {
        return appliedProjectName;
    }

    public String getBookingDate() {
        return bookingDate;
    }
}