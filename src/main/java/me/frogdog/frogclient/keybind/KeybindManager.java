package me.frogdog.frogclient.keybind;

import java.util.ArrayList;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.InputEvent;
import me.frogdog.frogclient.util.registry.ListRegistry;

public final class KeybindManager extends ListRegistry<Keybind> {

    public KeybindManager() {
        this.registry = new ArrayList();
        Frog.getInstance().getEventManager().register(new Listener<InputEvent>("keybinds_input_listener"){

            @Override
            public void call(InputEvent event) {
                if (event.getType() == InputEvent.Type.KEYBOARD_KEY_PRESS) {
                    KeybindManager.this.registry.forEach(keybind -> {
                        if (keybind.getKey() != 0 && keybind.getKey() == event.getKey()) {
                            keybind.onPressed();
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

