package Authentication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String getHash(String inputString) {
        String hashText = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(inputString.getBytes());
            byte[] digest = messageDigest.digest();
            BigInteger no = new BigInteger(1, digest);
            hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
        }catch (Exception e){
            System.out.println("Exception occurred: " + e.toString());
        }
        return hashText;
    }
}
