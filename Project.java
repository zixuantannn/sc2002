package OOProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Project {
    private String name;
    private String neighborhood;
    private boolean type1 = false;
    private boolean type2 = false;
    private int numType1;
    private int numType2;
    private Date openDate;
    private Date closeDate;
    private HDBManager manager;
    private int numOfficerSlots;
    private String[] officers;  
    private String visibility;
    private static List<Project> allProjects = new ArrayList<>(); // Static list to store all projects

    private static final int MAX_OFFICER_SLOTS = 10; // Max slots is 10

    private static String savedNeighborhoodFilter = "";
    private static Boolean savedType1Filter = null;
    private static Boolean savedType2Filter = null;

    public Project(String name, String neighborhood, boolean type1, boolean type2, int numType1, int numType2, Date openDate, Date closeDate, HDBManager manager, int numOfficerSlots) {
        this.name = name;
        this.neighborhood = neighborhood;
        this.type1 = type1;
        this.type2 = type2;
        this.numType1 = numType1;
        this.numType2 = numType2;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.manager = manager;
        setNumOfficerSlots(numOfficerSlots);
        allProjects.add(this); // Add the current instance to the list of all projects
    }

    public static List<Project> getAllProjects() {
        return allProjects;
    }

    public void destroy() {
        // Remove this project from the static list
        allProjects.remove(this);
        System.out.println("Project '" + this.name + "' has been destroyed and removed from the list.");
    }

    public static void displayAllProjects() {
        if (allProjects.isEmpty()) {
            System.out.println("No projects available.");
        } else {
            for (Project project : allProjects) {
                System.out.println("Project Name: " + project.getName());
            }
        }
    }

    public static void displayProjectsByManager(HDBManager filterManager) {
        boolean filtered = false;
        for (Project project : allProjects) {
            if (project.getManager().equals(filterManager)) {
                System.out.println("Project Name: " + project.getName());
                filtered = true;
            }
        }
        if (!filtered) {
            System.out.println("No projects found for the specified manager.");
        }
    }

    public static void displayProjectsWithFilters() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Would you like to filter by neighborhood? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter neighborhood:");
            savedNeighborhoodFilter = scanner.nextLine();
        }
        
        System.out.println("Would you like to filter by 2-Room flats availability? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter 'true' if filtering for projects with 2-Room flats, 'false' otherwise:");
            savedType1Filter = Boolean.valueOf(scanner.nextLine());
        }
        
        System.out.println("Would you like to filter by 3-Room flats availability? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter 'true' if filtering for projects with 3-Room flats, 'false' otherwise:");
            savedType2Filter = Boolean.valueOf(scanner.nextLine());
        }
        
        List<Project> filteredProjects = new ArrayList<>();
        for (Project project : allProjects) {
            if (!savedNeighborhoodFilter.isEmpty() && !project.getNeighborhood().equalsIgnoreCase(savedNeighborhoodFilter)) {
                continue;
            }
            if (savedType1Filter != null && project.getType1() != savedType1Filter) {
                continue;
            }
            if (savedType2Filter != null && project.getType2() != savedType2Filter) {
                continue;
            }
            filteredProjects.add(project);
        }
        
        filteredProjects.sort(Comparator.comparing(Project::getName));
        
        if (filteredProjects.isEmpty()) {
            System.out.println("No projects match the selected filters.");
        } else {
            System.out.println("Filtered Projects:");
            for (Project project : filteredProjects) {
                System.out.println("Project Name: " + project.getName() + ", Neighborhood: " + project.getNeighborhood());
            }
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public boolean getType1() {
        return type1;
    }

    public void setType1(boolean type1) {
        this.type1 = type1;
    }

    public boolean getType2() {
        return type2;
    }

    public void setType2(boolean type2) {
        this.type2 = type2;
    }

    public int getNumType1() {
        return numType1;
    }

    public void setNumType1(int numType1) {
        this.numType1 = numType1;
    }

    public int getNumType2() {
        return numType2;
    }

    public void setNumType2(int numType2) {
        this.numType2 = numType2;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public HDBManager getManager() {
        return manager;
    }

    public void setManager(HDBManager manager) {
        this.manager = manager;
    }

    public int getNumOfficerSlots() {
        return numOfficerSlots;
    }

    public void setNumOfficerSlots(int numOfficerSlots) {
        if (numOfficerSlots > MAX_OFFICER_SLOTS) {
            throw new IllegalArgumentException("Cannot have more than " + MAX_OFFICER_SLOTS + " officer slots.");
        }
        this.numOfficerSlots = numOfficerSlots;
        this.officers = new String[numOfficerSlots];  // Initialize officers array 
    }

    public String[] getOfficers() {
        return officers;
    }

    public void setOfficers(String[] officers) {
        this.officers = officers;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void resizeOfficersArray(int newSize) {
        String[] newOfficers = new String[newSize];
        int lengthToCopy = Math.min(this.numOfficerSlots, newSize);
        System.arraycopy(this.officers, 0, newOfficers, 0, lengthToCopy);
        this.officers = newOfficers;
        this.numOfficerSlots = newSize;
    }

    public boolean addOfficer(String newOfficerName) {
        for (int i = 0; i < numOfficerSlots; i++) {
            if (officers[i] == null) {
                officers[i] = newOfficerName;
                return true;  // Added successfully
            }
        }
        return false;  // No space available
    }

    public void interactiveEditProjectDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the current name of Project you would like to edit/update:");
        String currentName = scanner.nextLine();

        // Check if the entered name matches the current project name
        if (!currentName.equals(this.name)) {
            System.out.println("The project name does not match. Try again.");
        }

        // Ask if the user wants to edit the project name
        System.out.println("Would you like to edit the name of the project? (yes/no)");
        String editNameResponse = scanner.nextLine();
        if (editNameResponse.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new name for the project:");
            this.name = scanner.nextLine();
        }

        // Ask if the user wants to edit the neighborhood
        System.out.println("Would you like to edit the neighborhood? (yes/no)");
        String editNeighborhoodResponse = scanner.nextLine();
        if (editNeighborhoodResponse.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new neighborhood:");
            this.neighborhood = scanner.nextLine();
        }

        // Ask if the user wants to edit the availability of room types
        System.out.println("Would you like to edit the project types? (yes/no)");
        String editTypeResponse = scanner.nextLine();
        if (editTypeResponse.equalsIgnoreCase("yes")) {
            System.out.println("Enter 'true' if this project has 2-Room flats, otherwise enter 'false':");
            this.type1 = Boolean.parseBoolean(scanner.nextLine());

            System.out.println("Enter 'true' if this project has 3-Room flats, otherwise enter 'false':");
            this.type2 = Boolean.parseBoolean(scanner.nextLine());
        }

        // Ask if the user wants to update the number of 2-Room flats
        System.out.println("Would you like to update the number of 2-Room flats? (yes/no)");
        String editNumType1Response = scanner.nextLine();
        if (editNumType1Response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new number of 2-Room flats:");
            this.numType1 = Integer.parseInt(scanner.nextLine());
        }

        // Ask if the user wants to update the number of 3-Room flats
        System.out.println("Would you like to update the number of 3-Room flats? (yes/no)");
        String editNumType2Response = scanner.nextLine();
        if (editNumType2Response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new number of 3-Room flats:");
            this.numType2 = Integer.parseInt(scanner.nextLine());
        }

        // Ask if the user wants to edit the application opening date
        System.out.println("Would you like to edit the application opening date? (yes/no)");
        if ("yes".equalsIgnoreCase(scanner.nextLine())) {
            System.out.println("Enter the new application opening date (DD-MM-YYYY):");
            try {
                this.openDate = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());
                System.out.println("Application opening date set to: " + new SimpleDateFormat("dd-MM-yyyy").format(this.openDate));
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY.");
            }
        }

        // Ask if the user wants to edit the application closing date
        System.out.println("Would you like to edit the application closing date? (yes/no)");
        if ("yes".equalsIgnoreCase(scanner.nextLine())) {
            System.out.println("Enter the new application closing date (DD-MM-YYYY):");
            try {
                this.closeDate = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());
                System.out.println("Application closing date set to: " + new SimpleDateFormat("dd-MM-yyyy").format(this.closeDate));
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY.");
            }
        }

        // Ask if the user wants to edit the visibility
        System.out.println("Would you like to edit the visibility? (yes/no)");
        String editVisibilityResponse = scanner.nextLine();
        if (editVisibilityResponse.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new visibility (public/private):");
            this.visibility = scanner.nextLine();
        }

        // Ask if the user wants to add a new officer
        System.out.println("Would you like to assign a new officer to the project? (yes/no)");
        String addOfficerResponse = scanner.nextLine();
        if (addOfficerResponse.equalsIgnoreCase("yes")) {
            // Add the officer (just using the name)
            System.out.println("Enter officer name:");
            String officerName = scanner.nextLine();
            boolean officerAdded = addOfficer(officerName);
            if (officerAdded) {
                System.out.println("Officer added successfully!");
            } else {
                System.out.println("Could not add officer. No space available.");
            }
        }

        System.out.println("Project details updated successfully!");
    }
}
