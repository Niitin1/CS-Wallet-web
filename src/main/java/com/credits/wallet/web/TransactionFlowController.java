package com.credits.wallet.web;

import com.credits.common.exception.CreditsException;
import com.credits.common.utils.Converter;
import com.credits.leveldb.client.data.TransactionFlowData;
import com.credits.wallet.domain.Transaction;
import com.credits.wallet.domain.transformer.TransactionTransformer;
import com.credits.wallet.domain.transformer.Transformer;
import com.credits.wallet.exception.WalletWebException;
import com.credits.wallet.utils.WalletWebConverter;
import com.credits.wallet.utils.WalletWebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class TransactionFlowController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionFlowController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/transactionFlow", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void newTransaction(@RequestBody Transaction transaction) {
        try {

            byte[] transactionFieldsBytes = Converter.decodeFromBASE58(transaction.getTranFieldsBytesBase58());
            LOGGER.info("Transaction fields bytes: {}", Converter.byteArrayToString(transactionFieldsBytes, " "));
            byte[] transactionSignatureBytes = Converter.decodeFromBASE58(transaction.getSignatureBase58());
            LOGGER.info("Transaction signature bytes: {}", Converter.byteArrayToString(transactionSignatureBytes, " "));

            TransactionFlowData transactionFlowData = WalletWebConverter.transactionToTransactionFlowData(transaction);
            apiClient.transactionFlow(transactionFlowData,true);
            LOGGER.info("Transaction has been sent");
        } catch (CreditsException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WalletWebException(ERROR_CODE, WalletWebUtils.createWalletWebExceptionMessage(e));
        }
    }
}

