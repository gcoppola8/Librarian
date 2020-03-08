package dev.coppola.librarian.core;

public class Registry {
	
	private Registry() {
	}
	private static Registry instance;

	public static Registry getInstance() {
		if (instance == null) {
			instance = new Registry();
		}
		return instance;
	}
	
	
}
