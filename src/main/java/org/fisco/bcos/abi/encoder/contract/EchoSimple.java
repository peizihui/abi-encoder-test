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
    private static final String BINARY = "608060405234801561001057600080fd5b50611221806100206000396000f3006080604052600436106100e5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680620267a4146100ea57806312a7b914146101155780631e26fd331461014057806325f233a5146101695780634ef65c3b146101945780634fac76dd146101bd57806362738998146101e65780636d4ce63c14610211578063747586b8146102425780637fcaf6661461026b578063839ebdfd1461029457806389ea642f146102bf578063a74c2bb6146102ea578063b5f8a97014610315578063c7273f1a1461033e578063d1d80fdf14610367575b600080fd5b3480156100f657600080fd5b506100ff610390565b60405161010c9190610fcd565b60405180910390f35b34801561012157600080fd5b5061012a610399565b6040516101379190610f38565b60405180910390f35b34801561014c57600080fd5b5061016760048036036101629190810190610c72565b6103b0565b005b34801561017557600080fd5b5061017e610404565b60405161018b9190610f6e565b60405180910390f35b3480156101a057600080fd5b506101bb60048036036101b69190810190610d6f565b6104a6565b005b3480156101c957600080fd5b506101e460048036036101df9190810190610c9b565b6104e7565b005b3480156101f257600080fd5b506101fb61052c565b6040516102089190610f90565b60405180910390f35b34801561021d57600080fd5b50610226610536565b6040516102399796959493929190610fe8565b60405180910390f35b34801561024e57600080fd5b5061026960048036036102649190810190610d05565b6106d0565b005b34801561027757600080fd5b50610292600480360361028d9190810190610d2e565b610711565b005b3480156102a057600080fd5b506102a9610762565b6040516102b69190610f53565b60405180910390f35b3480156102cb57600080fd5b506102d461076c565b6040516102e19190610fab565b60405180910390f35b3480156102f657600080fd5b506102ff61080e565b60405161030c9190610f1d565b60405180910390f35b34801561032157600080fd5b5061033c60048036036103379190810190610d98565b610838565b005b34801561034a57600080fd5b5061036560048036036103609190810190610cc4565b610926565b005b34801561037357600080fd5b5061038e60048036036103899190810190610c49565b610977565b005b60008054905090565b6000600260009054906101000a900460ff16905090565b80600260006101000a81548160ff0219169083151502179055507f333dd89d9c702f4d468fd02e664620fa34a8145656d32ac75091b105d8bec331816040516103f99190610f38565b60405180910390a150565b606060058054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561049c5780601f106104715761010080835404028352916020019161049c565b820191906000526020600020905b81548152906001019060200180831161047f57829003601f168201915b5050505050905090565b806000819055507f1ae2cdbaf14c82e9502eb279d8b33ca04d3a48a3014e563a601d2ef44fa88511816040516104dc9190610fcd565b60405180910390a150565b80600381600019169055507f2d806945b5a489eb05d4f9ef20afa07f3fdd263e33fa85a173879ff770dc47d6816040516105219190610f53565b60405180910390a150565b6000600154905090565b6000806000806000606080600054600154600260009054906101000a900460ff16600260019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660035460046005818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106165780601f106105eb57610100808354040283529160200191610616565b820191906000526020600020905b8154815290600101906020018083116105f957829003601f168201915b50505050509150808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106b25780601f10610687576101008083540402835291602001916106b2565b820191906000526020600020905b81548152906001019060200180831161069557829003601f168201915b50505050509050965096509650965096509650965090919293949596565b806001819055507f58da1ac8e1dd43d74ab3708472afd8391aecca3649f2396bc77173054cd515e6816040516107069190610f90565b60405180910390a150565b8060049080519060200190610727929190610a14565b507f7240e2f75cccc64acf37f699b7cc2726ccd9c0ed8afeafdbf7911af78d077bad816040516107579190610fab565b60405180910390a150565b6000600354905090565b606060048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108045780601f106107d957610100808354040283529160200191610804565b820191906000526020600020905b8154815290600101906020018083116107e757829003601f168201915b5050505050905090565b6000600260019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b7f2d7eda0ce58aaf84d16283e6cc11e0ba865c61e2f03bb62f3b2b5f07a1546a36878787878787876040516108739796959493929190610fe8565b60405180910390a1866000819055508560018190555084600260006101000a81548160ff02191690831515021790555083600260016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600381600019169055508160049080519060200190610905929190610a14565b50806005908051906020019061091c929190610a94565b5050505050505050565b806005908051906020019061093c929190610a94565b507ffef1b5edca9f0ec6cf6c33519bb95cd434ce7803a62f010cce7bd44e1313a7e88160405161096c9190610f6e565b60405180910390a150565b80600260016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507fd2d9079bdd911e7455624d367e4a5b49c273caa1ef0ad03b7111443129156c5a600260019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16604051610a099190610f1d565b60405180910390a150565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a5557805160ff1916838001178555610a83565b82800160010185558215610a83579182015b82811115610a82578251825591602001919060010190610a67565b5b509050610a909190610b14565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610ad557805160ff1916838001178555610b03565b82800160010185558215610b03579182015b82811115610b02578251825591602001919060010190610ae7565b5b509050610b109190610b14565b5090565b610b3691905b80821115610b32576000816000905550600101610b1a565b5090565b90565b6000610b45823561114a565b905092915050565b6000610b59823561116a565b905092915050565b6000610b6d8235611176565b905092915050565b600082601f8301121515610b8857600080fd5b8135610b9b610b9682611092565b611065565b91508082526020830160208301858383011115610bb757600080fd5b610bc2838284611194565b50505092915050565b6000610bd78235611180565b905092915050565b600082601f8301121515610bf257600080fd5b8135610c05610c00826110be565b611065565b91508082526020830160208301858383011115610c2157600080fd5b610c2c838284611194565b50505092915050565b6000610c41823561118a565b905092915050565b600060208284031215610c5b57600080fd5b6000610c6984828501610b39565b91505092915050565b600060208284031215610c8457600080fd5b6000610c9284828501610b4d565b91505092915050565b600060208284031215610cad57600080fd5b6000610cbb84828501610b61565b91505092915050565b600060208284031215610cd657600080fd5b600082013567ffffffffffffffff811115610cf057600080fd5b610cfc84828501610b75565b91505092915050565b600060208284031215610d1757600080fd5b6000610d2584828501610bcb565b91505092915050565b600060208284031215610d4057600080fd5b600082013567ffffffffffffffff811115610d5a57600080fd5b610d6684828501610bdf565b91505092915050565b600060208284031215610d8157600080fd5b6000610d8f84828501610c35565b91505092915050565b600080600080600080600060e0888a031215610db357600080fd5b6000610dc18a828b01610c35565b9750506020610dd28a828b01610bcb565b9650506040610de38a828b01610b4d565b9550506060610df48a828b01610b39565b9450506080610e058a828b01610b61565b93505060a088013567ffffffffffffffff811115610e2257600080fd5b610e2e8a828b01610bdf565b92505060c088013567ffffffffffffffff811115610e4b57600080fd5b610e578a828b01610b75565b91505092959891949750929550565b610e6f81611100565b82525050565b610e7e81611120565b82525050565b610e8d8161112c565b82525050565b6000610e9e826110ea565b808452610eb28160208601602086016111a3565b610ebb816111d6565b602085010191505092915050565b610ed281611136565b82525050565b6000610ee3826110f5565b808452610ef78160208601602086016111a3565b610f00816111d6565b602085010191505092915050565b610f1781611140565b82525050565b6000602082019050610f326000830184610e66565b92915050565b6000602082019050610f4d6000830184610e75565b92915050565b6000602082019050610f686000830184610e84565b92915050565b60006020820190508181036000830152610f888184610e93565b905092915050565b6000602082019050610fa56000830184610ec9565b92915050565b60006020820190508181036000830152610fc58184610ed8565b905092915050565b6000602082019050610fe26000830184610f0e565b92915050565b600060e082019050610ffd600083018a610f0e565b61100a6020830189610ec9565b6110176040830188610e75565b6110246060830187610e66565b6110316080830186610e84565b81810360a08301526110438185610ed8565b905081810360c08301526110578184610e93565b905098975050505050505050565b6000604051905081810181811067ffffffffffffffff8211171561108857600080fd5b8060405250919050565b600067ffffffffffffffff8211156110a957600080fd5b601f19601f8301169050602081019050919050565b600067ffffffffffffffff8211156110d557600080fd5b601f19601f8301169050602081019050919050565b600081519050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b6000819050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b6000819050919050565b82818337600083830152505050565b60005b838110156111c15780820151818401526020810190506111a6565b838111156111d0576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820dba28464014a5427d7a2a12de549cef9709c84d4e9c07f114e3693109dfe116c6c6578706572696d656e74616cf50037";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"getUint\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBool\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bool\"}],\"name\":\"setBool\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBS\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_u\",\"type\":\"uint256\"}],\"name\":\"setUint\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bytes32\"}],\"name\":\"setBS32\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getInt\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"bytes32\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bytes\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_i\",\"type\":\"int256\"}],\"name\":\"setInt\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_s\",\"type\":\"string\"}],\"name\":\"setString\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBS32\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getString\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_u\",\"type\":\"uint256\"},{\"name\":\"_i\",\"type\":\"int256\"},{\"name\":\"_b\",\"type\":\"bool\"},{\"name\":\"_addr\",\"type\":\"address\"},{\"name\":\"_bs32\",\"type\":\"bytes32\"},{\"name\":\"_s\",\"type\":\"string\"},{\"name\":\"_bs\",\"type\":\"bytes\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bytes\"}],\"name\":\"setBS\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_addr\",\"type\":\"address\"}],\"name\":\"setAddr\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"u\",\"type\":\"uint256\"}],\"name\":\"EventUint\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"i\",\"type\":\"int256\"}],\"name\":\"EventInt\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_b\",\"type\":\"bool\"}],\"name\":\"EventBool\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"EventAddr\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"b\",\"type\":\"bytes32\"}],\"name\":\"EventB32\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"s\",\"type\":\"string\"}],\"name\":\"EventString\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"b\",\"type\":\"bytes\"}],\"name\":\"EventBS\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_u\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_i\",\"type\":\"int256\"},{\"indexed\":false,\"name\":\"_b\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"_addr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_bs32\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"_s\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"_bs\",\"type\":\"bytes\"}],\"name\":\"Event\",\"type\":\"event\"}]";

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

    public String setBoolSeq(Boolean _b) {
        final Function function = new Function(
                FUNC_SETBOOL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Bool(_b)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
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

    public String setUintSeq(BigInteger _u) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_u)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
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

    public String setBS32Seq(byte[] _b) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_b)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
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

    public String setIntSeq(BigInteger _i) {
        final Function function = new Function(
                FUNC_SETINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_i)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
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

    public String setStringSeq(String _s) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_s)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
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

    public String setSeq(BigInteger _u, BigInteger _i, Boolean _b, String _addr, byte[] _bs32, String _s, byte[] _bs) {
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
        return createTransactionSeq(function);
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

    public String setBSSeq(byte[] _b) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_b)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
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

    public String setAddrSeq(String _addr) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_addr)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
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

		@Override
		public String toString() {
			return "[ _u : " + _u + "  _i : " + _i + " _b : " + _b + "  _addr : " + _addr + "  _bs32 : "
					+ Arrays.toString(_bs32) + "  _s : " + _s + "  _bs : " + Arrays.toString(_bs) + "]";
		}
    }
}
