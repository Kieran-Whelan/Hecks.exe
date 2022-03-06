package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.EnumProperty;
import me.frogdog.frogclient.properties.NumberProperty;

public final class Speed extends ToggleableModule {
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.STRAFE, "Mode", "m");
    private final NumberProperty<Double> speed = new NumberProperty<Double>(0.5, 0.0, 3.0, "Speed");

    public Speed() {
        super("Speed", new String[]{"speed", "Speed"}, ModuleType.MOVEMENT);
        this.offerProperties(this.mode, this.speed);
        this.listeners.add(new Listener<TickEvent>("tick_listener"){
        	
        	@Override
        	public void call(TickEvent event) {
        		if(Speed.this.mode.getValue() == Mode.STRAFE) {
        			if(mc.player.onGround) {
        				if(mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown()) {
        					mc.player.jump();
        					double[] direction = directionSpeed(speed.getValue());
        					mc.player.motionX = direction[0];
        					mc.player.motionZ = direction[1];
        					
        				}
        			} else {
    					double[] direction = directionSpeed(0.26);
    					mc.player.motionX = direction[0];
    					mc.player.motionZ = direction[1];
        			}
        		}
        		
        		if(Speed.this.mode.getValue() == Mode.BHop) {
        			if(mc.player.onGround) {
        				mc.player.jump();
        			}
        		}
        	}
        });
    }
    
    private double[] directionSpeed(double speed) {
    	float forward = mc.player.movementInput.moveForward;
    	float side = mc.player.movementInput.moveStrafe;
    	float yaw = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
    	
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
        ONGROUND,
        BHop

    }
}
