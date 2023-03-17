package org.hyperledger.fabric.samples.fabnft;
import java.util.ArrayList;
import java.util.List;

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
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import com.owlike.genson.Genson;
@Contract(
        name = "FabNFT",
        info = @Info(
                title = "FabNFT contract",
                description = "The hyperlegendary NFT contract",
                version = "0.0.1-SNAPSHOT",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "NFT@example.com",
                        name = "F NFT",
                        url = "https://hyperledger.example.com")))
@Default
public class FabNFT implements ContractInterface{
    private final Genson genson = new Genson();
    private enum FabNFTErrors {
        NFT_NOT_FOUND,
        NFT_ALREADY_EXISTS
    }
    @Transaction()
    public NFT queryNFT(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String NFTState = stub.getStringState(key);
        if (NFTState.isEmpty()) {
            String errorMessage = String.format("NFT %s does not exist", key);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, FabNFTErrors.NFT_NOT_FOUND.toString());
        }
        NFT NFT = genson.deserialize(NFTState, NFT.class);
        return NFT;
    }
    @Transaction()
    public void initLeger(final Context ctx) {
        System.out.println("============= START : Initialize Ledger ===========");
        ChaincodeStub stub = ctx.getStub();
        List<NFT> NFTs = new ArrayList<NFT>();
        NFTs.add(new NFT("1","Jason","123","NTF1","first NFT","2023/3/8","20","123"));
        NFTs.add(new NFT("2","Jason","123","NTF2","second NFT","2023/3/8","20","123"));
        NFTs.add(new NFT("3","Jason","123","NTF3","third NFT","2023/3/8","20","123"));
        for (NFT NFT : NFTs) {
            String key = String.format("NFT%s", NFT.getId());
            String NFTState = genson.serialize(NFT);
            stub.putStringState(key, NFTState);
        }
    }
    @Transaction()
    public NFT createNFT(final Context ctx, final String key,final String id, final String owner, final String ownerID, final String NFTID, final String NFTName, final String NFTDate, final String NFTPrice, final String NFTHash) {
        ChaincodeStub stub = ctx.getStub();
        String NFTState = stub.getStringState(key);
        if (!NFTState.isEmpty()) {
            String errorMessage = String.format("NFT %s already exists", key);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, FabNFTErrors.NFT_ALREADY_EXISTS.toString());
        }
        NFT NFT = new NFT(id, owner, ownerID, NFTID, NFTName, NFTDate, NFTPrice, NFTHash);
        NFTState = genson.serialize(NFT);
        stub.putStringState(key, NFTState);
        return NFT;
    }
    @Transaction
    public String queryAllNFTs(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        String startKey = "NFT0";
        String endKey = "NFT999";
        List<NFTQueryResult> queryResults = new ArrayList<NFTQueryResult>();
        QueryResultsIterator<KeyValue> results = stub.getStateByRange(startKey, endKey);
        for (KeyValue result: results) {
            NFT NFT = genson.deserialize(result.getStringValue(), NFT.class);
            queryResults.add(new NFTQueryResult(result.getKey(), NFT));
        }
        return genson.serialize(queryResults);
    }
    @Transaction()
    public NFT changeNFTOwner(final Context ctx, final String key, final String newOwner) {
        ChaincodeStub stub = ctx.getStub();
        String NFTState = stub.getStringState(key);
        if (NFTState.isEmpty()) {
            String errorMessage = String.format("NFT %s does not exist", key);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, FabNFTErrors.NFT_NOT_FOUND.toString());
        }
        NFT NFT = genson.deserialize(NFTState, NFT.class);
        NFT newNFT = new NFT(NFT.getId(), newOwner, NFT.getFileFinger(),NFT.getName(),NFT.getDescription(),NFT.getDate(),NFT.getPrice(),NFT.getIPFSHash());
        String newNFTState = genson.serialize(newNFT);
        stub.putStringState(key, newNFTState);
        return newNFT;
    }

}
