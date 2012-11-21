package org.ques2;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.shared.compression.Compress;
public class Serialization {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageObject MsgObj= new MessageObject("1,5,6,8,4,","One line Message");
		
		byte[] dataCompress=serializeAndCompress(MsgObj);
		
	
		
		//deserialize:
		MessageObject yourObject = (MessageObject) deserializeAndDecompress(dataCompress);
		
		System.out.println(yourObject);
		
		
	}
	
	
	public static Object deserializeAndDecompress(String HexCode){
		byte[] dataDecompress = null;
		try {
			dataDecompress = Compress.DecompressDeflater(Hex.decodeHex(HexCode.toCharArray()));
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SerializationUtils.deserialize(dataDecompress);
	}
	
	public static Object deserializeAndDecompress(byte[] CompressDataArray){
		byte[] dataDecompress=Compress.DecompressDeflater(CompressDataArray);
		return SerializationUtils.deserialize(dataDecompress);
		
	}
	
	public static byte[] serializeAndCompress(Object ObjectInput){
		byte[] data = SerializationUtils.serialize((Serializable) ObjectInput);
		return Compress.CompressDeflater(data);
	}
	
	public static String serializeAndCompressEncodeHex(Object ObjectInput){
		byte[] data = SerializationUtils.serialize((Serializable) ObjectInput);
		byte[] dataCompress=Compress.CompressDeflater(data);
		return Hex.encodeHexString(dataCompress);
	}
	
	

}
