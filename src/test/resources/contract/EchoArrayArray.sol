pragma solidity ^0.4.24;
pragma experimental ABIEncoderV2;

contract EchoArrayArray {

    /*uint[][]*/
    uint256[][] private udd;
    event EventUint(uint256[][] u);
    function setUint(uint256[][] _u) public returns(uint256[][]) {
        udd = _u;
        emit EventUint(_u);
        return udd;
    }
    function getUint() public constant returns(uint256[][]) {
        return udd;
    }
    
    /*int[][]*/
    int256[][] private idd;
    event EventInt(int256[][] i);
    function setInt(int256[][] _i) public returns(int256[][]) {
        idd = _i;
        emit EventInt(_i);
        return idd;
    }
    function getInt() public constant returns(int256[][]) {
        return idd;
    }

    /*bool[][]*/
    bool[][] private bdd;
    event EventBool(bool[][] b);
    function setBool(bool[][] _b) public returns(bool[][]) {
        bdd = _b;
        emit EventBool(_b);
        return bdd;
    }
    function getBool() public constant returns(bool[][]) {
        return bdd;
    }
    
    /*address[][]*/
    address[][] private addrdd;
    event EventAddr(address[][] addr);
    function setAddr(address[][] _addr) public returns(address[][]) {
        addrdd = _addr;
        emit EventAddr(_addr);
        return addrdd;
    }
    function getAddr() public  constant returns(address[][]) {
        return addrdd;
    }
    
    /*bytes32[][]*/
    bytes32[][] private bs32dd;
    event EventB32(bytes32[][] b);
    function setBS32(bytes32[][] _b) public returns(bytes32[][]) {
        bs32dd = _b;
        emit EventB32(_b);
        return bs32dd;
    }
    function getBS32() public constant returns(bytes32[][]) {
        return bs32dd;
    }
    
    /*string[][]*/
    string[][] private sdd;
    event EventString(string[][] s);
    function setString(string[][] _s) public returns(string[][]) {
        sdd = _s;
        emit EventString(_s);
        return sdd;
    }
    function getString() public constant returns(string[][]) {
        return sdd;
    }
    
    /*bytes[][]*/
    bytes[][] private bsdd;
    event EventBS(bytes[][] b);
    function setBS(bytes[][] _b) public returns(bytes[][]) {
        bsdd = _b;
        emit EventBS(_b);
        return bsdd;
    } 
    function getBS() public constant returns(bytes[][]) {
        return bsdd;
    } 

    /* uint[][] ,int[][] ,bool[][] , address[][] ,bytes32[][] ,string[][] ,bytes[][] */
    event Event(uint[][] _u,int[][] _i,bool[][] _b, address[][] _addr,bytes32[][] _bs32,string[][] _s,bytes[][] _bs);
    function set(uint[][] _u,int[][] _i,bool[][] _b, address[][] _addr,bytes32[][] _bs32,string[][] _s,bytes[][] _bs) public returns (uint[][],int[][],bool[][],address[][],bytes32[][],string[][],bytes[][]) {
        emit Event(_u,_i,_b,_addr,_bs32,_s,_bs);
        udd = _u;
        idd = _i;
        bdd = _b;
        addrdd = _addr;
        bs32dd = _bs32;
        sdd = _s;
        bsdd = _bs;
        return (udd,idd,bdd,addrdd,bs32dd,sdd,bsdd);
    }

    function get() public constant  returns (uint[][],int[][],bool[][],address[][],bytes32[][],string[][],bytes[][]) {
        return (udd,idd,bdd,addrdd,bs32dd,sdd,bsdd);
    }
}