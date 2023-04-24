package ui;

import java.util.Scanner;
import model.Controller;



public class Main{

    private Scanner reader;
    private Controller controller;
    private int projectCount = 0;
    private Main view;
    
    
    
    /**
     * Main(): class constructor
     * 
     */
    public Main(){
        reader = new Scanner(System.in);
        controller = new Controller();
        
    }

    public static void main(String[] args){
        Main view = new Main();
        view.initProjects();
        int option = 0;
        
        do{
        view.menu();
        option = view.validateIntegerInput();
        view.executeOption(option);
        }while(option != 0);
        
        view.reader.close();
    }

    /**
     * menu(): void:  method that will display the system's menu
     */
    public void menu(){
        System.out.println("Select an option: ");
        System.out.println("1. Create project");
        System.out.println("2. End current stage of a project");
        System.out.println("3. Register capsule");
        System.out.println("4. Approve capsule");
        System.out.println("5. Publish capsule");
        System.out.println("6. Count capsules by type");
        System.out.println("7. List capsule learnings");
        System.out.println("8. Get project with the most capsules");
        System.out.println("9. Validate collaborator participation by name");
        System.out.println("10. Search capsule with keywords");
        System.out.println("11. Exit");
        System.out.println("-----------------------------------");
        System.out.println("");

    }

    

    /**
     * executeOption(int option): void: method that will execute the option selected by the user
     * @param option :user's selection
     */

    public void executeOption(int option){
        switch(option){
            
            case 1: 
                addProject();
            break;

            case 2:
                System.out.println("Enter the name of the stage's project: ");
                String projectName = reader.next();
                int pos = controller.searchProjectByName(projectName);
                String msg = controller.endStage(pos);
                System.out.println(msg);
                System.out.println("------------------");
                String amsg = controller.setStageAsActive(pos);
                System.out.println(amsg);
                System.out.println("------------------");
            break;

            case 3:
                String added = registerCapsule();
                System.out.println(added);
            break;

            case 4:
                String approved = approveCapsule();
                System.out.println(approved);
                System.out.println("------------------");
            break;

            case 5:
                String html = publishCapsule();
                System.out.println("The capsule's html format is: " + html);
            break;

            case 6: 
                System.out.println(countCapsulesByType());
                System.out.println("------------------");
            break;

            case 7:
                getLearningsInStage();
            break;

            case 8:
                getMostCapsules();
            break;

            case 9:
                verifyCollaboratorParticipation();
            break;

            case 10:
                hashtagSearch();
            break;

            case 11:
                System.out.println("Exit");
            break;


        }

    }

   /**
    * addProject: void: this method asks for the project's info and calls for its creation.
    */
    public void addProject(){
        

        System.out.println("Enter the project's name: ");
        String projectName = reader.next();
        System.out.println("------------------");

        System.out.println("Enter the client's name: ");
        String clientName = reader.next();
        System.out.println("------------------");

        System.out.println("Enter the project's budget: ");
        double projectBudget = validateDoubleInput();
        System.out.println("------------------");

        System.out.println("Enter manager name: ");
        String managerName = reader.next();
        System.out.println("------------------");

        System.out.println("Enter manager phone: ");
        String managerPhone = reader.next();
        System.out.println("------------------");

        System.out.println("Enter client manager name: ");
        String clientManagerName = reader.next();
        System.out.println("------------------");
        System.out.println("Enter client manager phone: ");
        String clientManagerPhone = reader.next();
        System.out.println("------------------");

        String msg = controller.addProject(projectName, clientName, projectBudget, projectCount);

        controller.setManagerName(projectCount, managerName);
        controller.setManagerPhone(projectCount, managerPhone);
        controller.setClientManagerName(projectCount, clientManagerName);
        controller.setClientManagerPhone(projectCount, clientManagerPhone);

        
        System.out.println(msg);
        System.out.println("----------**----------");
        
        String verify = askForStagesDuration(); 

        System.out.println(verify);
        System.out.println("----------**----------");

        String act = controller.setStageAsActive(projectCount);

        System.out.println(act);
        projectCount++;

        
    }

    /**
     * validateIntegerInput: int: method that validates the user has entered an int value for option
     * @return option
     */

    public int validateIntegerInput(){
        int option = 0; 
        if(reader.hasNextInt()){
            option = reader.nextInt(); 
        }
        else{
            reader.nextLine(); 
            option = -1; 
            System.out.println("Please enter an int value"); 
            System.out.println("----------**----------");
        }
        return option; 
    }

    /**
     * validateDurationInput: int: method that validates the user has entered an int value for duration
     * @return duration
     */
    public int validateDurationInput(){
        int duration = -1;
        do{
            if(reader.hasNextInt()){
                duration = reader.nextInt();
            }
            else{
                reader.nextLine();
                reader.nextLine();
                System.out.println("Please enter an int value");
                System.out.println("----------**----------");
            }
        }while(duration == 0 || duration == -1);

        return duration;
        
    }


/**
 * askForStagesDuration: String: this method asks for the duration of each stage and returns a validation message
 * @return msg
 */
    public String askForStagesDuration(){

        String msg = "Stage durations not registered";
        System.out.println("Enter the duration of the stages (in months): ");
        for(int i = 0; i < 6; i++){
            System.out.println(controller.accessStageName(projectCount, i) + ":");
            int duration = validateDurationInput();
            controller.setStageDuration(projectCount, i, duration);
            msg = "Stage durations registered!";
        }
        
        return msg;

    }


    /**
     * askForCapsuleData: void: method that asks for the info of a capsule and calls for the adding method
     */

    public String registerCapsule(){
        String projectName = "";
        String capsuleId = "";
        String capsuleType = "";
        String capsuleDescription = "";
        String collaboratorName = "";
        String collaboratorPosition = "";
        String learning = "";


        System.out.println("Enter the name of the project: ");
        projectName = reader.next();
        System.out.println("------------------");

        System.out.println("Enter capsule ID");
        capsuleId = reader.next();
        System.out.println("------------------");

        System.out.println("Enter capsule type: ");
        capsuleType = validateCapsuleTypeInput();
        System.out.println("------------------");
        
        System.out.println("Enter capsule description (press enter before): ");
        reader.next();
        reader.nextLine();
        capsuleDescription = reader.nextLine();
        
        System.out.println("------------------");

        System.out.println("Enter collaborator name: ");
        collaboratorName = reader.next();
        System.out.println("------------------");

        System.out.println("Enter collaborator position: ");
        collaboratorPosition = reader.next();
        System.out.println("------------------");

        System.out.println("Enter capsule learning (press enter before): ");
        reader.next();
        reader.nextLine();
        learning = reader.nextLine();
        
        System.out.println("------------------");

        String msg = controller.addCapsule(projectName, capsuleId, capsuleDescription, capsuleType, collaboratorName, collaboratorPosition, learning);

        return msg;
    }

    /**
     * initProjects: void: method that initializes the projects array
     */
    public void initProjects(){
        controller.initProjects();
       
    }

    /**
     * approveCapsule: string: method that asks the user for the capsule that they want to approve, and then calls for its approval
     * @return msg
     */
    public String approveCapsule(){
        System.out.println("Enter the name of the project: ");
        String projectName = reader.next();
        System.out.println("------------------");

        System.out.println("Enter the capsule's ID: ");
        String capsuleId = reader.next();
        System.out.println("------------------");

       String msg = controller.approveCapsule(projectName, capsuleId);

       return msg;
    }

    /**
     * publishCapsule: String: method that asks the user for the capsule that they want to publish, calls for its publishing, and generates html format
     * @return html
     */

    public String publishCapsule(){
        System.out.println("Enter the name of the project: ");
        String projectName = reader.next();
        System.out.println("------------------");

        System.out.println("Enter the capsule's ID: ");
        String capsuleId = reader.next();
        System.out.println("------------------");

        String html = controller.publishCapsule(projectName, capsuleId);
        return html;
    }

    /**
     * countCapsulesByType: String: method that calls for another method that counts the amount of capsules per type in a project
     * @return msg
     */
    public String countCapsulesByType(){
        System.out.println("Enter the name of the project: ");
        String projectName = reader.next();
        System.out.println("------------------");

        String msg = controller.countCapsulesByType(projectName);
        
        return msg;

    }

    /**
     * getLearningsInStage: void: method that calls for the listing of the capsules' learnings in a project
     */
    public void getLearningsInStage(){
        System.out.println("Enter the name of the project: ");
        String projectName = reader.next();
        System.out.println("------------------");
        System.out.println("Learnings: ");
        String msg = "";
        for(int i = 0; i < 50; i++){

            msg = controller.getLearningsInStage(i, projectName);

            if(msg != "N/A"){
                System.out.println(msg);
            }

            
        }
        
        System.out.println("------------------");
    }

    /**
     * getMostCapsules: void: this method calls for the other method that returns the project with the most capsules
     */
    public void getMostCapsules(){
        String msg = "";
        for(int i = 0; i < projectCount; i++){
            msg = controller.getMostCapsules(i);
        }

        System.out.println(msg);
    }

    /**
     * validateCapsuleTypeInput: String: method that makes sure the user's input for capsule type fits the established options
     * @return capsuleType
     */
    public String validateCapsuleTypeInput(){
        String capsuleType;
        do{
            capsuleType = reader.next();
        }while(!capsuleType.equalsIgnoreCase("TECHNICAL") && !capsuleType.equalsIgnoreCase("DOMAIN") && !capsuleType.equalsIgnoreCase("MANAGEMENT") && !capsuleType.equalsIgnoreCase("EXPERIENCES"));

        return capsuleType;
    }

    /**
     * validateDoubleInput: double: method that makes sure the imputted value is a double
     * @return projectBudget
     */
    public double validateDoubleInput(){
        double projectBudget = 0;
        do{
            if(reader.hasNextDouble()){
                projectBudget = reader.nextDouble();
            }
            else{
                reader.nextLine();
                projectBudget = -1;
                System.out.println("Enter a double value: ");
                reader.nextLine();
            }
        }while(projectBudget == -1);
  

        return projectBudget;
    }

    /**
     * verifyCollaboratorParticipation: void: method that checks if a given collaborator has registered a capsule in any project
     */

    public void verifyCollaboratorParticipation(){
        System.out.println("Enter the collaborator's name: ");
        String collaboratorName = reader.next();
        boolean flag = controller.compareCollaboratorName(collaboratorName);
        if(flag == true){
            System.out.println("The collaborator has registered a capsule in a project");
        }
    }

    /**
     * hashtagSearch: void: method that calls for the search of a capsule using its project and keywords
     */

    public void hashtagSearch(){
        System.out.println("Enter the name of the project: ");
        String projectName = reader.next();
        System.out.println("------------------");

        System.out.println("Enter keywords: ");
        reader.next();
        String keywords = reader.nextLine();
        System.out.println("------------------");

        for(int j = 0; j < 6; j++){
            for(int i = 0; i < 50; i++){
                String msg = controller.hashtagSearch(j, i, keywords, projectName);
                if(!msg.equals("N/A")){
                    System.out.println(msg);
                }
            }
        }
    }



}