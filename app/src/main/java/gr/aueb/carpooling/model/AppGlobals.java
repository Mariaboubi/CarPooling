package gr.aueb.carpooling.model;

public class AppGlobals {
    /**
     * The cost per kilometer constant represents the default cost per kilometer for carpooling services.
     * It is a global setting used for pricing calculations.
     */
    public static double COST_PER_KM = 0.25;

    /**
     * The minimum rating constant represents the lowest possible rating a driver or passenger can receive.
     * It is used to define the lower bound for valid ratings in the application.
     */
    public static float MIN_RATING = 0.0f;
    /**
     * The maximum rating constant represents the highest possible rating a driver or passenger can receive.
     * It is used to define the upper bound for valid ratings in the application.
     */
    public static float MAX_RATING = 5.0f;

}

