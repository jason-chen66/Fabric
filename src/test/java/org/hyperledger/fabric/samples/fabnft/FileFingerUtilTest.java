package org.hyperledger.fabric.samples.fabnft;
import org.junit.jupiter.api.Test;
public class FileFingerUtilTest {
@Test
    public void testSHA_256() throws Exception {
        FileFingerUtil fileFingerUtil = new FileFingerUtil();
        String result = fileFingerUtil.SHA_256("D:\\images\\1.png");
        System.out.println(result);
    }
}
