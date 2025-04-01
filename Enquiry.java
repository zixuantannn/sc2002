

public class Enquiry {
	private int enquiryID;
	private int applicantID;
	private String content;
	private String response;
	
	public Enquiry(int id, int aID, String c) {
		enquiryID = id;
		applicantID = aID;
		content = c;
		response = null;
	}
	public int getID() {
		return enquiryID;
	}
	public String getContent() {
		return content;
	}
	public String getResponse() {
		return response;
	}
	public int getApplicantID(){
		return applicantID;
	}
	
	public void updateContent(String newContent) {
		this.content = newContent;
	}
	
	public void updateResponse(String newResponse) {
		this.response = newResponse;
	}
}
