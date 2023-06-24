package pirateCrewManagementSystem;

public class DevilFruit {
    private String name;
    private String type;

    DevilFruit(String name,String type){
    this.name=name;
    this.type = type;
    }
    DevilFruit(){
    
    }
    DevilFruit(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
   public String toString(){
       return type + " , " + name;
   }
}
