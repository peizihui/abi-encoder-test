package org.fisco.bcos.abi.encoder.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fisco.bcos.abi.encoder.contract.EchoArray3;
import org.fisco.bcos.abi.encoder.contract.EchoArray3.EventAddrEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArray3.EventB32EventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArray3.EventBSEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArray3.EventBoolEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArray3.EventEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArray3.EventIntEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArray3.EventStringEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArray3.EventUintEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.abi.datatypes.DynamicBytes;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Array3Client {

	static Logger logger = LoggerFactory.getLogger(Array3Client.class);
	
	private static final String contractAddresskey = "array3";
	private static final int arrayLength = 3;

	private Web3j web3j;

	private Credentials credentials;

	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void recordAddr(String address) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty(contractAddresskey, address);
		final Resource contractResource = new ClassPathResource("contract.properties");
		FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
		prop.store(fileOutputStream, "contract address");
	}

	public String loadAddr() throws Exception {
		// load SimpleClient contact address from contract.properties
		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		prop.load(contractResource.getInputStream());

		String contractAddress = prop.getProperty(contractAddresskey);
		if (contractAddress == null || contractAddress.trim().equals("")) {
			throw new Exception(" load EchoArray3 contract address failed, please deploy it first. ");
		}
		logger.info(" load EchoArray3 address from contract.properties, address is {}", contractAddress);
		return contractAddress;
	}

	public void initialize() throws Exception {

		// init the Service
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);

		// init Credentials
		Credentials credentials = Credentials.create(Keys.createEcKeyPair());

		setCredentials(credentials);
		setWeb3j(web3j);

		logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
	}

	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");

	public void deploy() {

		try {
			EchoArray3 echo = EchoArray3.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy EchoArray3 success, contract address is " + echo.getContractAddress());

			recordAddr(echo.getContractAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy EchoArray3 contract failed, error message is  " + e.getMessage());
		}
	}

	public void setUint(List<BigInteger> _u) {
		try {
			EchoArray3 arrayObj = EchoArray3.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setUint(_u).send();

			List<EventUintEventResponse> response = arrayObj.getEventUintEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getUint().send();

				System.out.printf(
						" [ EchoArray3 ][ setUinit ] success => getUint result : %s \n", result);

			} else {
				System.out.printf(" [ EchoArray3 ][ setUinit ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArray3 ][ setUinit ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public static List<String> toStringList(List<byte[]> byteList) {
		List<String> result = new ArrayList<String>();
		for(int i = 0;i<byteList.size();i++) {
			result.add(new String(byteList.get(i)));
		}
		
		return result;
	}
	
	public static List<String> toStringListBytes(List<DynamicBytes> byteList) {
		List<String> result = new ArrayList<String>();
		for(int i = 0;i<byteList.size();i++) {
			result.add(new String(byteList.get(i).getValue()));
		}
		
		return result;
	}
	
	public static List<String> toStringListByte32(List<Bytes32> byteList) {
		List<String> result = new ArrayList<String>();
		for(int i = 0;i<byteList.size();i++) {
			result.add(new String(byteList.get(i).getValue()));
		}
		
		return result;
	}
	
	public void setInt(List<BigInteger> _i) {
		try {
			EchoArray3 arrayObj = EchoArray3.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setInt(_i).send();

			List<EventIntEventResponse> response = arrayObj.getEventIntEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getInt().send();

				System.out.printf(
						" [ EchoArray3 ][ setInt ] success => getInt result : %s \n",
						result);

			} else {
				System.out.printf(" [ EchoArray3 ][ setInit ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArray3 ][ setInit ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBool(List<Boolean> _b) {
		try {
			EchoArray3 arrayObj = EchoArray3.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setBool(_b).send();

			List<EventBoolEventResponse> response = arrayObj.getEventBoolEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getBool().send();

				System.out.printf(
						" [ EchoArray3 ][ setBool ] success => getBool result : %s \n", result);

			} else {
				System.out.printf(" [ EchoArray3 ][ setBool ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArray3 ][ setBool ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setAddr(List<String> _addr) {
		try {
			EchoArray3 arrayObj = EchoArray3.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setAddr(_addr).send();

			List<EventAddrEventResponse> response = arrayObj.getEventAddrEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getAddr().send();

				System.out.printf(
						" [ EchoArray3 ][ setAddr ] success => getAddr result : %s \n",
						result);

			} else {
				System.out.printf(" [ EchoArray3 ][ setAddr ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArray3 ][ setAddr ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBS32(List<byte[]> _b) {
		try {
			EchoArray3 arrayObj = EchoArray3.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setBS32(_b).send();

			List<EventB32EventResponse> response = arrayObj.getEventB32Events(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getBS32().send();

				System.out.printf(
						" [ EchoArray3 ][ setBS32 ] success => getBS32 result : %s \n",
						result);

			} else {
				System.out.printf(" [ EchoArray3 ][ setBS32 ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArray3 ][ setBS32 ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setString(List<String> s) {
		try {
			EchoArray3 simple = EchoArray3.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setString(s).send();

			List<EventStringEventResponse> response = simple.getEventStringEvents(receipt);
			if (!response.isEmpty()) {

				List result = simple.getString().send();

				System.out.printf(
						" [ EchoArray3 ][ setString ] success => getString result : %s \n",
						result);

			} else {
				System.out.printf(" [ EchoArray3 ][ setString ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArray3 ][ setString ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBS(List<byte[]> _b) {
		try {
			EchoArray3 arrayObj = EchoArray3.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setBS(_b).send();

			List<EventBSEventResponse> response = arrayObj.getEventBSEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getBS().send();

				System.out.printf(
						" [ EchoArray3 ][ setBS ] success =>  getString result : %s \n",
						result);

			} else {
				System.out.printf(" [ EchoArray3 ][ setBS ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArray3 ][ setBS ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void set(List<BigInteger> _u, List<BigInteger> _i, List<Boolean> _b, List<String> _addr, List<byte[]> _bs32, List<String> _s, List<byte[]> _bs) {
		try {
			EchoArray3 simple = EchoArray3.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.set(_u, _i, _b, _addr, _bs32, _s, _bs).send();

			List<EventEventResponse> response = simple.getEventEvents(receipt);
			if (!response.isEmpty()) {

				Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>> result = simple.get().send();
				
				System.out.printf(
						" [ EchoArray3 ][ set ] success => getString result : %s \n",
						result);
				

			} else {
				System.out.printf(" [ EchoArray3 ][ set ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArray3 ][ set ] failed, error message is %s\n", e.getMessage());
		}
	}

	public static void Usage() {
		System.out.println(" Usage:");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client deploy");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setUint [ BigInteger ... ] ");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setInt [ BigInteger ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setBool [ Bool ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setAddr [ Address ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setBS32 [ Bytes32 ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setString [ String ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setBS [ Bytes ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client set [ BigInteger ..., BigInteger ..., Boolean ..., String ..., byte[] ..., String ..., byte[]... ]");
		
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client deploy");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setUint 1 2 3 ");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setInt -1 -2 3");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setBool true false true");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setAddr 0x777788889999AaAAbBbbCcccddDdeeeEfFFfCcCc 0x777788889999AaAAbBbbCcccddDdeeeEfFFfCcCc 0x777788889999AaAAbBbbCcccddDdeeeEfFFfCcCc");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setBS32 01234567890123456789012345678901 01234567890123456789012345678901 01234567890123456789012345678901");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setString aaaaa bbbbbb jlkaldsjfkld");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client setBS adf ljklj  jljlkjl");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.Array3Client set 1|2|3 -1|2|-4 true|false|true afdaf|fadsfdsf|fdafdfdf 01234567890123456789012345678901|01234567890123456789012345678901|01234567890123456789012345678901  adfaf|jllkj|jllkj aaa|aaa|aaa ]");
		System.exit(0);
	}
	
	public static void checkListLength(String[] args) {
		if(args.length != arrayLength) {
			throw new InvalidParameterException(" input array not 3 dimensions. ");
		}
	}
	
	public static List<BigInteger> toBigIntegerList(String[] args) {
		checkListLength(args);
		List<BigInteger> r = new ArrayList<BigInteger>();
		for (int i = 0; i < args.length; ++i) {
			r.add(new BigInteger(args[i]));
		}
		return r;
	}
	
	public static List<BigInteger> toBigIntegerList(String s) {
		List<BigInteger> r = new ArrayList<BigInteger>();
		String[] args = s.split("|");
		checkListLength(args);
		for (int i = 0; i < args.length; ++i) {
			r.add(new BigInteger(args[i]));
		}
		return r;
	}
	
	public static List<Boolean> toBoolList(String[] args) {
		List<Boolean> r = new ArrayList<Boolean>();
		checkListLength(args);
		for (int i = 0; i < args.length; ++i) {
			r.add(Boolean.parseBoolean(args[i]));
		}
		return r;
	}
	
	public static List<Boolean> toBoolList(String s) {
		List<Boolean> r = new ArrayList<Boolean>();
		String[] args = s.split("|");
		checkListLength(args);
		for (int i = 0; i < args.length; ++i) {
			r.add(Boolean.parseBoolean(args[i]));
		}
		return r;
	}
	
	public static List<String> toAddrList(String[] args) {
		checkListLength(args);
		List<String> r = new ArrayList<String>();
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i]);
		}
		return r;
	}
	
	public static List<String> toAddrList(String s) {
		String[] args = s.split("|");
		checkListLength(args);
		List<String> r = new ArrayList<String>();
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i]);
		}
		return r;
	}
	
	public static List<String> toStringList(String[] args) {
		List<String> r = new ArrayList<String>();
		checkListLength(args);
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i]);
		}
		return r;
	}
	
	public static List<String> toStringList(String s) {
		String[] args = s.split("|");
		checkListLength(args);
		List<String> r = new ArrayList<String>();
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i]);
		}
		return r;
	}
	
	public static List<byte[]> toBSList(String[] args) {
		List<byte[]> r = new ArrayList<byte[]>();
		checkListLength(args);
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i].getBytes());
		}
		return r;
	}
	
	public static List<byte[]> toBSList(String s) {
		String[] args = s.split("|");
		checkListLength(args);
		List<byte[]> r = new ArrayList<byte[]>();
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i].getBytes());
		}
		return r;
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			Usage();
		}
	
		Array3Client client = new Array3Client();
		client.initialize();

		switch (args[0]) {
		case "deploy":
			client.deploy();
			break;
		case "setUint":
			if (args.length < 2) {
				Usage();
			}
			client.setUint(toBigIntegerList(Arrays.copyOfRange(args, 1, args.length)));
			break;
		case "setInt":
			if (args.length < 2) {
				Usage();
			}
			client.setInt(toBigIntegerList(Arrays.copyOfRange(args, 1, args.length)));
			break;
		case "setBool":
			if (args.length < 2) {
				Usage();
			}
			client.setBool(toBoolList(Arrays.copyOfRange(args, 1, args.length)));
			break;
		case "setAddr":
			if (args.length < 2) {
				Usage();
			}
			client.setAddr(toAddrList(Arrays.copyOfRange(args, 1, args.length)));
			break;
		case "setString":
			if (args.length < 2) {
				Usage();
			}
			client.setString(toStringList(Arrays.copyOfRange(args, 1, args.length)));
			break;
		case "setBS":
			if (args.length < 2) {
				Usage();
			}
			client.setBS(toBSList(Arrays.copyOfRange(args, 1, args.length)));
			break;
		case "setBS32":
			if (args.length < 2) {
				Usage();
			}
			client.setBS(toBSList(Arrays.copyOfRange(args, 1, args.length)));
			break;
		case "set":
			if (args.length < 2) {
				Usage();
			}
			client.set(toBigIntegerList(args[1]), toBigIntegerList(args[2]), toBoolList(args[3]), toStringList(args[4]), toBSList(args[5]), toStringList(args[6]), toBSList(args[7]));
			break;
		default: {
			Usage();
		}
		}

		System.exit(0);
	}
}
