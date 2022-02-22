package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.MotionUpdateEvent;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.module.modules.render.Fullbright.Mode;
import me.frogdog.frogclient.properties.EnumProperty;
import me.frogdog.frogclient.properties.NumberProperty;

public final class Speed extends ToggleableModule {
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.STRAFE, "Mode", "m");
    //private final NumberProperty<Double> speed = new NumberProperty<Double>("Speed", 0.5, 0.6,);

    public Speed() {
        super("Speed", new String[]{"speed", "Speed"}, -2366720, ModuleType.MOVEMENT);
        this.offerProperties(this.mode);
        this.listeners.add(new Listener<TickEvent>("key_listener"){
        	
        	@Override
        	public void call(TickEvent event) {
        		if(Speed.this.mode.getValue() == Mode.STRAFE) {
        			Frog.getInstance().mc.player.motionY *= 0.985;
        			if(Frog.getInstance().mc.player.onGround) {
        				if(Frog.getInstance().mc.gameSettings.keyBindForward.isKeyDown() || Frog.getInstance().mc.gameSettings.keyBindLeft.isKeyDown() || Frog.getInstance().mc.gameSettings.keyBindRight.isKeyDown() || Frog.getInstance().mc.gameSettings.keyBindBack.isKeyDown()) {
        					Frog.getInstance().mc.player.jump();
        					//double[] direction = directionSpeed();
        				}
        			}
        		}
        	}
        });
    }
    
    public double[] directionSpeed(double speed) {
    	float forward = Frog.getInstance().mc.player.movementInput.moveForward;
    	float side = Frog.getInstance().mc.player.movementInput.moveStrafe;
    	float yaw = Frog.getInstance().mc.player.prevRotationYaw + (Frog.getInstance().mc.player.rotationYaw - Frog.getInstance().mc.player.prevRotationYaw) * Frog.getInstance().mc.getRenderPartialTicks();
    	
        if (forward != 0) {
            if (side > 0) {
                yaw += (forward > 0 ? -45 : 45);
            } else if (side < 0) {
                yaw += (forward > 0 ? 45 : -45);
            }
            side = 0;

            if (forward > 0) {
                forward = 1;
            } else if (forward < 0) {
                forward = -1;
            }
        }

        final double sin = Math.sin(Math.toRadians(yaw + 90));
        final double cos = Math.cos(Math.toRadians(yaw + 90));
        final double posX = (forward * speed * cos + side * speed * sin);
        final double posZ = (forward * speed * sin - side * speed * cos);
        return new double[] { posX, posZ };
    }
    
    public enum Mode {
        STRAFE,
        ONGROUND;

    }
}
