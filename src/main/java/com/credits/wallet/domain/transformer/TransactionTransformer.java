package com.credits.wallet.domain.transformer;

import com.credits.common.exception.CreditsCommonException;
import com.credits.common.utils.Converter;
import com.credits.leveldb.client.data.TransactionData;
import com.credits.leveldb.client.data.TransactionFlowData;
import com.credits.wallet.domain.Transaction;

import java.nio.ByteBuffer;
import java.util.function.Function;

public class TransactionTransformer {

    public static Function<TransactionData, Transaction> TO_WALLET = transactionData -> {
        Transaction transaction = new Transaction();
        transaction.setId(transactionData.getId());
        transaction.setSource(Converter.encodeToBASE58(transactionData.getSource()));
        transaction.setTarget(Converter.encodeToBASE58(transactionData.getTarget()));
        transaction.setAmount(transactionData.getAmount());
        transaction.setCurrency(transactionData.getCurrency());
        return transaction;
    };
}