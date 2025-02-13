/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Floodgate
 */

package org.geysermc.floodgate.api;

import java.nio.charset.StandardCharsets;
import org.geysermc.floodgate.api.logger.FloodgateLogger;
import org.geysermc.floodgate.config.FloodgateConfigHolder;
import org.geysermc.floodgate.crypto.FloodgateCipher;
import org.geysermc.floodgate.pluginmessage.PluginMessageManager;
import org.geysermc.floodgate.util.BedrockData;

public final class ProxyFloodgateApi extends SimpleFloodgateApi {
    private final FloodgateCipher cipher;

    public ProxyFloodgateApi(
            PluginMessageManager pluginMessageManager,
            FloodgateConfigHolder configHolder,
            FloodgateLogger logger,
            FloodgateCipher cipher) {
        super(pluginMessageManager, configHolder, logger);
        this.cipher = cipher;
    }

    public byte[] createEncryptedData(BedrockData bedrockData) {
        try {
            return cipher.encryptFromString(bedrockData.toString());
        } catch (Exception exception) {
            throw new IllegalStateException("We failed to create the encrypted data, " +
                    "but creating encrypted data is mandatory!", exception);
        }
    }

    public String createEncryptedDataString(BedrockData bedrockData) {
        return new String(createEncryptedData(bedrockData), StandardCharsets.UTF_8);
    }
}
