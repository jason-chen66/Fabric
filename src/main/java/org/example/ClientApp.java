package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.ipfs.api.IPFS;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;

public class ClientApp {

    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "false");
    }
    public Contract parseContract(String loginUser) throws Exception {
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("wallet");
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
        Path networkConfigPath = Paths.get("src/main/resources/connection.json");

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, loginUser).networkConfig(networkConfigPath);

        // create a gateway connection
        try (Gateway gateway = builder.connect()) {

            // get the network and contract
            Network network = gateway.getNetwork("mychannel");

//            byte[] result;
//
//            result = contract.evaluateTransaction("queryAllCars");
//            System.out.println(new String(result));
//
////            contract.submitTransaction("createCar", "CAR10", "VW", "Polo", "Grey", "Mary");
//
//            result = contract.evaluateTransaction("queryCar", "CAR10");
//            System.out.println(new String(result));
//
//            contract.submitTransaction("changeCarOwner", "CAR10", loginUser);
//
//            result = contract.evaluateTransaction("queryCar", "CAR10");
//            System.out.println(new String(result));
            return network.getContract("fabcar");
        }
    }

    public static void main(String[] args) throws Exception {
        ClientApp clientApp = new ClientApp();
        clientApp.createNFT("user2","src/main/resources/NFTs/architecture_31.jpg","architecture_31.jpg");
    }


    public void createNFT(String loginUser,String filePath,String fileName) throws Exception {
        Contract contract = this.parseContract(loginUser);
        IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
        IPFSUtil ipfsUtil = new IPFSUtil();
        String cid = ipfsUtil.upload(ipfs,filePath);
        FileFingerUtil fileFingerUtil = new FileFingerUtil();
        String finger = fileFingerUtil.SHA_256(fileName);
        System.out.println(cid);
//        contract.submitTransaction("createNFT",cid,loginUser,finger);
    }
}
