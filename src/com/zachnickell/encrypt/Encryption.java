package com.zachnickell.encrypt;

import java.util.Random;

public class Encryption
{
	public static Matrix createKey()
	{
		Random rand = new Random(System.currentTimeMillis());
		int n = 3;
		Matrix key = new Matrix(n, n);
		boolean isInvertible = false;
		while (!isInvertible)
		{
			for (int y = 0; y < n; y ++)
			{
				for (int x = 0; x < n; x ++)
				{
					double element = rand.nextInt(255) + 1;
					key.set(element, y, x);
				}
			}
			if (key.determinant() != 0)
			{
				isInvertible = true;
			}
		}
		return key;
	}

	public static Matrix encrypt(Matrix key, String s)
	{
		int height = 3;
		int width = s.length() / height + s.length() % height;
		Matrix result = new Matrix(height, width);
		
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
		return key.multiply(result);
	}

	public static String decrypt(Matrix key, Matrix m)
	{
		String message = "";
		Matrix antikey = key.inverse();
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
