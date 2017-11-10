import java.awt.BorderLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * SwingFileChooserDemo.java is a 1.4 application that uses these files:
 * images/Open16.gif images/Save16.gif
 */
public class Crypto extends JPanel implements ActionListener {
  static private final String newline = "\n";
  static File file=null;
  JButton openButton, OK;

  JTextArea log;

  JFileChooser fc;

  public Crypto() {
    super(new BorderLayout());

    //Create the log first, because the action listeners
    //need to refer to it.
    log = new JTextArea(5, 25);
    log.setMargin(new Insets(5, 5, 5, 5));
    log.setEditable(false);
    JScrollPane logScrollPane = new JScrollPane(log);

    //Create a file chooser
    fc = new JFileChooser();

    //Uncomment one of the following lines to try a different
    //file selection mode. The first allows just directories
    //to be selected (and, at least in the Java look and feel,
    //shown). The second allows both files and directories
    //to be selected. If you leave these lines commented out,
    //then the default mode (FILES_ONLY) will be used.
    //
    //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    //Create the open button. We use the image from the JLF
    //Graphics Repository (but we extracted it from the jar).
    openButton = new JButton("Open a File...",
        createImageIcon("images/Open16.gif"));
    openButton.addActionListener(this);

    //Create the save button. We use the image from the JLF
    //Graphics Repository (but we extracted it from the jar).
    OK = new JButton("OK",
        createImageIcon("images/Save16.gif"));
    OK.addActionListener(this);

    //For layout purposes, put the buttons in a separate panel
    JPanel buttonPanel = new JPanel(); //use FlowLayout
    buttonPanel.add(openButton);
    buttonPanel.add(OK);

    //Add the buttons and the log to this panel.
    add(buttonPanel, BorderLayout.PAGE_START);
    add(logScrollPane, BorderLayout.CENTER);
  }

  public void actionPerformed(ActionEvent e) {
//	 static File file=null;
    //Handle open button action.
    if (e.getSource() == openButton) {
      int returnVal = fc.showOpenDialog(Crypto.this);
	 
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        System.out.println("Entering APPROVe_Opton");
        
        file=fc.getSelectedFile();
        //This is where a real application would open the file.
        log.append("Opening: " + file.getName() + "." + newline);
		log.append("Path of file:"+file.getPath());
    } else {
        log.append("Open command cancelled by user." + newline);
      }
      log.setCaretPosition(log.getDocument().getLength());

      //Handle save button action.
    } else if (e.getSource() == OK) {
    	
    	// Removing extension of file
    	//String rFile=file.getName().substring(0, file.getName().lastIndexOf("."));
    	String path=file.getPath().substring(0, file.getPath().lastIndexOf("."));
    	System.out.println("Path"+path);
    	String fileName=file.getName().substring(0, file.getName().lastIndexOf("."));
    	
    	
    	
    	 //Output files for AES 
   	 File outFile = new File(path+"_aes_enc.txt"); //Path of where the encrypted file to be stored.
        File outFile_dec = new File("/Users/gyani/Desktop/trial/"+fileName+"_aes_dec.txt"); // Path of the decrypted file.
        log.append(file.getPath());
        //Output Files for RSA 
        File f_enc=new File(path+"_rsa_enc.txt");
        File f_dec=new File("/Users/gyani/Desktop/trial/"+fileName+"_rsa_dec.txt");
        
        RSA1.generateKey();
        
        
        //AES Encryption
        
        try {
       	 
       	 
       	 
            SecretKey key = KeyGenerator.getInstance(Encrypter.ALGO_SECRET_KEY_GENERATOR).generateKey(); // SecretKey is a inbuilt class in JAVA. Generating key

            byte[] keyData = key.getEncoded();
            SecretKey key2 = new SecretKeySpec(keyData, 0, keyData.length, Encrypter.ALGO_SECRET_KEY_GENERATOR); //if you want to store key bytes to db so its just how to //recreate back key from bytes array
            System.out.println("AES Keys ");
          //  System.out.println("Key1: "+key1);
            System.out.println("Key2: "+key2);
            
          //  log.append("AES Keys "+newline );
            //log.append("Key "+key2+newline );
            
            log.append("Encrypting file with AES");
            
            
            byte[] iv = new byte[Encrypter.IV_LENGTH];
            SecureRandom.getInstance(Encrypter.ALGO_RANDOM_NUM_GENERATOR).nextBytes(iv); // If storing separately. 
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            
            System.out.println("\nEncrypting the file with AES");
            log.append("\nEncrypting file with AES");
            Encrypter.encrypt(key, paramSpec, new FileInputStream(file), new FileOutputStream(outFile)); // Calling Encryption method defined above
            
            log.append("\nDecrypting file with AES"+newline);
            System.out.println("\nDecrypting the file with AES");
            Encrypter.decrypt(key2, paramSpec, new FileInputStream(outFile), new FileOutputStream(outFile_dec)); // Calling Decryption method defined above
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    	
      //RSA Encryption
        
        try {

            // Check if the pair of keys are present else generate those.


              
              System.out.println("\nCreating files");
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
   			
   			
   			
   			byte[] contents = new byte[(int)file.length()];
   			
              BufferedInputStream bis = null;
              try
              {
                  bis = new BufferedInputStream(new FileInputStream(file));
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
            
            log.append("Encrypting file with RSA..."+newline); 
   		//  bw.write(enc,0,enc.length());
   		 // bw.close();
            
            e_fos.write(cipherText, 0, cipherText.length);
            e_fos.close();
            inputStream.close();
            // Decrypt the cipher text using the private key.
            ObjectInputStream inputStream1 = new ObjectInputStream(new FileInputStream(RSA1.PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream1.readObject();
           final byte[] plainText=RSA1.decrypt(cipherText, privateKey);
           log.append("Decrypting file with RSA..."+newline);
            d_fos.write(plainText, 0, plainText.length);
            d_fos.close();
            
            
            // Printing the Original, Encrypted and Decrypted Text

           
            inputStream.close();
            inputStream1.close();

          } catch (Exception exx) {
            exx.printStackTrace();
          }
          finally
          {

          }
        
        
        
        
    	
    	
     
      log.setCaretPosition(log.getDocument().getLength());
    }
  }

  /** Returns an ImageIcon, or null if the path was invalid. */
  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = Crypto.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    JFrame frame = new JFrame("Cryptography");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create and set up the content pane.
    JComponent newContentPane = new Crypto();
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}