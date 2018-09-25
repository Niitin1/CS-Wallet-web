package com.credits.wallet.domain;

import java.util.List;

public class GenerateSmartContractBytesRequest {
    private String addressBase58;
    private String methodName;
    private List<String> paramsVals;
    private boolean forgetNewState;

    public GenerateSmartContractBytesRequest(String addressBase58, String methodName, List<String> paramsVals, boolean forgetNewState) {
        this.addressBase58 = addressBase58;
        this.methodName = methodName;
        this.paramsVals = paramsVals;
        this.forgetNewState = forgetNewState;
    }

    public String getAddressBase58() {
        return addressBase58;
    }

    public void setAddressBase58(String addressBase58) {
        this.addressBase58 = addressBase58;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParamsVals() {
        return paramsVals;
    }

    public void setParamsVals(List<String> paramsVals) {
        this.paramsVals = paramsVals;
    }

    public boolean isForgetNewState() {
        return forgetNewState;
    }

    public void setForgetNewState(boolean forgetNewState) {
        this.forgetNewState = forgetNewState;
    }
}
