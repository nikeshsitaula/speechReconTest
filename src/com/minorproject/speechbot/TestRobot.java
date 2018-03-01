package com.minorproject.speechbot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class TestRobot {

static Robot robot;
	
	public static void saveFile() throws AWTException {
		robot= new Robot();
		
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_S);
        
	}
	
	public static void close() throws AWTException {
		robot= new Robot();
		
		robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);
        
	}
	
	public static void openFile() throws AWTException {
		robot= new Robot();
		
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_O);
        
	}
	
	public static void up() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_UP);
        robot.keyRelease(KeyEvent.VK_UP);    
	}
	
	public static void down() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
	}
	
	public static void enter() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
	}
	public static void right() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        
	}
	public static void left() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
        
	}
	
	public static void file() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F);
        
	}
	
	public static void insertMenu() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_N);
        
	}
	
	public static void designMenu() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_G);
        
	}
	public static void layoutMenu() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_P);
        
	}
	public static void printScreen() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_PRINTSCREEN);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
        robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
        
	}
	public static void printFile() throws AWTException {
		robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_P);
        
	}
	
	

}