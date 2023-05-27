package net.hadesbot.server.cli;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Hades Development
 * @project Hades
 * @date 5/27/2023
 */

/**
 * A utility class to parse and process command-line arguments into a configuration object.
 */
public class CommandLineParser {

    private final Configuration configuration;
    private final String[] args;

    /**
     * Constructs a CommandLineParser object with the given configuration and arguments.
     *
     * @param configuration The configuration object to store the parsed arguments.
     * @param args          The command-line arguments to be parsed.
     */
    public CommandLineParser(final Configuration configuration, final String[] args) {
        this.configuration = configuration;
        this.args = args;
    }

    /**
     * Parses the command-line arguments and loads them into the configuration.
     */
    public void parseArguments() {
        final AtomicReference<String> latestArgument = new AtomicReference<>("unknown");
        Arrays.stream(this.args).forEach(argument -> {
            if (!latestArgument.get().equals("unknown")) {
                // Remove the leading '-' from the argument and set it in the configuration
                this.configuration.set(latestArgument.toString().replaceFirst("-", ""), argument);
                latestArgument.set("unknown");
            } else {
                latestArgument.set(argument);
            }
        });
    }
}