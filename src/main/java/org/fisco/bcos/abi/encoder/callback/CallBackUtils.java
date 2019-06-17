package org.fisco.bcos.abi.encoder.callback;

import java.io.IOException;

import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.protocol.exceptions.TransactionException;
import org.fisco.bcos.web3j.tx.txdecode.BaseException;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CallBackUtils {
	
	public static boolean openCallBack = true;
	
	public static void setOpenCallBack(boolean _openCallBack) {
		openCallBack = _openCallBack;
	}
	
	public static boolean getOpenCallBack() {
		return openCallBack;
	}
	
	public static void txDecodeInCallBack(TransactionDecoder decode, TransactionReceipt receipt) {
		
		if(getOpenCallBack()) {
			
			System.out.println(" receipt input => " + receipt.getInput());
			System.out.println(" receipt output => " + receipt.getOutput());
			System.out.println(" receipt logs => " + receipt.getLogs());
			
			try {
				System.out.println(" input json => ");
				System.out.println("\t " + decode.decodeInputReturnJson(receipt.getInput()));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				System.out.println(" input object => ");
				System.out.println("\t " + decode.decodeInputReturnObject(receipt.getInput()));
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				System.out.println(" output json => ");
				System.out.println("\t " + decode.decodeOutputReturnJson(receipt.getInput(), receipt.getOutput()));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				System.out.println(" output object => ");
				System.out.println("\t " + decode.decodeOutputReturnObject(receipt.getInput(), receipt.getOutput()));
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				System.out.println(" event json => ");
				System.out.println("\t " + decode.decodeEventReturnJson(receipt.getLogs()));
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				System.out.println(" event object => ");
				System.out.println("\t " + decode.decodeEventReturnObject(receipt.getLogs()));
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
