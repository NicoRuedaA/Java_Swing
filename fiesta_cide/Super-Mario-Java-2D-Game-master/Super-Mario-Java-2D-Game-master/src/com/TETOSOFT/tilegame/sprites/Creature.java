package com.TETOSOFT.tilegame.sprites;

import java.lang.reflect.Constructor;
import com.TETOSOFT.graphics.*;

/**
 * A Creature is a Sprite that is affected by gravity and can
 * die. It has four Animations: moving left, moving right,
 * dying on the left, and dying on the right.
 */
public abstract class Creature extends Sprite {

    /**
     * Amount of time to go from STATE_DYING to STATE_DEAD.
     */
    private static final int DIE_TIME = 1000;

    public static final int STATE_NORMAL = 0;
    public static final int STATE_DYING = 1;
    public static final int STATE_DEAD = 2;

    private Animation left;
    private Animation right;
    private Animation deadLeft;
    private Animation deadRight;
    private int state;
    private long stateTime;

    /**
     * Creates a new Creature with the specified Animations.
     */
    public Creature(Animation left, Animation right,
            Animation deadLeft, Animation deadRight) {
        super(right);
        this.left = left;
        this.right = right;
        this.deadLeft = deadLeft;
        this.deadRight = deadRight;
        state = STATE_NORMAL;
    }

    public Object clone() {
        // use reflection to create the correct subclass
        Constructor constructor = getClass().getConstructors()[0];
        try {
            return constructor.newInstance(new Object[] {
                    (Animation) left.clone(),
                    (Animation) right.clone(),
                    (Animation) deadLeft.clone(),
                    (Animation) deadRight.clone()
            });
        } catch (Exception ex) {
            // should never happen
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the maximum speed of this Creature.
     */
    public float getMaxSpeed() {
        return 0;
    }

    /**
     * Wakes up the creature when the Creature first appears
     * on screen. Normally, the creature starts moving left.
     */
    public void wakeUp() {
        if (getState() == STATE_NORMAL && getVelocityX() == 0) {
            setVelocityX(-getMaxSpeed());
        }
    }

    /**
     * Gets the state of this Creature. The state is either
     * STATE_NORMAL, STATE_DYING, or STATE_DEAD.
     */
    public int getState() {
        return state;
    }

    /**
     * Sets the state of this Creature to STATE_NORMAL,
     * STATE_DYING, or STATE_DEAD.
     */
    public void setState(int state) {
        if (this.state != state) {
            this.state = state;
            stateTime = 0;
            if (state == STATE_DYING) {
                setVelocityX(0);
                setVelocityY(0);
            }
        }
    }

    /**
     * Checks if this creature is alive.
     */
    public boolean isAlive() {
        return (state == STATE_NORMAL);
    }

    /**
     * Checks if this creature is flying.
     */
    public boolean isFlying() {
        return false;
    }

    /**
     * Called before update() if the creature collided with a
     * tile horizontally.
     */
    public void collideHorizontal() {
        setVelocityX(-getVelocityX());
    }

    /**
     * Called before update() if the creature collided with a
     * tile vertically.
     */
    public void collideVertical() {
        setVelocityY(0);
    }

    /**
     * Updates the animaton for this creature.
     */
    public void update(long elapsedTime) {
        // 1. Seleccionar la animación correcta según dirección y estado
        Animation newAnim = anim;
        if (getVelocityX() < 0) {
            newAnim = left;
        } else if (getVelocityX() > 0) {
            newAnim = right;
        }

        if (state == STATE_DYING) {
            if (newAnim == left)
                newAnim = deadLeft;
            else if (newAnim == right)
                newAnim = deadRight;
        }

        // 2. Cambiar de animación si es necesario
        if (anim != newAnim) {
            anim = newAnim;
            anim.start();
        }

        // 3. --- LÓGICA DE CONTROL DE FRAMES (Salto, Idle y Caminata) ---
        if (state == STATE_NORMAL) {
            if (getVelocityY() != 0) {
                // SALTO: Si la velocidad vertical no es 0, usamos el 4º sprite (índice 3)
                anim.setCurrFrame(3);
            } else if (getVelocityX() == 0) {
                // PARADO: Si no hay velocidad horizontal ni vertical, usamos el 5º sprite
                // (índice 4)
                anim.setCurrFrame(4);
            } else {
                // CAMINANDO: Si se está moviendo, dejamos que la animación corra sus frames
                // normales
                anim.update(elapsedTime);
            }
        } else {
            // Si está muriendo, la animación de muerte se actualiza normalmente
            anim.update(elapsedTime);
        }

        // 4. Actualizar estado de muerte
        stateTime += elapsedTime;
        if (state == STATE_DYING && stateTime >= DIE_TIME) {
            setState(STATE_DEAD);
        }
    }

}