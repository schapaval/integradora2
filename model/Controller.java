package model;


public class Controller {

    private Project[] projects;
    private Project project;
    private Stage[] stages;
    private Stage stage;
    private int PROJECTS_SIZE = 10;
    private int STAGES_SIZE = 6;
    private int projectsCount;
    private int mostCapsulesPos;
    private int aCapsuleCount;
    private int capsuleCount;
    private Collaborator[] totalCollaborators;
    private int COLLABORATORS_SIZE = 100;
    private int collaboratorsCounter = 0;

    /**
     * Controller: class constructor
     */
    public Controller(){
        projects = new Project[PROJECTS_SIZE];
        stages = new Stage[STAGES_SIZE]; 
        projectsCount = 0;
        mostCapsulesPos = 0;
        capsuleCount = 0;
        aCapsuleCount = 0;
        totalCollaborators = new Collaborator[COLLABORATORS_SIZE];
        initCollaboratorsArray();
       
    }

    /**
     * initProjects: void: method that initializes the projects array
     */
    public void initProjects(){
        for(int i = 0; i < PROJECTS_SIZE; i++){
            projects[i] = new Project("", "", 0);
        }
    }

    /**
     * addProject: String: method that creates a project and adds it to the project array
     * @param projectName String: name of the project
     * @param clientName String: name of the client
     * @param startDate Calendar: start date of the first stage 
     * @param endDate Calendar: end date of the last stage
     * @param projectBudget double: project budget
     * @return
     */
    public String addProject(String projectName, String clientName, double projectBudget, int projectCount){
        Project project = new Project(projectName, clientName, projectBudget);
        int pos = getFirstValidPosition();
        String msg = "The project has not been added";

        if(pos != -1){
            projects[pos] = project;
            msg = "Project successfully added";
            projectsCount++;        
        }
        return msg;
    }
    
    /**
     * initManager: void: method that initializes the manager
     */
    public void initManager(){
        Manager manager = new Manager("","");
    }

    /**
     * initManager: void: method that calls for the creation of a manager
     * @param managerName String: name of the Green manager
     * @param managerPhone String: Green manager phone
     */

     /**
      * addManager: void: method that adds a manager to a project
      * @param i counter
      * @param managerName String: green manager name
      * @param managerPhone String: green manager phone
      */
    public void addManager(int i, String managerName, String managerPhone){
        projects[i].addManager(managerName, managerPhone);
    }

    public void setManagerName(int i, String managerName){
        projects[i].setManagerName(managerName);
    }

    public void setManagerPhone(int i, String managerPhone){
        projects[i].setManagerPhone(managerPhone);
    }

    public void setClientManagerName(int i, String clientManagerName){
        projects[i].setClientManagerName(clientManagerName);
    }

    public void setClientManagerPhone(int i, String clientManagerPhone){
        projects[i].setClientManagerPhone(clientManagerPhone);
    }
    /**
     * initClientManager: method that calls for the creation of the client manager
     * @param clientManagerName String: client manager name
     * @param clientManagerPhone String: client manager phone
     */
    public void initClientManager(String clientManagerName, String clientManagerPhone){
        Manager clientManager = new Manager(clientManagerName, clientManagerPhone);
    }


    /**
     * getFirstValidPosition: int: method that gets the first valid position in the projects array
     * @return pos
     */
    public int getFirstValidPosition(){
        int pos = -1; 
        boolean isFound = false; 
        for(int i = 0; i < projects.length && !isFound; i++){
            if(projects[i].getName() == ""){
                pos = i; 
                isFound = true;
            }
        }
        return pos; 
    }


    
   
   

    

    /**
     * addCapsule: void: method that calls for the creation of a capsule
     * @param capsuleDescription String: text describing the capsule
     * @param capsuleType String: capsule type (management, domain, experiences or technical)
     * @param colaboratorName String: name of the capsule's author
     * @param colaboratorPosition String: position in the organization
     * @param learning String: teaching gained from the capsule
     */

    public String addCapsule(String projectName, String capsuleId, String capsuleDescription, String capsuleType, String colaboratorName, String colaboratorPosition, String learning){
        String msg = "Capsule not added";
        int pos = searchProjectByName(projectName);
        if(pos != -1){
            projects[pos].addCapsuleToStage(capsuleId, capsuleDescription, capsuleType, colaboratorName, colaboratorPosition, learning);
            msg = "Capsule added successfully!";
            totalCollaborators[collaboratorsCounter] = new Collaborator(colaboratorName, colaboratorPosition);
            collaboratorsCounter++;
        }
        
        return msg;
    }



    /**
     * approveCapsule: void: method that calls for the approval of a capsule
     */
    public String approveCapsule(String projectName, String capsuleId){
        String msg = "";
        int pos = searchProjectByName(projectName);
        if(pos != -1){
            msg = projects[pos].approveCapsuleInStage(capsuleId);
            
        }

        return msg;
    }

    /**
     * publishCapsule: String: method that calls for a capsule to be published and returns the html format
     * @param projectName String: name of the project
     * @param capsuleId String: ID of the desired capsule
     * @return
     */
    public String publishCapsule(String projectName, String capsuleId){ 
        int pos = searchProjectByName(projectName);
        String html = "Capsule could not be published";
        if(pos != -1){
            html = projects[pos].publishCapsule(capsuleId);
        }

        return html;
        
    }

    public String accessStageName(int projectCount, int i){
        return projects[projectCount].accessStageName(i);
    }

    public void setStageDuration(int projectCount, int i, int duration){
        projects[projectCount].setStageDuration(i, duration);
    }

    /**
     * endStage: String: method that calls for the ending of the current stage in the project, it returns a validation message
     * @param pos int: position
     * @return msg
     */
    public String endStage(int pos){

        String msg = projects[pos].endStage(projects[pos].getStageCounter());
        
        return msg;
    }
  


    /**
     * setStageAsActive: String: method that calls for the activation of a stage and returns a verification message
     * @param projectCount int: number of projects successfully registered
     * @return msg
     */
    public String setStageAsActive(int projectCount){
        String msg = "Project array is full";
        if(projectCount <= 9){
            msg = projects[projectCount].setStageAsActive();
        }
  

        return msg;
    }

    /**
     * searchProjectByName: int: method that looks for a project in the array by comparing its name, it returns the project's position
     * @param projectName String: name of the project
     * @return pos
     */
    public int searchProjectByName(String projectName){
        int pos = -1;

        for(int i = 0; i < PROJECTS_SIZE; i++){
            if(projectName.equals(projects[i].getName())){
                pos = i;
            }
        }

        return pos;
    }

    /**
     * countCapsulesByType: String: this method calls for the counting of the capsules sorted by type, it returns a message with the values
     * @param projectName String: name of the project
     * @return msg
     */
    public String countCapsulesByType(String projectName){
        int pos = searchProjectByName(projectName);
        String msg = "Capsules not counted";
        if(pos != -1){
            msg = projects[pos].countCapsulesByType();
        }
        return msg;
    }   

    /**
     * getLearningsInStage: String: method that accesses the learning of a given capsule, in a given stage and in a given project, returning the learning in a message
     * @param i int: counter
     * @param projectName String: name of the project
     * @return msg
     */

    public String getLearningsInStage(int i, String projectName){
        int pos = searchProjectByName(projectName);

        String msg = projects[pos].getLearningsInStage(i);

        return msg;
    }

    /**
     * compareCapsulesInProject: int: this method compares the number of capsules in each project and determines which is the greater number, it returns the position of the project with the most capsules
     * @param i int: counter
     * @return mostCapsulesPos
     */
    public int compareCapsulesInProject(int i){
        
        aCapsuleCount = projects[i].getCapsulesPerProject(i);

        if(aCapsuleCount > capsuleCount){
           capsuleCount = aCapsuleCount;
           mostCapsulesPos = i;
        }
        return mostCapsulesPos;
    }

    
    /**
     * getMostCapsules: String: this method calls for the comparison of the projects and finally returns a message that states the project with the most capsules.
     * @param i int: counter
     * @return msg
     */
    public String getMostCapsules(int i){
        int pos = compareCapsulesInProject(i);
        String msg = "The project with the most capsules is: " + projects[pos].getName() + ", with " + projects[pos].getCapsulesInProject() + " capsules";
        return msg;
    }

    /**
     * searchCollaboratorByName: boolean: this method calls for the search of a collaborator in a project, returning a flag depending on the result of the following methods
     * @param collaboratorName
     * @param p int: project counter
     * @param j int: stages counter
     * @param i int: collaborator counter
     * @return
     */
    public boolean searchCollaboratorByName(String collaboratorName, int p, int j, int i){
        boolean flag = projects[p].searchCollaboratorByName(collaboratorName, j, i);
        return flag;
    }

    /**
     * initCollaboratorsArray: void: initializes the array of total collaborators
     */
    public void initCollaboratorsArray(){
        for(int i = 0; i < COLLABORATORS_SIZE; i++){
            totalCollaborators[i] = new Collaborator("", "");
        }
    }


    /**
     * compareCollaboratorName: boolean: compares the inputted name with the names of the collaborators in the array, and returns a boolean variable that determines whether it was found
     * @param collaboratorName String: name of the searched collaborator name
     * @return
     */
    public boolean compareCollaboratorName(String collaboratorName){
        boolean foundIt = false;
        for(int i = 0; i < COLLABORATORS_SIZE; i++){
            if(collaboratorName.equals(totalCollaborators[i].getCollaboratorName())){
                foundIt = true;
            }
        }
        return foundIt;
    }

    /**
     * hashtagSearch: String: method that calls for the hashtag search using keywords and the name of the project
     * @param j int: stage counter
     * @param i int: capsule counter
     * @param keywords String: user input search
     * @param projectName String: name of the project containing the capsule
     * @return msg: it contains the capsule's info
     */

    public String hashtagSearch(int j, int i, String keywords, String projectName){
        int pos = searchProjectByName(projectName);
        String msg = projects[pos].hashtagSearch(j, i, keywords);
        return msg;
    }



 
}
