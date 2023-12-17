public class Myexcep{
	public static void main(String[] args) {
try

		{

			int ar[]= {5,1,7,3,2};

			System.out.println(ar[5]);

		}

		catch(ArithmeticException e)

		{

			e.printStackTrace();

			System.out.println("catch1");

		}

		catch(NullPointerException n)

		{

			n.printStackTrace();

			System.out.println("catch2");

		}

		catch(ArrayIndexOutOfBoundsException n)

		{

			n.printStackTrace();

			System.out.println("catch3");

		}

		catch(NumberFormatException n)

		{

			n.printStackTrace();

			System.out.println("catch4");

		}

		catch(Exception e)

		{

			e.printStackTrace();

			System.out.println("catch5");

		}

		finally

		{

			System.out.println("finally...");

		}
}
}
