package topic04.exerciseJSON;

public class Geography {
    private String name;
    private Capital capital;
    private String[] languages;
    private Integer area;
    private Canton[] cantons;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(Geography.class.getName())
                .append(" " + "[")
                .append(" Name: " + name + "; ")
                .append(" Capital: " + capital + "; ")
                .append(" Languages: ");
        for(String l : languages){
            sb.append(l + ", ");
        }
        sb.append(" Area: " + area + "; ");
        for(Canton c : cantons){
            sb.append(c + ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
