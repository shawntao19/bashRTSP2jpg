package ceertsptransport;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author elegance
 */
public class LoggingConfiguration {

    public static void load(String configPath) {
        LogManager lm = LogManager.getLogManager();
        InputStream is = LoggingConfiguration.class.getResourceAsStream(configPath);
        try {
            lm.readConfiguration(is);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(LoggingConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
