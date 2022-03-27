package me.frogdog.hecks.module.modules.client;

import me.frogdog.hecks.module.Module;
import me.frogdog.hecks.property.Property;

import java.util.ArrayList;

public final class Hud extends Module {
    private static final Property<Boolean> speed = new Property<>(false,"Speed");
    private static final Property<Boolean> coords = new Property<>(false,"Coords");
    private static final Property<Boolean> watermark = new Property<>(false,"Watermark");
    private static final Property<Boolean> moduleArray = new Property<>(false,"ModuleArray");
    private static final Property<Boolean> facing = new Property<>(false,"Facing");
    private static final Property<Boolean> title = new Property<>(true,"Title");

    static ArrayList<Property> properties = new ArrayList<>();
    static boolean value;

    public Hud() {
        super("HudEditor", new String[] {"HudEditor", "ComponentEditor"});
        this.offerProperties(speed, coords, watermark, moduleArray, facing, title);
        this.getProperties().forEach(p -> {
            properties.add(p);
        });
    }

    public static boolean getShow(String label) {
        value = false;
        properties.forEach(p -> {
            if (p.getAliases()[0].equalsIgnoreCase(label)) {
                if (p.getValue() instanceof Boolean && p.getValue().equals(true)) {
                    value = true;
                }
            }
        });
        return value;
    }
}