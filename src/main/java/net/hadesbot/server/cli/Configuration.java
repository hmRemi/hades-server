package net.hadesbot.server.cli;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hades Development
 * @project Hades
 * @date 5/27/2023
 */

/**
 * A class to store and retrieve configuration values.
 */
public class Configuration {

    private final Map<String, Object> args;

    public Configuration() {
        this.args = new ConcurrentHashMap<>();
    }

    /**
     * Sets a configuration value for the specified key.
     *
     * @param key   The key of the configuration value.
     * @param value The value to be set.
     */
    public void set(String key, Object value) {
        args.put(key, value);
    }

    /**
     * Retrieves the configuration value for the specified key.
     *
     * @param key The key of the configuration value.
     * @return The value associated with the key, or null if not found.
     */
    public Object get(String key) {
        return args.get(key);
    }
}