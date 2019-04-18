package simulation;

import java.util.HashMap;

class RoadSign {

    private String name;
    private HashMap<String,String> attribute;

    RoadSign(String name, HashMap<String,String> attribute) {
        this.name = name;
        this.attribute = new HashMap<>(attribute);
    }

    String getName() {
        return name;
    }

    HashMap<String,String> getAttribute() {
        return attribute;
    }
}
