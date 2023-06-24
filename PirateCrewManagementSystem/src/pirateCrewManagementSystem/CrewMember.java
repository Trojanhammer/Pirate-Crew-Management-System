package pirateCrewManagementSystem;

public class CrewMember {
    private Crew[] crew;
    private int crewCount =0;

    public CrewMember() {
        crew = new Crew[3]; // Assuming a maximum of 10 crew members for simplicity
    }

    public void addCrewMember(Crew member) {
        // Add the crew member to the crewMembers array
        if(crewCount< crew.length){
            crew[crewCount]= member;
            crewCount++;
        }
        else
        System.out.println("Already full");              
        }

    public Crew getCrewMember(String name) {
        // Find and return the crew member with the given name
        for (Crew member : crew) {
            if (member != null && member.getName().equals(name)) {
                return member;
            }
        }
        return null; // Return null if crew member not found
    }

    public Crew[] getAllCrewMembers() {
        // Return all crew members in the crewMembers array
        return crew;
    }
}
