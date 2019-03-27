package org.elastos.ela;

import org.elastos.ela.bitcoinj.Utils;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by nan on 18/1/14.
 */
public class Common {
    //public final static String SYSTEM_ASSET_ID = "a3d0eaa466df74983b5d7c543de6904f4c9418ead5ffd6d25814234a96db37b0";
    public final static String SYSTEM_ASSET_ID = "61ccbfae9f8ce9660a71321041917139cb72cbb85bd105e92f0ed32cb1d1298f";
    public final static byte[] ELA_ASSETID = Utils.reverseBytes(DatatypeConverter.parseHexBinary(SYSTEM_ASSET_ID));
}
