package org.hyperledger.fabric.samples.fabnft;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.junit.Test;

public class NFTContractTest {

    private ChaincodeStub mockStub = mock(ChaincodeStub.class);

    @Test
    public void testPublish() {
        NFTContext ctx = new NFTContext(mockStub);
        NFTContract contract = new NFTContract();
        String publisher = "publisher";
        String nftNumber = "nft001";
        String publishDateTime = "2023-04-15T10:00:00.000Z";
        List<String> owners = Arrays.asList("owner1", "owner2");
        String name = "nft name";
        String description = "nft description";
        String fileFinger = "file finger";
        String IPFSHash = "ipfs hash";
        String watermark = "watermark";
        NFT nft = contract.publish(ctx, publisher, nftNumber, publishDateTime, owners, name, description, fileFinger, IPFSHash, watermark);
        assertNotNull(nft);
        assertEquals(nft.getPublisher(), publisher);
        assertEquals(nft.getNftNumber(), nftNumber);
        assertEquals(nft.getPublishDateTime(), publishDateTime);
        assertEquals(nft.getOwners(), owners);
        assertEquals(nft.getUsers(), owners);
        assertEquals(nft.getName(), name);
        assertEquals(nft.getDescription(), description);
        assertEquals(nft.getFileFinger(), fileFinger);
        assertEquals(nft.getIPFSHash(), IPFSHash);
        assertEquals(nft.getWatermark(), watermark);
    }

    @Test
    public void testPublishExistingNFT() {
        NFTContext ctx = new NFTContext(mockStub);
        NFTContract contract = new NFTContract();
        String publisher = "publisher";
        String nftNumber = "nft001";
        String publishDateTime = "2023-04-15T10:00:00.000Z";
        List<String> owners = Arrays.asList("owner1", "owner2");
        String name = "nft name";
        String description = "nft description";
        String fileFinger = "file finger";
        String IPFSHash = "ipfs hash";
        String watermark = "watermark";
        NFT nft = contract.publish(ctx, publisher, nftNumber, publishDateTime, owners, name, description, fileFinger, IPFSHash, watermark);
        assertEquals(null, nft);
    }

    @Test
    public void testTransferOwnership() {
        NFTContext ctx = new NFTContext(mockStub);
        NFTContract contract = new NFTContract();
        String publisher = "publisher";
        String nftNumber = "nft001";
        String publishDateTime = "2023-04-15T10:00:00.000Z";
        List<String> owners = Arrays.asList("owner1", "owner2");
        String name = "nft name";
        String description = "nft description";
        String fileFinger = "file finger";
        String IPFSHash = "ipfs hash";
        String watermark = "watermark";
        NFT nft = contract.transferOwnership(ctx, nftNumber, "owner1", "owner3");
        assertEquals(true, nft.getOwners().contains("owner3"));
    }

    @Test
    public void testGrantAccess() {
        NFTContext ctx = new NFTContext(mockStub);
        NFTContract contract = new NFTContract();
        String publisher = "publisher";
        String nftNumber = "nft001";
        String publishDateTime = "2023-04-15T10:00:00.000Z";
        List<String> owners = Arrays.asList("owner1", "owner2");
        String name = "nft name";
        String description = "nft description";
        String fileFinger = "file finger";
        String IPFSHash = "ipfs hash";
        String watermark = "watermark";
        NFT nft = contract.grantAccess(ctx, nftNumber, "owner1", "owner3");
        assertEquals(true, nft.getUsers().contains("owner3"));
    }

    @Test
    public void testRevokeAccess() {
        NFTContext ctx = new NFTContext(mockStub);
        NFTContract contract = new NFTContract();
        String publisher = "publisher";
        String nftNumber = "nft001";
        String publishDateTime = "2023-04-15T10:00:00.000Z";
        List<String> owners = Arrays.asList("owner1", "owner2");
        String name = "nft name";
        String description = "nft description";
        String fileFinger = "file finger";
        String IPFSHash = "ipfs hash";
        String watermark = "watermark";
        NFT nft = contract.revokeAccess(ctx, nftNumber, "owner1", "owner2");
        assertEquals(false, nft.getUsers().contains("owner2"));
    }

}
