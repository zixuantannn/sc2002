package OOProject;

public abstract class UserAccount {
    private String userID;
    private String name;
    private int age;
    private String maritalStatus;
    private String password = "password";
    private boolean loggedIn = false; 
    public UserAccount(String u, String n, int a, String m){
        userID = u;
        name = n;
        age = a;
        maritalStatus = m;
    }

    public UserAccount(String u, String n, int a, String m, String p){ // overloading for password
        userID = u;
        name = n;
        age = a;
        maritalStatus = m;
        password = p;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password) {
        this.password = password;
    }
    
    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public boolean login(String p) {
        if (this.password.equals(p)) {
            System.out.println("Login successful!");
            this.loggedIn = true;
            return true;
        } else {
            System.out.println("Wrong password.");
            return false;
        }
    }

    public void logout() {
        this.loggedIn = false;
        System.out.println("You have logged out.");
    }
}
