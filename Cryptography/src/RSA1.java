import java.io.*;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


    public class RSA1 {

      /**
       * String to hold name of the encryption algorithm.
       */
      public static final String ALGORITHM = "RSA";

      /**
       * String to hold the name of the private key file.
       */
      public static final String PRIVATE_KEY_FILE = "private.txt";

      /**
       * String to hold name of the public key file.
       */
      public static final String PUBLIC_KEY_FILE = "public.txt";


      public static void generateKey() {
        try {
          final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
          keyGen.initialize(1024);
          final KeyPair key = keyGen.generateKeyPair();

          File privateKeyFile = new File(PRIVATE_KEY_FILE);
          File publicKeyFile = new File(PUBLIC_KEY_FILE);

          // Create files to store public and private key
          if (privateKeyFile.getParentFile() != null) {
            privateKeyFile.getParentFile().mkdirs();
          }
          privateKeyFile.createNewFile();

          if (publicKeyFile.getParentFile() != null) {
            publicKeyFile.getParentFile().mkdirs();
          }
          publicKeyFile.createNewFile();

          // Saving the Public key in a file
          ObjectOutputStream publicKeyOS = new ObjectOutputStream(
              new FileOutputStream(publicKeyFile));
          publicKeyOS.writeObject(key.getPublic());
          System.out.println("public"+key.getPublic().getEncoded());
          publicKeyOS.close();

          // Saving the Private key in a file
          ObjectOutputStream privateKeyOS = new ObjectOutputStream(
              new FileOutputStream(privateKeyFile));
          privateKeyOS.writeObject(key.getPrivate());
          System.out.println("private"+key.getPrivate().getEncoded());
          //System.out.println(key.getPrivate());
          privateKeyOS.close();
        } catch (Exception e) {
          e.printStackTrace();
        }

      }

      public static boolean areKeysPresent() {

        File privateKey = new File(PRIVATE_KEY_FILE);
        File publicKey = new File(PUBLIC_KEY_FILE);

        if (privateKey.exists() && publicKey.exists()) {
          return true;
        }
        return false;
      }


      public static byte[] encrypt(byte[]bs, PublicKey key) {
        byte[] cipherText = null;
        try {
          // get an RSA cipher object and print the provider
          final Cipher cipher = Cipher.getInstance(ALGORITHM);
          // encrypt the plain text using the public key
          cipher.init(Cipher.ENCRYPT_MODE, key);
          cipherText = cipher.doFinal(bs);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return cipherText;
      }


      public static byte[] decrypt(byte[] text, PrivateKey key) {
        byte[] dectyptedText = null;
        try {
          // get an RSA cipher object and print the provider
          final Cipher cipher = Cipher.getInstance(ALGORITHM);

          // decrypt the text using the private key
          cipher.init(Cipher.DECRYPT_MODE, key);
          dectyptedText = cipher.doFinal(text);

        } catch (Exception ex) {
          ex.printStackTrace();
        }

        return dectyptedText;
      }

      public static void main(String[] args)throws IOException {
          

        try {

          // Check if the pair of keys are present else generate those.


            generateKey();
            File f=new File("/Users/gyani/Desktop/trial/datafile.txt");
			File f_enc=new File("/Users/gyani/Desktop/trial/datafile_enc_rsa.txt");
           	File f_dec=new File("/Users/gyani/Desktop/trial/datafile_dec_rsa.txt");
			System.out.println("Creating files");
			if(!f_enc.exists()){
				f_enc.createNewFile();
				System.out.println("Encrypted file created");
			}
			if(!f_dec.exists()){
				f_dec.createNewFile();
				System.out.println("Decrypted file created");
			}
			//FileInputStream fis=new FileInputStream(f_enc);
			FileOutputStream e_fos=new FileOutputStream(f_enc);
			FileOutputStream d_fos=new FileOutputStream(f_dec);

			byte[] contents = new byte[(int)f.length()];
            BufferedInputStream bis = null;
            try
            {
                bis = new BufferedInputStream(new FileInputStream(f));
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
          ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
          final PublicKey publicKey = (PublicKey) inputStream.readObject();
          final byte[] cipherText = encrypt(contents, publicKey);
          String enc=cipherText.toString();
		//  bw.write(enc,0,enc.length());
		 // bw.close();
          
          e_fos.write(cipherText, 0, cipherText.length);
          e_fos.close();
          inputStream.close();
          // Decrypt the cipher text using the private key.
          ObjectInputStream inputStream1 = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
          final PrivateKey privateKey = (PrivateKey) inputStream1.readObject();
         final byte[] plainText=decrypt(cipherText, privateKey);
          
          d_fos.write(plainText, 0, plainText.length);
          d_fos.close();
          
          
          // Printing the Original, Encrypted and Decrypted Text

          System.out.println("Original Text: " + contents.toString());
          System.out.println("Encrypted Text: " +cipherText);
          System.out.println("Decrypted Text: " + plainText);
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