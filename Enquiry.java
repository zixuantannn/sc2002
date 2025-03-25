

public class Enquiry {
	public int enquiryID;
	public String content;
	public String response;
	
	public Enquiry(int id, String c, String r) {
		enquiryID = id;
		content = c;
		response = r;
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
	
	public void updateContent(String newContent) {
		this.content = newContent;
	}
	
	public void updateResponse(String newResponse) {
		this.response = newResponse;
	}
}
