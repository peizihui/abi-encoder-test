package org.fisco.bcos.abi.encoder.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.DynamicBytes;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class EchoSimple extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506115fc806100206000396000f3006080604052600436106100e5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680620267a4146100ea57806312a7b914146101155780631e26fd331461014057806325f233a51461017d5780634ef65c3b146101a85780634fac76dd146101e557806362738998146102225780636d4ce63c1461024d578063747586b81461027e5780637fcaf666146102bb578063839ebdfd146102f857806389ea642f14610323578063a74c2bb61461034e578063b5f8a97014610379578063c7273f1a146103bc578063d1d80fdf146103f9575b600080fd5b3480156100f657600080fd5b506100ff610436565b60405161010c91906113a8565b60405180910390f35b34801561012157600080fd5b5061012a61043f565b6040516101379190611313565b60405180910390f35b34801561014c57600080fd5b506101676004803603610162919081019061104d565b610456565b6040516101749190611313565b60405180910390f35b34801561018957600080fd5b506101926104c0565b60405161019f9190611349565b60405180910390f35b3480156101b457600080fd5b506101cf60048036036101ca919081019061114a565b610562565b6040516101dc91906113a8565b60405180910390f35b3480156101f157600080fd5b5061020c60048036036102079190810190611076565b6105ac565b604051610219919061132e565b60405180910390f35b34801561022e57600080fd5b506102376105fa565b604051610244919061136b565b60405180910390f35b34801561025957600080fd5b50610262610604565b60405161027597969594939291906113c3565b60405180910390f35b34801561028a57600080fd5b506102a560048036036102a091908101906110e0565b61079e565b6040516102b2919061136b565b60405180910390f35b3480156102c757600080fd5b506102e260048036036102dd9190810190611109565b6107e8565b6040516102ef9190611386565b60405180910390f35b34801561030457600080fd5b5061030d6108da565b60405161031a919061132e565b60405180910390f35b34801561032f57600080fd5b506103386108e4565b6040516103459190611386565b60405180910390f35b34801561035a57600080fd5b50610363610986565b60405161037091906112f8565b60405180910390f35b34801561038557600080fd5b506103a0600480360361039b9190810190611173565b6109b0565b6040516103b397969594939291906113c3565b60405180910390f35b3480156103c857600080fd5b506103e360048036036103de919081019061109f565b610c37565b6040516103f09190611349565b60405180910390f35b34801561040557600080fd5b50610420600480360361041b9190810190611024565b610d29565b60405161042d91906112f8565b60405180910390f35b60008054905090565b6000600260009054906101000a900460ff16905090565b600081600260006101000a81548160ff0219169083151502179055507f333dd89d9c702f4d468fd02e664620fa34a8145656d32ac75091b105d8bec331826040516104a19190611313565b60405180910390a1600260009054906101000a900460ff169050919050565b606060058054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105585780601f1061052d57610100808354040283529160200191610558565b820191906000526020600020905b81548152906001019060200180831161053b57829003601f168201915b5050505050905090565b6000816000819055507f1ae2cdbaf14c82e9502eb279d8b33ca04d3a48a3014e563a601d2ef44fa885118260405161059a91906113a8565b60405180910390a16000549050919050565b600081600381600019169055507f2d806945b5a489eb05d4f9ef20afa07f3fdd263e33fa85a173879ff770dc47d6826040516105e8919061132e565b60405180910390a16003549050919050565b6000600154905090565b6000806000806000606080600054600154600260009054906101000a900460ff16600260019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660035460046005818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106e45780601f106106b9576101008083540402835291602001916106e4565b820191906000526020600020905b8154815290600101906020018083116106c757829003601f168201915b50505050509150808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107805780601f1061075557610100808354040283529160200191610780565b820191906000526020600020905b81548152906001019060200180831161076357829003601f168201915b50505050509050965096509650965096509650965090919293949596565b6000816001819055507f58da1ac8e1dd43d74ab3708472afd8391aecca3649f2396bc77173054cd515e6826040516107d6919061136b565b60405180910390a16001549050919050565b60608160049080519060200190610800929190610def565b507f7240e2f75cccc64acf37f699b7cc2726ccd9c0ed8afeafdbf7911af78d077bad826040516108309190611386565b60405180910390a160048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108ce5780601f106108a3576101008083540402835291602001916108ce565b820191906000526020600020905b8154815290600101906020018083116108b157829003601f168201915b50505050509050919050565b6000600354905090565b606060048054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561097c5780601f106109515761010080835404028352916020019161097c565b820191906000526020600020905b81548152906001019060200180831161095f57829003601f168201915b5050505050905090565b6000600260019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008060008060006060807f2d7eda0ce58aaf84d16283e6cc11e0ba865c61e2f03bb62f3b2b5f07a1546a368e8e8e8e8e8e8e6040516109f697969594939291906113c3565b60405180910390a18d6000819055508c6001819055508b600260006101000a81548160ff0219169083151502179055508a600260016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555089600381600019169055508860049080519060200190610a88929190610def565b508760059080519060200190610a9f929190610e6f565b50600054600154600260009054906101000a900460ff16600260019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660035460046005818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b755780601f10610b4a57610100808354040283529160200191610b75565b820191906000526020600020905b815481529060010190602001808311610b5857829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c115780601f10610be657610100808354040283529160200191610c11565b820191906000526020600020905b815481529060010190602001808311610bf457829003601f168201915b505050505090509650965096509650965096509650975097509750975097509750979050565b60608160059080519060200190610c4f929190610e6f565b507ffef1b5edca9f0ec6cf6c33519bb95cd434ce7803a62f010cce7bd44e1313a7e882604051610c7f9190611349565b60405180910390a160058054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d1d5780601f10610cf257610100808354040283529160200191610d1d565b820191906000526020600020905b815481529060010190602001808311610d0057829003601f168201915b50505050509050919050565b600081600260016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507fd2d9079bdd911e7455624d367e4a5b49c273caa1ef0ad03b7111443129156c5a600260019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16604051610dbd91906112f8565b60405180910390a1600260019054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e3057805160ff1916838001178555610e5e565b82800160010185558215610e5e579182015b82811115610e5d578251825591602001919060010190610e42565b5b509050610e6b9190610eef565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610eb057805160ff1916838001178555610ede565b82800160010185558215610ede579182015b82811115610edd578251825591602001919060010190610ec2565b5b509050610eeb9190610eef565b5090565b610f1191905b80821115610f0d576000816000905550600101610ef5565b5090565b90565b6000610f208235611525565b905092915050565b6000610f348235611545565b905092915050565b6000610f488235611551565b905092915050565b600082601f8301121515610f6357600080fd5b8135610f76610f718261146d565b611440565b91508082526020830160208301858383011115610f9257600080fd5b610f9d83828461156f565b50505092915050565b6000610fb2823561155b565b905092915050565b600082601f8301121515610fcd57600080fd5b8135610fe0610fdb82611499565b611440565b91508082526020830160208301858383011115610ffc57600080fd5b61100783828461156f565b50505092915050565b600061101c8235611565565b905092915050565b60006020828403121561103657600080fd5b600061104484828501610f14565b91505092915050565b60006020828403121561105f57600080fd5b600061106d84828501610f28565b91505092915050565b60006020828403121561108857600080fd5b600061109684828501610f3c565b91505092915050565b6000602082840312156110b157600080fd5b600082013567ffffffffffffffff8111156110cb57600080fd5b6110d784828501610f50565b91505092915050565b6000602082840312156110f257600080fd5b600061110084828501610fa6565b91505092915050565b60006020828403121561111b57600080fd5b600082013567ffffffffffffffff81111561113557600080fd5b61114184828501610fba565b91505092915050565b60006020828403121561115c57600080fd5b600061116a84828501611010565b91505092915050565b600080600080600080600060e0888a03121561118e57600080fd5b600061119c8a828b01611010565b97505060206111ad8a828b01610fa6565b96505060406111be8a828b01610f28565b95505060606111cf8a828b01610f14565b94505060806111e08a828b01610f3c565b93505060a088013567ffffffffffffffff8111156111fd57600080fd5b6112098a828b01610fba565b92505060c088013567ffffffffffffffff81111561122657600080fd5b6112328a828b01610f50565b91505092959891949750929550565b61124a816114db565b82525050565b611259816114fb565b82525050565b61126881611507565b82525050565b6000611279826114c5565b80845261128d81602086016020860161157e565b611296816115b1565b602085010191505092915050565b6112ad81611511565b82525050565b60006112be826114d0565b8084526112d281602086016020860161157e565b6112db816115b1565b602085010191505092915050565b6112f28161151b565b82525050565b600060208201905061130d6000830184611241565b92915050565b60006020820190506113286000830184611250565b92915050565b6000602082019050611343600083018461125f565b92915050565b60006020820190508181036000830152611363818461126e565b905092915050565b600060208201905061138060008301846112a4565b92915050565b600060208201905081810360008301526113a081846112b3565b905092915050565b60006020820190506113bd60008301846112e9565b92915050565b600060e0820190506113d8600083018a6112e9565b6113e560208301896112a4565b6113f26040830188611250565b6113ff6060830187611241565b61140c608083018661125f565b81810360a083015261141e81856112b3565b905081810360c0830152611432818461126e565b905098975050505050505050565b6000604051905081810181811067ffffffffffffffff8211171561146357600080fd5b8060405250919050565b600067ffffffffffffffff82111561148457600080fd5b601f19601f8301169050602081019050919050565b600067ffffffffffffffff8211156114b057600080fd5b601f19601f8301169050602081019050919050565b600081519050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b6000819050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b6000819050919050565b82818337600083830152505050565b60005b8381101561159c578082015181840152602081019050611581565b838111156115ab576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820ddd292db13b45f7f15aa2468af44a5fee0606705c9b9acbbbcdf6c6af2a8f3226c6578706572696d656e74616cf50037";

    public static final String FUNC_GETUINT = "getUint";

    public static final String FUNC_GETBOOL = "getBool";

    public static final String FUNC_SETBOOL = "setBool";

    public static final String FUNC_GETBS = "getBS";

    public static final String FUNC_SETUINT = "setUint";

    public static final String FUNC_SETBS32 = "setBS32";

    public static final String FUNC_GETINT = "getInt";

    public static final String FUNC_GET = "get";

    public static final String FUNC_SETINT = "setInt";

    public static final String FUNC_SETSTRING = "setString";

    public static final String FUNC_GETBS32 = "getBS32";

    public static final String FUNC_GETSTRING = "getString";

    public static final String FUNC_GETADDR = "getAddr";

    public static final String FUNC_SET = "set";

    public static final String FUNC_SETBS = "setBS";

    public static final String FUNC_SETADDR = "setAddr";

    public static final Event EVENTUINT_EVENT = new Event("EventUint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event EVENTINT_EVENT = new Event("EventInt", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event EVENTBOOL_EVENT = new Event("EventBool", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    public static final Event EVENTADDR_EVENT = new Event("EventAddr", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event EVENTB32_EVENT = new Event("EventB32", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    public static final Event EVENTSTRING_EVENT = new Event("EventString", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event EVENTBS_EVENT = new Event("EventBS", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event EVENT_EVENT = new Event("Event", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    @Deprecated
    protected EchoSimple(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EchoSimple(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EchoSimple(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EchoSimple(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getUint() {
        final Function function = new Function(FUNC_GETUINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> getBool() {
        final Function function = new Function(FUNC_GETBOOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> setBool(Boolean _b) {
        final Function function = new Function(
                FUNC_SETBOOL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Bool(_b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBool(Boolean _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBOOL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Bool(_b)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<byte[]> getBS() {
        final Function function = new Function(FUNC_GETBS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setUint(BigInteger _u) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_u)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setUint(BigInteger _u, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_u)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> setBS32(byte[] _b) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBS32(byte[] _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_b)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<BigInteger> getInt() {
        final Function function = new Function(FUNC_GETINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple7<BigInteger, BigInteger, Boolean, String, byte[], String, byte[]>> get() {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteCall<Tuple7<BigInteger, BigInteger, Boolean, String, byte[], String, byte[]>>(
                new Callable<Tuple7<BigInteger, BigInteger, Boolean, String, byte[], String, byte[]>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, Boolean, String, byte[], String, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, BigInteger, Boolean, String, byte[], String, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (byte[]) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (byte[]) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setInt(BigInteger _i) {
        final Function function = new Function(
                FUNC_SETINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_i)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setInt(BigInteger _i, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_i)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> setString(String _s) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setString(String _s, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_s)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<byte[]> getBS32() {
        final Function function = new Function(FUNC_GETBS32, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> getString() {
        final Function function = new Function(FUNC_GETSTRING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getAddr() {
        final Function function = new Function(FUNC_GETADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> set(BigInteger _u, BigInteger _i, Boolean _b, String _addr, byte[] _bs32, String _s, byte[] _bs) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_u), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_i), 
                new org.fisco.bcos.web3j.abi.datatypes.Bool(_b), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_addr), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_bs32), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_s), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_bs)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void set(BigInteger _u, BigInteger _i, Boolean _b, String _addr, byte[] _bs32, String _s, byte[] _bs, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_u), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_i), 
                new org.fisco.bcos.web3j.abi.datatypes.Bool(_b), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_addr), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_bs32), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_s), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_bs)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> setBS(byte[] _b) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBS(byte[] _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_b)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> setAddr(String _addr) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setAddr(String _addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_addr)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public List<EventUintEventResponse> getEventUintEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTUINT_EVENT, transactionReceipt);
        ArrayList<EventUintEventResponse> responses = new ArrayList<EventUintEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventUintEventResponse typedResponse = new EventUintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.u = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventUintEventResponse> eventUintEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventUintEventResponse>() {
            @Override
            public EventUintEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTUINT_EVENT, log);
                EventUintEventResponse typedResponse = new EventUintEventResponse();
                typedResponse.log = log;
                typedResponse.u = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventUintEventResponse> eventUintEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTUINT_EVENT));
        return eventUintEventFlowable(filter);
    }

    public List<EventIntEventResponse> getEventIntEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTINT_EVENT, transactionReceipt);
        ArrayList<EventIntEventResponse> responses = new ArrayList<EventIntEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventIntEventResponse typedResponse = new EventIntEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.i = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventIntEventResponse> eventIntEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventIntEventResponse>() {
            @Override
            public EventIntEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTINT_EVENT, log);
                EventIntEventResponse typedResponse = new EventIntEventResponse();
                typedResponse.log = log;
                typedResponse.i = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventIntEventResponse> eventIntEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTINT_EVENT));
        return eventIntEventFlowable(filter);
    }

    public List<EventBoolEventResponse> getEventBoolEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTBOOL_EVENT, transactionReceipt);
        ArrayList<EventBoolEventResponse> responses = new ArrayList<EventBoolEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventBoolEventResponse typedResponse = new EventBoolEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._b = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventBoolEventResponse> eventBoolEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventBoolEventResponse>() {
            @Override
            public EventBoolEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTBOOL_EVENT, log);
                EventBoolEventResponse typedResponse = new EventBoolEventResponse();
                typedResponse.log = log;
                typedResponse._b = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventBoolEventResponse> eventBoolEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTBOOL_EVENT));
        return eventBoolEventFlowable(filter);
    }

    public List<EventAddrEventResponse> getEventAddrEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTADDR_EVENT, transactionReceipt);
        ArrayList<EventAddrEventResponse> responses = new ArrayList<EventAddrEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventAddrEventResponse typedResponse = new EventAddrEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventAddrEventResponse> eventAddrEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventAddrEventResponse>() {
            @Override
            public EventAddrEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTADDR_EVENT, log);
                EventAddrEventResponse typedResponse = new EventAddrEventResponse();
                typedResponse.log = log;
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventAddrEventResponse> eventAddrEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTADDR_EVENT));
        return eventAddrEventFlowable(filter);
    }

    public List<EventB32EventResponse> getEventB32Events(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTB32_EVENT, transactionReceipt);
        ArrayList<EventB32EventResponse> responses = new ArrayList<EventB32EventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventB32EventResponse typedResponse = new EventB32EventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.b = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventB32EventResponse> eventB32EventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventB32EventResponse>() {
            @Override
            public EventB32EventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTB32_EVENT, log);
                EventB32EventResponse typedResponse = new EventB32EventResponse();
                typedResponse.log = log;
                typedResponse.b = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventB32EventResponse> eventB32EventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTB32_EVENT));
        return eventB32EventFlowable(filter);
    }

    public List<EventStringEventResponse> getEventStringEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTSTRING_EVENT, transactionReceipt);
        ArrayList<EventStringEventResponse> responses = new ArrayList<EventStringEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventStringEventResponse typedResponse = new EventStringEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.s = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventStringEventResponse> eventStringEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventStringEventResponse>() {
            @Override
            public EventStringEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTSTRING_EVENT, log);
                EventStringEventResponse typedResponse = new EventStringEventResponse();
                typedResponse.log = log;
                typedResponse.s = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventStringEventResponse> eventStringEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTSTRING_EVENT));
        return eventStringEventFlowable(filter);
    }

    public List<EventBSEventResponse> getEventBSEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTBS_EVENT, transactionReceipt);
        ArrayList<EventBSEventResponse> responses = new ArrayList<EventBSEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventBSEventResponse typedResponse = new EventBSEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.b = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventBSEventResponse> eventBSEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventBSEventResponse>() {
            @Override
            public EventBSEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTBS_EVENT, log);
                EventBSEventResponse typedResponse = new EventBSEventResponse();
                typedResponse.log = log;
                typedResponse.b = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventBSEventResponse> eventBSEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTBS_EVENT));
        return eventBSEventFlowable(filter);
    }

    public List<EventEventResponse> getEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENT_EVENT, transactionReceipt);
        ArrayList<EventEventResponse> responses = new ArrayList<EventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventEventResponse typedResponse = new EventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._u = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._i = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._b = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._addr = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._bs32 = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._s = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse._bs = (byte[]) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventEventResponse> eventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventEventResponse>() {
            @Override
            public EventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENT_EVENT, log);
                EventEventResponse typedResponse = new EventEventResponse();
                typedResponse.log = log;
                typedResponse._u = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._i = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._b = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._addr = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._bs32 = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse._s = (String) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse._bs = (byte[]) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventEventResponse> eventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENT_EVENT));
        return eventEventFlowable(filter);
    }

    @Deprecated
    public static EchoSimple load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EchoSimple(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EchoSimple load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EchoSimple(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EchoSimple load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EchoSimple(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EchoSimple load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EchoSimple(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EchoSimple> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EchoSimple.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EchoSimple> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EchoSimple.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EchoSimple> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EchoSimple.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EchoSimple> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EchoSimple.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class EventUintEventResponse {
        public Log log;

        public BigInteger u;
    }

    public static class EventIntEventResponse {
        public Log log;

        public BigInteger i;
    }

    public static class EventBoolEventResponse {
        public Log log;

        public Boolean _b;
    }

    public static class EventAddrEventResponse {
        public Log log;

        public String addr;
    }

    public static class EventB32EventResponse {
        public Log log;

        public byte[] b;
    }

    public static class EventStringEventResponse {
        public Log log;

        public String s;
    }

    public static class EventBSEventResponse {
        public Log log;

        public byte[] b;
    }

    public static class EventEventResponse {
        public Log log;

        public BigInteger _u;

        public BigInteger _i;

        public Boolean _b;

        public String _addr;

        public byte[] _bs32;

        public String _s;

        public byte[] _bs;
    }
}
