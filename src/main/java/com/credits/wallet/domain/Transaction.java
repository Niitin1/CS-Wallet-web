package com.credits.wallet.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.ByteBuffer;

public class Transaction implements Serializable {

    private long innerId;
    private String source;
    private String target;
    private BigDecimal amount;
    private BigDecimal balance;
    private byte currency;
    private String signatureBase58;
    private BigDecimal offeredMaxFee;
    private String tranFieldsBytesBase58;

    public long getInnerId() {
        return innerId;
    }

    public void setInnerId(long innerId) {
        this.innerId = innerId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public byte getCurrency() {
        return currency;
    }

    public void setCurrency(byte currency) {
        this.currency = currency;
    }

    public BigDecimal getOfferedMaxFee() {
        return offeredMaxFee;
    }

    public void setOfferedMaxFee(BigDecimal offeredMaxFee) {
        this.offeredMaxFee = offeredMaxFee;
    }

    public String getSignatureBase58() {
        return signatureBase58;
    }

    public void setSignatureBase58(String signatureBase58) {
        this.signatureBase58 = signatureBase58;
    }

    public String getTranFieldsBytesBase58() {
        return tranFieldsBytesBase58;
    }

    public void setTranFieldsBytesBase58(String tranFieldsBytesBase58) {
        this.tranFieldsBytesBase58 = tranFieldsBytesBase58;
    }
}