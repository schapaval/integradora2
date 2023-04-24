package model;


public class Manager{

    private String managerName;
    private String managerPhone;

    /**
     * Manager: class constructor
     * @param managerName String: name of the manager
     * @param managerPhone String: phone number of the manager
     */

    public Manager(String managerName, String managerPhone){

       this.managerName = managerName;
       this.managerPhone = managerPhone;
    }

    public String getName(){
        return managerName;
    }

    public String getPhone(){
        return managerPhone;
    }

    public void setName(String managerName){
        this.managerName = managerName;
    }

    public void setPhone(String managerPhone){
        this.managerPhone = managerPhone;
    }
}