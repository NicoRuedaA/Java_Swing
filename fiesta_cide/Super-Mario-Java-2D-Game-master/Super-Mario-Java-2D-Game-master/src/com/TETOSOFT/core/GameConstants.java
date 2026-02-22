package com.TETOSOFT.core;

/**
 * Central repository for all tunable game constants.
 * Edit this file to adjust physics, speed, and timing without
 * hunting across multiple classes.
 */
public final class GameConstants {

    private GameConstants() {
    }

    // -------------------------------------------------------------------------
    // Window
    // -------------------------------------------------------------------------
    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;

    // -------------------------------------------------------------------------
    // Physics
    // -------------------------------------------------------------------------
    /** Downward acceleration applied every millisecond while rising. */
    public static final float GRAVITY = 0.002f;
    /** Stronger downward acceleration applied while falling (Mario-feel). */
    public static final float GRAVITY_FALLING = 0.005f;

    // -------------------------------------------------------------------------
    // Player movement
    // -------------------------------------------------------------------------
    public static final float PLAYER_WALK_SPEED = 0.34f;
    public static final float PLAYER_RUN_SPEED = 0.45f;

    /** Impulso inicial al saltar. */
    public static final float PLAYER_JUMP_SPEED = -0.65f;
    /** Fuerza extra aplicada cada ms mientras se mantiene pulsado el botón. */
    public static final float PLAYER_JUMP_HOLD_FORCE = -0.0020f;
    /** Máximo de ms que se aplica el boost de salto. */
    public static final int PLAYER_JUMP_HOLD_MAX = 300;
    public static final float PLAYER_JUMP_BOUNCE_SPEED = -0.5f; // combo salto+rebote

    /** Milisegundos de invencibilidad tras recibir daño. */
    public static final int PLAYER_INVINCIBLE_TIME = 2000;
    /** Intervalo de parpadeo en ms durante la invencibilidad. */
    public static final int PLAYER_BLINK_INTERVAL = 100;
    /** Bote al pisar un enemigo (más suave que el salto normal). */
    public static final float PLAYER_BOUNCE_SPEED = -0.70f;

    // -------------------------------------------------------------------------
    // Creature timing
    // -------------------------------------------------------------------------
    /** Milliseconds a creature stays in STATE_DYING before STATE_DEAD. */
    public static final int CREATURE_DIE_TIME = 1000;

    // -------------------------------------------------------------------------
    // Scoring
    // -------------------------------------------------------------------------
    /** Coins needed to earn an extra life. */
    public static final int COINS_PER_EXTRA_LIFE = 100;
    public static final int STARTING_LIVES = 6;
}