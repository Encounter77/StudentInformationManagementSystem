package com.bcxtm.solution.component;


import java.util.Scanner;

public class Keypad {
	private Scanner input;

	public Keypad() {
		input = new Scanner(System.in);
	}

	public String getInput() {
		return input.next();
	}
	
	public int getChoice() {
		return input.nextInt();
	}

}
