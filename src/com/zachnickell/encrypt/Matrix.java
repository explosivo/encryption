package com.zachnickell.encrypt;

public class Matrix
{
	public int width, height;
	private double elements[][];

	public Matrix(int height, int width)
	{
		this.width = width;
		this.height = height;
		elements = new double[height][width];
	}

	public Matrix(double[][] elements)
	{
		this.elements = elements;
		width = elements[0].length;
		height = elements.length;
	}

	public Matrix(Matrix m)
	{
		this.width = m.width;
		this.height = m.height;
		elements = new double[height][width];
		for (int y = 0; y < height; y ++)
		{
			for (int x = 0; x < width; x ++)
			{
				set(m.get(y, x), y, x);
			}
		}
	}
	
	public Matrix(String s)
	{
		String tokens[] = s.split(" ");
    	for (int i = 0; i < tokens.length; i ++)
    	{
    		if (i == 0)
    		{
    			height = Integer.valueOf(tokens[i]);
    		}
    		else if (i == 1)
    		{
    			width = Integer.valueOf(tokens[i]);
    			elements = new double[height][width];
    		}
    		else
    		{
    			double element = Double.valueOf(tokens[i]);
    			set(element, (i - 2) / height, (i - 2) % width);
    		}
    	}
	}

	public double get(int y, int x)
	{
		return elements[y][x];
	}

	public void set(double n, int y, int x)
	{
		elements[y][x] = n;
	}

	public void print()
	{
		System.out.println("----------");
		for (int y = 0; y < height; y ++)
		{
			for (int x = 0; x < width; x ++)
			{
				System.out.print(get(y,x) + " ");	
			}
			System.out.println();
		}
		System.out.println("----------");
	}

	private int isEven(int i)
	{
		int result = -1;
		if (i % 2 == 0)
		{
			result = 1;
		}
		return result;
	}

	public Matrix transpose()
	{
		Matrix transposed = new Matrix(width, height);
		for (int y = 0; y < width; y ++)
		{
			for (int x = 0; x < height; x ++)
			{
				transposed.set(get(x,y), y, x);
			}
		}
		return transposed;
	}

	public double determinant()
	{
		double det = 0.0;
		if (width != height)
		{
			return det;
		}

		if (width == 2)
		{
			det = get(0, 0) * get(1, 1) - get(0, 1) * get(1, 0);
			return det;
		}

		for (int x = 0; x < width; x ++)
		{
			det += -isEven(x) * get(0, x) * partMatrix(0, x).determinant();
		}
		return det;
		
	}

	public Matrix partMatrix(int y, int x)
	{
		Matrix part = new Matrix(height - 1, width - 1);
		int yy = -1;
		for (int j = 0; j < height; j ++)
		{
			if (j == y)
			
				continue;
			yy ++;
			int xx = -1;
			for (int i = 0; i < width; i ++)
			{
				if (i == x)
					continue;
				part.set(get(j, i), yy, ++xx);
			}
		}
		return part;
	}

	public Matrix cofactor()
	{
		Matrix matrix = new Matrix(height, width);
		for (int y = 0; y < height; y ++)
		{
			for (int x = 0; x < width; x ++)
			{
				matrix.set(isEven(x) * -isEven(y) * partMatrix(y, x).determinant(), y, x);
			}
		}
		return matrix;
	}

	public Matrix inverse()
	{
		return cofactor().transpose().multiply(1.0/determinant());
	}
	
	public Matrix inverse3()
	{
		double a = get(0, 0);
		double b = get(0, 1);
		double c = get(0, 2);
		double d = get(1, 0);
		double e = get(1, 1);
		double f = get(1, 2);
		double g = get(2, 0);
		double h = get(2, 1);
		double i = get(2, 2);
		
		double determinant = a * e * i + b * f * g + c * d * h - c * e * g - b * d * i - a * f * h;
		
		System.out.println("det " + determinant);
		
		Matrix inverse = multiply(1 / determinant);
		
		return inverse;
	}

	public Matrix multiply(Matrix m)
	{
		Matrix result = null;
		if (width == m.height)
		{
			result = new Matrix(height, m.width);
			for (int y = 0; y < height; y ++)
			{
				for (int x = 0; x < m.width; x ++)
				{
					double element = 0;
					for (int i = 0; i < width; i ++)
					{
						element += get(y, i) * m.get(i, x);	 
					}
					result.set(element, y, x);
				}
			}
		}
		return result;
	}
	
	public Matrix multiply(double i)
	{
		Matrix result = null;
		result = new Matrix(height, width);
		for (int y = 0; y < height; y ++)
		{
			for (int x = 0; x < width; x ++)
			{
				result.set(i * get(y, x), y, x);
			}
		}
		return result;
	}

	public Matrix add(Matrix m)
	{
		Matrix result = null;
		if (height == m.height && width == m.width)
		{
			result = new Matrix(height, m.width);
			for (int y = 0; y < height; y ++)
			{
				for (int x = 0; x < width; x ++)
				{
					double sum = get(y, x) + m.get(y, x);
					result.set(sum, y, x);
				}
			}
		}
		return result;
	}

	public Matrix subtract(Matrix m)
	{
		Matrix result = null;
		if (height == m.height && width == m.width)
		{
			result = new Matrix(height, m.width);
			for (int y = 0; y < height; y ++)
			{
				for (int x = 0; x < width; x ++)
				{
					double sum = get(y, x) - m.get(y, x);
					result.set(sum, y, x);
				}
			}
		}
		return result;
	}
	
	public String toString()
	{
		String s = "";
		s += height + " " + width;
		for (int y = 0; y < height; y ++)
		{
			for (int x = 0; x < width; x ++)
			{
				s += " " + get(y, x);
			}
		}
		return s;
	}
}
