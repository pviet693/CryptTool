package DES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import UI.MainUI;

public class DES {
	public void generateKey(String dir) {
		String filePath = dir + "\\key.txt";
        SecretKey secretKey = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            secretKey = kg.generateKey();
            FileOutputStream saveKey = new FileOutputStream(filePath);
            byte[] strToBytes = secretKey.toString().getBytes();
            strToBytes = Arrays.copyOfRange(strToBytes, strToBytes.length - 8,strToBytes.length); // copy last 8 bits
            saveKey.write(strToBytes);
            saveKey.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public SecretKey setKey(String fileName)  {
        SecretKey secretKey = null;
        try{
        	FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String read = br.readLine();
			StringBuilder str = new StringBuilder();
			while (read != null) {
				str.append(read);
				read = br.readLine();
			}
			br.close();
			isr.close();
			fis.close();
			
			String myKey = str.toString();
			byte[] key;
			key = myKey.getBytes();
			//MessageDigest sha;
			//sha = MessageDigest.getInstance("SHA-256");
			//key = sha.digest(key); // 32 bytes, 256 bits
			//key = Arrays.copyOf(key, 8); // get first 8 bytes, 64 bits
			// key length: 128, 192, 256 bits
			secretKey = new SecretKeySpec(key, "DES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretKey;
    }
    
    private void processFile(boolean encrypt, File inputFile, SecretKey inputKey, File outputFile) throws Exception {
    	Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
    	if (encrypt)
    		cipher.init(Cipher.ENCRYPT_MODE, inputKey);
    	else
    		cipher.init(Cipher.DECRYPT_MODE, inputKey);
    	
        // Read input file into byte array
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int)inputFile.length()];
        fileInputStream.read(inputBytes);
        MainUI.updateProgressBar(45);

        // Process the byte array from the input file
        byte[] outputBytes = cipher.doFinal(inputBytes);
        MainUI.updateProgressBar(55);

        // Write the output byte array to the output file
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        fileOutputStream.write(outputBytes);
        MainUI.updateProgressBar(100);

        // Close file streams
        fileInputStream.close();
        fileOutputStream.close();
    }
    
    // Encrypt File
    public void encryptFile(File inputFile, SecretKey inputKey, File outputFile) throws Exception {
        processFile(true,inputFile,inputKey,outputFile);
    }
    
    // Decrypt a file
    public void decryptFile(File inputFile, SecretKey inputKey, File outputFile) throws Exception {
        processFile(false,inputFile,inputKey,outputFile);
    }
    
    private String processString(boolean encrypt, String input, SecretKey inputKey) throws Exception {
        String output = "";
        try{
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
            if (encrypt) {
            	cipher.init(Cipher.ENCRYPT_MODE, inputKey);
            	byte[] byteEncrypted = cipher.doFinal(input.getBytes("UTF-8"));
            	output = Base64.getEncoder().encodeToString(byteEncrypted);
            	output = URLEncoder.encode(output,"UTF-8");
            }
            else {
            	cipher.init(Cipher.DECRYPT_MODE, inputKey);
            	input = URLDecoder.decode(input,"UTF-8");
            	byte[] byteDecrypted = cipher.doFinal(Base64.getDecoder().decode(input));
                output = new String(byteDecrypted);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
    
    // encrypt string
    public String encryptString(String plainText, SecretKey key) throws Exception {
    	return processString(true, plainText, key);
    }
    
    // decrypt string
    public String decryptString(String cipherText, SecretKey key) throws Exception {
    	return processString(false, cipherText, key);
    }
}
