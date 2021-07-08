package com.ucm.ms.accounts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * If down the line, you want to use custom configuration properties specific to this microservice, add them as
 * members of this class, then generate getters and setters for them. They'll be loaded from properties called
 * ms.accounts.[propertyname] in application[-profile].properties.
 *
 * @author Joshua Podhola
 */
@ConfigurationProperties("ms.accounts")
public class ConfigProperties {
}
