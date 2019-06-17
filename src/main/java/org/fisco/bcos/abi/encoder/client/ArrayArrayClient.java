package org.fisco.bcos.abi.encoder.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray.EventBSEventResponse;
import org.fisco.bcos.abi.encoder.callback.EchoArrayArrayCallBack;
import org.fisco.bcos.abi.encoder.callback.EchoArrayCallBack;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray.EventAddrEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray.EventB32EventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray.EventBoolEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray.EventEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray.EventIntEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray.EventStringEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoArrayArray.EventUintEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.DynamicBytes;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
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

public class ArrayArrayClient {

	static Logger logger = LoggerFactory.getLogger(ArrayArrayClient.class);

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

	public void recordAddr(String key, String address) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty(key, address);
		final Resource contractResource = new ClassPathResource("contract.properties");
		FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
		prop.store(fileOutputStream, "contract address");
	}

	public String loadAddr() throws Exception {
		// load SimpleClient contact address from contract.properties
		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		prop.load(contractResource.getInputStream());

		String contractAddress = prop.getProperty("arrayarray");
		if (contractAddress == null || contractAddress.trim().equals("")) {
			throw new Exception(" load EchoArrayArray contract address failed, please deploy it first. ");
		}
		logger.info(" load EchoArrayArray address from contract.properties, address is {}", contractAddress);
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
			EchoArrayArray echo = EchoArrayArray.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy EchoArrayArray success, contract address is " + echo.getContractAddress());

			recordAddr("arrayarray", echo.getContractAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy EchoArrayArray contract failed, error message is  " + e.getMessage());
		}
	}
	
	List<List<BigInteger>> toBigIntegerListList(List<List<Uint256>> params) {
		List<List<BigInteger>> result = new ArrayList<List<BigInteger>>();
		
		for(int i=0;i<params.size();++i) {
			List<BigInteger> r = new ArrayList<BigInteger>();
			for(int j = 0;j<params.get(i).size();++j) {
				r.add(params.get(i).get(j).getValue());
			}
			
			result.add(r);
		}
		
		return result;
	}
	
	List<List<BigInteger>> toBigIntegerListList0(List<List<Int256>> params) {
		List<List<BigInteger>> result = new ArrayList<List<BigInteger>>();
		
		for(int i=0;i<params.size();++i) {
			List<BigInteger> r = new ArrayList<BigInteger>();
			for(int j = 0;j<params.get(i).size();++j) {
				r.add(params.get(i).get(j).getValue());
			}
			
			result.add(r);
		}
		
		return result;
	}
	
	List<List<Boolean>> toBoolListList(List<List<Bool>> params) {
		
		List<List<Boolean>> result = new ArrayList<List<Boolean>>();
		
		for(int i=0;i<params.size();++i) {
			List<Boolean> r = new ArrayList<Boolean>();
			for(int j = 0;j<params.get(i).size();++j) {
				r.add(params.get(i).get(j).getValue());
			}
			
			result.add(r);
		}
		
		return result;
	}
	
	List<List<String>> toStringlListList0(List<List<DynamicBytes>> params) {
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		for(int i=0;i<params.size();++i) {
			List<String> r = new ArrayList<String>();
			for(int j = 0;j<params.get(i).size();++j) {
				r.add(new String(params.get(i).get(j).getValue()));
			}
			
			result.add(r);
		}
		
		return result;
	}
	
	List<List<String>> toStringlListList1(List<List<Bytes32>> params) {
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		for(int i=0;i<params.size();++i) {
			List<String> r = new ArrayList<String>();
			for(int j = 0;j<params.get(i).size();++j) {
				r.add(new String(params.get(i).get(j).getValue()));
			}
			
			result.add(r);
		}
		
		return result;
	}
	
	public void setUint(List<List<BigInteger>> _u) {
		try {
			EchoArrayArray arrayObj = EchoArrayArray.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setUint(_u).send();

			List<EventUintEventResponse> response = arrayObj.getEventUintEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getUint().send();

				System.out.printf(
						" [ EchoArrayArray ][ setUinit ] success => getUint result : %s \n",
						toBigIntegerListList(result));

			} else {
				System.out.printf(" [ EchoArrayArray ][ setUinit ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArrayArray ][ setUinit ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setInt(List<List<BigInteger>> _i) {
		try {
			EchoArrayArray arrayObj = EchoArrayArray.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setInt(_i).send();

			List<EventIntEventResponse> response = arrayObj.getEventIntEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getInt().send();

				System.out.printf(
						" [ EchoArrayArray ][ setInt ] success => getInt result : %s \n",
						toBigIntegerListList0(result));

			} else {
				System.out.printf(" [ EchoArrayArray ][ setInit ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArrayArray ][ setInit ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBool(List<List<Boolean>> _b) {
		try {
			EchoArrayArray arrayObj = EchoArrayArray.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setBool(_b).send();

			List<EventBoolEventResponse> response = arrayObj.getEventBoolEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getBool().send();

				System.out.printf(
						" [ EchoArrayArray ][ setBool ] success => getBool result : %s \n",
						toBoolListList(result));

			} else {
				System.out.printf(" [ EchoArrayArray ][ setBool ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArrayArray ][ setBool ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setAddr(List<List<String>> _addr) {
		try {
			EchoArrayArray arrayObj = EchoArrayArray.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setAddr(_addr).send();
			List<EventAddrEventResponse> response = arrayObj.getEventAddrEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getAddr().send();

				System.out.printf(
						" [ EchoArrayArray ][ setAddr ] success => getAddr result : %s \n",
						 result);

			} else {
				System.out.printf(" [ EchoArrayArray ][ setAddr ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArrayArray ][ setAddr ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBS32(List<List<byte[]>> _b) {
		try {
			EchoArrayArray arrayObj = EchoArrayArray.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setBS32(_b).send();
			List<EventB32EventResponse> response = arrayObj.getEventB32Events(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getBS32().send();

				System.out.printf(
						" [ EchoArrayArray ][ setBS32 ] success =>  getBS32 result : %s \n",
						toStringlListList1(result));

			} else {
				System.out.printf(" [ EchoArrayArray ][ setBS32 ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArrayArray ][ setBS32 ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setString(List<List<String>> s) {
		try {
			EchoArrayArray simple = EchoArrayArray.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setString(s).send();
			List<EventStringEventResponse> response = simple.getEventStringEvents(receipt);
			if (!response.isEmpty()) {

				List result = simple.getString().send();

				System.out.printf(
						" [ EchoArrayArray ][ setString ] success => getString result : %s \n",
					 result);

			} else {
				System.out.printf(" [ EchoArrayArray ][ setString ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArrayArray ][ setString ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBS(List<List<byte[]>> _b) {
		try {
			EchoArrayArray arrayObj = EchoArrayArray.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = arrayObj.setBS(_b).send();
			List<EventBSEventResponse> response = arrayObj.getEventBSEvents(receipt);
			if (!response.isEmpty()) {

				List result = arrayObj.getBS().send();

				System.out.printf(
						" [ EchoArrayArray ][ setBS ] success => getString result : %s \n",
						toStringlListList0(result));

			} else {
				System.out.printf(" [ EchoArrayArray ][ setBS ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArrayArray ][ setBS ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void set(List<List<BigInteger>> _u, List<List<BigInteger>> _i, List<List<Boolean>> _b, List<List<String>> _addr, List<List<byte[]>> _bs32, List<List<String>> _s, List<List<byte[]>> _bs) {
		try {
			EchoArrayArray simple = EchoArrayArray.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.set(_u, _i, _b, _addr, _bs32, _s, _bs).send();
			List<EventEventResponse> response = simple.getEventEvents(receipt);
			if (!response.isEmpty()) {

				Tuple7<List<List<BigInteger>>, List<List<BigInteger>>, List<List<Boolean>>, List<List<String>>, List<List<byte[]>>, List<List<String>>, List<List<byte[]>>> result = simple.get().send();
	
			} else {
				System.out.printf(" [ EchoArrayArray ][ set ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoArrayArray ][ set ] failed, error message is %s\n", e.getMessage());
		}
	}

	public static void Usage() {
		System.out.println(" Usage:");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient deploy");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setUint [ BigInteger ... ] ");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setInt [ BigInteger ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setBool [ Bool ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setAddr [ Address ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setBS32 [ Bytes32 ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setString [ String ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setBS [ Bytes ... ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient set [ BigInteger ..., BigInteger ..., Boolean ..., String ..., byte[] ..., String ..., byte[]... ]");
		
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient deploy");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setUint 1|1|2  2|2|2 ");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setInt -1|-2|3 4|5");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setBool false|false|true true false|true" );
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setAddr 0x777788889999AaAAbBbbCcccddDdeeeEfFFfCcCc 0x777788889999AaAAbBbbCcccddDdeeeEfFFfCcCc ");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setBS32 01234567890123456789012345678901 01234567890123456789012345678901 01234567890123456789012345678901");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setString aaaaa bbbbbb jlkaldsjfkld");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient setBS adf ljklj  jljlkjl jkjljlkjl");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.ArrayArrayClient set 1|2|3 -1|2|-4 true|false|true 0x0|0x1|0x2 01234567890123456789012345678901  adfaf|jllkj aaa ]");
		System.exit(0);
		System.exit(0);
	}
	
	public static List<BigInteger> toBigIntegerList(String s) {
		List<BigInteger> r = new ArrayList<BigInteger>();
		String[] args = s.split("\\|");
		for (int i = 0; i < args.length; ++i) {
			r.add(new BigInteger(args[i]));
		}
		return r;
	}
	
	public static List<List<BigInteger>> toBigIntegerList(String[] args) {
		List<List<BigInteger>> r = new ArrayList<List<BigInteger>>();
		for (int i = 0; i < args.length; ++i) {
			r.add(toBigIntegerList(args[i]));
		}
		return r;
	}
	
	public static List<Boolean> toBoolList(String s) {
		List<Boolean> r = new ArrayList<Boolean>();
		String[] args = s.split("\\|");
		for (int i = 0; i < args.length; ++i) {
			r.add(Boolean.parseBoolean(args[i]));
		}
		return r;
	}
	
	public static List<List<Boolean>> toBoolList(String[] args) {
		List<List<Boolean>> r = new ArrayList<List<Boolean>>();
		for (int i = 0; i < args.length; ++i) {
			r.add(toBoolList(args[i]));
		}
		return r;
	}
	
	public static List<String> toAddrList(String s) {
		String[] args = s.split("\\|");
		List<String> r = new ArrayList<String>();
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i]);
		}
		return r;
	}

	public static List<List<String>> toAddrList(String[] args) {
		List<List<String>> r = new ArrayList<List<String>>();
		for (int i = 0; i < args.length; ++i) {
			r.add(toAddrList(args[i]));
		}
		return r;
	}
	
	public static List<String> toStringList(String s) {
		String[] args = s.split("\\|");
		List<String> r = new ArrayList<String>();
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i]);
		}
		return r;
	}
	
	public static List<List<String>> toStringList(String[] args) {
		List<List<String>> r = new ArrayList<List<String>>();
		for (int i = 0; i < args.length; ++i) {
			r.add(toStringList(args[i]));
		}
		return r;
	}
	
	public static List<byte[]> toBSList(String s) {
		String[] args = s.split("\\|");
		List<byte[]> r = new ArrayList<byte[]>();
		for (int i = 0; i < args.length; ++i) {
			r.add(args[i].getBytes());
		}
		return r;
	}

	public static List<List<byte[]>> toBSList(String[] args) {
		List<List<byte[]>> r = new ArrayList<List<byte[]>>();
		for (int i = 0; i < args.length; ++i) {
			r.add(toBSList(args[i]));
		}
		return r;
	}


	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			Usage();
		}
	
		ArrayArrayClient client = new ArrayArrayClient();
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
			client.setAddr(toStringList(Arrays.copyOfRange(args, 1, args.length)));
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
			// client.set(toBigIntegerList(args[1]), toBigIntegerList(args[2]), toBoolList(args[3]), toStringList(args[4]), toBSList(args[5]), toStringList(args[6]), toBSList(args[7]));
			break;
		default: {
			Usage();
		}
		}

		System.exit(0);
	}
}
