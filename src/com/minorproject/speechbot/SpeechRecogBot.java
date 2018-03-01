package com.minorproject.speechbot;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechRecogBot {

	static String resultText;
	static boolean wordopen=false;  // when word is not open then no operation can be performed,so initially set as false
    public static void main(String[] args) throws InstantiationException {
  
    	
    	
//-----------------------------| GUI COMPONENTS KO PART HO YO|-----------------------------------------//	
    	/* Main Container */
    	JFrame SpeechFrame=new JFrame("Speech Recognition");
    	SpeechFrame.setSize(480,530);
    	SpeechFrame.setLocationRelativeTo(null);									
		SpeechFrame.setResizable(false); 
		SpeechFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel mainBackgoundImage=new JLabel ("");								
		SpeechFrame.add(mainBackgoundImage);										
		mainBackgoundImage.setIcon(new ImageIcon ("data"+File.separator+"bg.png")); 
		SpeechFrame.setVisible(true);
		mainBackgoundImage.setOpaque(false);
		
		/* Info Button */
		JButton btnInfo=new JButton(new ImageIcon ("data"+File.separator+File.separator+"info.png"));
		mainBackgoundImage.add(btnInfo);
		btnInfo.setRolloverIcon(new ImageIcon ("data"+File.separator+File.separator+"info_actv.png"));
		btnInfo.setBounds(40,210,80,80);
		btnInfo.setToolTipText("About This Application");
		btnInfo.setBorderPainted(false);
		
		// Action Listener for Info Button
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	        JOptionPane.showMessageDialog(null, "<html><center><h3>Minor Project on Speech Recognition Bot</h3>"+ "</center><br/>A Minor Project research on Speech Recognition Technology by Nikesh Sitaula <br/>"+ "  </html>");
	            }
	        });
		
		/* How to use Button */
		JButton btnHowToUse=new JButton(new ImageIcon ("data"+File.separator+File.separator+"help.png"));
		mainBackgoundImage.add(btnHowToUse);
		btnHowToUse.setRolloverIcon(new ImageIcon ("data"+File.separator+File.separator+"help_actv.png"));
		btnHowToUse.setBounds(130,210,80,80);
		btnHowToUse.setToolTipText("How To Use");
		btnHowToUse.setBorderPainted(false);
		
		// Action Listener for How to use Button
		btnHowToUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   JOptionPane.showMessageDialog(null, "<html> <h3> HOW TO USE </h3> Start the application and u are ready to go<br/> Use commands like: <br><i><ul><li>Hey Computer</li><li>Open Menu</li><li> Open File</li><li>Move Up</li><li>Move Down</li> </ul>more commands be added soon<br/></html>");
			       }
			   });
		
		
		
		        //Pane to display 
				JTextArea userSpeechDisp=new JTextArea("");
				mainBackgoundImage.add(userSpeechDisp);
				//userSpeechDisp.setLineWrap(true);
				//userSpeechDisp.setWrapStyleWord(true);
				userSpeechDisp.setBounds(48,290,390,65);
				userSpeechDisp.setForeground(new Color(241, 144, 0));
				userSpeechDisp.setFont(new Font("Cambria" , Font.BOLD, 24));
				Border border = BorderFactory.createLineBorder(new Color(0, 84, 186));
				userSpeechDisp.setBorder(BorderFactory.createCompoundBorder(border, 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
				userSpeechDisp.setEditable(false);
				
		
				JTextArea slnDisp=new JTextArea("");
				mainBackgoundImage.add(slnDisp);
				slnDisp.setLineWrap(true);
				slnDisp.setWrapStyleWord(true);
				slnDisp.setForeground(new Color(241, 144, 0));
				slnDisp.setFont(new Font("Cambria" , Font.BOLD, 24));
				slnDisp.setBounds(48,370,390,65);
				slnDisp.setBorder(BorderFactory.createCompoundBorder(border, 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
				slnDisp.setEditable(false);
				
				// Listening Notification
				JLabel listening=new JLabel("");
				listening.setForeground(Color.RED);
				listening.setFont(new Font("Cambria" , Font.BOLD, 24));
				mainBackgoundImage.add(listening);
				listening.setBounds(30,180,290,30);
				listening.setText("Loading");
				
							
//--------------------------------| SPEECH RECOGNITION COMPONENTS KO PART|-----------------------------------------//	
		
//Loading Grammars and xml config files
	ConfigurationManager cm;
if (args.length > 0) {
		cm = new ConfigurationManager(args[0]);
} else {
		cm = new ConfigurationManager(SpeechRecogBot.class.getResource("SpeechRecogBot.config.xml"));
		}
//--------------------------------| Welcome Message |-------------------------------------------------------//
try{

Runtime.getRuntime().exec("Say "+"\""+"Welcome Sir"+"\"");


} catch(Exception er) {}

	Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
	recognizer.allocate();
	listening.setText("Listening");
// start the microphone or exit if not
		        Microphone microphone = (Microphone) cm.lookup("microphone");
		        if (!microphone.startRecording()) {
		            JOptionPane.showMessageDialog(null,"Cannot start microphone.","Error",JOptionPane.ERROR_MESSAGE);
		            recognizer.deallocate();
		            System.exit(1);
		        }
		        
		        
		        
		        // loop the recognition until the program exits.
		        
		        while (true) {
		        	listening.setVisible(true);
		            Result result = recognizer.recognize();
		            
		    
		            if (result != null) {
		                resultText = result.getBestFinalResultNoFiller();
		                userSpeechDisp.setText("You said: " + '\n' + resultText );
		                
		                
		               
		                

		  		if (resultText.equalsIgnoreCase("stop recognition") || (resultText.equalsIgnoreCase("good bye computer")))
		  		{
		  			try{
		  				slnDisp.setText("Good Bye! See you again") ;
		  				TimeUnit.SECONDS.sleep(3);
		  				microphone.clear();
		  				System.exit(0);
		  			   }catch(Exception ae){}
		        }
		  		
		  		 if(resultText.equalsIgnoreCase("open file"))
					{
						try{
							if(wordopen==true){
							slnDisp.setText("open file" ) ;
							System.out.println("open file choosed");
							TestRobot.openFile();
							TimeUnit.SECONDS.sleep(2);
							microphone.clear();
							}
						} catch(Exception er) {}
					} 
		  		 
		  		 
		  		 if(resultText.equalsIgnoreCase("save file"))
					{
						try{
							if(wordopen==true){
							slnDisp.setText("save file" ) ;
							System.out.println("save file choosed");
							
							TestRobot.saveFile();
							
							TimeUnit.SECONDS.sleep(2);
							microphone.clear();
							}else System.out.println("cannout perform when word is not open");
						} catch(Exception er) {}
					} 
		  		 
		  		 
		  		 
		  		 if(resultText.equalsIgnoreCase("close"))
					{
						try{
							if(wordopen==true){
							slnDisp.setText("close file" );
							System.out.println("close file choosed");
							
							TestRobot.close();
							
							TimeUnit.SECONDS.sleep(1);
							microphone.clear();
							}
						} catch(Exception er) {}
					} 
		  		 
		  		 /// file from word
		  		 
		  		if(resultText.equalsIgnoreCase("open menu"))
				{
					try{
						if(wordopen==true) {
						slnDisp.setText("file");
						System.out.println("open menu");
						
						TestRobot.file();
						System.out.println("file menu selected");
						TimeUnit.SECONDS.sleep(1);
						microphone.clear();
						}else System.out.println("cannout perform when word is not open");
					} catch(Exception er) {}
				} 
		  		
		  		 /////up down///////
		  		if(resultText.equalsIgnoreCase("move up"))
				{
					try{
						
						slnDisp.setText("moving" ) ;
						System.out.println("moving");
						
						TestRobot.up();
						TimeUnit.SECONDS.sleep(0);
						microphone.clear();
					} catch(Exception er) {}
				} 
		  		
		  		if(resultText.equalsIgnoreCase("move down"))
				{
					try{
						
						slnDisp.setText("moving" ) ;
						System.out.println("moving");
						
						TestRobot.down();
						
						TimeUnit.SECONDS.sleep(0);
						microphone.clear();
					} catch(Exception er) {}
				}
		  		
		  		if(resultText.equalsIgnoreCase("move right"))
				{
					try{
						
						slnDisp.setText("moving" ) ;
						System.out.println("moving");
						
						TestRobot.right();
						
						TimeUnit.SECONDS.sleep(0);
						microphone.clear();
					} catch(Exception er) {}
				}		  		
		  		if(resultText.equalsIgnoreCase("move left"))
				{
					try{
						
						slnDisp.setText("moving" ) ;
						System.out.println("moving");
						
						TestRobot.left();
						
						TimeUnit.SECONDS.sleep(0);
						microphone.clear();
					} catch(Exception er) {}
				}
		  		
		  		if(resultText.equalsIgnoreCase("insert menu"))
				{
					try{
						
						slnDisp.setText("insert menu" ) ;
						System.out.println("working");
						
						TestRobot.insertMenu();
						
						TimeUnit.SECONDS.sleep(1);
						microphone.clear();
					} catch(Exception er) {}
				}
		  		
		  		if(resultText.equalsIgnoreCase("design menu"))
				{
					try{
						
						slnDisp.setText("design menu" ) ;
						System.out.println("working");
						
						TestRobot.designMenu();						
						TimeUnit.SECONDS.sleep(1);
						microphone.clear();
					} catch(Exception er) {}
				}
		  		
		  		if(resultText.equalsIgnoreCase("layout menu"))
				{
					try{
						
						slnDisp.setText("layout menu" ) ;
						System.out.println("working");
						
						TestRobot.layoutMenu();
						
						TimeUnit.SECONDS.sleep(1);
						microphone.clear();
					} catch(Exception er) {}
				}
		  		
		  		if(resultText.equalsIgnoreCase("print file"))
				{
					try{
						if(wordopen==true) {
						slnDisp.setText("print file" ) ;
						System.out.println("printing file");
						
						TestRobot.printFile();						
						TimeUnit.SECONDS.sleep(1);
						microphone.clear();
						}else System.out.println("word not open so cannot perform");
					} catch(Exception er) {}
				}
		  		
		  		if(resultText.equalsIgnoreCase("print screen"))
				{
					try{
						
						slnDisp.setText("print screen" ) ;
						System.out.println("taking screenshot");
						
						TestRobot.printScreen();
						TimeUnit.SECONDS.sleep(1);
						microphone.clear();
						
						
					} catch(Exception er) {}
				}
		  		
		  		
		  		 ///////////////////ok//////////////////
		  		if(resultText.equalsIgnoreCase("ok") || resultText.equalsIgnoreCase("enter"))
				{
					try{
						if(wordopen==true) {
						slnDisp.setText("ok") ;
						System.out.println("ok or entered choosed");
						TestRobot.enter();
						TimeUnit.SECONDS.sleep(4);
						microphone.clear();
						}else System.out.println("word not open so cant perform");
					} catch(Exception er) {}
				} 
		  		
		  		
		  		if(resultText.equalsIgnoreCase("open word"))
				{
					try{
						wordopen = true;
						slnDisp.setText("opening word" ) ;
						String cmd = "C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.exe";  /* /mOpenPage C:\\Users\\anon\\Documents\\wordtest.docx"; */
						Runtime.getRuntime().exec(cmd);
						
						System.out.println("open word choosed");
						TimeUnit.SECONDS.sleep(3);
						microphone.clear();
					} catch(Exception er) {}
				} 

		  		
		            }
		        
		        
		        else {
		            	slnDisp.setText("I can't hear what you said.\n");
		        
		            }
		        }
		        		
		    			}

		}
			
