package it.unibo.sweng.bank;

public class Configuration {
    private boolean test = false;
    private static boolean alreadySet = false;
    private static Configuration CONFIGURATION;

    private Configuration(boolean test) {
        this.test = test;
    }

    public boolean isTest() {
        return this.test;
    }

    public static void init(boolean test) {
        if (!alreadySet) {
            CONFIGURATION = new Configuration(test);
        } else {
            throw new IllegalStateException("Configuration already set");
        }
    } 

    public static Configuration getInstance() {
        if (CONFIGURATION == null) {
            CONFIGURATION = new Configuration(false);
        }
        return CONFIGURATION;
    }
}
