package com.bestsoft.utils.sharedpreference;

import android.content.SharedPreferences;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.List;

/**
 * @package: com.juzhe.www.utils.sharedpreference
 * @user:xhkj
 * @date:2018/11/4
 * @description:
 **/
public class DefaultRecoveryHandler extends RecoveryHandler {
    @Override
    protected boolean recover(Exception e, KeyStore keyStore, List<String> keyAliases, SharedPreferences preferences) {
        e.printStackTrace();

        try {
            clearKeyStore(keyStore, keyAliases);
            clearPreferences(preferences);
            return true;
        } catch (KeyStoreException e1) {
            e1.printStackTrace();
        }

        return false;
    }
}