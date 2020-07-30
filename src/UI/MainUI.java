package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import MD5.HashMD5;
import RSA.RSA;
import AES.AES;
import DES.DES;

public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cbxAlgorithm;
	private JTextField txtSaveKey;
	private JButton btnSaveKey;
	private JButton btnGenKey;
	private JButton btnChooseFile;
	private JButton btnChooseFolder;
	private JButton btnSaveFolder;
	private JButton btnKey;
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JButton btnMD5Encrypt;
	private JButton btnHashFileEncrypt;
	private JTextField txtFile;
	private JTextField txtSaveFolder;
	private JTextField txtKey;
	private JTextField txtFileMD5Encrypt;
	private JTextArea txtMessage;
	private static JProgressBar pBar;

	public MainUI() {
		super("Encryption-Decryption Tool");
		addControls();
		addEvents();
	}

	public void setTextMesageColor(Color fg) {
		txtMessage.setForeground(fg);
	}

	public void updateMessage(String msg) {
		txtMessage.append(msg + "\n");
	}

	public static void updateProgressBar(int val) {
		SwingUtilities.invokeLater(new Runnable(){
			//@Override
			public void run() {
				pBar.setValue(val);
			}

		});
	}

	public void addControls() {
		// TODO Auto-generated method stub

		Container con = getContentPane();

		JPanel pnEncrypt = new JPanel();
		pnEncrypt.setLayout(null);
		pnEncrypt.setBounds(0, 0, 660, 650);

		JPanel pnAlgorithm = new JPanel();
		pnAlgorithm.setLayout(null);
		pnAlgorithm.setBounds(20, 10, 610, 50);
		pnAlgorithm.setBackground(new Color(232, 198, 106));
		pnEncrypt.add(pnAlgorithm);
		// 
		JLabel lblAlgorithm = new JLabel();
		lblAlgorithm.setText("<html><font color='#e10304'>Algorithm: </font></html>");
		lblAlgorithm.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblAlgorithm.setBounds(10, 10, 80, 32);
		pnAlgorithm.add(lblAlgorithm);

		// 
		String algroithm[] = {"AES", "DES", "RSA"};
		cbxAlgorithm = new JComboBox<String>(algroithm);
		cbxAlgorithm.setFont(new Font("Consolas", Font.PLAIN, 13));
		//cbxAlgorithm.setForeground(new Color(226, 140, 6));
		cbxAlgorithm.setBackground(Color.WHITE);
		cbxAlgorithm.setBounds(100, 10, 50, 32);
		pnAlgorithm.add(cbxAlgorithm);

		JLabel lblGenKey = new JLabel();
		lblGenKey.setText("<html><font color='#e10304'>GenKey: </font></html>");
		lblGenKey.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblGenKey.setBounds(160, 10, 80, 32);
		pnAlgorithm.add(lblGenKey);

		txtSaveKey = new JTextField(20);
		txtSaveKey.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtSaveKey.setEditable(false);
		txtSaveKey.setBackground(Color.WHITE);
		txtSaveKey.setBounds(230, 10, 250, 32);
		pnAlgorithm.add(txtSaveKey);

		btnSaveKey = new JButton();
		btnSaveKey.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSaveKey.setContentAreaFilled(false);
		btnSaveKey.setIcon(new ImageIcon(MainUI.class.getResource("../Images/folder-icon.png")));
		btnSaveKey.setBounds(485, 10, 32, 32);
		pnAlgorithm.add(btnSaveKey);

		btnGenKey = new JButton();
		btnGenKey.setText("GenKey");
		btnGenKey.setContentAreaFilled(true);
		btnGenKey.setBackground(Color.WHITE);
		btnGenKey.setBounds(520, 10, 80, 32);
		pnAlgorithm.add(btnGenKey);

		JPanel pnFile = new JPanel();
		pnFile.setLayout(null);
		pnFile.setBounds(20, 70, 610, 50);
		pnFile.setBackground(new Color(232, 198, 106));
		pnEncrypt.add(pnFile);

		// File
		JLabel lblFile = new JLabel();
		lblFile.setText("<html><font color='#e10304'>File: </font></html>");
		lblFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFile.setBounds(10, 10, 75, 32);
		pnFile.add(lblFile);

		// txtFile
		txtFile = new JTextField(20);
		txtFile.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtFile.setEditable(false);
		txtFile.setBackground(Color.WHITE);
		txtFile.setBounds(100, 10, 380, 32);
		pnFile.add(txtFile);

		// btnChooseFile
		btnChooseFile = new JButton();
		btnChooseFile.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChooseFile.setContentAreaFilled(false);
		btnChooseFile.setIcon(new ImageIcon(MainUI.class.getResource("../Images/file-icon.png")));
		btnChooseFile.setBounds(480, 10, 32, 32);
		pnFile.add(btnChooseFile);

		// btnChooseFolder
		btnChooseFolder = new JButton();
		btnChooseFolder.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChooseFolder.setContentAreaFilled(false);
		btnChooseFolder.setIcon(new ImageIcon(MainUI.class.getResource("../Images/folder-icon.png")));
		btnChooseFolder.setBounds(515, 10, 32, 32);
		pnFile.add(btnChooseFolder);

		JPanel pnSave = new JPanel();
		pnSave.setLayout(null);
		pnSave.setBounds(20, 130, 610, 50);
		pnSave.setBackground(new Color(232, 198, 106));
		pnEncrypt.add(pnSave);

		// Save File
		JLabel lblSave = new JLabel();
		lblSave.setText("<html><font color='#e10304'>Save: </font></html>");
		lblSave.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSave.setBounds(10, 10, 75, 32);
		pnSave.add(lblSave);

		// txtSaveFolder
		txtSaveFolder = new JTextField(20);
		txtSaveFolder.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtSaveFolder.setEditable(false);
		txtSaveFolder.setBackground(Color.WHITE);
		txtSaveFolder.setBounds(100, 10, 380, 32);
		pnSave.add(txtSaveFolder);

		// btnSaveFolder
		btnSaveFolder = new JButton();
		btnSaveFolder.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSaveFolder.setContentAreaFilled(false);
		btnSaveFolder.setIcon(new ImageIcon(MainUI.class.getResource("../Images/folder-icon.png")));
		btnSaveFolder.setBounds(485, 10, 32, 32);
		pnSave.add(btnSaveFolder);

		JPanel pnKey = new JPanel();
		pnKey.setLayout(null);
		pnKey.setBounds(20, 190, 610, 50);
		pnKey.setBackground(new Color(232, 198, 106));
		pnEncrypt.add(pnKey);

		// Key
		JLabel lblKey = new JLabel();
		lblKey.setText("<html><font color='#e10304'>Key: </font></html>");
		lblKey.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblKey.setBounds(10, 10, 75, 32);
		pnKey.add(lblKey);

		// txtKey
		txtKey = new JTextField(20);
		txtKey.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtKey.setEditable(false);
		txtKey.setBackground(Color.WHITE);
		txtKey.setBounds(100, 10, 380, 32);
		pnKey.add(txtKey);

		// btnKey
		btnKey = new JButton();
		btnKey.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnKey.setContentAreaFilled(false);
		btnKey.setIcon(new ImageIcon(MainUI.class.getResource("../Images/file-icon.png")));
		btnKey.setBounds(485, 10, 32, 32);
		pnKey.add(btnKey);

		JPanel pnMD5 = new JPanel();
		pnMD5.setLayout(null);
		pnMD5.setBounds(20, 250, 610, 50);
		pnMD5.setBackground(new Color(232, 198, 106));
		pnEncrypt.add(pnMD5);

		// MD5
		JLabel lblMD5 = new JLabel();
		lblMD5.setText("<html><font color='#e10304'>MD5: </font></html>");
		lblMD5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMD5.setBounds(10, 10, 75, 32);
		pnMD5.add(lblMD5);

		// txtFileMD5
		txtFileMD5Encrypt = new JTextField(20);
		txtFileMD5Encrypt.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtFileMD5Encrypt.setEditable(false);
		txtFileMD5Encrypt.setBackground(Color.WHITE);
		txtFileMD5Encrypt.setBounds(100, 10, 380, 32);
		pnMD5.add(txtFileMD5Encrypt);

		// btnHashFile
		btnHashFileEncrypt = new JButton();
		btnHashFileEncrypt.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHashFileEncrypt.setContentAreaFilled(false);
		btnHashFileEncrypt.setIcon(new ImageIcon(MainUI.class.getResource("../Images/file-icon.png")));
		btnHashFileEncrypt.setBounds(485, 10, 32, 32);
		pnMD5.add(btnHashFileEncrypt);

		// btnMD5
		btnMD5Encrypt = new JButton();
		btnMD5Encrypt.setText("MD5");
		btnMD5Encrypt.setContentAreaFilled(true);
		btnMD5Encrypt.setBackground(Color.WHITE);
		btnMD5Encrypt.setBounds(520, 10, 80, 32);
		pnMD5.add(btnMD5Encrypt);

		JPanel pnProcessBar = new JPanel();
		pnProcessBar.setLayout(null);
		pnProcessBar.setBounds(20, 310, 610, 50);
		pnProcessBar.setBackground(new Color(232, 198, 106));
		pnEncrypt.add(pnProcessBar);

		// progressbar
		JLabel lblProcess = new JLabel();
		lblProcess.setText("<html><font color='#e10304'>Process: </font></html>");
		lblProcess.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblProcess.setBounds(10, 10, 75, 32);
		pnProcessBar.add(lblProcess);

		pBar = new JProgressBar();
		pBar.setBounds(100, 10, 320, 32);
		pBar.setForeground(Color.green);
		pBar.setBackground(new Color(255, 255, 255));
		pBar.setStringPainted(true);
		pBar.setValue(0);
		pnProcessBar.add(pBar);

		// btnEncrypt
		btnEncrypt = new JButton();
		btnEncrypt.setContentAreaFilled(true);
		btnEncrypt.setBackground(Color.WHITE);
		btnEncrypt.setText("Encrypt");
		btnEncrypt.setBounds(430, 10, 80, 32);
		pnProcessBar.add(btnEncrypt);

		// btnDecrypt
		btnDecrypt = new JButton();
		btnDecrypt.setContentAreaFilled(true);
		btnDecrypt.setBackground(Color.WHITE);
		btnDecrypt.setText("Decrypt");
		btnDecrypt.setBounds(520, 10, 80, 32);
		pnProcessBar.add(btnDecrypt);

		// text Area
		txtMessage = new JTextArea();
		txtMessage.setBackground(Color.WHITE);
		txtMessage.setForeground(Color.BLACK);
		txtMessage.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtMessage.setEditable(false);
		txtMessage.setWrapStyleWord(true);
		txtMessage.setLineWrap(true);
		JScrollPane sc = new JScrollPane(txtMessage,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sc.setBounds(5, 380, 640, 220);
		pnEncrypt.add(sc);

		con.add(pnEncrypt);
	}

	public void addEvents() {
		// TODO Auto-generated method stub

		btnSaveKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/" + cbxAlgorithm.getSelectedItem()));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showSaveDialog(getContentPane());
				if (result == JFileChooser.APPROVE_OPTION) {
					txtSaveKey.setText(fileChooser.getSelectedFile().getAbsolutePath().toString());
				}
			}
		});

		btnGenKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cbxAlgorithm.getSelectedItem() == "AES") {
					AES aes = new AES();
					aes.generateKey(txtSaveKey.getText());
				}
				else if (cbxAlgorithm.getSelectedItem() == "DES") {
					DES des = new DES();
					des.generateKey(txtSaveKey.getText());
				}
				else if (cbxAlgorithm.getSelectedItem() == "RSA") {
					RSA rsa = new RSA();
					rsa.generateKey(txtSaveKey.getText());
				}
				updateMessage("Generate key complete !!");
			}
		});

		btnChooseFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/" + cbxAlgorithm.getSelectedItem()));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showSaveDialog(getContentPane());
				if (result == JFileChooser.APPROVE_OPTION) {
					txtFile.setText(fileChooser.getSelectedFile().getAbsolutePath().toString());
				}
			}
		});

		btnChooseFolder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/" + cbxAlgorithm.getSelectedItem()));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showSaveDialog(getContentPane());
				if (result == JFileChooser.APPROVE_OPTION) {
					txtFile.setText(fileChooser.getSelectedFile().getAbsolutePath().toString());
				}
			}
		});

		btnSaveFolder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/" + cbxAlgorithm.getSelectedItem()));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showSaveDialog(getContentPane());
				if (result == JFileChooser.APPROVE_OPTION) {
					txtSaveFolder.setText(fileChooser.getSelectedFile().getAbsolutePath().toString());
				}
			}
		});

		btnKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/" + cbxAlgorithm.getSelectedItem()));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showSaveDialog(getContentPane());
				if (result == JFileChooser.APPROVE_OPTION) {
					txtKey.setText(fileChooser.getSelectedFile().getAbsolutePath().toString());
				}
			}
		});

		btnEncrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String filePath = txtFile.getText();
				File file = new File(filePath);
				if (file.isDirectory()) {
					String inputFilePath = "";

					String keyFilePath = txtKey.getText();

					File inputFile = null;

					String outputFilePath = "";

					try {
						if (cbxAlgorithm.getSelectedItem() == "AES") {
							AES aes = new AES();
							for (File f : file.listFiles()) {
								inputFilePath = txtFile.getText() + "\\" + f.getName();
								inputFile = new File(inputFilePath);
								outputFilePath = txtSaveFolder.getText() + "\\" + aes.encryptString(inputFile.getName(), aes.setKey(keyFilePath));
								File outputFile = new File(outputFilePath);
								aes.encryptFile(inputFile, aes.setKey(keyFilePath), outputFile);
								setTextMesageColor(Color.black);
								updateMessage("Encrypted '" + inputFile.getName() +"' file completed !!");
							}
						}
						else if (cbxAlgorithm.getSelectedItem() == "DES") {
							DES des = new DES();
							for (File f : file.listFiles()) {
								inputFilePath = txtFile.getText() + "\\" + f.getName();
								inputFile = new File(inputFilePath);
								outputFilePath = txtSaveFolder.getText() + "\\" + des.encryptString(inputFile.getName(), des.setKey(keyFilePath));
								File outputFile = new File(outputFilePath);
								des.encryptFile(inputFile, des.setKey(keyFilePath), outputFile);
								setTextMesageColor(Color.black);
								updateMessage("Encrypted '" + inputFile.getName() +"' file completed !!");
							}
						}
						else if (cbxAlgorithm.getSelectedItem() == "RSA") {
							RSA rsa = new RSA();
							for (File f : file.listFiles()) {
								inputFilePath = txtFile.getText() + "\\" + f.getName();
								inputFile = new File(inputFilePath);
								outputFilePath = txtSaveFolder.getText() + "\\" + rsa.encryptString(inputFile.getName(), rsa.setPublicKeyRSA(keyFilePath));
								File outputFile = new File(outputFilePath);
								rsa.encryptFile(inputFile, rsa.setPublicKeyRSA(keyFilePath), outputFile);
								setTextMesageColor(Color.black);
								updateMessage("Encrypted '" + inputFile.getName() +"' file completed !!");
							}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						setTextMesageColor(Color.RED);
						updateMessage("Error: "+ e1.getMessage());
						e1.printStackTrace();
					}
				}
				else {
					String inputFilePath = txtFile.getText();

					String keyFilePath = txtKey.getText();

					File inputFile = new File(inputFilePath);

					String outputFilePath = "";

					try {
						if (cbxAlgorithm.getSelectedItem() == "AES") {
							AES aes = new AES();
							outputFilePath = txtSaveFolder.getText() + "\\" + aes.encryptString(inputFile.getName(), aes.setKey(keyFilePath));
							File outputFile = new File(outputFilePath);
							aes.encryptFile(inputFile, aes.setKey(keyFilePath), outputFile);
						}
						else if (cbxAlgorithm.getSelectedItem() == "DES") {
							DES des = new DES();
							outputFilePath = txtSaveFolder.getText() + "\\" + des.encryptString(inputFile.getName(), des.setKey(keyFilePath));
							File outputFile = new File(outputFilePath);
							des.encryptFile(inputFile, des.setKey(keyFilePath), outputFile);
						}
						else if (cbxAlgorithm.getSelectedItem() == "RSA") {
							RSA rsa = new RSA();
							outputFilePath = txtSaveFolder.getText() + "\\" + rsa.encryptString(inputFile.getName(), rsa.setPublicKeyRSA(keyFilePath));
							File outputFile = new File(outputFilePath);
							rsa.encryptFile(inputFile, rsa.setPublicKeyRSA(keyFilePath), outputFile);
						}
						setTextMesageColor(Color.black);
						updateMessage("Encrypted '" + inputFile.getName() +"' file completed !!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						setTextMesageColor(Color.RED);
						updateMessage("Error: "+ e1.getMessage());
						e1.printStackTrace();
					}
				}
				return;
			}
		});

		btnDecrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String filePath = txtFile.getText();
				File file = new File(filePath);
				if (file.isDirectory()) {
					String inputFilePath = "";

					String keyFilePath = txtKey.getText();

					File inputFile = null;

					String outputFilePath = "";

					try {
						if (cbxAlgorithm.getSelectedItem() == "AES") {
							AES aes = new AES();
							for (File f : file.listFiles()) {
								inputFilePath = txtFile.getText() + "\\" + f.getName();
								inputFile = new File(inputFilePath);
								outputFilePath = txtSaveFolder.getText() + "\\" + aes.decryptString(inputFile.getName(), aes.setKey(keyFilePath));
								File outputFile = new File(outputFilePath);
								aes.decryptFile(inputFile, aes.setKey(keyFilePath), outputFile);
								setTextMesageColor(Color.black);
								updateMessage("Decrypt file completed !!");
							}
						}
						else if (cbxAlgorithm.getSelectedItem() == "DES") {
							DES des = new DES();
							for (File f : file.listFiles()) {
								inputFilePath = txtFile.getText() + "\\" + f.getName();
								inputFile = new File(inputFilePath);
								outputFilePath = txtSaveFolder.getText() + "\\" + des.decryptString(inputFile.getName(), des.setKey(keyFilePath));
								File outputFile = new File(outputFilePath);
								des.decryptFile(inputFile, des.setKey(keyFilePath), outputFile);
								setTextMesageColor(Color.black);
								updateMessage("Decrypt file completed !!");
							}
						}
						else if (cbxAlgorithm.getSelectedItem() == "RSA") {
							RSA rsa = new RSA();
							for (File f : file.listFiles()) {
								inputFilePath = txtFile.getText() + "\\" + f.getName();
								inputFile = new File(inputFilePath);
								outputFilePath = txtSaveFolder.getText() + "\\" + rsa.decryptString(inputFile.getName(), rsa.setPrivateKeyRSA(keyFilePath));
								File outputFile = new File(outputFilePath);
								rsa.decryptFile(inputFile, rsa.setPrivateKeyRSA(keyFilePath), outputFile);
								setTextMesageColor(Color.black);
								updateMessage("Decrypt file completed !!");
							}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						setTextMesageColor(Color.RED);
						updateMessage("Error: "+ e1.getMessage());
						e1.printStackTrace();
					}
				}
				else {
					String inputFilePath = txtFile.getText();

					String keyFilePath = txtKey.getText();
					File inputFile = new File(inputFilePath);

					String outputFilePath = "";

					try {
						if (cbxAlgorithm.getSelectedItem() == "AES") {
							AES aes = new AES();
							outputFilePath = txtSaveFolder.getText() + "\\" + aes.decryptString(inputFile.getName(), aes.setKey(keyFilePath));
							File outputFile = new File(outputFilePath);
							aes.decryptFile(inputFile, aes.setKey(keyFilePath), outputFile);
						}
						else if (cbxAlgorithm.getSelectedItem() == "DES") {
							DES des = new DES();
							outputFilePath = txtSaveFolder.getText() + "\\" + des.decryptString(inputFile.getName(), des.setKey(keyFilePath));
							File outputFile = new File(outputFilePath);
							des.decryptFile(inputFile, des.setKey(keyFilePath), outputFile);
						}
						else if (cbxAlgorithm.getSelectedItem() == "RSA") {
							RSA rsa = new RSA();
							outputFilePath = txtSaveFolder.getText() + "\\" + rsa.decryptString(inputFile.getName(), rsa.setPrivateKeyRSA(keyFilePath));
							File outputFile = new File(outputFilePath);
							rsa.decryptFile(inputFile, rsa.setPrivateKeyRSA(keyFilePath), outputFile);
						}
						setTextMesageColor(Color.black);
						updateMessage("Decrypt file completed !!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						setTextMesageColor(Color.red);
						updateMessage("Error: " + e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});

		btnHashFileEncrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/" + cbxAlgorithm.getSelectedItem()));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showSaveDialog(getContentPane());
				if (result == JFileChooser.APPROVE_OPTION) {
					txtFileMD5Encrypt.setText(fileChooser.getSelectedFile().getAbsolutePath().toString());
				}
			}
		});

		btnMD5Encrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file = new File(txtFileMD5Encrypt.getText());
				HashMD5 md5 = new HashMD5();
				try {
					updateMessage("MD5 of '" + file.getName() + "': " + md5.getFileChecksum(file));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void showWindow() {
		this.setSize(660, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//this.setBackground(new Color(255, 255, 255));
		this.getContentPane().setBackground(new Color(255, 255, 255));
		this.getContentPane().setLayout(null);
		this.setVisible(true);
	}
}
