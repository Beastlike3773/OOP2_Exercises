package topic04.exerciseJSON;

public class Canton {
    private String name;
    private String abbreviation;

    public String toString(){
        return Canton.class.getName() + " [ Name: " + name + ", Abbreviation: " + abbreviation + " ]";
    }
}
