package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

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
//        clientApp.createNFT("user2","src/main/resources/NFTs/architecture_31.jpg","architecture_31.jpg");
    }


    public String createNFT(String loginUser, String filePath, String fileName, Set<String> owners) throws Exception {
        Contract contract = this.parseContract(loginUser);
        IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
        IPFSUtil ipfsUtil = new IPFSUtil();
        String hash = ipfsUtil.upload(ipfs,filePath);
        FileFingerUtil fileFingerUtil = new FileFingerUtil();
        String finger = fileFingerUtil.SHA_256(fileName);
        System.out.println(hash);
        RandomIdGenerator randomIdGenerator = new RandomIdGenerator();
        String NFTId = randomIdGenerator.generateId();
        StringBuilder  ownersBuilder = new StringBuilder();
        for (String owner : owners) {
            ownersBuilder.append(owner).append(",");
        }
        contract.submitTransaction("createNFT",NFTId,hash,loginUser,finger,ownersBuilder.toString(),fileName);
        return NFTId;
    }
    public String queryNFT(String loginUser, String NFTId) throws Exception {
        Contract contract = this.parseContract(loginUser);
        byte[] result = contract.evaluateTransaction("queryNFT", loginUser, NFTId);
        //从result获取到hash，然后从ipfs下载文件
        String hash = "result";
        IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
        IPFSUtil ipfsUtil = new IPFSUtil();
        ipfsUtil.download(ipfs,hash);
        //下载后可以传给前端

        return new String(result);
    }

    public String queryAllNFTs(String loginUser) throws Exception {
        Contract contract = this.parseContract(loginUser);
        byte[] result = contract.evaluateTransaction("queryAllNFTs", loginUser);
        return new String(result);
    }

    public void changeNFTOwner(String NFTId, String loginUser, String newOwner) throws Exception {
        Contract contract = this.parseContract(loginUser);
        contract.submitTransaction("changeNFTOwner", NFTId, loginUser, newOwner);
    }
}
