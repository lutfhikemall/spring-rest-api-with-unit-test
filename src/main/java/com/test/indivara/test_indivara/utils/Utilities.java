package com.test.indivara.test_indivara.utils;

import com.test.indivara.test_indivara.security.BCrypt;

public class Utilities {

	public static String hashPassword(String input) {
		return BCrypt.hashpw(input, BCrypt.gensalt());
	}

	public static boolean isValidPassword(String input, String hash) {
		boolean isValid = BCrypt.checkpw(input, hash);

		if (isValid) {
			return true;
		}

		return false;
	}
}
