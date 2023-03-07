package src.models;

public enum Consoles {
    
    XBOX("xbox x"),
    PLAY_STATION("play station 5"),
    NINTENDO_SWITCH("switch");

    private final String name;

    private Consoles(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    

}
