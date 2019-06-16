pragma solidity ^0.4.24;
pragma experimental ABIEncoderV2;

contract EchoArray3 {
 /*uint[3]*/
    uint256[3] private u3;
    event EventUint(uint256[3] u);
    function setUint(uint256[3] _u) public returns(uint256[3]) {
        u3 = _u;
        emit EventUint(_u);
        return u3;
    }
    function getUint() public constant returns(uint256[3]) {
        return u3;
    }
    
    /*int[3]*/
    int256[3] private i3;
    event EventInt(int256[3] i);
    function setInt(int256[3] _i) public returns(int256[3]) {
        i3 = _i;
        emit EventInt(_i);
        return i3;
    }
    function getInt() public constant returns(int256[3]) {
        return i3;
    }

    /*bool[3]*/
    bool[3] private b3;
    event EventBool(bool[3] _b);
    function setBool(bool[3] _b) public returns(bool[3]) {
        b3 = _b;
        emit EventBool(_b);
        return b3;
    }
    function getBool() public constant returns(bool[3]) {
        return b3;
    }
    
    /*address[3]*/
    address[3] private addr3;
    event EventAddr(address[3] addr);
    function setAddr(address[3] _addr) public returns(address[3])  {
        addr3 = _addr;
        emit EventAddr(_addr);
        return addr3;
    }
    function getAddr() public constant returns(address[3]) {
        return addr3;
    }
    
    /*bytes32[3]*/
    bytes32[3] private bs323;
    event EventB32(bytes32[3] b);
    function setBS32(bytes32[3] _b) public returns(bytes32[3])  {
        bs323 = _b;
        emit EventB32(_b);
        return bs323;
    }
    function getBS32() public constant returns(bytes32[3]) {
        return bs323;
    }
    
    /*string[3]*/
    string[3] private s3;
    event EventString(string[3] s);
    function setString(string[3] _s) public returns(string[3]) {
        s3 = _s;
        emit EventString(_s);
        return s3;
    }
    function getString() public constant returns(string[3]) {
        return s3;
    }
    
    /*bytes[3]*/
    bytes[3] private bs3;
    event EventBS(bytes[3] b);
    function setBS(bytes[3] _b) public  returns(bytes[3]) {
        bs3 = _b;
        emit EventBS(_b);
        return bs3;
    }
    function getBS() public constant returns(bytes[3]) {
        return bs3;
    }

    /* uint[3] ,int[3] ,bool[3] , address[3] ,bytes32[3] ,string[3] ,bytes[3] */
    event Event(uint[3] _u,int[3] _i,bool[3] _b, address[3] _addr,bytes32[3] _bs32,string[3] _s,bytes[3] _bs);
    function set(uint[3] _u,int[3] _i,bool[3] _b, address[3] _addr,bytes32[3] _bs32,string[3] _s,bytes[3] _bs) public returns (uint[3],int[3],bool[3],address[3],bytes32[3],string[3],bytes[3]) {
        emit Event(_u,_i,_b,_addr,_bs32,_s,_bs);
        u3 = _u;
        i3 = _i;
        b3 = _b;
        addr3 = _addr;
        bs323 = _bs32;
        s3 = _s;
        bs3 = _bs;
        return (u3,i3,b3,addr3,bs323,s3,bs3);
    }
    function get() public constant returns (uint[3],int[3],bool[3],address[3],bytes32[3],string[3],bytes[3]) {
        return (u3,i3,b3,addr3,bs323,s3,bs3);
    }
}
