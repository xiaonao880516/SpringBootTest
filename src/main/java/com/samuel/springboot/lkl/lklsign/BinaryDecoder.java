/*
 * lakala.com Inc.
 * Copyright (c) 2005-2016 All Rights Reserved.
 */ 

package com.samuel.springboot.lkl.lklsign;

public interface BinaryDecoder extends Decoder {

    /**
     * Decodes a byte array and returns the results as a byte array. 
     *
     * @param pArray A byte array which has been encoded with the
     *      appropriate encoder
     * 
     * @return a byte array that contains decoded content
     * 
     * @throws DecoderException A decoder exception is thrown
     *          if a Decoder encounters a failure condition during
     *          the decode process.
     */
    byte[] decode(byte[] pArray) throws DecoderException;
}  

