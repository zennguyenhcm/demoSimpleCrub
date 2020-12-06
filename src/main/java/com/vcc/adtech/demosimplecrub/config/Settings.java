package com.vcc.adtech.demosimplecrub.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

public class Settings {

    private static final Logger logger = LoggerFactory.getLogger(Settings.class);
    private static final Object mutex = new Object();
    private static volatile Settings instance = null;

    //  web config
    public int PORT = 9999;
    public int MYSQL_PORT = 3306;
    public String MYSQL_HOST="localhost";
    public String MYSQL_DB_NAME="test";
    public String MYSQL_USER="test";
    public String MYSQL_PASSWORD="pw";
    public int MYSQL_POOL_SIZE=2;

    public static Settings getInstance() {
        Settings result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    try {
                        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

                        File file = new File("settings.yaml");
                        logger.info("Overiding Application Settings:");
                        logger.info(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
                        logger.info("##################\n");

                        instance = result = mapper.readValue(file, Settings.class);
                    } catch (FileNotFoundException e1) {
                        instance = result = new Settings();
                        logger.info("Using default settings!");
                    } catch (JsonMappingException e) {
                        logger.error("Unknown settings: ", e);
                        System.exit(0);
                    } catch (Exception e) {
                        instance = result = new Settings();
                        logger.error("Error when loading setting properties", e);
                        logger.info("Using default settings!");
                    }
                }
            }
        }
        return result;
    }
}
