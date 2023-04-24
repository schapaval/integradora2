package model;

import java.util.Calendar;

public class Capsule {
    
    private String capsuleId;
    private String capsuleDescription;
    private String capsuleType;
    private Collaborator collaborator;
    private String learning;
    private boolean isApproved;
    private Calendar approvalDate;
    private boolean isPublished;


    /**
     * Capsule: constructor
     * @param capsuleDescription String: description of the capsule
     * @param capsuleType String: type between management, domain, technical or experiences
     * @param colaboratorName String: name of the author
     * @param colaboratorPosition String: position of the author
     * @param learning String: description of what was learned with the capsule
     */

    public Capsule(String capsuleId, String capsuleDescription, String capsuleType, String colaboratorName, String colaboratorPosition, String learning){
        this.capsuleId = capsuleId;
        this.capsuleDescription = capsuleDescription;
        this.capsuleType = capsuleType;
        this.collaborator = new Collaborator(colaboratorName, colaboratorPosition);
        this.learning = learning;
        this.approvalDate = Calendar.getInstance();
        this.isApproved = false;
        this.isPublished = false;
    }

    public String getCapsuleId(){
        return capsuleId;
    }

    public void setCapsuleId(String capsuleID){
        this.capsuleId = capsuleID;
    }

    public String getCapsuleDescription() {
        return capsuleDescription;
    }

    public void setCapsuleDescription(String capsuleDescription) {
        this.capsuleDescription = capsuleDescription;
    }

    public String getCapsuleType() {
        return capsuleType;
    }

    public void setCapsuleType(String capsuleType) {
        this.capsuleType = capsuleType;
    }



    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }


    /**
     * setCapsuleApproval: String: sets the capsule as approved and stores the date of approval, a confirmation message is returned
     * @return
     */
    public String setCapsuleApproval(){
        this.isApproved = true;
        this.approvalDate = Calendar.getInstance();
        String msg = "Capsule approved!";
        return msg;
    }

    public String setPublished(){
        boolean isPublished = true;
        String html = getCapsuleId() + ".html";

        return html;

    }

    public boolean isPublished(){
        return isPublished;
    }

    public boolean isApproved() {
        return isApproved;
    }



    public Calendar getApprovalDate(){
        return approvalDate;
    }

  
    public Collaborator getCollaborator(){
        return collaborator;
    }

}
