package model;

import java.util.Calendar;
import java.util.Random;

public class Stage {

    private String stageName;
    private Calendar plannedStartDate;
    private Calendar plannedEndDate;
    private Calendar startDate;
    private Calendar actualEndDate;
    private Calendar approvalDate;
    private boolean isApproved;
    private boolean isActive;
    private int duration;
    private Capsule[] capsules;
    private final int CAPSULES_SIZE = 50;
    private int capsulesCounter;
    private int technicalNumber;
    private int managementNumber;
    private int domainNumber;
    private int experiencesNumber;
    private Collaborator[] collaborators;
    private int COLLABORATORS_SIZE = 100;


    /**
     * Stage(stageName, startDate, duration): class constructor
     * @param stageName String: name of the stage
     * @param duration : int: duration of the stage in months
     */
    public Stage(String stageName, int duration) {
        this.stageName = stageName;
        this.plannedStartDate = Calendar.getInstance();
        this.duration = duration;
        this.startDate = Calendar.getInstance();
        this.plannedEndDate = Calendar.getInstance();
        this.approvalDate = Calendar.getInstance();
        this.isApproved = false;
        this.isActive = false;
        this.capsules = new Capsule[CAPSULES_SIZE];
        this.capsulesCounter = 0;
        this.collaborators = new Collaborator[COLLABORATORS_SIZE];


        initCapsules();
       
    }

    public String getName() {
        return stageName;
    }

    public Calendar getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setStartDate(Calendar plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public Calendar getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(Calendar plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public Calendar getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Calendar actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    /**
     * calculatePlannedEndDate: Calendar: calculates the planned end date using duration
     * @param duration int: duration of the stage in months
     * @return plannedEndDate
     */
    public Calendar calculatePlannedEndDate(int duration){
        Calendar plannedEndDate = Calendar.getInstance();
        plannedEndDate.add(Calendar.MONTH, duration);
        
        return plannedEndDate;
    }
    

    public boolean isApproved() {
        return isApproved;
    }

    /**
     * setApproval: String: sets the stage as approved and returns confirmation message
     * @return msg
     */
    public String setApproval() {
        this.isApproved = true;
        Calendar approvalDate = Calendar.getInstance();
        String msg = "Stage approved!";
        
        return msg;
        
    }

    public Calendar getApprovalDate(){
        return approvalDate;
    }

    public boolean getStatus() {
        return isActive;
    }

    public void setActive() {
        isActive = true;
    }

    public int getDuration() {
        return duration;
    }

    public void setDurationAndCalculate(int duration) {
        this.duration = duration;
        this.plannedEndDate = calculatePlannedEndDate(duration);
    }

    public Capsule[] getCapsules() {
        return capsules;
    }

    public void setCapsules(Capsule[] capsules) {
        this.capsules = capsules;
    }
    /**
     * initCapsules: void: initializes capesules array
     */
    public void initCapsules(){
        for(int i = 0; i < CAPSULES_SIZE; i++){
            capsules[i] = new Capsule("", "", "", "", "", "");

        }
    }

    /**
     * addCapsule: String: method that calls for the construction of a capsule
     * @param capsuleDescription String: situation description
     * @param capsuleType String: management, experiences, domain or technical
     * @param colaboratorName String: name of the author
     * @param colaboratorPosition String: position of the author
     * @param learning String: description of what was learned with the capsule
     * @return msg
     */
    public String addCapsule(String capsuleId, String capsuleDescription, String capsuleType, String colaboratorName, String colaboratorPosition, String learning) {
        String msg = "The capsule has not been added";
        int pos = getFirstValidPosition();
        if(pos != -1){

            Capsule capsule = new Capsule(capsuleId, capsuleDescription, capsuleType, colaboratorName, colaboratorPosition, learning);
            capsules[pos] = capsule;
            collaborators[pos] = capsules[pos].getCollaborator();
            msg = "Capsule successfully added";
        }
        this.capsulesCounter++;
        return msg;
    }
    
    
    
    private int getFirstValidPosition(){
        int pos = -1; 
        boolean isFound = false; 
        for(int i = 0; i < capsules.length && !isFound; i++){
            if((capsules[i].getCapsuleId().equals(""))){
                pos = i; 
                isFound = true;
            }
        }
        return pos; 
    }


    /**
     * searchCapsuleById: int: looks for a capsule in the array using its ID, and returns its position if found
     * @param capsuleId String: ID of the searched capsule
     * @return pos
     */
    public int searchCapsuleById(String capsuleId){
        int pos = -1;

        for(int i = 0; i < CAPSULES_SIZE; i++){
            
            if(capsuleId.equals(capsules[i].getCapsuleId())){
                pos = i;
                
            }
        }
        return pos;
    }

    /**
     * approveCapsule: void: method that calls the method that approves a capsule
     * @param capsuleId String: ID of the capsule that will be approved
     * @return msg
     */

    public String approveCapsule(String capsuleId){
        int pos = searchCapsuleById(capsuleId);
        String msg = "Capsule not found";
        
        if(pos != -1){
            msg = capsules[pos].setCapsuleApproval();
        }
        return msg;
    }

    /**
     * publishCapsule: String: calls for the publishing of a capsule using its id, returns html format
     * @param capsuleId String: ID of the published capsule
     * @return html
     */

    public String publishCapsule(String capsuleId){
     int pos = searchCapsuleById(capsuleId);
     String html = capsules[pos].setPublished();

     return html;

    }

    /**
     * countCapsulesByType: String: this method counts the amount of capsules per each type in a project, and returns a message with this information
     * @return msg
     */
    public String countCapsulesByType(){
        int technicalNumber = 0;
        int managementNumber = 0;
        int domainNumber = 0;
        int experiencesNumber = 0;
        String msg = "";
        for(int i = 0; i < CAPSULES_SIZE; i++){
            if(capsules[i].getCapsuleType().equalsIgnoreCase("TECHNICAL")){
                technicalNumber++;
            }
            else if(capsules[i].getCapsuleType().equalsIgnoreCase("MANAGEMENT")){
                managementNumber++;
            }
            else if(capsules[i].getCapsuleType().equalsIgnoreCase("DOMAIN")){
                domainNumber++;
            }
            else if(capsules[i].getCapsuleType().equalsIgnoreCase("EXPERIENCES")){
                experiencesNumber++;
            }
        }
        msg = "TECHNICAL: " + technicalNumber + ", MANAGEMENT: " + managementNumber + ", DOMAIN: " + domainNumber + ", EXPERIENCES: " + experiencesNumber;
        
        return msg;
    }

    public int getTechnicalNumber() {
        return technicalNumber;
    }

    public void setTechnicalNumber(int technicalNumber) {
        this.technicalNumber = technicalNumber;
    }

    public int getManagementNumber() {
        return managementNumber;
    }

    public void setManagementNumber(int managementNumber) {
        this.managementNumber = managementNumber;
    }

    public int getDomainNumber() {
        return domainNumber;
    }

    public void setDomainNumber(int domainNumber) {
        this.domainNumber = domainNumber;
    }

    public int getExperiencesNumber() {
        return experiencesNumber;
    }

    public void setExperiencesNumber(int experiencesNumber) {
        this.experiencesNumber = experiencesNumber;
    }

    /**
     * getLearningEachCapsule: String: generates a string with the capsule's learning and stores it in a variable to be returned
     * @param i int: counter
     * @return msg
     */
    public String getLearningEachCapsule(int i){
        String msg = "N/A";
 
        if(!capsules[i].getLearning().equals("")){
            msg =  "ID: " + capsules[i].getCapsuleId() + " , learning: " + capsules[i].getLearning();        
        }

        return msg;
    }

    public int getCapsulesCounter(){
        return capsulesCounter;
    }

    /**
     * searchColaboratorsByName: boolean: this method looks for a collaborator in the array using its name, is returns a flag variable stating if it was found
     * @param collaboratorName STring: name of the searched collaborator
     * @param i int: counter
     * @return flag
     */
   public boolean searchCollaboratorByName(String collaboratorName, int i){
        boolean flag = false;
        if(collaboratorName.equalsIgnoreCase(collaborators[i].getCollaboratorName())){
            flag = true;
        }

        return flag;
   }

   public Collaborator getCollaborator(int i){
        return collaborators[i];
   }

   public String getCollaboratorName(int i){
        return collaborators[i].getCollaboratorName();
   }

   /**
    * hashtagSearch: String: compares the inputted keywords with the description of the capsule, if they match, a string with the info is stored in the variable msg
    * @param i int: counter
    * @param keywords string: user search input
    * @return msg
    */
   public String hashtagSearch(int i, String keywords){
        String msg = "N/A";
        if((capsules[i].getCapsuleDescription().toUpperCase()).contains(keywords.toUpperCase()) && (capsules[i].isApproved() == true)){
            msg = capsules[i].getCapsuleId() + " /Description: " + capsules[i].getCapsuleDescription() +  " /Learning: " + capsules[i].getLearning();

        }

        return msg;
   }
}
