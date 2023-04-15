package org.hyperledger.fabric.samples.fabnft.ledgerapi;

@FunctionalInterface
public interface StateDeserializer {
    State deserialize(byte[] buffer);
}
