package livelib.utils;

import org.apache.logging.log4j.LogManager;

import java.util.Random;

public class Utils {

    public static String generateNameForRegistration(int numberOfParts, String domainName, int maxRandom, int minRandom) {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i <= numberOfParts; i++) {
            int randomInt = getRandomInteger(maxRandom, minRandom);
            builder.append(randomInt);
            if (i % numberOfParts != 0) {
                builder.append('_');
            }
        }
        builder.append(domainName);
        LogManager.getLogger().debug("Email " + builder.toString() + " was created");
        return builder.toString();
    }

    public static int getRandomInteger(int maxRandom, int minRandom) {
        return new Random().nextInt((maxRandom - minRandom) + 1) + minRandom;
    }
}
