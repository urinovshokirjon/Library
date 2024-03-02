package dasturlash.uz.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

 public static String encode(String login){
     MessageDigest md=null;
     try {
         md=MessageDigest.getInstance("MD5");
         byte [] shifrbyte=md.digest(login.getBytes());
         BigInteger no=new BigInteger(1,shifrbyte);
         String hashtext=no.toString(16);
         while (hashtext.length()<32){
             hashtext="0"+ hashtext;
         }
         return hashtext;
     } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException(e);
     }


 }
}
