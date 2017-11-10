import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {
    public final static int IV_LENGTH = 16; // Default length with Default 128
                                                // key AES encryption
    public final static int DEFAULT_READ_WRITE_BLOCK_BUFFER_SIZE = 1024;

    public final static String ALGO_RANDOM_NUM_GENERATOR = "SHA1PRNG"; //PRNG :pseudorandom number generator to be used in cryptography 
    public final static String ALGO_SECRET_KEY_GENERATOR = "AES"; // Name of the algoritham to be used
    public final static String ALGO_ENCRYPTOR = "AES/CBC/PKCS5Padding"; //Uses 128 bit key,AES: Name of Algo, CBC: Cipher Block Chaining , Cipher block chaining (CBC) is a mode of operation for a block cipher (one in which a sequence of bits are encrypted as a single unit or block with a cipher key applied to the entire block). Cipher block chaining uses what is known as an initialization vector (IV) of a certain length. One of its key characteristics is that it uses a chaining mechanism that causes the decryption of a block of ciphertext to depend on all the preceding ciphertext blocks. ,
														// PKCS5Padding is a padding scheme. It follows the following rules : The number of bytes to be padded equals to "8 - numberOfBytes(clearText) mod 8". So 1 to 8 bytes will be padded to the clear text data depending on the length of the clear text data. All padded bytes have the same value - the number of bytes padded.
    //@SuppressWarnings("resource")
	
	/* Defining Encryption MEthod */
    public static void encrypt(SecretKey key, AlgorithmParameterSpec paramSpec, InputStream in, OutputStream out) // SecretKey: This interface contains no methods or constants. Its only purpose is to group (and provide type safety for) secret keys.  ,  , 
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IOException {
        try {
            // byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C,            AlgorithmParameterSpec: This interface contains no methods or constants. Its only purpose is to group (and provide type safety for) all parameter specifications.
            // 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C,			InputStream,OutputStream: InputStream is used for reading,  for writing. The goal of InputStream and OutputStream is to abstract different ways to input and output: whether the stream is a file, a web page, or the screen shouldn't matter.
            // 0x07, 0x72, 0x6F, 0x5A };
            // AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            Cipher c = Cipher.getInstance(ALGO_ENCRYPTOR); //Cipher Class: This class provides the functionality of a cryptographic cipher for encryption and decryption. In order to create a Cipher object, the application calls the Cipher's getInstance method, and passes the name of the requested transformation to it. Optionally, the name of a provider may be specified.
            c.init(Cipher.ENCRYPT_MODE, key, paramSpec); // init(int opmode, Key key, AlgorithmParameterSpec params) method : Initializes this cipher with a key and a set of algorithm parameters.  ENCRYPT_MODE : Constant used to initialize cipher to encryption mode.Here its value is 1
            out = new CipherOutputStream(out, c); //CipherOutputStream Class :A CipherOutputStream is composed of an OutputStream and a Cipher so that write() methods first process the data before writing them out to the underlying OutputStream. The cipher must be fully initialized before being used by a CipherOutputStream.For example, if the cipher is initialized for encryption, the CipherOutputStream will attempt to encrypt data before writing out the encrypted data
            int count = 0;
            byte[] buffer = new byte[DEFAULT_READ_WRITE_BLOCK_BUFFER_SIZE]; // Defines the block size in the form of byte array.
            while ((count = in.read(buffer)) >= 0) {  // Executing the loop until 
                out.write(buffer, 0, count); // write(byte[] b, int off, int len) method : Writes len bytes from the specified byte array starting at offset off to this output stream.
            }
        } finally {
            out.close(); // Closing the output stream.
        }
    }

    //@SuppressWarnings("resource")
	/* Defining Decryption MEthod */
    public static void decrypt(SecretKey key, AlgorithmParameterSpec paramSpec, InputStream in, OutputStream out)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IOException {
        try {
            // byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C,
            // 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C,
            // 0x07, 0x72, 0x6F, 0x5A };
            // AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            Cipher c = Cipher.getInstance(ALGO_ENCRYPTOR);
            c.init(Cipher.DECRYPT_MODE, key, paramSpec); // DECRYPT_MODE: Constant used to initialize cipher to encryption mode.Here its value is 1
            out = new CipherOutputStream(out, c);
            int count = 0;
            byte[] buffer = new byte[DEFAULT_READ_WRITE_BLOCK_BUFFER_SIZE];
            while ((count = in.read(buffer)) >= 0) {
                out.write(buffer, 0, count);
            }
        } finally {
            out.close();
        }
    }

    public static void main(String[] args) {
        File inFile = new File("/Users/gyani/Desktop/trial/datafile.txt");// Path of file to be encrypted
        File outFile = new File("/Users/gyani/Desktop/trial/datafile_aes_enc.txt"); //Path of where the encrypted file to be stored.
        File outFile_dec = new File("/Users/gyani/Desktop/trial/datafile_aes_dec.txt");// Path of the decrypted file.
	
        try {
            SecretKey key = KeyGenerator.getInstance(ALGO_SECRET_KEY_GENERATOR).generateKey(); // SecretKey is a inbuilt class in JAVA. Generating key

            byte[] keyData = key.getEncoded();
            SecretKey key2 = new SecretKeySpec(keyData, 0, keyData.length, ALGO_SECRET_KEY_GENERATOR); //if you want to store key bytes to db so its just how to //recreate back key from bytes array

            byte[] iv = new byte[IV_LENGTH];
            SecureRandom.getInstance(ALGO_RANDOM_NUM_GENERATOR).nextBytes(iv); // If storing separately. 
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);

            Encrypter.encrypt(key, paramSpec, new FileInputStream(inFile), new FileOutputStream(outFile)); // Calling Encryption method defined above
            Encrypter.decrypt(key2, paramSpec, new FileInputStream(outFile), new FileOutputStream(outFile_dec)); // Calling Decryption method defined above
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}