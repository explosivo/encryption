package com.zachnickell.encrypt;

import java.util.Random;

public class Encryption
{
	public static Matrix createKey()
	{
		Random rand = new Random(System.currentTimeMillis());
		int n = 3;
		Matrix key = new Matrix(n, n);
		for (int y = 0; y < n; y ++)
		{
			for (int x = 0; x < n; x ++)
			{
				double element = rand.nextInt(255) + 1;
				key.set(element, y, x);
			}
		}
		return key;
	}

	public static Matrix encrypt(Matrix key, String s)
	{
		int height = 3;
		int width = s.length() / 3 + s.length() % 3;
		Matrix result = new Matrix(height, width);
		System.out.println("\nencrypt started");
		System.out.println("key");
		key.print();
		
		int i = 0;
		for (int y = 0; y < height; y ++)
		{
			for (int x = 0; x < width; x ++)
			{
				if (i >= s.length())
				{
					break;	
				}
				else 
				{
					result.set((double) s.charAt(i), y, x);
				}
				i ++;
			}
		}
		result.print();
		key.multiply(result).print();
		return key.multiply(result);
	}

	public static String decrypt(Matrix key, Matrix m)
	{
		String message = "";
		System.out.println("\n\ndecrypt started");
		System.out.println("key");
		key.print();
		System.out.println("inverse");
		key.inverse().print();
		System.out.println("m");
		m.print();
		Matrix antikey = key.inverse();
		System.out.println("message matrix");
		antikey.multiply(m).print();
		Matrix matrix = key.inverse().multiply(m);
		for (int y = 0; y < matrix.height; y ++)
		{
			for (int x = 0; x < matrix.width; x ++)
			{
				if(matrix.get(y, x) == 0)
				{
					break;
				}
				message += (char) Math.round(matrix.get(y, x));
			}
		}
		return message;
	}
}
