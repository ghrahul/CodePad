package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.xml.soap.Text;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.ImageIcon;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.autocomplete.*;


public class TextEditor extends JFrame {

	private JPanel contentPane;
	RSyntaxTextArea textArea = new RSyntaxTextArea();
	String filename;
	String str;
	int n,position;

	/**0
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextEditor frame = new TextEditor();
					frame.setTitle("CodePad");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TextEditor() {
		
		// adding syntax highlighting
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		//adding auto complete functionality
		
		CompletionProvider provider = createCompletionProvider();
		AutoCompletion ac = new AutoCompletion(provider);
		ac.install(textArea);
		//
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 516);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon("C:\\Users\\rahul\\Downloads\\open.jpg"));
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK));
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// choosing file
				JFileChooser j = new JFileChooser("f:");
				//invoking file open dialog
				int r = j.showOpenDialog(null);
				
				if(r == JFileChooser.APPROVE_OPTION)
				{
					File fi = new File(j.getSelectedFile().getAbsolutePath());
					filename = j.getSelectedFile().getAbsolutePath();
					try {
						
						String s1 = "", sl = "";
						FileReader fr = new FileReader(fi);
						BufferedReader br = new BufferedReader(fr);
						sl = br.readLine();
						while((s1 = br.readLine()) != null)
						{
							sl = sl + "\n" +s1;
						}
						textArea.setText(sl);
						
					} catch (Exception e2) {
						// TODO: handle exception
						
						e2.printStackTrace();
					}
					
				}
				
				
				TextEditor.this.setTitle(filename);
				
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setIcon(new ImageIcon("C:\\Users\\rahul\\Downloads\\new (2).jpg"));
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(filename == null)
				{
					TextEditor.this.setTitle("New File *");
				}
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon("C:\\Users\\rahul\\Downloads\\save.jpg"));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(filename == null)
				{
					
					FileDialog dialog1 = new FileDialog(TextEditor.this, "Save As", FileDialog.SAVE);
					dialog1.setVisible(true);
					String s7 = dialog1.getDirectory();
					String s8 = dialog1.getFile();
					String s9 = s7+ s8;
					String s6 = textArea.getText();
					int len1 = s6.length();
					byte buf[] = s6.getBytes();
					File f1 = new File(s9);
					FileOutputStream fobj1 = null;
					
					try {
						
						fobj1 = new FileOutputStream(f1);
						
						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
					
					for(int k=0;k<len1;k++)
					{
						try {
							fobj1.write(buf[k]);
							
						} catch (IOException e2) {
							
							e2.printStackTrace();
							// TODO: handle exception
						}
					}
					
					try {
						
						fobj1.close();
						
					} catch (IOException e3) {
						
						e3.printStackTrace();
						// TODO: handle exception
					}
					
					TextEditor.this.setTitle(dialog1.getFile());
					
				}
				
				else
				{
					String s6 = textArea.getText();
					int len1 = s6.length();
					byte buf[] = s6.getBytes();
					File f1 = new File(filename);
					FileOutputStream fobj1 = null;
                    try {
						
						fobj1 = new FileOutputStream(f1);
						
						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
                    for(int k=0;k<len1;k++)
					{
						try {
							fobj1.write(buf[k]);
							
						} catch (IOException e2) {
							
							e2.printStackTrace();
							// TODO: handle exception
						}
					}
                    
                    try {
						
						fobj1.close();
						
					} catch (IOException e3) {
						
						e3.printStackTrace();
						// TODO: handle exception
					}
                    TextEditor.this.setTitle(filename);
					
					
				}
				
				
				
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as");
		mntmSaveAs.setIcon(new ImageIcon("C:\\Users\\rahul\\Downloads\\saveas.jpg"));
		mntmSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileDialog dialog1 = new FileDialog(TextEditor.this, "Save As", FileDialog.SAVE);
				dialog1.setVisible(true);
				String s7 = dialog1.getDirectory();
				String s8 = dialog1.getFile();
				String s9 = s7+ s8;
				String s6 = textArea.getText();
				int len1 = s6.length();
				byte buf[] = s6.getBytes();
				File f1 = new File(s9);
				FileOutputStream fobj1 = null;
				
				try {
					
					fobj1 = new FileOutputStream(f1);
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				for(int k=0;k<len1;k++)
				{
					try {
						fobj1.write(buf[k]);
						
					} catch (IOException e2) {
						
						e2.printStackTrace();
						// TODO: handle exception
					}
				}
				
				try {
					
					fobj1.close();
					
				} catch (IOException e3) {
					
					e3.printStackTrace();
					// TODO: handle exception
				}
				
				TextEditor.this.setTitle(dialog1.getFile());
				
				
			}
		});
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon("C:\\Users\\rahul\\Downloads\\exit.jpg"));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnNewMenu = new JMenu("Edit");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				str = textArea.getSelectedText();
			}
		});
		mntmCopy.setIcon(new ImageIcon("C:\\Users\\rahul\\Downloads\\copy.jpg"));
		mnNewMenu.add(mntmCopy);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		mntmCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    str = textArea.getSelectedText();
				n = textArea.getText().indexOf(str);
				textArea.replaceRange("", n, str.length());
				
			}
		});
		mntmCut.setIcon(new ImageIcon("C:\\Users\\rahul\\Downloads\\cut(1).jpg"));
		mnNewMenu.add(mntmCut);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		mntmPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				position = textArea.getCaretPosition();
				textArea.insert(str, position);
			}
		});
		mntmPaste.setIcon(new ImageIcon("C:\\Users\\rahul\\Downloads\\paste.jpg"));
		mnNewMenu.add(mntmPaste);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.EAST);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, BorderLayout.SOUTH);
	}

	private CompletionProvider createCompletionProvider() {
		//this is a default completion provider which does not need to know any language symantics
		DefaultCompletionProvider provider = new DefaultCompletionProvider();
		
		//adding some basic completion
		provider.addCompletion(new BasicCompletion(provider, "abstract"));
		provider.addCompletion(new BasicCompletion(provider, "assert"));
		provider.addCompletion(new BasicCompletion(provider, "break"));
		provider.addCompletion(new BasicCompletion(provider, "case"));
		provider.addCompletion(new BasicCompletion(provider, "trasient"));
		provider.addCompletion(new BasicCompletion(provider, "try"));
		provider.addCompletion(new BasicCompletion(provider, "try"));
		provider.addCompletion(new BasicCompletion(provider, "void"));
		provider.addCompletion(new BasicCompletion(provider, "volatile"));
		provider.addCompletion(new BasicCompletion(provider, "while"));
		
		//these are some shorthand completions
		provider.addCompletion(new ShorthandCompletion(provider, "sysout", "System.out.println(","System.out.println("));
		provider.addCompletion(new ShorthandCompletion(provider, "syserr", "System.err.println(","System.err.println("));
		return provider;
		
		
		
		
	}

}
