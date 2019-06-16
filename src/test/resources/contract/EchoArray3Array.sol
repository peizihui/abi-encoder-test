pragma solidity ^0.4.24;
pragma experimental ABIEncoderV2;

contract EchoArray3Array {
/*uint[3][]*/
    uint256[3][] private u3d;
    event EventUint(uint256[3][] u);
    function setUint(uint256[3][] _u) public returns(uint256[3][]) {
        u3d = _u;
        emit EventUint(_u);
        return u3d;
    }
    function getUint() constant public returns(uint256[3][]) {
        return u3d;
    }
    
    /*int[3][]*/
    int256[3][] private i3d;
    event EventInt(int256[3][] i);
    function setInt(int256[3][] _i) public returns(int256[3][]) {
        i3d = _i;
        emit EventInt(_i);
        return i3d;
    }
    function getInt() constant public returns(int256[3][]) {
        return i3d;
    }

    /*bool[3][]*/
    bool[3][] private b3d;
    event EventBool(bool[3][] b);
    function setBool(bool[3][] _b) public returns(bool[3][]) {
        b3d = _b;
        emit EventBool(_b);
        return b3d;
    }
    function getBool() public constant returns(bool[3][]) {
        return b3d;
    }
    
    /*address[3][]*/
    address[3][] private addr3d;
    event EventAddr(address[3][] addr);
    function setAddr(address[3][] _addr) public returns(address[3][]) {
        addr3d = _addr;
        emit EventAddr(_addr);
        return addr3d;
    }
    function getAddr() public constant returns(address[3][]) {
        return addr3d;
    }
    
    /*bytes32[3][]*/
    bytes32[3][] private bs323d;
    event EventB32(bytes32[3][] b);
    function setBS32(bytes32[3][] _b) public returns(bytes32[3][]) {
        bs323d = _b;
        emit EventB32(_b);
        return bs323d;
    }
    function getBS32() constant public returns(bytes32[3][]) {
        return bs323d;
    }
    
    /*string[3][]*/
    string[3][] private s3d;
    event EventString(string[3][] s);
    function setString(string[3][] _s) public returns(string[3][]) {
        s3d = _s;
        emit EventString(_s);
        return s3d;
    }
    function getString() public constant returns(string[3][]) {
        return s3d;
    }
    
    /*bytes[3][]*/
    bytes[3][] private bs3d;
    event EventBS(bytes[3][] b);
    function setBS(bytes[3][] _b) public returns(bytes[3][]) {
        bs3d = _b;
        emit EventBS(_b);
        return bs3d;
    }
    function getBS() public constant returns(bytes[3][]) {
        return bs3d;
    }

    /* uint[3][] ,int[3][] ,bool[3][] , address[3][] ,bytes32[3][] ,string[3][] ,bytes[3][] */
    event Event(uint[3][] _u,int[3][] _i,bool[3][] _b, address[3][] _addr,bytes32[3][] _bs32,string[3][] _s,bytes[3][] _bs);
    function set(uint[3][] _u,int[3][] _i,bool[3][] _b, address[3][] _addr,bytes32[3][] _bs32,string[3][] _s,bytes[3][] _bs) public returns (uint[3][],int[3][],bool[3][],address[3][],bytes32[3][],string[3][],bytes[3][]) {
        emit Event(_u,_i,_b,_addr,_bs32,_s,_bs);
        u3d = _u;
        i3d = _i;
        b3d = _b;
        addr3d = _addr;
        bs323d = _bs32;
        s3d = _s;
        bs3d = _bs;
        return (u3d,i3d,b3d,addr3d,bs323d,s3d,bs3d);
    }

    function get() public constant returns (uint[3][],int[3][],bool[3][],address[3][],bytes32[3][],string[3][],bytes[3][]) {
        return (u3d,i3d,b3d,addr3d,bs323d,s3d,bs3d);
    }
     
}
