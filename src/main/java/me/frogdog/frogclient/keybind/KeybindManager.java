package me.frogdog.frogclient.keybind;

import java.util.ArrayList;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.InputEvent;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.util.interfaces.Toggleable;
import me.frogdog.frogclient.util.registry.ListRegistry;

public final class KeybindManager extends ListRegistry<Keybind> {

    public KeybindManager() {
        this.registry = new ArrayList<Keybind>();
        Frog.getInstance().getEventManager().register(new Listener<InputEvent>("keybinds_input_listener"){

            @Override
            public void call(InputEvent event) {
                if (event.getType() == InputEvent.Type.KEYBOARD_KEY_PRESS) {
                    KeybindManager.this.registry.forEach(keybind -> {
                        if (keybind.getKey() != 0 && keybind.getKey() == event.getKey()) {
                            String label = keybind.getLabel();
                            Module module = Frog.getInstance().getModuleManager().getModuleByAlias(label);
                            if (module == null) {
                            	Command.sendClientSideMessage("No such module exists");
                            } else if (!(module instanceof Toggleable)) {
                            	Command.sendClientSideMessage("That module can't be toggled");
                            } else {
                                ToggleableModule toggleableModule = (ToggleableModule)module;
                                if(toggleableModule.isRunning()) {
                                	toggleableModule.setRunning(false);
                                } else {
                                	toggleableModule.setRunning(true);
                                }
                    	        Command.sendClientSideMessage(module.getLabel() + " has been set to " + toggleableModule.isRunning());    
                            }
                        }
                    });
                }
            }
        });
    }

    public Keybind getKeybindByLabel(String label) {
        for (Keybind keybind : registry) {
            if (!label.equalsIgnoreCase(keybind.getLabel())) continue;
            return keybind;
        }
        return null;
    }
}

