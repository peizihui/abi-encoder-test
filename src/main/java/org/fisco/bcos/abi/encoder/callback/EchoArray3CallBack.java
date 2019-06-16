package org.fisco.bcos.abi.encoder.callback;

import org.fisco.bcos.abi.encoder.contract.EchoArray3;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoderFactory;

public class EchoArray3CallBack extends TransactionSucCallback {

	@Override
	public void onResponse(TransactionReceipt response) {
		TransactionDecoder decode = TransactionDecoderFactory.buildTransactionDecoder(EchoArray3.ABI, "");
		
		try {
			System.out.println(" input json => ");
			System.out.println("\t " + decode.decodeInputReturnJson(response.getInput()));
			System.out.println(" input object => ");
			System.out.println("\t " + decode.decodeInputReturnObject(response.getInput()));
			System.out.println(" output json => ");
			System.out.println("\t " + decode.decodeOutputReturnJson(response.getInput(), response.getOutput()));
			System.out.println(" output object => ");
			System.out.println("\t " + decode.decodeOutputReturnObject(response.getInput(), response.getOutput()));
			System.out.println(" event json => ");
			System.out.println("\t " + decode.decodeEventReturnJson(response.getLogs()));
			System.out.println(" event object => ");
			System.out.println("\t " + decode.decodeEventReturnObject(response.getLogs()));
		} catch (Exception e) {
			System.out.println(" EchoArray3CallBack transaction call faild, exception => " + e.getMessage());
		}
	}
}
