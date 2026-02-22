package com.TETOSOFT.tilegame.sprites;

import com.TETOSOFT.core.GameConstants;
import com.TETOSOFT.graphics.Animation;

/**
 * El personaje controlado por el jugador.
 *
 * Salto variable: impulso inicial al pulsar, boost extra mientras
 * se mantiene pulsado (hasta PLAYER_JUMP_HOLD_MAX ms).
 * Bote al pisar enemigo: velocidad independiente via bounce().
 */
public class Player extends Creature {

    private boolean onGround;
    private boolean sprinting;
    private boolean jumpHeld;
    private long jumpHeldMs;
    private long invincibleMs;
    private boolean justBounced;

    public Player(Animation left, Animation right,
            Animation deadLeft, Animation deadRight) {
        super(left, right, deadLeft, deadRight);
    }

    // -------------------------------------------------------------------------
    // Collision callbacks
    // -------------------------------------------------------------------------

    @Override
    public void collideHorizontal() {
        // No zeroing velocity here — PlayerController reassigns it every frame.
        // PhysicsSystem already snaps the position to the tile boundary.
    }

    @Override
    public void collideVertical() {
        if (getVelocityY() > 0)
            onGround = true;
        setVelocityY(0);
    }

    @Override
    public void setY(float y) {
        if (Math.round(y) > Math.round(getY()))
            onGround = false;
        super.setY(y);
    }

    // -------------------------------------------------------------------------
    // Salto variable
    // -------------------------------------------------------------------------

    /**
     * Impulso inicial. Solo tiene efecto si está en el suelo (o forceJump=true).
     */
    public void jump(boolean forceJump) {
        if (onGround || forceJump) {
            onGround = false;
            jumpHeld = true;
            jumpHeldMs = 0;
            setVelocityY(GameConstants.PLAYER_JUMP_SPEED);
        }
    }

    /**
     * Aplica fuerza extra mientras el botón sigue pulsado.
     * Llamar cada frame desde PlayerController cuando jump esté pressed.
     */
    public void holdJump(long elapsedTime) {
        if (jumpHeld && jumpHeldMs < GameConstants.PLAYER_JUMP_HOLD_MAX) {
            setVelocityY(getVelocityY() + GameConstants.PLAYER_JUMP_HOLD_FORCE * elapsedTime);
            jumpHeldMs += elapsedTime;
        }
    }

    /** Corta el boost al soltar el botón. */
    public void releaseJump() {
        jumpHeld = false;
    }

    // -------------------------------------------------------------------------
    // Bote al pisar un enemigo
    // -------------------------------------------------------------------------

    /**
     * Altura independiente del salto normal — ajustable via PLAYER_BOUNCE_SPEED.
     */
    public void bounce() {
        onGround = false;
        jumpHeld = false;
        justBounced = true;
        setVelocityY(GameConstants.PLAYER_BOUNCE_SPEED);
    }

    /**
     * Suma la fuerza del salto al rebote ya aplicado.
     * Solo se llama si el jugador pulsa salto justo al pisar un enemigo.
     */

    public void jumpFromBounce() {
        jumpHeld = true;
        jumpHeldMs = 0;
        setVelocityY(GameConstants.PLAYER_JUMP_BOUNCE_SPEED); // ← en lugar de la suma
    }

    /** Devuelve true (y lo consume) si el jugador acaba de pisar un enemigo. */
    public boolean consumeBounce() {
        if (justBounced) {
            justBounced = false;
            return true;
        }
        return false;
    }

    // -------------------------------------------------------------------------
    // Sprint
    // -------------------------------------------------------------------------

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    public boolean isSprinting() {
        return sprinting;
    }

    @Override
    public float getMaxSpeed() {
        return sprinting ? GameConstants.PLAYER_RUN_SPEED : GameConstants.PLAYER_WALK_SPEED;
    }

    // -------------------------------------------------------------------------
    // Invencibilidad
    // -------------------------------------------------------------------------

    /** Activa el periodo de invencibilidad tras recibir daño. */
    public void triggerInvincibility() {
        invincibleMs = GameConstants.PLAYER_INVINCIBLE_TIME;
    }

    public boolean isInvincible() {
        return invincibleMs > 0;
    }

    /**
     * Devuelve si el jugador debe ser visible este frame.
     * Alterna cada PLAYER_BLINK_INTERVAL ms durante la invencibilidad.
     */
    public boolean isVisible() {
        if (invincibleMs <= 0)
            return true;
        return (invincibleMs / GameConstants.PLAYER_BLINK_INTERVAL) % 2 == 0;
    }

    @Override
    public void update(long elapsedTime) {
        if (invincibleMs > 0)
            invincibleMs -= elapsedTime;
        anim.setCurrFrame(0);
    }

    @Override
    public void wakeUp() {
        /* Controlado por input, no por IA */ }
}