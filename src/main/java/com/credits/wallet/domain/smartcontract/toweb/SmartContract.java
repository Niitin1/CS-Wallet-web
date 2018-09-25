package com.credits.wallet.domain.smartcontract.toweb;

import java.io.Serializable;
import java.util.List;

public class SmartContract implements Serializable {

    private String address;
    private String deployer;
    private SourceCode sourceCode;
    private String hashState;

    public SmartContract(String address, String deployer, SourceCode sourceCode, String hashState) {
        this.address = address;
        this.deployer = deployer;
        this.sourceCode = sourceCode;
        this.hashState = hashState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeployer() {
        return deployer;
    }

    public void setDeployer(String deployer) {
        this.deployer = deployer;
    }

    public SourceCode getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getHashState() {
        return hashState;
    }

    public void setHashState(String hashState) {
        this.hashState = hashState;
    }
}
