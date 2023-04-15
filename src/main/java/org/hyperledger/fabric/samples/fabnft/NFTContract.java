package org.hyperledger.fabric.samples.fabnft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.hyperledger.fabric.samples.fabnft.ledgerapi.State;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;

@Contract(name = "org.nftnet.nft", info = @Info(title = "MyAsset contract", description = "", version = "0.0.1", license = @License(name = "SPDX-License-Identifier: Apache-2.0", url = ""), contact = @Contact(email = "java-contract@example.com", name = "java-contract", url = "http://java-contract.me")))
@Default
public class NFTContract implements ContractInterface {
    private final static Logger LOG = Logger.getLogger(NFTContract.class.getName());

    @Override
    public Context createContext(ChaincodeStub stub) {
        return new NFTContext(stub);
    }

    public NFTContract() {
    }

    @Transaction()
    public void instantiate(NFTContext ctx) {
        LOG.info("Instantiate the contract");
    }

    @Transaction()
    public NFT publish(NFTContext ctx, String publisher, String nftNumber, String publishDateTime, String owners, String name, String description, String fileFinger, String IPFSHash, String watermark) {
        System.out.println(ctx);

        if (queryNFT(ctx, nftNumber) != null) {
            throw new ChaincodeException("NFT " + nftNumber + " already exists");
        }

        List<String> ownerList = new ArrayList<String>();
        Arrays.stream(owners.split(",")).forEach(owner -> ownerList.add(owner));

        NFT nft = NFT.createInstance(publisher, nftNumber, publishDateTime, ownerList, ownerList, name, description, fileFinger, IPFSHash, watermark);

        System.out.println(nft);

        ctx.nftList.addNFT(nft);

        return nft;
    }

    @Transaction()
    public NFT transferOwnership(NFTContext ctx, String nftNumber, String currentOwner, String newOwner) {
        String nftKey = State.makeKey(new String[] { nftNumber });
        NFT nft = ctx.nftList.getNFT(nftKey);

        if (queryNFT(ctx, nftNumber) == null) {
            throw new ChaincodeException("NFT " + nftNumber + " does not exist on blockchain");
        }

        if (!nft.getOwners().contains(currentOwner)) {
            throw new ChaincodeException("NFT " + nftNumber + " is not owned by " + currentOwner);
        }

        List<String> owners = nft.getOwners();
        owners.remove(currentOwner);
        owners.add(newOwner);
        nft.setOwners(owners);

        ctx.nftList.updateNFT(nft);
        return nft;
    }

    @Transaction()
    public NFT grantAccess(NFTContext ctx, String nftNumber, String currentOwner, String newAccessUser) {
        String nftKey = State.makeKey(new String[] { nftNumber });
        NFT nft = ctx.nftList.getNFT(nftKey);

        if (queryNFT(ctx, nftNumber) == null) {
            throw new ChaincodeException("NFT " + nftNumber + " does not exist on blockchain");
        }

        if (!nft.getOwners().contains(currentOwner)) {
            throw new ChaincodeException("NFT " + nftNumber + " is not owned by " + currentOwner);
        }

        if (nft.getUsers().contains(newAccessUser)) {
            throw new ChaincodeException("NFT " + nftNumber + " already has access for " + newAccessUser);
        }

        List<String> users = nft.getUsers();
        users.add(newAccessUser);
        nft.setUsers(users);

        ctx.nftList.updateNFT(nft);
        return nft;
    }

    @Transaction()
    public NFT revokeAccess(NFTContext ctx, String nftNumber, String currentOwner, String revokeAccessUser) {
        String nftKey = State.makeKey(new String[] { nftNumber });
        NFT nft = ctx.nftList.getNFT(nftKey);

        if (queryNFT(ctx, nftNumber) == null) {
            throw new ChaincodeException("NFT " + nftNumber + " does not exist on blockchain");
        }

        if (!nft.getOwners().contains(currentOwner)) {
            throw new ChaincodeException("NFT " + nftNumber + " is not owned by " + currentOwner);
        }

        if (!nft.getUsers().contains(revokeAccessUser)) {
            throw new ChaincodeException("NFT " + nftNumber + " does not have access for " + revokeAccessUser);
        }

        List<String> users = nft.getUsers();
        users.remove(revokeAccessUser);
        nft.setUsers(users);

        ctx.nftList.updateNFT(nft);
        return nft;
    }

    @Transaction()
    public NFT queryNFT(NFTContext ctx, String nftNumber) {
        String nftKey = State.makeKey(new String[] { nftNumber });
        NFT nft = ctx.nftList.getNFT(nftKey);

        return nft;
    }
}
