package com.example;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.RegistrationForm;
import main.java.com.example.ApplicationForm;
import main.java.com.example.Enquiry;

public class Manager extends UserAccount{
    private Project handledProject = null;

    public Manager(String name, String NRIC, int age, String maritalStatus){
        super(name, NRIC, age, maritalStatus);
    }

    public Manager(String name, String NRIC, int age, String maritalStatus, String password){
        super(name, NRIC, age, maritalStatus, password);
    }      
    public void setHandledProject(Project p) {
    this.handledProject = p;
}

    public Project getHandledProject() {
        return this.handledProject;
    }

    public void setVisibility(Scanner sc){
        while (true) {
            System.out.print("Set the visibility of your handled project (on/off): ");
            String visibilityInput = sc.nextLine().trim().toLowerCase();

            if (visibilityInput.equals("on")) {
                handledProject.setVisibility(visibilityInput);     
                System.out.println("Project visibility set to ON.");
                break;
            } else if (visibilityInput.equals("off")) {
                handledProject.setVisibility(visibilityInput);
                System.out.println("Project visibility set to OFF.");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'on' or 'off'.");
            }
        }

    }

    public void  viewAllProject(Database db){
        System.out.println("View all projects: ");
        for(int i=0;i<db.projectList.size();i++){
            db.projectList.get(i).viewProjectDetails();   // new function for class Project
        }
    }

    public void viewMyProject(){
        System.out.println("View the details of your handled project:");
        handledProject.viewProjectDetails();
    }
    public void viewRegistration(){
        handledProject.viewListOfRegistration();  // new function for class Project
    }
    public void viewApplication(){
        handledProject.viewListOfApplication();  // new function for class Project
    }

    public void assignOfficerToProject(Database db){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter registration form ID: ");
        int ID = sc.nextInt();                          // create ID for registration form
        RegistrationForm registerForm = null;
        List<RegistrationForm> registerList = handledProject.getListOfRegisterForm();   // new function for class Project
        for(int i=0;i<registerList.size();i++){
            if(ID == registerList.get(i).getRegistrationID()){
                registerForm =  registerList.get(i);
                break;
            }
        }

        if(registerForm == null){
            System.out.println("Your ID you enter is invalid!");
        }else{
            System.out.println("Do you want to approve or reject the registration form with ID " + ID + " (approve/reject)?");
            while(true){
                String assign = sc.nextLine();

                if(assign.equals("approve")){
                    registerForm.setRegistrationStatus("approved");
                    handledProject.setNumOfficerSlots(handledProject.getNumOfficerSlots()-1);
                    String officerName = registerForm.getOfficerName();
                    String NRICofficer = registerForm.getOfficerNRIC();
                    String[] currentOfficers = handledProject.getOfficers();
    
                    // Expand and update officer list
                    for (int i = 0; i < currentOfficers.length; i++) {
                        if (currentOfficers[i] == null || currentOfficers[i].isEmpty()) {
                        
                                currentOfficers[i] = officerName;
                                for (Officer o : db.officerList) {
                                    if (o != null && o.getNRIC().equalsIgnoreCase(NRICofficer)) { // create function in Officer
            //                            o.setAssignedProject(handledProject);
                                        break;
                                    }
                                }
                                System.out.println(" Officer " + officerName + " approved and assigned to project " + handledProject.getName());
                            } else {
                                System.out.println("Officer slots are full for this project.");
                                return;
                            }
                        }
                    break;
                }else if(assign.equals("reject")){
                    registerForm.setRegistrationStatus("rejected");

                }else{
                    System.out.println("Invalid input.Please try again!");
                }                
            }

        }
    }

    public void manageApplicationForm(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter application form ID: ");
        int ID = sc.nextInt();                          // create ID for registration form
        ApplicationForm applyForm = null;
        List<ApplicationForm> applyList = handledProject.getListOfApplyForm();   // new function for class Project
        for(int i=0;i<applyList.size();i++){
            if(ID == applyList.get(i).getApplicationID()){
                applyForm =  applyList.get(i);
                break;
            }
        }

        if(applyForm == null){
            System.out.println("Your ID you enter is invalid!");
        }else{
            System.out.println("Do you want to approve or reject the application form with ID " + ID + " (approve/reject)?");
            while(true){
                String assign = sc.nextLine();

                if(assign.equals("approve")){
                    applyForm.updateStatus("successful");

                    System.out.println(" Applicant " + applyForm.getApplicantName() + " approved to project " + handledProject.getName());
                    break;
                }else if(assign.equals("reject")){
                    applyForm.updateStatus("unsuccessful");

                    System.out.println(" Applicant " + applyForm.getApplicantName() + " rejected to project " + handledProject.getName());                    
                }else{
                    System.out.println("Invalid input.Please try again!");
                }                
            }

        }
    }  
    public void viewWithdrawalRequest(){
        this.handledProject.viewRequestWithdrawal();
    }
    public void manageWithdrawalRequest(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter application ID with withdrawal request: ");
        int ID = sc.nextInt();  
        sc.nextLine();

        List<ApplicationForm> applyList = handledProject.getListOfApplyForm();
        ApplicationForm targetForm = null;

        for (ApplicationForm form : applyList) {
            if (form != null && form.getApplicationID() == ID && form.getWithdrawalEnquiry() != null) {
                targetForm = form;
                break;
            }
        }

        if (targetForm == null) {
            System.out.println("No withdrawal request found for the given ID.");
            return;
        }

        System.out.println("Withdrawal request found for Applicant: " + targetForm.getApplicantName());
        System.out.println("Message: " + targetForm.getWithdrawalEnquiry());
        System.out.print("Approve withdrawal? (yes/no): ");
        String input = sc.nextLine().trim().toLowerCase();

        if (input.equals("yes")) {
            applyList.remove(targetForm);
            System.out.println(" Withdrawal request approved. Application removed.");
        } else {
            System.out.println(" Withdrawal request not approved. No changes made.");
        }
    }

    public void viewAllEnquiries(Database db){
        for(Project pro : db.projectList){
            pro.viewEnquiryList();
        }
    }

    public void viewAndReplyEnquiries(Database db) {
        for(Project pro : db.projectList){
            System.out.println("The list of enquiries in the project " + pro.getName());
            List<Enquiry> lis = pro.getEnquiryList();
            for (Enquiry enquiry : lis) {
                System.out.println("Enquiry: " + enquiry.getContent());
                System.out.print("Enter your reply: ");
                Scanner sc = new Scanner(System.in);
                String reply = sc.nextLine();
                enquiry.updateResponse(reply);
                System.out.println("Response saved.");
            }
        }
    }


}