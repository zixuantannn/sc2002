public class RegistrationForm{
    private HDBOfficer officer = null;
    private String registeredProjectName;
    private String registrationStatus = null;

    public RegistrationForm(HDBOfficer officer, String registeredProjectName){
        this.officer = officer;
        this.registeredProjectName = registeredProjectName;
        this.registrationStatus = "Pending";
    }

    public HDBOfficer getOfficer(){
        return this.officer;
    }

    public String getRegisteredProject(){
        return this.registeredProjectName;
    }

    public void getRegistrationStatus(){
        System.out.println("Registration Status of project "+ this.registeredProject.getName() + " is " + this.registrationStatus);
    }

    public void setRegistrationStatus(String newStatus){
        this.getRegistrationStatus = new String(newStatus);
    }

}