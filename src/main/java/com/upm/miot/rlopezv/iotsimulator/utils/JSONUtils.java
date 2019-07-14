/**
 *
 */
package com.upm.miot.rlopezv.iotsimulator.utils;

import com.google.gson.Gson;

/**
 * @author ramon
 *
 */


public final class JSONUtils {
	private static final Gson gson = new Gson();

	private JSONUtils() {
	}

	public static boolean isJSONValid(String jsonInString) {
		try {
			gson.fromJson(jsonInString, Object.class);
			return true;
		} catch (com.google.gson.JsonSyntaxException ex) {
			return false;
		}
	}

	public static boolean isJSONValid(byte[] jsonByteArray) {
		return isJSONValid(new String(jsonByteArray));
	}
}
