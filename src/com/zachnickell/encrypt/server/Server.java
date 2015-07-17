package com.zachnickell.encrypt.server;

import java.net.*;
import com.zachnickell.encrypt.*;

public class Server
{
	public static void main(String args[])
	{
		Matrix a = new Matrix(new double[][] {{3,4,6},{2,5,2},{1,2,7}});
		Matrix b = new Matrix(new double[][] {{8, 12},{5, 15},{12,0}});
		Matrix enc = a.multiply(b);
		
		a.inverse().print();
		a.inverse().multiply(enc).print();

		//a.inverse().multiply(enc).print();
		/*
		System.out.println("server");
		Matrix key = Encryption.createKey();
		String s = "hello";
		Matrix e = Encryption.encrypt(key, s);
		e.print();
		String back = Encryption.decrypt(key, e);
		System.out.println(back);
		*/
	}
}
