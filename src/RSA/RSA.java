/*Error: Data must not be longer than 53 bytes*/

package RSA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import UI.MainUI;

public class RSA {

	public void generateKey(String dir) {
		String filePathPub = dir + "\\PublicKey.txt";
		String filePathPvt = dir + "\\PrivateKey.txt";
		KeyPair kp = null;
		try {
			SecureRandom sr = new SecureRandom();
			KeyPairGenerator kpg;
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(512, sr);
			kp = kpg.genKeyPair();
			PublicKey pub = kp.getPublic();
			PrivateKey pvt = kp.getPrivate();
			FileOutputStream out = new FileOutputStream(filePathPub);
			out.write(pub.getEncoded());
			out.close();
			out = new FileOutputStream(filePathPvt);
			out.write(pvt.getEncoded());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PublicKey setPublicKeyRSA(String fileName) throws Exception {
		/*PublicKey pub = null;
		try {
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
			System.out.println(myKey.length());
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getEncoder().encode(myKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            pub = keyFactory.generatePublic(keySpec);
			System.out.println(pub.toString().length());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return pub;*/
		/* Read all the public key bytes */
		Path path = Paths.get(fileName);
		byte[] bytes = Files.readAllBytes(path);

		/* Generate public key. */
		X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey pub = kf.generatePublic(ks);
		return pub;
	}
	public PrivateKey setPrivateKeyRSA(String fileName) throws Exception {
		/*PrivateKey pk = null;        
		try{
			FileInputStream fis = new FileInputStream(filename);
			byte[] keyBytes = new byte[fis.available()];
			fis.read(keyBytes);
			fis.close();
			PKCS8EncodedKeySpec  spec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			pk=kf.generatePrivate(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;*/
		/* Read all bytes from the private key file */
		Path path = Paths.get(fileName);
		byte[] bytes = Files.readAllBytes(path);

		/* Generate private key. */
		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey pvt = kf.generatePrivate(ks);
		return pvt;
	}

	// Encrypt File
	public void encryptFile(File inputFile, PublicKey publicKey, File outputFile) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");

		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

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

	// Decrypt a file
	public void decryptFile(File inputFile, PrivateKey privateKey, File outputFile) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");

		cipher.init(Cipher.DECRYPT_MODE, privateKey);

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

	// encrypt string
	public String encryptString(String plainText, PublicKey key) throws Exception {
		String output = "";
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");

		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] byteEncrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
		output = Base64.getEncoder().encodeToString(byteEncrypted);
		output = URLEncoder.encode(output,"UTF-8");

		return output;
	}

	// decrypt string
	public String decryptString(String cipherText, PrivateKey key) throws Exception {
		String output = "";
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");

		cipher.init(Cipher.DECRYPT_MODE, key);
		cipherText = URLDecoder.decode(cipherText,"UTF-8");
		byte[] byteDecrypted = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		output = new String(byteDecrypted);

		return output;
	}
}
