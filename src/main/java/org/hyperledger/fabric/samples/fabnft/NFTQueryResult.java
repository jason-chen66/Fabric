package org.hyperledger.fabric.samples.fabnft;

import java.util.Objects;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.annotation.JsonProperty;
@DataType()
public class NFTQueryResult {
    @Property()
    private final String Key;

    @Property()
    private final NFT Record;

    public NFTQueryResult(@JsonProperty("Key") final String Key, @JsonProperty("Record") final NFT Record) {
        this.Key = Key;
        this.Record = Record;
    }
    public String getKey() {
        return Key;
    }
    public NFT getRecord() {
        return Record;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        NFTQueryResult other = (NFTQueryResult) obj;
        Boolean recordsAreEquals = this.getRecord().equals(other.getRecord());
        Boolean keysAreEquals = this.getKey().equals(other.getKey());
        return recordsAreEquals && keysAreEquals;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getKey(), this.getRecord());
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [Key=" + Key + ", Record=" + Record + "]";
    }
}
