public class RegistrationForm{
    private HDBOfficer officer = null;
    private Project registeredProject = null;
    private String registrationStatus = null;

    public RegistrationForm(Project registeredProject, HDBOfficer officer){
        this.officer = officer;
        this.registeredProject = registeredProject;
        this.registrationStatus = "Pending";
    }

    public HDBOfficer getOfficer(){
        return this.officer;
    }

    public Project getRegisteredProject(){
        return this.registeredProject;
    }

    public String getRegistrationStatus(){
        return this.registrationStatus;
    }

    public void setRegistrationStatus(String newStatus){
        this.getRegistrationStatus = new String(newStatus);
    }

}