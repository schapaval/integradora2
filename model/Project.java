package model;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Project {

    private Manager manager;
    private String projectName;
    private String clientName;
    private double projectBudget;
    private Calendar startDate;
    private Calendar actualEndDate;
    private Calendar plannedEndDate;
    private int duration;
    private Stage[] stages;
    private Manager clientManager;
    private final int STAGES_SIZE = 6;
    private int stageCounter;
    private boolean active;
    private int capsulesInProject;
    
   

    
    

    /**
     * Project: class constructor
     * @param projectName String : project name
     * @param clientName String: client name
     * @param startDate Calendar: start date
     * @param endDate Calendar: end date
     * @param projectBudget double: project budget
     */
    public Project(String projectName, String clientName, double projectBudget){
        
        this.projectName = projectName;
        this.clientName = clientName;
        this.projectBudget = projectBudget;
        this.startDate = Calendar.getInstance();
        this.actualEndDate = Calendar.getInstance();
        this.stages = new Stage[STAGES_SIZE];
        this.plannedEndDate = getPlannedEndDate();
        this.stageCounter = 0;
        this.active = true;
        this.manager = new Manager("","");
        this.clientManager = new Manager("","");
        this.capsulesInProject = 0;
        initStages();
    }

    /**
     * initManager: method that calls for the construction of a manager
     * @param managerName String: green manager name
     * @param managerPhone String: green manager phone
     * @return msg
     */
    public String initManager(String managerName, String managerPhone){
        String msg = "";
        
        this.manager = new Manager(managerName, managerPhone);
        
        msg = "The manager has been registered";

        return msg;
    }

    public void setStage(String name, Calendar startDate, int duration, int i){ 
        this.stages[i] = new Stage(name, duration);
    }


    public Calendar getStartDate(){
        return startDate;
    }


    /**
     * initStages: void: method that initializes the stages with the established names and no duration
     */
    public void initStages(){

        stages[0] = new Stage("START", 0);
        stages[1] = new Stage("ANALYSIS", 0);
        stages[2] = new Stage("DESIGN", 0);
        stages[3] = new Stage("EXECUTION", 0);
        stages[4] = new Stage("END", 0);
        stages[5] = new Stage("MAINTENANCE", 0);

    }

    public String getName(){
        return projectName;
    }

    /**
     * accessStageName: String: method that allows to access the name of the stage
     * @param i
     * @return msg
     */
    public String accessStageName(int i){

        String msg = "No stage name found";
        if(stages[i].getName() != ""){
            msg = stages[i].getName();
        }
        return msg;
    }

    public void setStageDuration(int i, int duration){
        stages[i].setDurationAndCalculate(duration);
    }

    public int getStageCounter(){
        return stageCounter;
    }
    public void addToStageCounter(){
        this.stageCounter += 1;
    }

    /**
     * endStage: string: method that calls for the ending of a stage, it returns a confirmation message
     * @param stageCounter int: position of the current stage
     * @return msg
     */
    public String endStage(int stageCounter){
        String msg = "Unable to end stage";
        stages[stageCounter].setApproval();

        msg = "Stage " + stages[stageCounter].getName() + " ended!";

        this.stageCounter++;

        return msg;
    }

    /**
     * setStageAsActive: String: method that makes a stage active and returns a confirmation message
     * @return msg
     */
    public String setStageAsActive(){
        String msg = "";
        if(stageCounter <= 5){
            stages[stageCounter].setActive();
            msg = "Stage " + stages[stageCounter].getName() + " is now active";
        }else{
            setInactive();
            setActualEndDate();
            msg = "The project has ended";
        }
        
        return msg;
    }

    public Calendar getActualEndDate(){
        return actualEndDate;
    }

    public void setActualEndDate(){
        this.actualEndDate = Calendar.getInstance();
    }

    /**
     * calculatePlannedEndDate: void: assigns the value of the final date of the project
     */
    public void calculatePlannedEndDate(){
        this.plannedEndDate = stages[5].getPlannedEndDate();
    }

    public Calendar getPlannedEndDate(){
        return plannedEndDate;
    }

    /**
     * setInactive: void: sets a project inactive
     */
    public void setInactive(){
        this.active = false;
    }

    public boolean getStatus(){
        return active;
    }

    public void setManagerName(String managerName){
        manager.setName(managerName);
    }

    public void setManagerPhone(String managerPhone){
        manager.setPhone(managerPhone);
    }

    public void setClientManagerName(String clientManagerName){
        clientManager.setName(clientManagerName);
    }

    public void setClientManagerPhone(String clientManagerPhone){
        clientManager.setPhone(clientManagerPhone);
    }

    /**
     * addManager: void: method that calls for the addition of a manager to the project
     * @param managerName String: green manager name
     * @param managerPhone String: green manager phone
     */
    public void addManager(String managerName, String managerPhone){
        manager = new Manager(managerName, managerPhone);
    }

    /**
     * addCapsuleToStage: method that calls for the addition of a capsule to the current stage
     * @param capsuleId String: capsule ID
     * @param capsuleDescription String: text describing the capsule
     * @param capsuleType String: domain, experiences, management or technical
     * @param colaboratorName String: name of the capsule's author
     * @param colaboratorPosition String: collaborator position in the organization
     * @param learning String: what was learned from the capsule
     */
    public void addCapsuleToStage(String capsuleId, String capsuleDescription, String capsuleType, String colaboratorName, String colaboratorPosition, String learning){
        stages[stageCounter].addCapsule(capsuleId, capsuleDescription, capsuleType, colaboratorName, colaboratorPosition, learning);
    }

    /**
     * approveCapsuleInStage: String: calls for the approval of a capsule using its ID and returns a confirmation message
     * @param capsuleId String: ID of the approved capsule
     * @return
     */
    public String approveCapsuleInStage(String capsuleId){
        String msg = stages[stageCounter].approveCapsule(capsuleId);

        return msg;
    }

    /**
     * publishCapsule: String: calls for the publishing of a capsule using its ID and returns its html format
     * @param capsuleId String: ID of the published capsule
     * @return html
     */
    public String publishCapsule(String capsuleId){
        String html = stages[stageCounter].publishCapsule(capsuleId);
        return html;
    }

    /**
     * countCapsulesByType: String: calls for the counting of capsules by type and returns a message
     * @return msg
     */
    public String countCapsulesByType(){
       String msg = stages[stageCounter].countCapsulesByType();
       return msg;

    }

    /**
     * getLearningsInStage: String: calls for the learning information of a capsule and returns it in a message
     * @param i int: counter
     * @return
     */
    public String getLearningsInStage(int i){
        
        String msg = stages[stageCounter].getLearningEachCapsule(i);

        return msg;
    }

    /**
     * getCapsulesPerProject: int: calls for the number of capsules in a project and returns it
     * @param i int: counter
     * @return capsulesInProject
     */

    public int getCapsulesPerProject(int i){
        
        capsulesInProject += stages[i].getCapsulesCounter();
        System.out.println("Capsules in project: " + capsulesInProject);
        
        setCapsulesInProject(capsulesInProject);
        return capsulesInProject;
    }

    public void setCapsulesInProject(int capsulesInProject){
        this.capsulesInProject = capsulesInProject;
    }

    public int getCapsulesInProject(){
        return capsulesInProject;
    }

    /**
     * searchCollaboratorByName: boolean: calls for the search of a collaborator using its name, returns a flag variable
     * @param collaboratorName String: name of the desired collaborator
     * @param j int: stages counter
     * @param i int: collaborators counter
     * @return flag
     */
    public boolean searchCollaboratorByName(String collaboratorName, int j, int i){
        boolean flag = stages[j].searchCollaboratorByName(collaboratorName, i);
        return flag;
    }

    public String getCollaboratorName(int j, int i){
        String msg = stages[j].getCollaboratorName(i);
        return msg;
    }

    /**
     * hashtagSearch: String: calls for the hashtag search of a capsule
     * @param j int: stages counter
     * @param i int: collaborators counter
     * @param keywords String: user search input
     * @return msg
     */
    public String hashtagSearch(int j, int i, String keywords){
        String msg = stages[j].hashtagSearch(i, keywords);
        return msg;
    }
    
}
