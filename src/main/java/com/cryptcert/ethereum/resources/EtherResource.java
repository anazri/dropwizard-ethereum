/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cryptcert.ethereum.resources;

import com.cryptcert.ethereum.services.EtherService;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.ToString;
import org.apache.log4j.Logger;

/**
 *
 * @author nazri
 */
@Path("/v1/ether")
@Api("/v1/ether")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ToString
public class EtherResource {

    private static final Logger LOGGER = Logger.getLogger(EtherResource.class);

    private final EtherService etherService;
            
    public EtherResource(EtherService etherService) {
        this.etherService = etherService;
    }
    
    @GET
    @Path("/version")
    @ApiOperation("Get Ether Web3 Client Version")
    @ApiResponses(value = {
        @ApiResponse(code = 202, message = "Version found")
        ,        
        @ApiResponse(code = 400, message = "Version not found")
    })
    @UnitOfWork
    public Response getVersion() throws InterruptedException, ExecutionException {
        final String version = etherService.getVersion();
        LOGGER.info("version in resource " + version);
        if (!version.isEmpty()) {
            return Response.serverError().entity(new String()).status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();
        }
        return Response.ok(version).status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
}
