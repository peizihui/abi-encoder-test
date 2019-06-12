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
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
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
public class EchoArray extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50612494806100206000396000f3006080604052600436106100e5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680620267a4146100ea578063050ec1f81461011557806312a7b9141461013e5780631b3adf681461016957806325f233a51461019257806362738998146101bd5780636d4ce63c146101e8578063839ebdfd1461021957806389ea642f146102445780639d59e3641461026f578063a60f3fbc14610298578063a61f60e5146102c1578063a74c2bb6146102ea578063ad3b1c8514610315578063b21f51a71461033e578063fea9f6ed14610367575b600080fd5b3480156100f657600080fd5b506100ff610390565b60405161010c9190611ffb565b60405180910390f35b34801561012157600080fd5b5061013c6004803603610137919081019061194a565b6103e8565b005b34801561014a57600080fd5b50610153610439565b6040516101609190611f51565b60405180910390f35b34801561017557600080fd5b50610190600480360361018b9190810190611a0d565b6104b6565b005b34801561019e57600080fd5b506101a7610507565b6040516101b49190611f95565b60405180910390f35b3480156101c957600080fd5b506101d26105f0565b6040516101df9190611fb7565b60405180910390f35b3480156101f457600080fd5b506101fd610648565b604051610210979695949392919061201d565b60405180910390f35b34801561022557600080fd5b5061022e610a37565b60405161023b9190611f73565b60405180910390f35b34801561025057600080fd5b50610259610a93565b6040516102669190611fd9565b60405180910390f35b34801561027b57600080fd5b5061029660048036036102919190810190611909565b610b7c565b005b3480156102a457600080fd5b506102bf60048036036102ba919081019061198b565b610bcd565b005b3480156102cd57600080fd5b506102e860048036036102e39190810190611a4e565b610c1e565b005b3480156102f657600080fd5b506102ff610c6f565b60405161030c9190611f2f565b60405180910390f35b34801561032157600080fd5b5061033c600480360361033791908101906119cc565b610cfd565b005b34801561034a57600080fd5b5061036560048036036103609190810190611a8f565b610d4e565b005b34801561037357600080fd5b5061038e600480360361038991908101906118c8565b610e3b565b005b606060008054806020026020016040519081016040528092919081815260200182805480156103de57602002820191906000526020600020905b8154815260200190600101908083116103ca575b5050505050905090565b80600490805190602001906103fe929190610e8c565b507f9a2ec02620ec89c7c1922d21ec58d5d5eb720c54406320e612db3ac2017621e88160405161042e9190611f73565b60405180910390a150565b606060028054806020026020016040519081016040528092919081815260200182805480156104ac57602002820191906000526020600020906000905b82829054906101000a900460ff161515815260200190600101906020826000010492830192600103820291508084116104765790505b5050505050905090565b80600590805190602001906104cc929190610edf565b507f78d463c82c79bf10e49f08086cee9639a9efe066628619d9675c16852b2b1f7e816040516104fc9190611fd9565b60405180910390a150565b60606006805480602002602001604051908101604052809291908181526020016000905b828210156105e7578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105d35780601f106105a8576101008083540402835291602001916105d3565b820191906000526020600020905b8154815290600101906020018083116105b657829003601f168201915b50505050508152602001906001019061052b565b50505050905090565b6060600180548060200260200160405190810160405280929190818152602001828054801561063e57602002820191906000526020600020905b81548152602001906001019080831161062a575b5050505050905090565b60608060608060608060606000600160026003600460056006868054806020026020016040519081016040528092919081815260200182805480156106ac57602002820191906000526020600020905b815481526020019060010190808311610698575b50505050509650858054806020026020016040519081016040528092919081815260200182805480156106fe57602002820191906000526020600020905b8154815260200190600101908083116106ea575b505050505095508480548060200260200160405190810160405280929190818152602001828054801561077557602002820191906000526020600020906000905b82829054906101000a900460ff1615158152602001906001019060208260000104928301926001038202915080841161073f5790505b50505050509450838054806020026020016040519081016040528092919081815260200182805480156107fd57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190600101908083116107b3575b505050505093508280548060200260200160405190810160405280929190818152602001828054801561085357602002820191906000526020600020905b8154600019168152602001906001019080831161083b575b5050505050925081805480602002602001604051908101604052809291908181526020016000905b82821015610937578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109235780601f106108f857610100808354040283529160200191610923565b820191906000526020600020905b81548152906001019060200180831161090657829003601f168201915b50505050508152602001906001019061087b565b50505050915080805480602002602001604051908101604052809291908181526020016000905b82821015610a1a578382906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a065780601f106109db57610100808354040283529160200191610a06565b820191906000526020600020905b8154815290600101906020018083116109e957829003601f168201915b50505050508152602001906001019061095e565b505050509050965096509650965096509650965090919293949596565b60606004805480602002602001604051908101604052809291908181526020018280548015610a8957602002820191906000526020600020905b81546000191681526020019060010190808311610a71575b5050505050905090565b60606005805480602002602001604051908101604052809291908181526020016000905b82821015610b73578382906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b5f5780601f10610b3457610100808354040283529160200191610b5f565b820191906000526020600020905b815481529060010190602001808311610b4257829003601f168201915b505050505081526020019060010190610ab7565b50505050905090565b8060029080519060200190610b92929190610f3f565b507f87bcf8ab4f0f1f0a0cdd86e00e7bc2418f9b8a82d1ae40ccd29a370251a6af5281604051610bc29190611f51565b60405180910390a150565b8060069080519060200190610be3929190610fe5565b507f30fa3fd644c562cfa62382cd9a4d64e3b3e497d58a3f8f8be0ce0861a25bbe4f81604051610c139190611f95565b60405180910390a150565b8060009080519060200190610c34929190611045565b507f8d9e66367a9e22cdf1044c81464564e425156d766bc2d5adb50e9f1ab035df5281604051610c649190611ffb565b60405180910390a150565b60606003805480602002602001604051908101604052809291908181526020018280548015610cf357602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610ca9575b5050505050905090565b8060019080519060200190610d13929190611092565b507f4efc261d5065410276ec71f56518fa9f51632c821f1479790ef72c21e1fe08e381604051610d439190611fb7565b60405180910390a150565b7f40c1d6c63003c112efff0cf6150e03291cd8103b76a2c79e190f9b7b2c5fbcfd87878787878787604051610d89979695949392919061201d565b60405180910390a18660009080519060200190610da7929190611045565b508560019080519060200190610dbe929190611092565b508460029080519060200190610dd5929190610f3f565b508360039080519060200190610dec9291906110df565b508260049080519060200190610e03929190610e8c565b508160059080519060200190610e1a929190610edf565b508060069080519060200190610e31929190610fe5565b5050505050505050565b8060039080519060200190610e519291906110df565b507fadb9e49256352996d3689746012b00e0809ccbe17f15688f2e373314b797cc0481604051610e819190611f2f565b60405180910390a150565b828054828255906000526020600020908101928215610ece579160200282015b82811115610ecd578251829060001916905591602001919060010190610eac565b5b509050610edb9190611169565b5090565b828054828255906000526020600020908101928215610f2e579160200282015b82811115610f2d578251829080519060200190610f1d92919061118e565b5091602001919060010190610eff565b5b509050610f3b919061120e565b5090565b82805482825590600052602060002090601f01602090048101928215610fd45791602002820160005b83821115610fa557835183826101000a81548160ff0219169083151502179055509260200192600101602081600001049283019260010302610f68565b8015610fd25782816101000a81549060ff0219169055600101602081600001049283019260010302610fa5565b505b509050610fe1919061123a565b5090565b828054828255906000526020600020908101928215611034579160200282015b8281111561103357825182908051906020019061102392919061126a565b5091602001919060010190611005565b5b50905061104191906112ea565b5090565b828054828255906000526020600020908101928215611081579160200282015b82811115611080578251825591602001919060010190611065565b5b50905061108e9190611316565b5090565b8280548282559060005260206000209081019282156110ce579160200282015b828111156110cd5782518255916020019190600101906110b2565b5b5090506110db919061133b565b5090565b828054828255906000526020600020908101928215611158579160200282015b828111156111575782518260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550916020019190600101906110ff565b5b5090506111659190611360565b5090565b61118b91905b8082111561118757600081600090555060010161116f565b5090565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106111cf57805160ff19168380011785556111fd565b828001600101855582156111fd579182015b828111156111fc5782518255916020019190600101906111e1565b5b50905061120a9190611316565b5090565b61123791905b80821115611233576000818161122a91906113a3565b50600101611214565b5090565b90565b61126791905b8082111561126357600081816101000a81549060ff021916905550600101611240565b5090565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106112ab57805160ff19168380011785556112d9565b828001600101855582156112d9579182015b828111156112d85782518255916020019190600101906112bd565b5b5090506112e69190611316565b5090565b61131391905b8082111561130f576000818161130691906113eb565b506001016112f0565b5090565b90565b61133891905b8082111561133457600081600090555060010161131c565b5090565b90565b61135d91905b80821115611359576000816000905550600101611341565b5090565b90565b6113a091905b8082111561139c57600081816101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905550600101611366565b5090565b90565b50805460018160011615610100020316600290046000825580601f106113c957506113e8565b601f0160209004906000526020600020908101906113e79190611316565b5b50565b50805460018160011615610100020316600290046000825580601f106114115750611430565b601f01602090049060005260206000209081019061142f9190611316565b5b50565b600061143f82356123bd565b905092915050565b600082601f830112151561145a57600080fd5b813561146d611468826120ea565b6120bd565b9150818183526020840193506020810190508385602084028201111561149257600080fd5b60005b838110156114c257816114a88882611433565b845260208401935060208301925050600181019050611495565b5050505092915050565b600082601f83011215156114df57600080fd5b81356114f26114ed82612112565b6120bd565b9150818183526020840193506020810190508385602084028201111561151757600080fd5b60005b83811015611547578161152d88826117cc565b84526020840193506020830192505060018101905061151a565b5050505092915050565b600082601f830112151561156457600080fd5b81356115776115728261213a565b6120bd565b9150818183526020840193506020810190508385602084028201111561159c57600080fd5b60005b838110156115cc57816115b288826117e0565b84526020840193506020830192505060018101905061159f565b5050505092915050565b600082601f83011215156115e957600080fd5b81356115fc6115f782612162565b6120bd565b9150818183526020840193506020810190508360005b83811015611642578135860161162888826117f4565b845260208401935060208301925050600181019050611612565b5050505092915050565b600082601f830112151561165f57600080fd5b813561167261166d8261218a565b6120bd565b9150818183526020840193506020810190508385602084028201111561169757600080fd5b60005b838110156116c757816116ad888261184a565b84526020840193506020830192505060018101905061169a565b5050505092915050565b600082601f83011215156116e457600080fd5b81356116f76116f2826121b2565b6120bd565b9150818183526020840193506020810190508360005b8381101561173d5781358601611723888261185e565b84526020840193506020830192505060018101905061170d565b5050505092915050565b600082601f830112151561175a57600080fd5b813561176d611768826121da565b6120bd565b9150818183526020840193506020810190508385602084028201111561179257600080fd5b60005b838110156117c257816117a888826118b4565b845260208401935060208301925050600181019050611795565b5050505092915050565b60006117d882356123dd565b905092915050565b60006117ec82356123e9565b905092915050565b600082601f830112151561180757600080fd5b813561181a61181582612202565b6120bd565b9150808252602083016020830185838301111561183657600080fd5b611841838284612407565b50505092915050565b600061185682356123f3565b905092915050565b600082601f830112151561187157600080fd5b813561188461187f8261222e565b6120bd565b915080825260208301602083018583830111156118a057600080fd5b6118ab838284612407565b50505092915050565b60006118c082356123fd565b905092915050565b6000602082840312156118da57600080fd5b600082013567ffffffffffffffff8111156118f457600080fd5b61190084828501611447565b91505092915050565b60006020828403121561191b57600080fd5b600082013567ffffffffffffffff81111561193557600080fd5b611941848285016114cc565b91505092915050565b60006020828403121561195c57600080fd5b600082013567ffffffffffffffff81111561197657600080fd5b61198284828501611551565b91505092915050565b60006020828403121561199d57600080fd5b600082013567ffffffffffffffff8111156119b757600080fd5b6119c3848285016115d6565b91505092915050565b6000602082840312156119de57600080fd5b600082013567ffffffffffffffff8111156119f857600080fd5b611a048482850161164c565b91505092915050565b600060208284031215611a1f57600080fd5b600082013567ffffffffffffffff811115611a3957600080fd5b611a45848285016116d1565b91505092915050565b600060208284031215611a6057600080fd5b600082013567ffffffffffffffff811115611a7a57600080fd5b611a8684828501611747565b91505092915050565b600080600080600080600060e0888a031215611aaa57600080fd5b600088013567ffffffffffffffff811115611ac457600080fd5b611ad08a828b01611747565b975050602088013567ffffffffffffffff811115611aed57600080fd5b611af98a828b0161164c565b965050604088013567ffffffffffffffff811115611b1657600080fd5b611b228a828b016114cc565b955050606088013567ffffffffffffffff811115611b3f57600080fd5b611b4b8a828b01611447565b945050608088013567ffffffffffffffff811115611b6857600080fd5b611b748a828b01611551565b93505060a088013567ffffffffffffffff811115611b9157600080fd5b611b9d8a828b016116d1565b92505060c088013567ffffffffffffffff811115611bba57600080fd5b611bc68a828b016115d6565b91505092959891949750929550565b611bde81612373565b82525050565b6000611bef826122b5565b808452602084019350611c018361225a565b60005b82811015611c3357611c17868351611bd5565b611c2082612318565b9150602086019550600181019050611c04565b50849250505092915050565b6000611c4a826122c0565b808452602084019350611c5c83612267565b60005b82811015611c8e57611c72868351611e87565b611c7b82612325565b9150602086019550600181019050611c5f565b50849250505092915050565b6000611ca5826122cb565b808452602084019350611cb783612274565b60005b82811015611ce957611ccd868351611e96565b611cd682612332565b9150602086019550600181019050611cba565b50849250505092915050565b6000611d00826122d6565b80845260208401935083602082028501611d1985612281565b60005b84811015611d52578383038852611d34838351611ea5565b9250611d3f8261233f565b9150602088019750600181019050611d1c565b508196508694505050505092915050565b6000611d6e826122e1565b808452602084019350611d808361228e565b60005b82811015611db257611d96868351611edb565b611d9f8261234c565b9150602086019550600181019050611d83565b50849250505092915050565b6000611dc9826122ec565b80845260208401935083602082028501611de28561229b565b60005b84811015611e1b578383038852611dfd838351611eea565b9250611e0882612359565b9150602088019750600181019050611de5565b508196508694505050505092915050565b6000611e37826122f7565b808452602084019350611e49836122a8565b60005b82811015611e7b57611e5f868351611f20565b611e6882612366565b9150602086019550600181019050611e4c565b50849250505092915050565b611e9081612393565b82525050565b611e9f8161239f565b82525050565b6000611eb082612302565b808452611ec4816020860160208601612416565b611ecd81612449565b602085010191505092915050565b611ee4816123a9565b82525050565b6000611ef58261230d565b808452611f09816020860160208601612416565b611f1281612449565b602085010191505092915050565b611f29816123b3565b82525050565b60006020820190508181036000830152611f498184611be4565b905092915050565b60006020820190508181036000830152611f6b8184611c3f565b905092915050565b60006020820190508181036000830152611f8d8184611c9a565b905092915050565b60006020820190508181036000830152611faf8184611cf5565b905092915050565b60006020820190508181036000830152611fd18184611d63565b905092915050565b60006020820190508181036000830152611ff38184611dbe565b905092915050565b600060208201905081810360008301526120158184611e2c565b905092915050565b600060e0820190508181036000830152612037818a611e2c565b9050818103602083015261204b8189611d63565b9050818103604083015261205f8188611c3f565b905081810360608301526120738187611be4565b905081810360808301526120878186611c9a565b905081810360a083015261209b8185611dbe565b905081810360c08301526120af8184611cf5565b905098975050505050505050565b6000604051905081810181811067ffffffffffffffff821117156120e057600080fd5b8060405250919050565b600067ffffffffffffffff82111561210157600080fd5b602082029050602081019050919050565b600067ffffffffffffffff82111561212957600080fd5b602082029050602081019050919050565b600067ffffffffffffffff82111561215157600080fd5b602082029050602081019050919050565b600067ffffffffffffffff82111561217957600080fd5b602082029050602081019050919050565b600067ffffffffffffffff8211156121a157600080fd5b602082029050602081019050919050565b600067ffffffffffffffff8211156121c957600080fd5b602082029050602081019050919050565b600067ffffffffffffffff8211156121f157600080fd5b602082029050602081019050919050565b600067ffffffffffffffff82111561221957600080fd5b601f19601f8301169050602081019050919050565b600067ffffffffffffffff82111561224557600080fd5b601f19601f8301169050602081019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b600081519050919050565b600081519050919050565b600081519050919050565b600081519050919050565b600081519050919050565b600081519050919050565b600081519050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b6000819050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015612434578082015181840152602081019050612419565b83811115612443576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820bb3acf64a95d84a3d737356fef75537107d88592f839942a0e442ea7e80499c96c6578706572696d656e74616cf50037";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"getUint\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bytes32[]\"}],\"name\":\"setBS32\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBool\",\"outputs\":[{\"name\":\"\",\"type\":\"bool[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_s\",\"type\":\"string[]\"}],\"name\":\"setString\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBS\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getInt\",\"outputs\":[{\"name\":\"\",\"type\":\"int256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"},{\"name\":\"\",\"type\":\"int256[]\"},{\"name\":\"\",\"type\":\"bool[]\"},{\"name\":\"\",\"type\":\"address[]\"},{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"string[]\"},{\"name\":\"\",\"type\":\"bytes[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getBS32\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getString\",\"outputs\":[{\"name\":\"\",\"type\":\"string[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bool[]\"}],\"name\":\"setBool\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_b\",\"type\":\"bytes[]\"}],\"name\":\"setBS\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_u\",\"type\":\"uint256[]\"}],\"name\":\"setUint\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_i\",\"type\":\"int256[]\"}],\"name\":\"setInt\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_u\",\"type\":\"uint256[]\"},{\"name\":\"_i\",\"type\":\"int256[]\"},{\"name\":\"_b\",\"type\":\"bool[]\"},{\"name\":\"_addr\",\"type\":\"address[]\"},{\"name\":\"_bs32\",\"type\":\"bytes32[]\"},{\"name\":\"_s\",\"type\":\"string[]\"},{\"name\":\"_bs\",\"type\":\"bytes[]\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_addr\",\"type\":\"address[]\"}],\"name\":\"setAddr\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"u\",\"type\":\"uint256[]\"}],\"name\":\"EventUint\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"i\",\"type\":\"int256[]\"}],\"name\":\"EventInt\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_b\",\"type\":\"bool[]\"}],\"name\":\"EventBool\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"addr\",\"type\":\"address[]\"}],\"name\":\"EventAddr\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"b\",\"type\":\"bytes32[]\"}],\"name\":\"EventBS32\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"s\",\"type\":\"string[]\"}],\"name\":\"EventString\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"b\",\"type\":\"bytes[]\"}],\"name\":\"EventBS\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_u\",\"type\":\"uint256[]\"},{\"indexed\":false,\"name\":\"_i\",\"type\":\"int256[]\"},{\"indexed\":false,\"name\":\"_b\",\"type\":\"bool[]\"},{\"indexed\":false,\"name\":\"_addr\",\"type\":\"address[]\"},{\"indexed\":false,\"name\":\"_bs32\",\"type\":\"bytes32[]\"},{\"indexed\":false,\"name\":\"_s\",\"type\":\"string[]\"},{\"indexed\":false,\"name\":\"_bs\",\"type\":\"bytes[]\"}],\"name\":\"Event\",\"type\":\"event\"}]";

    public static final String FUNC_GETUINT = "getUint";

    public static final String FUNC_SETBS32 = "setBS32";

    public static final String FUNC_GETBOOL = "getBool";

    public static final String FUNC_SETSTRING = "setString";

    public static final String FUNC_GETBS = "getBS";

    public static final String FUNC_GETINT = "getInt";

    public static final String FUNC_GET = "get";

    public static final String FUNC_GETBS32 = "getBS32";

    public static final String FUNC_GETSTRING = "getString";

    public static final String FUNC_SETBOOL = "setBool";

    public static final String FUNC_SETBS = "setBS";

    public static final String FUNC_SETUINT = "setUint";

    public static final String FUNC_GETADDR = "getAddr";

    public static final String FUNC_SETINT = "setInt";

    public static final String FUNC_SET = "set";

    public static final String FUNC_SETADDR = "setAddr";

    public static final Event EVENTUINT_EVENT = new Event("EventUint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
    ;

    public static final Event EVENTINT_EVENT = new Event("EventInt", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Int256>>() {}));
    ;

    public static final Event EVENTBOOL_EVENT = new Event("EventBool", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bool>>() {}));
    ;

    public static final Event EVENTADDR_EVENT = new Event("EventAddr", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
    ;

    public static final Event EVENTBS32_EVENT = new Event("EventBS32", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
    ;

    public static final Event EVENTSTRING_EVENT = new Event("EventString", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
    ;

    public static final Event EVENTBS_EVENT = new Event("EventBS", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicBytes>>() {}));
    ;

    public static final Event EVENT_EVENT = new Event("Event", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Int256>>() {}, new TypeReference<DynamicArray<Bool>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<DynamicBytes>>() {}));
    ;

    @Deprecated
    protected EchoArray(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EchoArray(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EchoArray(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EchoArray(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<List> getUint() {
        final Function function = new Function(FUNC_GETUINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
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

    public RemoteCall<TransactionReceipt> setBS32(List<byte[]> _b) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBS32(List<byte[]> _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setBS32Seq(List<byte[]> _b) {
        final Function function = new Function(
                FUNC_SETBS32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<List> getBool() {
        final Function function = new Function(FUNC_GETBOOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bool>>() {}));
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
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setString(List<String> _s, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setStringSeq(List<String> _s) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<List> getBS() {
        final Function function = new Function(FUNC_GETBS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicBytes>>() {}));
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

    public RemoteCall<List> getInt() {
        final Function function = new Function(FUNC_GETINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Int256>>() {}));
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

    public RemoteCall<Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>>> get() {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Int256>>() {}, new TypeReference<DynamicArray<Bool>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<DynamicBytes>>() {}));
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
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
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
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
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
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBool(List<Boolean> _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBOOL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setBoolSeq(List<Boolean> _b) {
        final Function function = new Function(
                FUNC_SETBOOL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setBS(List<byte[]> _b) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setBS(List<byte[]> _b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setBSSeq(List<byte[]> _b) {
        final Function function = new Function(
                FUNC_SETBS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setUint(List<BigInteger> _u) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setUint(List<BigInteger> _u, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setUintSeq(List<BigInteger> _u) {
        final Function function = new Function(
                FUNC_SETUINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<List> getAddr() {
        final Function function = new Function(FUNC_GETADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
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
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setInt(List<BigInteger> _i, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setIntSeq(List<BigInteger> _i) {
        final Function function = new Function(
                FUNC_SETINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> set(List<BigInteger> _u, List<BigInteger> _i, List<Boolean> _b, List<String> _addr, List<byte[]> _bs32, List<String> _s, List<byte[]> _bs) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs32, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void set(List<BigInteger> _u, List<BigInteger> _i, List<Boolean> _b, List<String> _addr, List<byte[]> _bs32, List<String> _s, List<byte[]> _bs, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs32, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setSeq(List<BigInteger> _u, List<BigInteger> _i, List<Boolean> _b, List<String> _addr, List<byte[]> _bs32, List<String> _s, List<byte[]> _bs) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_u, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_i, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Bool>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_b, org.fisco.bcos.web3j.abi.datatypes.Bool.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs32, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Utf8String>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_s, org.fisco.bcos.web3j.abi.datatypes.Utf8String.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.DynamicBytes>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_bs, org.fisco.bcos.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> setAddr(List<String> _addr) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setAddr(List<String> _addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setAddrSeq(List<String> _addr) {
        final Function function = new Function(
                FUNC_SETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_addr, org.fisco.bcos.web3j.abi.datatypes.Address.class))), 
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

    public List<EventBS32EventResponse> getEventBS32Events(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTBS32_EVENT, transactionReceipt);
        ArrayList<EventBS32EventResponse> responses = new ArrayList<EventBS32EventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventBS32EventResponse typedResponse = new EventBS32EventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.b = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventBS32EventResponse> eventBS32EventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, EventBS32EventResponse>() {
            @Override
            public EventBS32EventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTBS32_EVENT, log);
                EventBS32EventResponse typedResponse = new EventBS32EventResponse();
                typedResponse.log = log;
                typedResponse.b = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventBS32EventResponse> eventBS32EventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTBS32_EVENT));
        return eventBS32EventFlowable(filter);
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
    public static EchoArray load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EchoArray(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EchoArray load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EchoArray(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EchoArray load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EchoArray(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EchoArray load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EchoArray(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EchoArray> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EchoArray.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EchoArray> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EchoArray.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EchoArray> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EchoArray.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EchoArray> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EchoArray.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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

    public static class EventBS32EventResponse {
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
