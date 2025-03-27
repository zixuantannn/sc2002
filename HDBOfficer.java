
public class HDBOfficer extends Applicant{

    private RegistrationForm[] registrationForm = new RegistrationForm[100];
    private ApplicationForm[] applicationForm = new ApplicationForm[100];
    private Project HandledProject = null;
    boolean isApplicant = false;
    boolean isHDBOfficer = false;
    int index = -1;

    public void registerForProject(String registeredProjectName){
        if(this.isApplicant){
            System.out.println("You cannot register as a HDB Officer for this project as you have applied as an applicant.");
        }
        else if(this.isHDBOfficer){
            System.out.println("You cannot register as a HDB Officer for this project as you are already a HDB Officer.");            
        }

        if(index == -1){
            registrationForm[0] = new RegistrationForm(this, registeredProjectName);
            System.out.println("Registration form submitted for approval.");
            index = 1;
        }else{
            boolean ok = true;
            for(int i=0;i<index;i++){
                if(registrationForm[i].getRegisteredProject().getName().equals(registeredProjectName)){
                    System.out.println("You are already registering this project. Please wait for your outcome.");
                    ok = false; break;
                }
            }
            if(ok){
                registrationForm[index] = new RegistrationForm(this, registeredProjectName);
                System.out.println("Registration form submitted for approval.");
                index++;
            }
        }
    }

    public void setHandledProject(Project project) {
        this.handledProject = project;
    }

    public Project getHandledProject() {
        return this.handledProject;
    }

    public void viewRegistrationStatus(){
        if(index==-1){
            System.out.println("No registration Found!!!");
        }else{
            for(int i=0;i<index;i++){
                registrationForm[i].viewRegistrationStatus();
            }
        }
    }

    public void viewHandledProject(){
        if (this.handledProject != null) {
            handledProject.viewProjectDetails();
        } else {
            System.out.println("No handled project.");
        }
    }

    public void viewAllEnquiries(){
        if(this.isHDBOfficer){
            for(int i=0;i<applicationForm.length;i++){

                Enquiry[] enquiryList_1 = applicationForm[i].getEnquiryList();

                for(int j=0;j<enquiryList_1.length;j++){
                    System.out.println(enquiryList_1[j].getEnquiryID() + " : " + enquiryList_1[j].getContent());
                } 
            }
        }
    }

    public void respondToEnquiry(String enquiryID, String message){
        if(this.isHDBOfficer){
            for(int i=0;i<applicationForm.length;i++){

                Enquiry[] enquiryList_1 = applicationForm[i].getEnquiryList();

                for(int j=0;j<enquiryList_1.length;j++){

                    if(enquiryID.equals(enquiryList_1[j].getEnquiryID())){
                        enquiryList_1[j].updateResponse(message);
                    }

                } 
            }
        }
    }
// handleFlatSelection includes: 
// - update number of flat
// - retrieve application from NRIC
// - update applicant profile 

    public void handleFlatSelection(String applicantNRIC, String chosenFlatType){
        if (this.handledProject == null) {
            System.out.println("No project assigned.");
            return;
        }
        ApplicationForm application = this.handledProject.findApplicationByNRIC(applicantNRIC);

        if (application == null) {
            System.out.println("No application found for NRIC: " + applicantNRIC);
            return;
        }
    
        if (!application.getRegistrationStatus().equals("Successful")) {
            System.out.println("Applicant's status is not 'Successful'. Booking denied.");
            return;
        }
    
        boolean isAvailable = this.handledProject.reduceFlatAvailability(chosenFlatType);
        if (!isAvailable) {
            System.out.println("Selected flat type (" + chosenFlatType + ") is no longer available.");
            return;
        }
    
        application.updateRegistrationStatus("Booked");       
    }

    public void generateReceipt(Applicant applicant){
        System.out.println("----- Flat Booking Receipt -----");
        System.out.println("Name: " + applicant.getName());
        System.out.println("NRIC: " + applicant.getUserID());
        System.out.println("Age: " + applicant.getAge());
        System.out.println("Marital Status: " + applicant.getMaritalStatus());
        System.out.println("Flat Type: " + applicant.getApplicationForm().getFlatBooking().getFlatType());
        System.out.println("Project: " + applicant.getApplicationForm().getAppliedProject().getName());
        System.out.println("--------------------------------");
    }


}