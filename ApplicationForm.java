import java.util.ArrayList;
import java.util.List;

public class ApplicationForm {
    private Applicant applicationInformation;
    private String appliedProjectName;
    private String applicationStatus; // Pending, Successful, Unsuccessful, Booked
    private HDBOfficer hdbOfficer;
    private String appliedFlatType;
    private FlatBooking flatBooking;

    public ApplicationForm(Applicant applicationInformation, String appliedProjectName, String applicationStatus,
            HDBOfficer hdbOfficer, String appliedFlatType, FlatBooking flatBooking) {
        this.applicationInformation = applicationInformation;
        this.appliedProjectName = appliedProjectName;
        this.applicationStatus = applicationStatus;
        this.hdbOfficer = hdbOfficer;
        this.appliedFlatType = appliedFlatType;
        this.flatBooking = flatBooking;
    }

    public Applicant getApplicantInformation() {
        return applicationInformation;
    }

    public void setApplicantInformation(Applicant applicantInfo) {
        this.applicationInformation = applicantInfo;
    }

    public String getAppliedProjectName() {
        return appliedProjectName;
    }

    public void setAppliedProjectName(String projectName) {
        this.appliedProjectName = projectName;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String updateStatus) {
        this.applicationStatus = updateStatus;
    }

    public HDBOfficer getHDBOfficer() {
        return hdbOfficer;
    }

    public void setHDBOfficer(HDBOfficer officer) {
        this.hdbOfficer = officer;
    }

    public String getAppliedFlatType() {
        return appliedFlatType;
    }

    public void setAppliedFlatType(String flatType) {
        this.appliedFlatType = flatType;
    }

    public FlatBooking getFlatBooking() {
        return flatBooking;
    }

    public void setFlatBooking(FlatBooking booking) {
        this.flatBooking = booking;
    }
}