package com.credits.wallet.utils;

import com.credits.common.exception.CreditsCommonException;
import com.credits.common.utils.Converter;
import com.credits.leveldb.client.data.TransactionFlowData;
import com.credits.wallet.domain.Transaction;

import java.nio.ByteBuffer;

public class WalletWebConverter {

    public static TransactionFlowData transactionToTransactionFlowData(Transaction transaction) throws CreditsCommonException {
        TransactionFlowData transactionFlowData = new TransactionFlowData(
                transaction.getInnerId(),
                transaction.getSource(),
                transaction.getTarget(),
                transaction.getAmount(),
                transaction.getBalance(),
                transaction.getCurrency(),
                ByteBuffer.wrap(Converter.decodeFromBASE58(transaction.getSignatureBase58())),
                transaction.getOfferedMaxFee()
        );
        return transactionFlowData;
    }
}
