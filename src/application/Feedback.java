package application;

public class Feedback {
	
	private int commentID;
	private int patientID;
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	private int doctorID;
	private String comment;
	private String feedback;
	
	public Feedback(int commentID, int patientID, int doctorID, String comment, String feedback) {
		super();
		this.commentID = commentID;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.comment = comment;
		this.feedback = feedback;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	
	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
    @Override
    public String toString() {
        return "Feedback{" +
                "commentID=" + commentID +
                ", patientID=" + patientID +
                ", doctorID=" + doctorID +
                ", comment=" + comment +
                ", feedback='" + feedback + '\'' +
                '}';
    }

}
