pragma solidity ^0.4.24;
pragma experimental ABIEncoderV2;

contract EchoArray {
/*uint[]*/
    uint256[] private ud;
    event EventUint(uint256[] u);
    function setUint(uint256[] _u) public {
        ud = _u;
        emit EventUint(_u);
    }
    function getUint() public constant returns(uint256[]) {
        return ud;
    }
    
    /*int[]*/
    int256[] private id;
    event EventInt(int256[] i);
    function setInt(int256[] _i) public {
        id = _i;
        emit EventInt(_i);
    }
    function getInt() public constant returns(int256[]) {
        return id;
    }

    /*bool[]*/
    bool[] private bd;
    event EventBool(bool[] _b);
    function setBool(bool[] _b) public {
        bd = _b;
        emit EventBool(_b);
    }
    function getBool() public constant returns(bool[]) {
        return bd;
    }
    
    /*address[]*/
    address[] private addrd;
    event EventAddr(address[] addr);
    function setAddr(address[] _addr) public {
        addrd = _addr;
        emit EventAddr(_addr);
    }
    function getAddr() constant public returns(address[]) {
        return addrd;
    }
    
    /*bytes32[]*/
    bytes32[] private bs32d;
    event EventBS32(bytes32[] b);
    function setBS32(bytes32[] _b) public {
        bs32d = _b;
        emit EventBS32(_b);
    }
    function getBS32() public constant returns(bytes32[]) {
        return bs32d;
    }
    
    /*string[]*/
    string[] private sd;
    event EventString(string[] s);
    function setString(string[] _s) public {
        sd = _s;
        emit EventString(_s);
    }
    function getString() public constant returns(string[]) {
        return sd;
    }
    
    /*bytes[]*/
    bytes[] private bsd;
    event EventBS(bytes[] b);
    function setBS(bytes[] _b) public {
        bsd = _b;
        emit EventBS(_b);
    }
    function getBS() public constant returns(bytes[]) {
        return bsd;
    }

    /* uint[] ,int[] ,bool[] , address[] ,bytes32[] ,string[] ,bytes[] */
    event Event(uint[] _u,int[] _i,bool[] _b, address[] _addr,bytes32[] _bs32,string[] _s,bytes[] _bs);
    function set(uint[] _u,int[] _i,bool[] _b, address[] _addr,bytes32[] _bs32,string[] _s,bytes[] _bs) public {
        emit Event(_u,_i,_b,_addr,_bs32,_s,_bs);
        ud = _u;
        id = _i;
        bd = _b;
        addrd = _addr;
        bs32d = _bs32;
        sd = _s;
        bsd = _bs;
    }
    function get() public constant returns (uint[],int[],bool[],address[],bytes32[],string[],bytes[]) {
        return (ud,id,bd,addrd,bs32d,sd,bsd);
    }
}
