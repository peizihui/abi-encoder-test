package org.fisco.bcos.abi.encoder.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fisco.bcos.abi.encoder.callback.EchoSimpleCallBack;
import org.fisco.bcos.abi.encoder.contract.EchoSimple;
import org.fisco.bcos.abi.encoder.contract.EchoSimple.EventAddrEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoSimple.EventB32EventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoSimple.EventBSEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoSimple.EventBoolEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoSimple.EventEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoSimple.EventIntEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoSimple.EventStringEventResponse;
import org.fisco.bcos.abi.encoder.contract.EchoSimple.EventUintEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.abi.datatypes.Address;
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

public class SimpleClient {

	static Logger logger = LoggerFactory.getLogger(SimpleClient.class);

	private Web3j web3j;

	private Credentials credentials;
	
	private static final String contractAddresskey = "simple";
	
	private EchoSimpleCallBack callBack = new EchoSimpleCallBack();

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
			throw new Exception(" load EchoSimple contract address failed, please deploy it first. ");
		}
		logger.info(" load EchoSimple address from contract.properties, address is {}", contractAddress);
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
			EchoSimple echo = EchoSimple.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy EchoSimple success, contract address is " + echo.getContractAddress());

			recordAddr(echo.getContractAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy EchoSimple contract failed, error message is  " + e.getMessage());
		}
	}

	public void setUint(BigInteger _u) {
		try {
			EchoSimple simple = EchoSimple.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setUint(_u).send();
			callBack.onResponse(receipt);
			List<EventUintEventResponse> response = simple.getEventUintEvents(receipt);
			if (!response.isEmpty()) {

				BigInteger result = simple.getUint().send();

				System.out.printf(
						" [ EchoSimple ][ setUinit ] success => event : %s , getUint result : %s \n",
						response.get(0).u, result);

			} else {
				System.out.printf(" [ EchoSimple ][ setUinit ] event empty. \n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf(" [ EchoSimple ][ setUinit ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setInt(BigInteger _i) {
		try {
			EchoSimple simple = EchoSimple.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setInt(_i).send();
			callBack.onResponse(receipt);
			List<EventIntEventResponse> response = simple.getEventIntEvents(receipt);
			if (!response.isEmpty()) {

				BigInteger result = simple.getInt().send();

				System.out.printf(
						" [ EchoSimple ][ setInt ] success => event : %s , getInt result : %s \n",
						response.get(0).i, result);

			} else {
				System.out.printf(" [ EchoSimple ][ setInit ] event empty. \n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf(" [ EchoSimple ][ setInit ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBool(boolean _b) {
		try {
			EchoSimple simple = EchoSimple.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setBool(_b).send();
			callBack.onResponse(receipt);
			List<EventBoolEventResponse> response = simple.getEventBoolEvents(receipt);
			if (!response.isEmpty()) {

				Boolean result = simple.getBool().send();

				System.out.printf(
						" [ EchoSimple ][ setBool ] success =>  event : %s , getBool result : %s \n",
						 response.get(0)._b, result);

			} else {
				System.out.printf(" [ EchoSimple ][ setBool ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoSimple ][ setBool ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setAddr(String _addr) {
		try {
			
			Address addrObj = new Address(_addr);
			
			EchoSimple simple = EchoSimple.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setAddr(_addr).send();
			callBack.onResponse(receipt);
			List<EventAddrEventResponse> response = simple.getEventAddrEvents(receipt);
			if (!response.isEmpty()) {

				String result = simple.getAddr().send();

				System.out.printf(
						" [ EchoSimple ][ setAddr ] success => event : %s , getAddr result : %s \n",
						response.get(0).addr, result);

			} else {
				System.out.printf(" [ EchoSimple ][ setAddr ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoSimple ][ setAddr ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBS32(byte[] _b) {
		try {
			EchoSimple simple = EchoSimple.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setBS32(_b).send();
			callBack.onResponse(receipt);
			List<EventB32EventResponse> response = simple.getEventB32Events(receipt);
			if (!response.isEmpty()) {

				byte[] result = simple.getBS32().send();

				System.out.printf(
						" [ EchoSimple ][ setBS32 ] success => event : %s , getBS32 result : %s \n",
						new String(response.get(0).b), new String(result.toString()));

			} else {
				System.out.printf(" [ EchoSimple ][ setBS32 ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoSimple ][ setBS32 ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setString(String s) {
		try {
			EchoSimple simple = EchoSimple.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setString(s).send();
			callBack.onResponse(receipt);
			List<EventStringEventResponse> response = simple.getEventStringEvents(receipt);
			if (!response.isEmpty()) {

				String result = simple.getString().send();

				System.out.printf(
						" [ EchoSimple ][ setString ] success => event : %s , getString result : %s \n",
						response.get(0).s, result);

			} else {
				System.out.printf(" [ EchoSimple ][ setString ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoSimple ][ setString ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void setBS(byte[] _b) {
		try {
			EchoSimple simple = EchoSimple.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.setBS(_b).send();
			callBack.onResponse(receipt);
			List<EventBSEventResponse> response = simple.getEventBSEvents(receipt);
			if (!response.isEmpty()) {

				byte[] result = simple.getBS().send();

				System.out.printf(
						" [ EchoSimple ][ setBS ] success => event : %s , getString result : %s \n",
						new String(response.get(0).b), new String(result));

			} else {
				System.out.printf(" [ EchoSimple ][ setBS ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoSimple ][ setBS ] failed, error message is %s\n", e.getMessage());
		}
	}
	
	public void set(BigInteger _u, BigInteger _i, Boolean _b, String _addr, byte[] _bs32, String _s, byte[] _bs) {
		try {
			EchoSimple simple = EchoSimple.load(loadAddr(), web3j, credentials,
					new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = simple.set(_u, _i, _b, _addr, _bs32, _s, _bs).send();
			callBack.onResponse(receipt);
			List<EventEventResponse> response = simple.getEventEvents(receipt);
			if (!response.isEmpty()) {

				Tuple7<BigInteger, BigInteger, Boolean, String, byte[], String, byte[]> result = simple.get().send();
				Tuple7<BigInteger, BigInteger, Boolean, String, String, String, String> result0 = new Tuple7<BigInteger, BigInteger, Boolean, String, String, String, String>(response.get(0)._u, response.get(0)._i, response.get(0)._b, response.get(0)._addr, new String(response.get(0)._bs32), response.get(0)._s,   new String(response.get(0)._bs));
				Tuple7<BigInteger, BigInteger, Boolean, String, String, String, String> result1 = new Tuple7<BigInteger, BigInteger, Boolean, String, String, String, String>(result.getValue1(), result.getValue2(), result.getValue3(), result.getValue4(), new String(result.getValue5()), result.getValue6(), new String(result.getValue7()));
				System.out.printf(
						" [ EchoSimple ][ set ] success => event : %s , getString result : %s \n",
						result0.toString(),
						result1.toString());

			} else {
				System.out.printf(" [ EchoSimple ][ set ] event empty. \n");
			}
		} catch (Exception e) {
			System.out.printf(" [ EchoSimple ][ set ] failed, error message is %s\n", e.getMessage());
		}
	}

	public static void Usage() {
		System.out.println(" Usage:");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient deploy");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setUint [ BigInteger ] ");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setInt [ BigInteger ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setBool [ Bool ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setAddr [ Address ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setBS32 [ Bytes32 ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setString [ String ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setBS [ Bytes ]");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient set [ BigInteger, BigInteger, Boolean, String, byte[], String, byte[] ]");
		
		System.out.println(" example : ");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient deploy");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setUint 10 ");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setInt 11111");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setBool true");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setAddr 0x777788889999AaAAbBbbCcccddDdeeeEfFFfCcCc ");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setBS32 01234567890123456789012345678901");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setString aaaaaaaaa ]");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient setBS bbbbbbbb");
		System.out.println("\t\t java -cp conf/:lib/*:apps/* org.fisco.bcos.abi.encoder.client.SimpleClient set 10  11 false aaaa 01234567890123456789012345678901 bbbbbb ssssssssss ");
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			Usage();
		}

		SimpleClient client = new SimpleClient();
		client.initialize();

		switch (args[0]) {
		case "deploy":
			client.deploy();
			break;
		case "setUint":
			if (args.length < 2) {
				Usage();
			}
			client.setUint(new BigInteger(args[1]));
			break;
		case "setInt":
			if (args.length < 2) {
				Usage();
			}
			client.setInt(new BigInteger(args[1]));
			break;
		case "setBool":
			if (args.length < 2) {
				Usage();
			}
			client.setBool(Boolean.parseBoolean(args[1]));;
			break;
		case "setAddr":
			if (args.length < 2) {
				Usage();
			}
			client.setAddr(args[1]);;
			break;
		case "setBS32":
			if (args.length < 2) {
				Usage();
			}
			client.setBS32(args[1].getBytes());;
			break;
		case "setString":
			if (args.length < 2) {
				Usage();
			}
			client.setString(args[1]);;
			break;
		case "setBS":
			if (args.length < 2) {
				Usage();
			}
			client.setBS(args[1].getBytes());;
			break;
		case "set":
			if (args.length < 8) {
				Usage();
			}
			client.set(new BigInteger(args[1]), new BigInteger(args[2]), Boolean.parseBoolean(args[3]), args[4], args[5].getBytes(), args[6], args[7].getBytes());
			break;
		default: {
			Usage();
		}
		}

		System.exit(0);
	}
}
