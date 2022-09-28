package com.kawahedukasi.util;

import org.wildfly.security.password.Password;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

public class DecryptUtil {

    public static boolean verifyBcryptPassword(String bcryptPassword, String passwordToVerify) throws Exception{
        WildFlyElytronPasswordProvider provider = new WildFlyElytronPasswordProvider();

        PasswordFactory passwordFactory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, provider);
        Password userPasswordDecoded = ModularCrypt.decode(bcryptPassword);
        Password userPasswordRestored = passwordFactory.translate(userPasswordDecoded);

        return passwordFactory.verify(userPasswordRestored, passwordToVerify.toCharArray());

    }

}
