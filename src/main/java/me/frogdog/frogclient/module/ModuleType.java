package me.frogdog.frogclient.module;

public enum ModuleType {
    COMBAT("Combat"),
    EXPLOITS("Exploits"),
    MISCELLANEOUS("Misc"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    WORLD("World");

    private final String label;

    private ModuleType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}

