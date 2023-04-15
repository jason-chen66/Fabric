package org.hyperledger.fabric.samples.fabnft;

import org.hyperledger.fabric.samples.fabnft.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;

import java.util.List;
import java.util.stream.Collectors;

public class NFTList {
    private StateList stateList;

    public NFTList(Context ctx) {
        this.stateList = StateList.getStateList(ctx, NFTList.class.getSimpleName(), NFT::deserialize);
    }

    public NFTList addNFT(NFT nft) {
        stateList.addState(nft);
        return this;
    }

    public NFT getNFT(String nftKey) {
        return (NFT) this.stateList.getState(nftKey);
    }

    public List<NFT> getAllNFTs(String startKey, String endKey) {
        return this.stateList.getAllStates(startKey, endKey).stream().map(nft -> (NFT) nft).collect(Collectors.toList());
    }

    public NFTList updateNFT(NFT nft) {
        this.stateList.updateState(nft);
        return this;
    }
}
