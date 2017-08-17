/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cryptcert.ethereum.services;

import java.util.concurrent.ExecutionException;
import org.apache.log4j.Logger;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

/**
 *
 * @author nazri
 */
public class EtherService {
    
    private static final Logger LOGGER = Logger.getLogger(EtherService.class);
    
    private final Web3j web3;
    
    public EtherService(Web3j web3) {
        this.web3 = web3;
    }
    
    public String getVersion() throws InterruptedException, ExecutionException {
       
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        LOGGER.info("version: " + web3ClientVersion.getWeb3ClientVersion());
        return web3ClientVersion.getWeb3ClientVersion();
    }
}
