package ooProject;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Applicant extends UserAccount implements EnquiryTask, ApplicantTask {
	private int applicantID;
    private boolean appliedForProject;
    private List<ApplicationForm> projectApplied;
	private List<Enquiry> enquiryList;
    
    public Applicant(String u, String n, int a, String m, int id){  
        super(u,n,a,m);
        this.appliedForProject = false;
		applicantID = id;
		this.projectApplied = new ArrayList<>(); // need read from excel 
		this.enquiryList = new ArrayList<>() // need read from excel 
		
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
            }     
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
        		// ApplicationForm (applicant, projectName, applicationStatus, HDBOfficer ?, flatType, flatBooking
        		ApplicationForm form = new ApplicationForm(this,project.getName(),"Pending", null,project.getType1()?"2-Room":"3-Room", null);
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
		System.out.println ("Please enter your enquiry details: ");
		String content = sc.nextLine();
		int lastId = 0;
		if (!enquiryList.isEmpty()){
			int lastId = enquiryList.get(enquiryList.size()-1).getID();
		}
		Enquiry newEnquiry = new Enquiry(lastId, applicantID, content);
		enquiryList.add(newEnquiry);
		System.out.println("New Enquiry submitted.");
    	
    }
    public void editEnquiry(){
    	Scanner sc = new Scanner (System.in);

		if (enquiryList.isEmpty()){
			System.out.println("You have no enquiries to edit.");
			return;
		}

		for (Enquiry each: enquiryList){
			System.out.println("Enquiry ID: "+each.getID()+ "|| Content: "+each.getContent()+" || Response: "+ each.getResponse());
		}
		System.out.print("Please enter the Enquiry ID to edit: ");
		int id = sc.nextInt();
		boolean found = false;
		for (Enquiry enquiry: enquiryList) {
			if (enquiry.getID() == id){
				found = true;
				if (enquiry.getResponse() == null){
					System.out.println("Please enter the new enquiry: ");
					String newContent = sc.nextLine();
					enquiry.updateContent(newContent);
					System.out.println("Enquiry content updated successfully.");
				}
				else{
					System.out.println("Enquiry has been answered. No further changes available.");
				}
				break;
			} 
		if (!found){
			System.out.println("Enquiry ID not found. Please check again.");
		}

    }
}
	public void deleteEnquiry(){
		if (enquiryList.isEmpty()){
			System.out.println("You have no enquiries to edit.");
			return;
		}
		// Display all enquiries
		for (Enquiry each: enquiryList){
			System.out.println("Enquiry ID: "+each.getID()+ "|| Content: "+each.getContent()+" || Response: "+ each.getResponse());
		}

		System.out.print("Please enter the Enquiry ID to delete: ");
		int id = sc.nextInt();
		sc.nextLine();

		boolean found = false;
		for (Enquiry enquiry: enquiryList) {
			if (enquiry.getID() == id){
				found = true;
				if (enquiry.getResponse() == null){
					System.out.println("Chosen Enquiry: Enquiry ID:"+enquiry.getID()+"|| Content: "+enquiry.getContent()+" || Response: "+enquiry.getResponse());
					String choice;
					do{
						System.out.print("Are you sure want to delete this enquiry (yes/no):");
						choice = sc.next().toLowerCase();
					}while (!choice.equals("yes") && !choice.equals("no"));

					if (choice.equals("yes")){
						enquiryList.remove(enquiry);
						System.out.println("Enquiry have been removed.");
					}
					else {
						System.out.println("No changes have been done");
					}
				}
				else{
					System.out.println("Enquiry has been answered. No further changes available.");
				}
				break;
			} 
		if (!found){
			System.out.println("Enquiry ID not found. Please check again.");
		}
	}
}

