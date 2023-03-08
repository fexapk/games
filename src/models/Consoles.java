package src.models;

public enum Consoles {
    
    XBOX("Xbox_X"),
    PLAY_STATION("Playstation_5"),
    NINTENDO_SWITCH("Switch");

    private final String name;

    private Consoles(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    

}
