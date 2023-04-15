package org.hyperledger.fabric.samples.fabnft;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;

public class NFTContext extends Context {

    public NFTContext(ChaincodeStub stub) {
        super(stub);
        this.nftList = new NFTList(this);
    }

    public NFTList nftList;
}
