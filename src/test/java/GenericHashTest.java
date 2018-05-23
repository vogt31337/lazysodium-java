/*
 * Copyright (c) Terl Tech Ltd • 11/05/18 23:19 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * This Java source file was generated by the Gradle 'init' task.
 */


import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import com.goterl.lazycode.lazysodium.interfaces.GenericHash;
import junit.framework.TestCase;
import org.junit.Test;

public class GenericHashTest extends BaseTest {

    @Test
    public void genKey() {
        String key = lazySodium.cryptoGenericHashKeygen();
        TestCase.assertNotNull(key);
    }

    @Test
    public void hash() throws SodiumException {
        String message = "https://terl.co";
        String key = lazySodium.cryptoGenericHashKeygen();
        String hash = lazySodium.cryptoGenericHash(message, key);
        TestCase.assertNotNull(hash);
    }

    @Test
    public void hashMultiPart() throws SodiumException {
        String message = "The sun";
        String message2 = "is shining";

        final GenericHash.State state = new GenericHash.State.ByReference();

        String key = lazySodium.cryptoGenericHashKeygen();
        int sizeOfHash = GenericHash.BYTES_MAX;

        lazySodium.cryptoGenericHashInit(state, key, sizeOfHash);
        String hashedMessage1 = lazySodium.cryptoGenericHashUpdate(state, message);
        String hashedMessage2 = lazySodium.cryptoGenericHashUpdate(state, message2);
        String hash = lazySodium.cryptoGenericHashFinal(state, sizeOfHash);

        state.clear();

        TestCase.assertNotNull(hash);
    }

}
