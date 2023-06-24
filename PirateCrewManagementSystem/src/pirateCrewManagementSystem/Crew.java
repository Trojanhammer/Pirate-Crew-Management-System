package pirateCrewManagementSystem;

public class Crew extends Character {
    private String role;
    private Bounty bounty; //Create a bounty object as an instance of the Bounty class
    private DevilFruit devilfruit;// to demonstrate Crew "has a" relationship with Bounty and DevilFruit 
    
    Crew(String name, int age, String role, Bounty bounty, DevilFruit devilfruit) {
        super(name, age);
        this.role = role;
        this.bounty = bounty;
        this.devilfruit=devilfruit;
    }
     Crew(String name,int age,String role,Bounty bounty){
            super(name,age);
            this.role =role;
            this.bounty =bounty;         
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Bounty getBounty() {
        return bounty;
    }

    public void setBounty(Bounty bounty) {
        this.bounty = bounty;
    }

    public DevilFruit getDevilFruit() {
        return devilfruit;
    }

    public void setDevilFruit(DevilFruit devilfruit) {
        this.devilfruit = devilfruit;
    }
   
public String getDetails() {
    String details = "Name: " + getName() + "\nAge: " + getAge() + "\nRole: " + getRole() + "\nBounty: " + bounty.getAmount();
    if (devilfruit != null) {
        details += "\nDevil Fruit: " + devilfruit.toString();
    } else {
        details += "\nDevil Fruit: None"; // Add a message indicating the absence of a devil fruit
    }
    return details;
}

}
