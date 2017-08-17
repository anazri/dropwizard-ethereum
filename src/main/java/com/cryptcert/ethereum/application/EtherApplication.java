/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cryptcert.ethereum.application;

import com.codahale.metrics.MetricRegistry;
import com.cryptcert.ethereum.resources.EtherResource;
import com.cryptcert.ethereum.services.EtherService;
import com.google.common.collect.ImmutableList;
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.contrib.codahalemetricspublisher.HystrixCodaHaleMetricsPublisher;
import com.netflix.hystrix.strategy.HystrixPlugins;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.apache.commons.configuration.MapConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

/**
 *
 * @author nazri
 */
public class EtherApplication extends Application<ServerConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EtherApplication.class);

    public static void main(String[] args) throws Exception {
        new EtherApplication().run(args);
    }

    private final HibernateBundle<ServerConfiguration> dbService = new HibernateBundle<ServerConfiguration>(ImmutableList.of(), new SessionFactoryFactory()) {
        @Override
        public DataSourceFactory getDataSourceFactory(ServerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        bootstrap.addBundle(dbService);
        bootstrap.addBundle(new SwaggerBundle<ServerConfiguration>() {
            @Override
            public SwaggerBundleConfiguration getSwaggerBundleConfiguration(ServerConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        
                final FilterRegistration.Dynamic cors
                = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        ConfigurationManager.install(new MapConfiguration(configuration.getDefaultHystrixConfig()));

        MetricRegistry metricRegistry = new MetricRegistry();

        HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixCodaHaleMetricsPublisher(metricRegistry));

        final DBIFactory factory = new DBIFactory();

        final DBI jdbi = factory
                .build(environment, configuration.getDataSourceFactory(), "mysql");
        
        /* Declare Services & DAOs. */
        
        final Web3j web3 = Web3j.build(new HttpService()); // defaults to http://localhost:8545/
        final EtherService etherService = new EtherService(web3);
        final EtherResource etherResource = new EtherResource(etherService);
        environment.jersey().register(new EtherResource(etherService));
    }

}
