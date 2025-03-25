package ooProject;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Applicant extends UserAccount {
    private boolean appliedForProject;
    private List<ApplicationForm> projectApplied;
    

    public Applicant(String u, String n, int a, String m){  
        super(u,n,a,m);
        this.appliedForProject = false;
    }
    
    public List<Project> viewAvailableProjects(){
        System.out.println("Displaying available projects...");
        List <Project> allProjects = Project.getAllProjects();
        List <Project> filteredProjects = new ArrayList<>();
    
        for (Project project: allProjects){
            // Singles, 35 years old and above, can only view and apply 2-room 
            if (super.getAge()>34 && super.getMaritalStatus().equals("Singles")){
                // If at least 1 2-room is available and visibility is on
                if (project.getType1() && (project.getVisibility()).equals("on")){ 
                    filteredProjects.add(project);
                }
            }    
            else if (super.getAge()>20 && super.getMaritalStatus().equals("Married")){
                // If either 2-room or 3-room is available and visibility is on
                if ((project.getType1() || project.getType2() ) && (project.getVisibility()).equals("on")){
                    filteredProjects.add(project);
                }
            }      // then how about people who fall out of this range 
        }

        filteredProjects.sort(Comparator.comparing(Project::getName));
        if (filteredProjects.isEmpty()){
            System.out.println("No projects available.");
        }
        else{
            System.out.println("List of Projects: ");
            for (Project project: filteredProjects){
                System.out.println("Project Name: "+ project.getName() + "|| Neighborhood: "+ project.getNeighborhood());
            }
        }
        return filteredProjects;



    } 
    public void applyForProject(Project project){
        if (!appliedForProject){
        	List<Project> availableProject = this.viewAvailableProjects();
        	if (availableProject.contains(project)) {
        		List <Enquiry> emptyEnquiryList = new ArrayList();
        		// ApplicationForm (applicant, projectName, applicationStatus, enquiryList ??, HDBOfficer ?, flatType, flatBooking
        		ApplicationForm form = new ApplicationForm(this,project.getName(),"Pending", emptyEnquiryList ,null,project.getType1()?"2-Room":"3-Room", null);
        		this.projectApplied.add(form);
                this.appliedForProject = true;
                System.out.println("Application is successful");
        	}
        	else {
        		System.out.println("Project is not available");
        	}
            
        }
        else{
            System.out.println("Cannot apply for multiple projects.");
        }
    }
    public void viewApplicationStatus(){ //should this be different from the appliedProjects
    	System.out.println("List of Applied Projects: ");
    	for (ApplicationForm form :projectApplied) {
    		System.out.println("Project Name: " + form.getProjectName() + "|| Flat Type: "+form.getFlatType() + "|| Application Status: "+ form.getStatus());
    		
    	}
    }
    public void viewAppliedProject(){
    	System.out.println("List of Applied Projects: ");
    	for (ApplicationForm form :projectApplied) {
    		System.out.println("Project Name: " + form.getProjectName() + "|| Flat Type: "+form.getFlatType() + "|| Application Status: "+ form.getStatus());
    		
    	}
    }
    public void cancelApplication(){
    	// If there are no projects   
    	if (projectApplied.isEmpty()) {
    		System.out.println("You have not applied for any project. ");
    	}
    	// If no pending project in the list
    	else if (!(projectApplied.get(0)).equals("Pending")) {
    		System.out.println("You have no pending projects. ");
    	}
    	else {
    		ApplicationForm.delete(projectApplied.get(0));
        	projectApplied.remove(0);
    	}
    	
    }
    public void sendEnquiry(){
    	Scanner sc = new Scanner (System.in);
    	// search for the application form (not sure)
    	ApplicationForm form = getApplication(this);
    	if (form != null) {
    		List <Enquiry> enquiries = form.getEnquiry();
    		int lastEnquiryID = 0;
    		if (!enquiries.isEmpty()) {
    			// retrieve the last id of the list of enquiries
    			lastEnquiryID = enquiries.get(enquiries.size() - 1).getId();
    		}
    		
    		System.out.println("Please enter your enquiry details: ");
    		String content = sc.nextLine();
    		// create new enquiry (ID, content, response)
    		Enquiry enquiry = new Enquiry (lastEnquiryID+1 ,content, null);
    		// add enquiry into the list of enquiries in application form
    		enquiries.add(enquiry);
    		System.out.println("New Enquiry submitted.");
    	}
    	else {
    		System.out.println("No application found.");
    	}
    	
    	
    }
    public void editEnquiry(){
    	Scanner sc = new Scanner (System.in);
    	ApplicationForm form = getApplication(this);
    	if (form != null) {
    		List <Enquiry> enquiryList = form.getEnquiry();
    		System.out.println("List of enquiries: ");
    		for (Enquiry enquiry: enquiryList) {
    			System.out.println("Enquiry ID: "+enquiry.getID()+ "|| Content: "+enquiry.getContent()+" || Response: "+enquiry.getResponse());
    		}
    		// need error handling
    		System.out.println("Please enter Enquiry ID: ");
    		int id = sc.nextInt();
    		for (Enquiry enquiry: enquiryList) {
    			if (enquiry.getID() == id ) {
    	    		System.out.println("Please enter the new enquiry: ");
    	    		String newContent = sc.nextLine();
    				enquiry.updateContent(newContent);
    				// not sure if response need to update since the content is different
    				enquiry.updateResponse(null);
    				System.out.println("Enquiry content updated successfully.");
    				break;
    				
    			}
    		}
    		
    	}
    	else {
    		System.out.println("No application found.");
    	}
    }
}
