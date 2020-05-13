/*
 * lakala.com Inc.
 * Copyright (c) 2005-2016 All Rights Reserved.
 */ 

package com.samuel.springboot.lkl.lklsign;

public interface BinaryEncoder extends Encoder {
    
    /**
     * Encodes a byte array and return the encoded data
     * as a byte array.
     * 
     * @param pArray Data to be encoded
     *
     * @return A byte array containing the encoded data
     * 
     * @throws EncoderException thrown if the Encoder
     *      encounters a failure condition during the
     *      encoding process.
     */
    byte[] encode(byte[] pArray) throws EncoderException;
}  

