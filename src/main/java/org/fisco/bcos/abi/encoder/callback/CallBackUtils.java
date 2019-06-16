package org.fisco.bcos.abi.encoder.callback;

import java.io.IOException;

import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.protocol.exceptions.TransactionException;
import org.fisco.bcos.web3j.tx.txdecode.BaseException;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

public class CallBackUtils {
	
	public static boolean openCallBack = true;
	
	public static void txDecodeInCallBack(TransactionDecoder decode, TransactionReceipt receipt) throws BaseException, TransactionException, IOException {
		
		if(openCallBack) {
			System.out.println(" input json => ");
			System.out.println("\t " + decode.decodeInputReturnJson(receipt.getInput()));
			System.out.println(" input object => ");
			System.out.println("\t " + decode.decodeInputReturnObject(receipt.getInput()));
			System.out.println(" output json => ");
			System.out.println("\t " + decode.decodeOutputReturnJson(receipt.getInput(), receipt.getOutput()));
			System.out.println(" output object => ");
			System.out.println("\t " + decode.decodeOutputReturnObject(receipt.getInput(), receipt.getOutput()));
			System.out.println(" event json => ");
			System.out.println("\t " + decode.decodeEventReturnJson(receipt.getLogs()));
			System.out.println(" event object => ");
			System.out.println("\t " + decode.decodeEventReturnObject(receipt.getLogs()));
		}
	}
}
