package com.credits.wallet.utils;

import com.credits.common.exception.CreditsCommonException;
import com.credits.common.utils.Converter;
import com.credits.leveldb.client.data.SmartContractData;
import com.credits.leveldb.client.data.SmartContractInvocationData;
import com.credits.leveldb.client.data.TransactionFlowData;
import com.credits.leveldb.client.data.VariantData;
import com.credits.wallet.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class WalletWebConverter {

    public static TransactionFlowData transactionToTransactionFlowData(Transaction transaction) throws CreditsCommonException {
        TransactionFlowData transactionFlowData = new TransactionFlowData(
                transaction.getId(),
                Converter.decodeFromBASE58(transaction.getSource()),
                Converter.decodeFromBASE58(transaction.getTarget()),
                transaction.getAmount(),
                transaction.getBalance(),
                transaction.getCurrency(),
                Converter.decodeFromBASE58(transaction.getSignatureBase58()),
                transaction.getOfferedMaxFee()
        );
        return transactionFlowData;
    }

    public static SmartContractInvocationData smartContractDataToSmartContractInvocationData(
            SmartContractData smartContractData,
            String methodName,
            List<String> paramsVals,
            boolean forgetNewState
    ) {
        // TODO Create VariantData list
        List<VariantData> variantDataList = new ArrayList<>();
        SmartContractInvocationData smartContractInvocationData = new SmartContractInvocationData(
                smartContractData.getSourceCode(),
                smartContractData.getByteCode(),
                smartContractData.getHashState(),
                methodName,
                variantDataList,
                forgetNewState
        );
        return smartContractInvocationData;
    }

}
