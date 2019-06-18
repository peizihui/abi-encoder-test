package org.fisco.bcos.tx.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
import org.fisco.bcos.web3j.abi.datatypes.DynamicBytes;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;

public class RetType {
	
    @SuppressWarnings("unchecked")
    protected static <S extends Type, T> List<T> convertToNative(List<S> arr) {
        List<T> out = new ArrayList<T>();
        for (Iterator<S> it = arr.iterator(); it.hasNext(); ) {
            out.add((T) it.next().getValue());
        }
        return out;
    }
	
	public static void testArray() {
		

        List<Type> params =
                Arrays.asList(
                        new DynamicArray<Uint256>(
                                new Uint256(11111), new Uint256(22222), new Uint256(33333)),
                        new DynamicArray<Int256>(
                                new Int256(-1111111), new Int256(-3333333), new Int256(-2222222)),
                        new DynamicArray<Bool>(new Bool(false), new Bool(true), new Bool(false)),
                        new DynamicArray<Address>(
                                new Address("0x692a70d2e424a56d2c6c27aa97d1a86395877b3a"),
                                new Address("0x692a70d2e424a56d2c6c27aa97d1a86395877b3a")),
                        new DynamicArray<Bytes32>(
                                new Bytes32("abcdefghiabcdefghiabcdefghiabhji".getBytes()),
                                new Bytes32("abcdefghiabcdefghiabcdefghiabhji".getBytes())),
                        new DynamicArray<Utf8String>(
                                new Utf8String(""),
                                new Utf8String("章鱼小丸子ljjkl;adjsfkljlkjl"),
                                new Utf8String("章鱼小丸子ljjkl;adjsfkljlkjl")),
                        new DynamicArray<DynamicBytes>(
                                new DynamicBytes("".getBytes()),
                                new DynamicBytes("sadfljkjkljkl".getBytes()),
                                new DynamicBytes("章鱼小丸子ljjkl;adjsfkljlkjl".getBytes())));
        

        final Function function = new Function("get", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Int256>>() {}, new TypeReference<DynamicArray<Bool>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<DynamicBytes>>() {}));
    
        
        String output = FunctionEncoder.encodeConstructor(params);
        
        List<Type> results = FunctionReturnDecoder.decode(output, function.getOutputParameters());
        
            
        Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>> typeResult = new Tuple7<List<BigInteger>, List<BigInteger>, List<Boolean>, List<String>, List<byte[]>, List<String>, List<byte[]>>(
	            convertToNative((List<Uint256>) results.get(0).getValue()), 
	            convertToNative((List<Int256>) results.get(1).getValue()), 
	            convertToNative((List<Bool>) results.get(2).getValue()), 
	            convertToNative((List<Address>) results.get(3).getValue()), 
	            convertToNative((List<Bytes32>) results.get(4).getValue()), 
	            convertToNative((List<Utf8String>) results.get(5).getValue()), 
	            convertToNative((List<DynamicBytes>) results.get(6).getValue()));
        
        
        System.out.println(typeResult);
	
	}
	
public static void testArrayArray() {
		

        List<Type> params =
                Arrays.asList(
                		new DynamicArray<DynamicArray<Uint256>>(
                                new DynamicArray<Uint256>(new Uint256(1), new Uint256(2)),
                                new DynamicArray<Uint256>(new Uint256(3))),
                		new DynamicArray<DynamicArray<Int256>>(
                                new DynamicArray<Int256>(new Int256(1), new Int256(2)),
                                new DynamicArray<Int256>(new Int256(3))),
                		new DynamicArray<DynamicArray<Bool>>(
                                new DynamicArray<Bool>(new Bool(false), new Bool(true)),
                                new DynamicArray<Bool>(new Bool(true))),
                		new DynamicArray<DynamicArray<Address>>(
                                new DynamicArray<Address>(new Address("0x0"), new Address("0x0")),
                                new DynamicArray<Address>(new Address("0x0"))),
                		new DynamicArray<DynamicArray<Bytes32>>(
                                new DynamicArray<Bytes32>(new Bytes32("01234567890123456789012345678901".getBytes()), new Bytes32("01234567890123456789012345678901".getBytes())),
                                new DynamicArray<Bytes32>(new Bytes32("01234567890123456789012345678901".getBytes()))),
                		new DynamicArray<DynamicArray<Utf8String>>(
                                new DynamicArray<Utf8String>(new Utf8String("0x0"), new Utf8String("0x0")),
                                new DynamicArray<Utf8String>(new Utf8String("0x0"))),
                        new DynamicArray<DynamicArray<DynamicBytes>>(
                                new DynamicArray<DynamicBytes>(new DynamicBytes("01234567890123456789012345678901".getBytes()), new DynamicBytes("01234567890123456789012345678901".getBytes())),
                                new DynamicArray<DynamicBytes>(new DynamicBytes("01234567890123456789012345678901".getBytes()))));
        
        final Function function = new Function("get", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}, new TypeReference<DynamicArray<DynamicArray<Int256>>>() {}, new TypeReference<DynamicArray<DynamicArray<Bool>>>() {}, new TypeReference<DynamicArray<DynamicArray<Address>>>() {}, new TypeReference<DynamicArray<DynamicArray<Bytes32>>>() {}, new TypeReference<DynamicArray<DynamicArray<Utf8String>>>() {}, new TypeReference<DynamicArray<DynamicArray<DynamicBytes>>>() {}));
        
        
        String output = FunctionEncoder.encodeConstructor(params);
        
        List<Type> results = FunctionReturnDecoder.decode(FunctionEncoder.encodeConstructor(params), function.getOutputParameters());
     
        
        Tuple7<List<List<BigInteger>>, List<List<BigInteger>>, List<List<Boolean>>, List<List<String>>, List<List<byte[]>>, List<List<String>>, List<List<byte[]>>> tupleResult = new Tuple7<List<List<BigInteger>>, List<List<BigInteger>>, List<List<Boolean>>, List<List<String>>, List<List<byte[]>>, List<List<String>>, List<List<byte[]>>>(
                convertToNative((List<DynamicArray<Uint256>>) results.get(0).getValue()), 
                convertToNative((List<DynamicArray<Int256>>) results.get(1).getValue()), 
                convertToNative((List<DynamicArray<Bool>>) results.get(2).getValue()), 
                convertToNative((List<DynamicArray<Address>>) results.get(3).getValue()), 
                convertToNative((List<DynamicArray<Bytes32>>) results.get(4).getValue()), 
                convertToNative((List<DynamicArray<Utf8String>>) results.get(5).getValue()), 
                convertToNative((List<DynamicArray<DynamicBytes>>) results.get(6).getValue()));
        
        
        System.out.println(tupleResult);
	
	}
}
