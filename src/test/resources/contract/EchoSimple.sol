pragma solidity ^0.4.24;
pragma experimental ABIEncoderV2;

contract EchoSimple {
    /*uint*/
    uint256 private u;
    event EventUint(uint256 u);
    function setUint(uint256 _u) public returns(uint256) {
        u = _u;
        emit EventUint(_u);
        return u;
    }
    function getUint() constant public returns(uint256) {
        return u;
    }
    
    /*int*/
    int256 private i;
    event EventInt(int256 i);
    function setInt(int256 _i) public returns(int256) {
        i = _i;
        emit EventInt(_i);
        return i;
    }
    function getInt() constant public returns(int256) {
        return i;
    }

    /*bool*/
    bool private b;
    event EventBool(bool _b);
    function setBool(bool _b) public returns(bool) {
        b = _b;
        emit EventBool(_b);
        return b;
    }
    function getBool() constant public returns(bool) {
        return b;
    }
    
    /*address*/
    address private addr;
    event EventAddr(address addr);
    function setAddr(address _addr) public returns(address) {
        addr = _addr;
        emit EventAddr(addr);
        return addr;
    }
    function getAddr() constant public returns(address) {
        return addr;
    }
    
    /*bytes32*/
    bytes32 private bs32;
    event EventB32(bytes32 b);
    function setBS32(bytes32 _b) public returns(bytes32) {
        bs32 = _b;
        emit EventB32(_b);
        return bs32;
    }
    function getBS32() public constant returns(bytes32) {
        return bs32;
    }
    
    /*string*/
    string private s;
    event EventString(string s);
    function setString(string _s) public returns(string) {
        s = _s;
        emit EventString(_s);
        return s;
    }
    function getString() public constant returns(string) {
        return s;
    }
    
    /*bytes*/
    bytes private bs;
    event EventBS(bytes b);
    function setBS(bytes _b) public returns(bytes) {
        bs = _b;
        emit EventBS(_b);
        return bs;
    }
    function getBS() public constant returns(bytes) {
        return bs;
    }

    /* uint ,int ,bool , address ,bytes32 ,string ,bytes */
    event Event(uint _u,int _i,bool _b, address _addr,bytes32 _bs32,string _s,bytes _bs) ;
    function set(uint _u,int _i,bool _b, address _addr,bytes32 _bs32,string _s,bytes _bs) public returns(uint,int,bool,address,bytes32,string,bytes) {
        emit Event(_u,_i,_b,_addr,_bs32,_s,_bs);
        u = _u;
        i = _i;
        b = _b;
        addr = _addr;
        bs32 = _bs32;
        s = _s;
        bs = _bs;
        return (u,i,b,addr,bs32,s,bs);
    }
    function get() public constant returns(uint,int,bool,address,bytes32,string,bytes) {
        return (u,i,b,addr,bs32,s,bs);
    }
}