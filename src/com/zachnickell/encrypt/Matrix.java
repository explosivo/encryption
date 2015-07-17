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
				matrix.set(isEven(y) * -isEven(y) * partMatrix(y, x).determinant(), y, x);
			}
		}
		return matrix;
	}

	public Matrix inverse()
	{
		return cofactor().multiply(1.0/determinant()).transpose();
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
					int element = 0;
					for (int i = 0; i < width; i ++)
					{
						element += get(y, i) * m.get(i, x);	 
						System.out.println(element);
					}
					System.out.println();
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
}
