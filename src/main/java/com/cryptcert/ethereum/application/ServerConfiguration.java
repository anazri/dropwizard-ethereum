/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cryptcert.ethereum.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 *
 * @author nazri
 */
class ServerConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    @Getter
    private final DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty("swagger")
    @Getter
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty
    @Getter
    private Map<String, Object> defaultHystrixConfig;

}
