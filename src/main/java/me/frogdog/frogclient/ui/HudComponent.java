package me.frogdog.frogclient.ui;

import me.frogdog.frogclient.util.interfaces.Labeled;

public abstract class HudComponent implements Labeled {
	
	private final String label;
	private boolean show;
	private int xPos;
	private int yPos;
	
	public HudComponent(String label, boolean show, int xPos, int yPos) {
		this.label = label;
		this.show = show;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	@Override
	public String getLabel() {
		return this.label;
	}
	
	public abstract Object getComponent();

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
	
	public void toogleShow() {
		this.show = !this.show;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}
	
}
