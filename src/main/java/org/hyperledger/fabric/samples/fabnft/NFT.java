package org.hyperledger.fabric.samples.fabnft;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.annotation.JsonProperty;
@JsonPropertyOrder({ "id", "owner", "fileFinger", "name", "description", "date", "price", "IPFSHash" })
@DataType()
public class NFT {
    @Property()
    private final String id;
    @Property()
    private final String owner;
    @Property()
    private final String fileFinger;
    @Property()
    private final String name;
    @Property()
    private final String description;
    @Property()
    private final String date;
    @Property()
    private final String price;
    @Property()
    private final String IPFSHash;

    public String getId() {
        return id;
    }
    public String getOwner() {
        return owner;
    }
    public String getFileFinger() {
        return fileFinger;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }
    public String getPrice() {
        return price;
    }
    public String getIPFSHash() {
        return IPFSHash;
    }
public NFT(@JsonProperty("id") final String id, @JsonProperty("owner") final String owner,
            @JsonProperty("fileFinger") final String fileFinger, @JsonProperty("name") final String name,
            @JsonProperty("description") final String description, @JsonProperty("date") final String date,
            @JsonProperty("price") final String price, @JsonProperty("IPFSHash") final String IPFSHash) {
        this.id = id;
        this.owner = owner;
        this.fileFinger = fileFinger;
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
        this.IPFSHash = IPFSHash;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        final NFT other = (NFT) obj;
        return Objects.deepEquals(new String[] { getId(), getOwner(), getFileFinger(), getName(), getDescription(), getDate(), getPrice(), getIPFSHash() },
                new String[] { other.getId(), other.getOwner(), other.getFileFinger(), other.getName(), other.getDescription(), other.getDate(), other.getPrice(), other.getIPFSHash() });
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwner(), getFileFinger(), getName(), getDescription(), getDate(), getPrice(), getIPFSHash());
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [id=" + id + ", owner=" + owner + ", fileFinger=" + fileFinger + ", name=" + name + ", description=" + description + ", date=" + date + ", price=" + price + ", IPFSHash=" + IPFSHash + "]";
    }
}
