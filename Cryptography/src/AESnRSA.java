import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESnRSA {

	public static void main(String args[]){
		
	
	 File inFile = new File("/Users/gyani/Desktop/trial/datafile.txt");// Path of file to be encrypted
    
	 //Output files for AES 
	 File outFile = new File("/Users/gyani/Desktop/trial/datafile_aes_enc.txt"); //Path of where the encrypted file to be stored.
     File outFile_dec = new File("/Users/gyani/Desktop/trial/datafile_aes_dec.txt"); // Path of the decrypted file.
     
     //Output Files for RSA 
     File f_enc=new File("/Users/gyani/Desktop/trial/datafile_rsa_enc.txt");
     File f_dec=new File("/Users/gyani/Desktop/trial/datafile_rsa_dec.txt");
     
     RSA1.generateKey();
     
     
     
     //AES Encryption
     
     try {
    	 
    	 
    	 
         SecretKey key = KeyGenerator.getInstance(Encrypter.ALGO_SECRET_KEY_GENERATOR).generateKey(); // SecretKey is a inbuilt class in JAVA. Generating key

         byte[] keyData = key.getEncoded();
         SecretKey key2 = new SecretKeySpec(keyData, 0, keyData.length, Encrypter.ALGO_SECRET_KEY_GENERATOR); //if you want to store key bytes to db so its just how to //recreate back key from bytes array
         System.out.println("AES Keys ");
         
         System.out.println("Key2: "+key2);
         System.out.println("Key2: "+key2);
         
         byte[] iv = new byte[Encrypter.IV_LENGTH];
         SecureRandom.getInstance(Encrypter.ALGO_RANDOM_NUM_GENERATOR).nextBytes(iv); // If storing separately. 
         AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
         
         System.out.println("Encrypting the file with AES");
         Encrypter.encrypt(key, paramSpec, new FileInputStream(inFile), new FileOutputStream(outFile)); // Calling Encryption method defined above
         
         System.out.println("Decrypting the file with AES");
         Encrypter.decrypt(key2, paramSpec, new FileInputStream(outFile), new FileOutputStream(outFile_dec)); // Calling Decryption method defined above
     } catch (Exception e) {
         e.printStackTrace();
     }
    
     
     //RSA Encryption
     
     try {

         // Check if the pair of keys are present else generate those.


           
           System.out.println("Creating files");
			if(!f_enc.exists()){
				f_enc.createNewFile();
		//		System.out.println("Encrypted file created");
			}
			if(!f_dec.exists()){
				f_dec.createNewFile();
			//	System.out.println("Decrypted file created");
			}
			//FileInputStream fis=new FileInputStream(f_enc);
			FileOutputStream e_fos=new FileOutputStream(f_enc);
			FileOutputStream d_fos=new FileOutputStream(f_dec);

			byte[] contents = new byte[(int)inFile.length()];
           BufferedInputStream bis = null;
           try
           {
               bis = new BufferedInputStream(new FileInputStream(inFile));
               DataInputStream dis = new DataInputStream(bis);
               dis.readFully(contents);
           }
           finally
           {
               if(bis != null)
               {
                   bis.close();
               }
           }           


        // final String originalText = "Text to be encrypted";


         // Encrypt the string using the public key
         ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream(RSA1.PUBLIC_KEY_FILE));
         final PublicKey publicKey = (PublicKey) inputStream.readObject();
         final byte[] cipherText = RSA1.encrypt(contents, publicKey);
         String enc=cipherText.toString();
		//  bw.write(enc,0,enc.length());
		 // bw.close();
         
         e_fos.write(cipherText, 0, cipherText.length);
         e_fos.close();
         inputStream.close();
         // Decrypt the cipher text using the private key.
         ObjectInputStream inputStream1 = new ObjectInputStream(new FileInputStream(RSA1.PRIVATE_KEY_FILE));
         final PrivateKey privateKey = (PrivateKey) inputStream1.readObject();
        final byte[] plainText=RSA1.decrypt(cipherText, privateKey);
         
         d_fos.write(plainText, 0, plainText.length);
         d_fos.close();
         
         
         // Printing the Original, Encrypted and Decrypted Text

        
         inputStream.close();
         inputStream1.close();

       } catch (Exception e) {
         e.printStackTrace();
       }
       finally
       {

       }

     
     
     
     
}

}

