package org.fisco.bcos.abi.encoder.callback;

import org.fisco.bcos.abi.encoder.contract.EchoArray3Array;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoderFactory;

public class EchoArray3ArrayCallBack extends TransactionSucCallback {

	@Override
	public void onResponse(TransactionReceipt response) {
		TransactionDecoder decode = TransactionDecoderFactory.buildTransactionDecoder(EchoArray3Array.ABI, "");
		CallBackUtils.txDecodeInCallBack(decode, response);
	}
}
