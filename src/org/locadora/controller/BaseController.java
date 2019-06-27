package org.locadora.controller;

public class BaseController {
	
	public static boolean isNumero(String dado) {
		if (dado.matches("[0-9]*")) {
			return true;
		}
		return false;
	}
}
