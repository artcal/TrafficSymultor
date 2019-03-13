package symulation;

import java.util.HashMap;

public class Roadsign {

    private String name;
    private HashMap<String,String> attribute;
    private Roadsign ComplementarySign;

    public Roadsign(String name, HashMap<String,String> attribute, Roadsign complementarySign) {
        this.name = name;
        this.attribute = new HashMap<>();
        ComplementarySign = complementarySign;
    }

    public String getName() {
        return name;
    }

    public HashMap<String,String> getAttribute() {
        return attribute;
    }

    public Roadsign getComplementarySign() {
        return ComplementarySign;
    }
}
