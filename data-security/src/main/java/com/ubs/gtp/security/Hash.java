package com.ubs.gtp.security;

import java.util.List;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;

import com.ubs.gtp.security.util.ApiPathUtil;

/**
 * Hash providing the salt prefix and the static method to generate the hash.
 * TODO: This class should not be exposed when in production.
 * 
 * @author Willy Lai
 * @since 0.1
 */

public class Hash {

	public static final String SALT = "6h892vnp'wq#of)*Rjs910-+(";

	public static String hash(String s, String user) {
		Sha256Hash hash = new Sha256Hash(s, new SimpleByteSource(SALT + user), 1024);
		return hash.toHex();
	}

	public static void main(String[] args) {

		String url = "http://localhost:8080/data-rest/api/client/123";
		List<String> paths = ApiPathUtil.getApiPath(url);
		for (String s : paths) {
			System.out.println(s);
		}
	}
}
