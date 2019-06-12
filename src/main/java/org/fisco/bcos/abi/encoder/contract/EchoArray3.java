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
import org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3;
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
public class EchoArray3 extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061220c806100206000396000f3006080604052600436106100e5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680620267a4146100ea57806312a7b914146101155780631e376cd11461014057806325f233a5146101695780632dd4150f1461019457806356dfa548146101bd57806359bbaa08146101e6578063627389981461020f578063655becb21461023a5780636d4ce63c14610263578063839ebdfd1461029457806389ea642f146102bf578063a74c2bb6146102ea578063a96b9e0414610315578063b3d440501461033e578063e923700714610367575b600080fd5b3480156100f657600080fd5b506100ff610390565b60405161010c9190611dd7565b60405180910390f35b34801561012157600080fd5b5061012a6103db565b6040516101379190611d42565b60405180910390f35b34801561014c57600080fd5b50610167600480360361016291908101906118c9565b61044b565b005b34801561017557600080fd5b5061017e610497565b60405161018b9190611d78565b60405180910390f35b3480156101a057600080fd5b506101bb60048036036101b69190810190611836565b610574565b005b3480156101c957600080fd5b506101e460048036036101df9190810190611888565b6105c0565b005b3480156101f257600080fd5b5061020d6004803603610208919081019061195c565b61060c565b005b34801561021b57600080fd5b506102246106d6565b6040516102319190611d9a565b60405180910390f35b34801561024657600080fd5b50610261600480360361025c919081019061180d565b610720565b005b34801561026f57600080fd5b5061027861076c565b60405161028b9796959493929190611df2565b60405180910390f35b3480156102a057600080fd5b506102a9610b05565b6040516102b69190611d5d565b60405180910390f35b3480156102cb57600080fd5b506102d4610b54565b6040516102e19190611db5565b60405180910390f35b3480156102f657600080fd5b506102ff610c31565b60405161030c9190611d27565b60405180910390f35b34801561032157600080fd5b5061033c600480360361033791908101906118f2565b610cb2565b005b34801561034a57600080fd5b5061036560048036036103609190810190611933565b610cfe565b005b34801561037357600080fd5b5061038e6004803603610389919081019061185f565b610d4a565b005b610398610d96565b60006003806020026040519081016040528092919082600380156103d1576020028201915b8154815260200190600101908083116103bd575b5050505050905090565b6103e3610db9565b6006600380602002604051908101604052809291908260038015610441576020028201916000905b82829054906101000a900460ff1615158152602001906001019060208260000104928301926001038202915080841161040b5790505b5050505050905090565b80600390600361045c929190610ddc565b507f385b653030828abf00404d959a34b620eaa27aefc622efa9f1299e082513c13f8160405161048c9190611d9a565b60405180910390a150565b61049f610e1c565b6010600380602002604051908101604052809291906000905b8282101561056b578382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105575780601f1061052c57610100808354040283529160200191610557565b820191906000526020600020905b81548152906001019060200180831161053a57829003601f168201915b5050505050815260200190600101906104b8565b50505050905090565b806006906003610585929190610e44565b507fb12d5bcec6e066d1862b27275a65e3bb7a469826444217d67fbc1c4f410e0a8b816040516105b59190611d42565b60405180910390a150565b8060109060036105d1929190610edd565b507f61e31513c8ea5b37e10126b6d238f001d3944b5d201b709d52c87808d214f23a816040516106019190611d78565b60405180910390a150565b7f39482f65c644915801180f94904b0357e572e430972abcd7bc8393038a30c55a878787878787876040516106479796959493929190611df2565b60405180910390a1866000906003610660929190610f30565b50856003906003610672929190610ddc565b50846006906003610684929190610e44565b50836007906003610696929190610f70565b5082600a9060036106a8929190610fed565b5081600d9060036106ba929190611033565b508060109060036106cc929190610edd565b5050505050505050565b6106de611086565b60038080602002604051908101604052809291908260038015610716576020028201915b815481526020019060010190808311610702575b5050505050905090565b806007906003610731929190610f70565b507f0204942a55eb4d28e562f93e974896eeb0d4c7704483f4f1e718a0e564911c22816040516107619190611d27565b60405180910390a150565b610774610d96565b61077c611086565b610784610db9565b61078c6110a9565b6107946110cc565b61079c6110ef565b6107a4610e1c565b6000600360066007600a600d6010866003806020026040519081016040528092919082600380156107ea576020028201915b8154815260200190600101908083116107d6575b5050505050965085600380602002604051908101604052809291908260038015610829576020028201915b815481526020019060010190808311610815575b505050505095508460038060200260405190810160405280929190826003801561088d576020028201916000905b82829054906101000a900460ff161515815260200190600101906020826000010492830192600103820291508084116108575790505b5050505050945083600380602002604051908101604052809291908260038015610902576020028201915b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190600101908083116108b8575b5050505050935082600380602002604051908101604052809291908260038015610945576020028201915b8154600019168152602001906001019080831161092d575b5050505050925081600380602002604051908101604052809291906000905b82821015610a17578382018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a035780601f106109d857610100808354040283529160200191610a03565b820191906000526020600020905b8154815290600101906020018083116109e657829003601f168201915b505050505081526020019060010190610964565b50505050915080600380602002604051908101604052809291906000905b82821015610ae8578382018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ad45780601f10610aa957610100808354040283529160200191610ad4565b820191906000526020600020905b815481529060010190602001808311610ab757829003601f168201915b505050505081526020019060010190610a35565b505050509050965096509650965096509650965090919293949596565b610b0d6110cc565b600a600380602002604051908101604052809291908260038015610b4a576020028201915b81546000191681526020019060010190808311610b32575b5050505050905090565b610b5c6110ef565b600d600380602002604051908101604052809291906000905b82821015610c28578382018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c145780601f10610be957610100808354040283529160200191610c14565b820191906000526020600020905b815481529060010190602001808311610bf757829003601f168201915b505050505081526020019060010190610b75565b50505050905090565b610c396110a9565b6007600380602002604051908101604052809291908260038015610ca8576020028201915b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610c5e575b5050505050905090565b80600d906003610cc3929190611033565b507f08060fce4ec5f53c788e8c0173b4c9433eb6e1d356e7cbef608b4b38b274390881604051610cf39190611db5565b60405180910390a150565b806000906003610d0f929190610f30565b507f094a1f4d28a8b4d576b700674f52407be47bde430e0f3844199d28d3a453be3281604051610d3f9190611dd7565b60405180910390a150565b80600a906003610d5b929190610fed565b507fb3174dddc339a75084d2e99cea33f9491d15baf143623d01d7f3614f32aa23a581604051610d8b9190611d5d565b60405180910390a150565b606060405190810160405280600390602082028038833980820191505090505090565b606060405190810160405280600390602082028038833980820191505090505090565b8260038101928215610e0b579160200282015b82811115610e0a578251825591602001919060010190610def565b5b509050610e189190611117565b5090565b6060604051908101604052806003905b6060815260200190600190039081610e2c5790505090565b826003601f01602090048101928215610ecc5791602002820160005b83821115610e9d57835183826101000a81548160ff0219169083151502179055509260200192600101602081600001049283019260010302610e60565b8015610eca5782816101000a81549060ff0219169055600101602081600001049283019260010302610e9d565b505b509050610ed9919061113c565b5090565b8260038101928215610f1f579160200282015b82811115610f1e578251829080519060200190610f0e92919061116c565b5091602001919060010190610ef0565b5b509050610f2c91906111ec565b5090565b8260038101928215610f5f579160200282015b82811115610f5e578251825591602001919060010190610f43565b5b509050610f6c9190611218565b5090565b8260038101928215610fdc579160200282015b82811115610fdb5782518260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555091602001919060010190610f83565b5b509050610fe9919061123d565b5090565b8260038101928215611022579160200282015b82811115611021578251829060001916905591602001919060010190611000565b5b50905061102f9190611280565b5090565b8260038101928215611075579160200282015b828111156110745782518290805190602001906110649291906112a5565b5091602001919060010190611046565b5b5090506110829190611325565b5090565b606060405190810160405280600390602082028038833980820191505090505090565b606060405190810160405280600390602082028038833980820191505090505090565b606060405190810160405280600390602082028038833980820191505090505090565b6060604051908101604052806003905b60608152602001906001900390816110ff5790505090565b61113991905b8082111561113557600081600090555060010161111d565b5090565b90565b61116991905b8082111561116557600081816101000a81549060ff021916905550600101611142565b5090565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106111ad57805160ff19168380011785556111db565b828001600101855582156111db579182015b828111156111da5782518255916020019190600101906111bf565b5b5090506111e89190611218565b5090565b61121591905b8082111561121157600081816112089190611351565b506001016111f2565b5090565b90565b61123a91905b8082111561123657600081600090555060010161121e565b5090565b90565b61127d91905b8082111561127957600081816101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905550600101611243565b5090565b90565b6112a291905b8082111561129e576000816000905550600101611286565b5090565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106112e657805160ff1916838001178555611314565b82800160010185558215611314579182015b828111156113135782518255916020019190600101906112f8565b5b5090506113219190611218565b5090565b61134e91905b8082111561134a57600081816113419190611399565b5060010161132b565b5090565b90565b50805460018160011615610100020316600290046000825580601f106113775750611396565b601f0160209004906000526020600020908101906113959190611218565b5b50565b50805460018160011615610100020316600290046000825580601f106113bf57506113de565b601f0160209004906000526020600020908101906113dd9190611218565b5b50565b60006113ed8235612135565b905092915050565b600082601f830112151561140857600080fd5b600361141b61141682611ea1565b611e74565b9150818385602084028201111561143157600080fd5b60005b83811015611461578161144788826113e1565b845260208401935060208301925050600181019050611434565b5050505092915050565b600082601f830112151561147e57600080fd5b600361149161148c82611ec3565b611e74565b915081838560208402820111156114a757600080fd5b60005b838110156114d757816114bd8882611711565b8452602084019350602083019250506001810190506114aa565b5050505092915050565b600082601f83011215156114f457600080fd5b600361150761150282611ee5565b611e74565b9150818385602084028201111561151d57600080fd5b60005b8381101561154d57816115338882611725565b845260208401935060208301925050600181019050611520565b5050505092915050565b600082601f830112151561156a57600080fd5b600361157d61157882611f07565b611e74565b9150818360005b838110156115b4578135860161159a8882611739565b845260208401935060208301925050600181019050611584565b5050505092915050565b600082601f83011215156115d157600080fd5b60036115e46115df82611f29565b611e74565b915081838560208402820111156115fa57600080fd5b60005b8381101561162a5781611610888261178f565b8452602084019350602083019250506001810190506115fd565b5050505092915050565b600082601f830112151561164757600080fd5b600361165a61165582611f4b565b611e74565b9150818360005b83811015611691578135860161167788826117a3565b845260208401935060208301925050600181019050611661565b5050505092915050565b600082601f83011215156116ae57600080fd5b60036116c16116bc82611f6d565b611e74565b915081838560208402820111156116d757600080fd5b60005b8381101561170757816116ed88826117f9565b8452602084019350602083019250506001810190506116da565b5050505092915050565b600061171d8235612155565b905092915050565b60006117318235612161565b905092915050565b600082601f830112151561174c57600080fd5b813561175f61175a82611f8f565b611e74565b9150808252602083016020830185838301111561177b57600080fd5b61178683828461217f565b50505092915050565b600061179b823561216b565b905092915050565b600082601f83011215156117b657600080fd5b81356117c96117c482611fbb565b611e74565b915080825260208301602083018583830111156117e557600080fd5b6117f083828461217f565b50505092915050565b60006118058235612175565b905092915050565b60006060828403121561181f57600080fd5b600061182d848285016113f5565b91505092915050565b60006060828403121561184857600080fd5b60006118568482850161146b565b91505092915050565b60006060828403121561187157600080fd5b600061187f848285016114e1565b91505092915050565b60006020828403121561189a57600080fd5b600082013567ffffffffffffffff8111156118b457600080fd5b6118c084828501611557565b91505092915050565b6000606082840312156118db57600080fd5b60006118e9848285016115be565b91505092915050565b60006020828403121561190457600080fd5b600082013567ffffffffffffffff81111561191e57600080fd5b61192a84828501611634565b91505092915050565b60006060828403121561194557600080fd5b60006119538482850161169b565b91505092915050565b6000806000806000806000610220888a03121561197857600080fd5b60006119868a828b0161169b565b97505060606119978a828b016115be565b96505060c06119a88a828b0161146b565b9550506101206119ba8a828b016113f5565b9450506101806119cc8a828b016114e1565b9350506101e088013567ffffffffffffffff8111156119ea57600080fd5b6119f68a828b01611634565b92505061020088013567ffffffffffffffff811115611a1457600080fd5b611a208a828b01611557565b91505092959891949750929550565b611a38816120eb565b82525050565b611a478161202d565b611a5082611fe7565b60005b82811015611a8257611a66858351611a2f565b611a6f82612090565b9150602085019450600181019050611a53565b5050505050565b611a9281612038565b611a9b82611ff1565b60005b82811015611acd57611ab1858351611c7f565b611aba8261209d565b9150602085019450600181019050611a9e565b5050505050565b611add81612043565b611ae682611ffb565b60005b82811015611b1857611afc858351611c8e565b611b05826120aa565b9150602085019450600181019050611ae9565b5050505050565b6000611b2a8261204e565b83602082028501611b3a85612005565b60005b84811015611b73578383038852611b55838351611c9d565b9250611b60826120b7565b9150602088019750600181019050611b3d565b508196508694505050505092915050565b611b8d81612059565b611b968261200f565b60005b82811015611bc857611bac858351611cd3565b611bb5826120c4565b9150602085019450600181019050611b99565b5050505050565b6000611bda82612064565b83602082028501611bea85612019565b60005b84811015611c23578383038852611c05838351611ce2565b9250611c10826120d1565b9150602088019750600181019050611bed565b508196508694505050505092915050565b611c3d8161206f565b611c4682612023565b60005b82811015611c7857611c5c858351611d18565b611c65826120de565b9150602085019450600181019050611c49565b5050505050565b611c888161210b565b82525050565b611c9781612117565b82525050565b6000611ca88261207a565b808452611cbc81602086016020860161218e565b611cc5816121c1565b602085010191505092915050565b611cdc81612121565b82525050565b6000611ced82612085565b808452611d0181602086016020860161218e565b611d0a816121c1565b602085010191505092915050565b611d218161212b565b82525050565b6000606082019050611d3c6000830184611a3e565b92915050565b6000606082019050611d576000830184611a89565b92915050565b6000606082019050611d726000830184611ad4565b92915050565b60006020820190508181036000830152611d928184611b1f565b905092915050565b6000606082019050611daf6000830184611b84565b92915050565b60006020820190508181036000830152611dcf8184611bcf565b905092915050565b6000606082019050611dec6000830184611c34565b92915050565b600061022082019050611e08600083018a611c34565b611e156060830189611b84565b611e2260c0830188611a89565b611e30610120830187611a3e565b611e3e610180830186611ad4565b8181036101e0830152611e518185611bcf565b9050818103610200830152611e668184611b1f565b905098975050505050505050565b6000604051905081810181811067ffffffffffffffff82111715611e9757600080fd5b8060405250919050565b600067ffffffffffffffff821115611eb857600080fd5b602082029050919050565b600067ffffffffffffffff821115611eda57600080fd5b602082029050919050565b600067ffffffffffffffff821115611efc57600080fd5b602082029050919050565b600067ffffffffffffffff821115611f1e57600080fd5b602082029050919050565b600067ffffffffffffffff821115611f4057600080fd5b602082029050919050565b600067ffffffffffffffff821115611f6257600080fd5b602082029050919050565b600067ffffffffffffffff821115611f8457600080fd5b602082029050919050565b600067ffffffffffffffff821115611fa657600080fd5b601f19601f8301169050602081019050919050565b600067ffffffffffffffff821115611fd257600080fd5b601f19601f8301169050602081019050919050565b6000819050919050565b6000819050919050565b6000819050919050565b6000819050919050565b6000819050919050565b6000819050919050565b6000819050919050565b600060039050919050565b600060039050919050565b600060039050919050565b600060039050919050565b600060039050919050565b600060039050919050565b600060039050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b6000819050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b6000819050919050565b82818337600083830152505050565b60005b838110156121ac578082015181840152602081019050612191565b838111156121bb576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820d268ce2b92b15d4a5dd3ffa4470779c2e58d16799fc7898ae3eff26b480126946c6578706572696d656e74616cf50037";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"getUint\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[3]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBool\",\"outputs\":[{\"name\":\"\",\"type\":\"bool[3]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_i\",\"type\":\"int256[3]\"}],\"name\":\"setInt\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBS\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes[3]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bool[3]\"}],\"name\":\"setBool\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bytes[3]\"}],\"name\":\"setBS\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_u\",\"type\":\"uint256[3]\"},{\"name\":\"_i\",\"type\":\"int256[3]\"},{\"name\":\"_b\",\"type\":\"bool[3]\"},{\"name\":\"_addr\",\"type\":\"address[3]\"},{\"name\":\"_bs32\",\"type\":\"bytes32[3]\"},{\"name\":\"_s\",\"type\":\"string[3]\"},{\"name\":\"_bs\",\"type\":\"bytes[3]\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getInt\",\"outputs\":[{\"name\":\"\",\"type\":\"int256[3]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_addr\",\"type\":\"address[3]\"}],\"name\":\"setAddr\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[3]\"},{\"name\":\"\",\"type\":\"int256[3]\"},{\"name\":\"\",\"type\":\"bool[3]\"},{\"name\":\"\",\"type\":\"address[3]\"},{\"name\":\"\",\"type\":\"bytes32[3]\"},{\"name\":\"\",\"type\":\"string[3]\"},{\"name\":\"\",\"type\":\"bytes[3]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBS32\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32[3]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getString\",\"outputs\":[{\"name\":\"\",\"type\":\"string[3]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"address[3]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_s\",\"type\":\"string[3]\"}],\"name\":\"setString\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_u\",\"type\":\"uint256[3]\"}],\"name\":\"setUint\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bytes32[3]\"}],\"name\":\"setBS32\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"u\",\"type\":\"uint256[3]\"}],\"name\":\"EventUint\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"i\",\"type\":\"int256[3]\"}],\"name\":\"EventInt\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_b\",\"type\":\"bool[3]\"}],\"name\":\"EventBool\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"addr\",\"type\":\"address[3]\"}],\"name\":\"EventAddr\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"b\",\"type\":\"bytes32[3]\"}],\"name\":\"EventB32\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"s\",\"type\":\"string[3]\"}],\"name\":\"EventString\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"b\",\"type\":\"bytes[3]\"}],\"name\":\"EventBS\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_u\",\"type\":\"uint256[3]\"},{\"indexed\":false,\"name\":\"_i\",\"type\":\"int256[3]\"},{\"indexed\":false,\"name\":\"_b\",\"type\":\"bool[3]\"},{\"indexed\":false,\"name\":\"_addr\",\"type\":\"address[3]\"},{\"indexed\":false,\"name\":\"_bs32\",\"type\":\"bytes32[3]\"},{\"indexed\":false,\"name\":\"_s\",\"type\":\"string[3]\"},{\"indexed\":false,\"name\":\"_bs\",\"type\":\"bytes[3]\"}],\"name\":\"Event\",\"type\":\"event\"}]";

    public static final String FUNC_GETUINT = "getUint";

    public static final String FUNC_GETBOOL = "getBool";

    public static final String FUNC_SETINT = "setInt";

    public static final String FUNC_GETBS = "getBS";

    public static final String FUNC_SETBOOL = "setBool";

    public static final String FUNC_SETBS = "setBS";

    public static final String FUNC_SET = "set";

    public static final String FUNC_GETINT = "getInt";

    public static final String FUNC_SETADDR = "setAddr";

    public static final String FUNC_GET = "get";

    public static final String FUNC_GETBS32 = "getBS32";

    public static final String FUNC_GETSTRING = "getString";

    public static final String FUNC_GETADDR = "getAddr";

    public static final String FUNC_SETSTRING = "setString";

    public static final String FUNC_SETUINT = "setUint";

    public static final String FUNC_SETBS32 = "setBS32";

    public static final Event EVENTUINT_EVENT = new Event("EventUint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Uint256>>() {}));
    ;

    public static final Event EVENTINT_EVENT = new Event("EventInt", 
            Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Int256>>() {}));
    ;

    public static final Event EVENTBOOL_EVENT = new Event("EventBool", 
            Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Bool>>() {}));
    ;

    public static final Event EVENTADDR_EVENT = new Event("EventAddr", 
            Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Address>>() {}));
    ;

    public static final Event EVENTB32_EVENT = new Event("EventB32", 
            Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Bytes32>>() {}));
    ;

    public static final Event EVENTSTRING_EVENT = new Event("EventString", 
            Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Utf8String>>() {}));
    ;

    public static final Event EVENTBS_EVENT = new Event("EventBS", 
            Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<DynamicBytes>>() {}));
    ;

    public static final Event EVENT_EVENT = new Event("Event", 
            Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Uint256>>() {}, new TypeReference<StaticArray3<Int256>>() {}, new TypeReference<StaticArray3<Bool>>() {}, new TypeReference<StaticArray3<Address>>() {}, new TypeReference<StaticArray3<Bytes32>>() {}, new TypeReference<StaticArray3<Utf8String>>() {}, new TypeReference<StaticArray3<DynamicBytes>>() {}));
    ;

    @Deprecated
    protected EchoArray3(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EchoArray3(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EchoArray3(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EchoArray3(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<List> getUint() {
        final Function function = new Function(FUNC_GETUINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getBool() {
        final Function function = new Function(FUNC_GETBOOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Bool>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setInt(List<BigInteger> _i) {
        final Function function = new Function(
                FUNC_SETINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setInt(List<BigInteger> _i, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setIntSeq(List<BigInteger> _i) {
        final Function function = new Function(
                FUNC_SETINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<List> getBS() {
        final Function function = new Function(FUNC_GETBS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<DynamicBytes>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setBool(List<Boolean> _b) {
        final Function function = new Function(
                FUNC_SETBOOL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBool(List<Boolean> _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBOOL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setBoolSeq(List<Boolean> _b) {
        final Function function = new Function(
                FUNC_SETBOOL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setBS(List<byte[]> _b) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBS(List<byte[]> _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setBSSeq(List<byte[]> _b) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> set(List<BigInteger> _u, List<BigInteger> _i, List<Boolean> _b, List<String> _addr, List<byte[]> _bs32, List<String> _s, List<byte[]> _bs) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs32, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void set(List<BigInteger> _u, List<BigInteger> _i, List<Boolean> _b, List<String> _addr, List<byte[]> _bs32, List<String> _s, List<byte[]> _bs, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs32, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setSeq(List<BigInteger> _u, List<BigInteger> _i, List<Boolean> _b, List<String> _addr, List<byte[]> _bs32, List<String> _s, List<byte[]> _bs) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs32, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<List> getInt() {
        final Function function = new Function(FUNC_GETINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Int256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setAddr(List<String> _addr) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setAddr(List<String> _addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setAddrSeq(List<String> _addr) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>>> get() {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Uint256>>() {}, new TypeReference<StaticArray3<Int256>>() {}, new TypeReference<StaticArray3<Bool>>() {}, new TypeReference<StaticArray3<Address>>() {}, new TypeReference<StaticArray3<Bytes32>>() {}, new TypeReference<StaticArray3<Utf8String>>() {}, new TypeReference<StaticArray3<DynamicBytes>>() {}));
        return new RemoteCall<Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>>>(
                new Callable<Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>>>() {
                    @Override
                    public Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Int256>) results.get(1).getValue()), 
                                convertToNative((List<Bool>) results.get(2).getValue()), 
                                convertToNative((List<Address>) results.get(3).getValue()), 
                                convertToNative((List<Bytes32>) results.get(4).getValue()), 
                                convertToNative((List<Utf8String>) results.get(5).getValue()), 
                                convertToNative((List<DynamicBytes>) results.get(6).getValue()));
                    }
                });
    }

    public RemoteCall<List> getBS32() {
        final Function function = new Function(FUNC_GETBS32, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Bytes32>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getString() {
        final Function function = new Function(FUNC_GETSTRING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getAddr() {
        final Function function = new Function(FUNC_GETADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setString(List<String> _s) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setString(List<String> _s, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setStringSeq(List<String> _s) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setUint(List<BigInteger> _u) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setUint(List<BigInteger> _u, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setUintSeq(List<BigInteger> _u) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setBS32(List<byte[]> _b) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBS32(List<byte[]> _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setBS32Seq(List<byte[]> _b) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public List<EventUintEventResponse> getEventUintEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTUINT_EVENT, transactionReceipt);
        ArrayList<EventUintEventResponse> responses = new ArrayList<EventUintEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventUintEventResponse typedResponse = new EventUintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.u = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.u = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse.i = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.i = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse._b = (List<Boolean>) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse._b = (List<Boolean>) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse.addr = (List<String>) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.addr = (List<String>) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse.b = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.b = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse.s = (List<String>) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.s = (List<String>) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse.b = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.b = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse._u = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._i = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._b = (List<Boolean>) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._addr = (List<String>) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._bs32 = (List<byte[]>) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._s = (List<String>) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse._bs = (List<byte[]>) eventValues.getNonIndexedValues().get(6).getValue();
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
                typedResponse._u = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._i = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._b = (List<Boolean>) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._addr = (List<String>) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._bs32 = (List<byte[]>) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse._s = (List<String>) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse._bs = (List<byte[]>) eventValues.getNonIndexedValues().get(6).getValue();
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
    public static EchoArray3 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EchoArray3(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EchoArray3 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EchoArray3(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EchoArray3 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EchoArray3(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EchoArray3 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EchoArray3(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EchoArray3> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EchoArray3.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EchoArray3> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EchoArray3.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EchoArray3> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EchoArray3.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EchoArray3> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EchoArray3.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class EventUintEventResponse {
        public Log log;

        public List<BigInteger> u;
    }

    public static class EventIntEventResponse {
        public Log log;

        public List<BigInteger> i;
    }

    public static class EventBoolEventResponse {
        public Log log;

        public List<Boolean> _b;
    }

    public static class EventAddrEventResponse {
        public Log log;

        public List<String> addr;
    }

    public static class EventB32EventResponse {
        public Log log;

        public List<byte[]> b;
    }

    public static class EventStringEventResponse {
        public Log log;

        public List<String> s;
    }

    public static class EventBSEventResponse {
        public Log log;

        public List<byte[]> b;
    }

    public static class EventEventResponse {
        public Log log;

        public List<BigInteger> _u;

        public List<BigInteger> _i;

        public List<Boolean> _b;

        public List<String> _addr;

        public List<byte[]> _bs32;

        public List<String> _s;

        public List<byte[]> _bs;

		@Override
		public String toString() {
			return "EventEventResponse [_u=" + _u + ", _i=" + _i + ", _b=" + _b + ", _addr=" + _addr + ", _bs32="
					+ _bs32 + ", _s=" + _s + ", _bs=" + _bs + "]";
		}
    }
}
