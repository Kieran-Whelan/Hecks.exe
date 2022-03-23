package me.frogdog.frogclient.module.modules.client;

import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.properties.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public final class HudEditor extends Module {
    private static final Property<Boolean> speed = new Property<>(false,"Speed");
    private static final Property<Boolean> coords = new Property<>(false,"Coords");
    private static final Property<Boolean> watermark = new Property<>(false,"Watermark");
    private static final Property<Boolean> moduleArray = new Property<>(false,"ModuleArray");
    private static final Property<Boolean> facing = new Property<>(false,"Facing");
    private static final Property<Boolean> clientName = new Property<>(false,"ClientName");

    static ArrayList<Property> properties = new ArrayList<>();
    static boolean value;

    public HudEditor() {
        super("HudEditor", new String[] {"HudEditor", "ComponentEditor"});
        this.offerProperties(speed, coords, watermark, moduleArray, facing, clientName);
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
