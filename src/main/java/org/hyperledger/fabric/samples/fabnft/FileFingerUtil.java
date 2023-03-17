package org.hyperledger.fabric.samples.fabnft;

import java.io.*;
import java.security.*;

public class FileFingerUtil {

    public String SHA_256(String filename) throws Exception {
        String algorithm = "SHA-256";

        MessageDigest digest = MessageDigest.getInstance(algorithm);

        InputStream is = new FileInputStream(filename);
        byte[] buffer = new byte[8192];
        int read = 0;
        while ((read = is.read(buffer)) > 0) {
            digest.update(buffer, 0, read);
        }
        is.close();

        byte[] hash = digest.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
