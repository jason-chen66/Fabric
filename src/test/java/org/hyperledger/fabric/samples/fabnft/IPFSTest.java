package org.hyperledger.fabric.samples.fabnft;

/**
 * IPFSTest
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import io.ipfs.api.IPFS;
import org.junit.jupiter.api.Test;

public class IPFSTest {

    // ipfs的服务器地址和端
    private IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
    private IPFSUtil ipfsUtil = new IPFSUtil();

    @Test
    public void testIPFSUpload() throws IOException {
        //  filePath 指的是文件的上传路径+文件名，如D:/1.png
        String filePath = "D:\\images\\1.png";

        String cid = ipfsUtil.upload(ipfs, filePath);
        System.out.println(cid);

    }

    @Test
    public void testIPFSDownload() throws IOException {
        String cid = "QmSHqyprWXUa64M8rS7q4jmisgdv1g2xoaC3skMYC7Ckxe";
        String destFile = "D:\\images\\2.jpg";

        ipfsUtil.download(ipfs, cid, destFile);

    }

}