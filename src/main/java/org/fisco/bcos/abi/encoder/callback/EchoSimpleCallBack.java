package org.fisco.bcos.abi.encoder.callback;

import org.fisco.bcos.abi.encoder.contract.EchoSimple;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoderFactory;

public class EchoSimpleCallBack extends TransactionSucCallback {

	@Override
	public void onResponse(TransactionReceipt response) {
		TransactionDecoder decode = TransactionDecoderFactory.buildTransactionDecoder(EchoSimple.ABI, "");
		
		try {
			CallBackUtils.txDecodeInCallBack(decode, response);
		} catch (Exception e) {
			System.out.println(" EchoSimpleCallBack transaction call faild, exception => " + e.getMessage());
		}
	}
}
