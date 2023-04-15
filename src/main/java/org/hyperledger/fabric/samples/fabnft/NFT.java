package org.hyperledger.fabric.samples.fabnft;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.hyperledger.fabric.samples.fabnft.ledgerapi.State;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

@DataType()
public class NFT extends State {

    @Property()
    private String state = "";

    public String getState() {
        return state;
    }

    public NFT setState(String state) {
        this.state = state;
        return this;
    }

    @Property()
    private String nftNumber;

    @Property()
    private String publisher;

    @Property()
    private String publishDateTime;

    @Property()
    private List<String> owners;

    @Property()
    private List<String> users;

    @Property()
    private String name;

    @Property()
    private String description;

    @Property()
    private String fileFinger;

    @Property()
    private String IPFSHash;

    @Property()
    private String watermark;

    public NFT() { super(); }

    public List<String> getOwners() { return owners; }

    public NFT setOwners(List<String> owners) {
        this.owners = owners;
        return this;
    }

    public List<String> getUsers() { return users; }

    public NFT setUsers(List<String> users) {
        this.users = users;
        return this;
    }

    public NFT setKey() {
        this.key = State.makeKey(new String[] { this.nftNumber });
        return this;
    }

    public String getNftNumber() { return nftNumber; }

    public NFT setNftNumber(String nftNumber) {
        this.nftNumber = nftNumber;
        return this;
    }

    public String getPublisher() { return publisher; }

    public NFT setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getPublishDateTime() { return publishDateTime; }

    public NFT setPublishDateTime(String publishDateTime) {
        this.publishDateTime = publishDateTime;
        return this;
    }

    public String getName() { return name; }

    public NFT setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() { return description; }

    public NFT setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFileFinger() { return fileFinger; }

    public NFT setFileFinger(String fileFinger) {
        this.fileFinger = fileFinger;
        return this;
    }

    public String getIPFSHash() { return IPFSHash; }

    public NFT setIPFSHash(String IPFSHash) {
        this.IPFSHash = IPFSHash;
        return this;
    }

    public String getWatermark() { return watermark; }

    public NFT setWatermark(String watermark) {
    	this.watermark = watermark;
    	return this;
    }

    @Override
    public String toString() {
        return "NFT::" + this.nftNumber + " " + this.publisher + " " + this.name + " " + this.description ;
    }

    public static NFT deserialize(byte[] data) {
        JSONObject json = new JSONObject(new String(data, UTF_8));
        String nftNumber = json.getString("nftNumber");
        String publisher = json.getString("publisher");
        String publishDateTime = json.getString("publishDateTime");
        List<String> owners = json.getJSONArray("owners").toList().stream().map(Object::toString).collect(Collectors.toList());
        List<String> users = json.getJSONArray("users").toList().stream().map(Object::toString).collect(Collectors.toList());
        String name = json.getString("name");
        String description = json.getString("description");
        String fileFinger = json.getString("fileFinger");
        String IPFSHash = json.getString("IPFSHash");
        String watermark = json.getString("watermark");
        return createInstance(nftNumber, publisher, publishDateTime, owners, users, name, description, fileFinger, IPFSHash, watermark);
    }

    public static byte[] serialize(NFT nft) { return State.serialize(nft); }

    public static NFT createInstance(String nftNumber, String publisher, String publishDateTime, List<String> owners, List<String> users, String name, String description, String fileFinger, String IPFSHash, String watermark) {
        return new NFT().setNftNumber(nftNumber).setPublisher(publisher).setPublishDateTime(publishDateTime).setOwners(owners).setUsers(users).setName(name).setDescription(description).setFileFinger(fileFinger).setIPFSHash(IPFSHash).setWatermark(watermark).setKey();
    }
}
