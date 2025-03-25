import java.util.ArrayList;
import java.util.List;

public class ApplicationForm {
    private String applicationInformation;
    private String appliedProjectName;
    private String applicationStatus; // Pending, Successful, Unsuccessful, Booked
    private static List<Enquiry> enquiryList = new ArrayList<>();
    private HDBOfficer hdbOfficer;
    private String appliedFlatType;
    private FlatBooking flatBooking;

    public ApplicationForm(String applicationInformation, String appliedProjectName, String applicationStatus,
            HDBOfficer hdbOfficer, String appliedFlatType, FlatBooking flatBooking) {
        this.applicationInformation = applicationInformation;
        this.appliedProjectName = appliedProjectName;
        this.applicationStatus = applicationStatus;
        this.hdbOfficer = hdbOfficer;
        this.appliedFlatType = appliedFlatType;
        this.flatBooking = flatBooking;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void updateStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public sendEnquiry(Enquiry enquiry) {
        ApplicationForm.enquiryList.add(enquiry);
    }
}