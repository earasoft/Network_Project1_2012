package org.ques2;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.codec.binary.Hex;
import org.shared.compression.Compress;
public class Serialization_test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageObject MsgObj= new MessageObject("System Time","1,5,6,8,4,","One line Message");
		
		byte[] data = SerializationUtils.serialize(MsgObj);
		byte[] dataCompress=Compress.CompressDeflater(data);
		
		System.out.println(data.length+"\t"+dataCompress.length);
		
		String DataHex=Hex.encodeHexString(data);
		String DataHexCompressed=Hex.encodeHexString(Compress.CompressDeflater(data));
		
		System.out.println("Object in Hex:"+DataHex);
		System.out.println("Object in Hex:"+DataHexCompressed);
		
		
		//deserialize:
		MessageObject yourObject = (MessageObject) SerializationUtils.deserialize(data);
		
		System.out.println(yourObject);
		
		
	}
	
	
	
	
	

}
