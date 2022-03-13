package me.frogdog.frogclient.ui;

import java.util.ArrayList;
import java.util.List;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.util.interfaces.Labeled;
import net.minecraft.client.gui.Gui;

public class HudComponent implements Labeled {
	private final String label;
    protected final List<Listener<?>> listeners = new ArrayList<>();
	
	public HudComponent(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}
	
}
