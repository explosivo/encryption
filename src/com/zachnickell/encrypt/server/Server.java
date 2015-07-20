package com.zachnickell.encrypt.server;

import java.net.*;
import com.zachnickell.encrypt.*;

public class Server
{
	public static void main(String args[])
	{
		System.out.println("server");
		Matrix key = Encryption.createKey();
		String s = "this is a test to see if this thing even works";
		Matrix e = Encryption.encrypt(key, s);
		String back = Encryption.decrypt(key, e);
		System.out.println("returned: " + back);
		System.out.println("original: " + s);
		
	}
}
