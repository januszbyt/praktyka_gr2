package classes;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Hash {
    public static String getHash(String haslo) {
        return BCrypt.withDefaults().hashToString(12, haslo.toCharArray());
    }
    public static boolean checkHash(String haslo, String hash) {
        BCrypt.Result x = BCrypt.verifyer().verify(haslo.toCharArray(), hash);
        return x.verified;
    }

}
