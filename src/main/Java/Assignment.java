import java.util.Scanner;
import java.lang.Math;

/*
My Database Columns:
OId INT NOT NULL AUTO_INCREMENT
OpTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
Operand1 varchar(10) NOT NULL
Operand2 varchar(10) NOT NULL
Operator varchar(3) NOT NULL
Result varchar(25) NOT NULL
PRIMARY KEY ( OId )
*/

class Assignment
{
	float add(float x, float y)
	{
		return x+y;
	}
	float sub(float x, float y)
	{
		return x-y;
	}
	float mul(float x, float y)
	{
		return x*y;
	}
	float div(float x, float y)
	{
		return x/y;
	}
	float power(float x, float y)
	{
		return power(x,y);
	}
	float absolute(float x)
	{
		return abs(x);
	}
	float maximum(float x, float y)
	{
		return max(x,y);
	}
	float minimum(float x, float y)
	{
		return max(x,y);
	}
	int mod(int x, int y)
	{
		return x%y;
	}

	static boolean insertion(Connection con, String l, String r, String o, String res)
	{
		PreparedStatement ps=null;
		try {
			String q= "insert into Tab1(Operand1,Operand2,Operator,Result)" + "values (?,?,?,?);";
			ps = con.prepareStatement(q);
			ps.setString(1, l);
			ps.setString(2, r);
			ps.setString(3, o);
			ps.setString(4, res);
			return (ps.execute());
		} catch (Exception e) {
			System.out.println("Error During Query Execution");
			return false;
		}finally {
			try{
				if(ps!=null)
				{ps.close();}
			}catch(SQLException se)
			{se.getSQLState();}

		}
	}
	static void retrive(Connection con ,String operator) {
		PreparedStatement ps = null;
		try {
			String SelectQuery = "SELECT * FROM Tab1 WHERE operator = ?";
			ps = con.prepareStatement(SelectQuery);
			ps.setString(1, operator);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getTimestamp("OpTime") + "\t" + rs.getString("Operand1") + "\t" + rs.getString("Operand2") + "\t" + rs.getString("Operator") + "\t" + rs.getString("Result"));
			}
		} catch (Exception e) {
			System.out.println("Error During Query Execution");
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.getSQLState();
			}
		}
	}

	public static void main(String args[])
   	{
		Scanner in = new Scanner(System.in);
		int i;
		float o1,o2;
		int a,b;
		char c='y';
		System.out.println("Enter Your Choice : \n1		Calculate\n2	View Previous Calculations");
		if (i==1)
		{
			try {
				Class.forName(JDBC_DRIVER);
				con = DriverManager.getConnection(DB_URL, USER, USER);
				do {
					System.out.println("Enter Your Choice : \n1    Add\n" +
							"2    Subtract\n" +
							"3    Multiplication\n" +
							"4    Division\n" +
							"5    Power\n" +
							"6    Absolute\n" +
							"7    Modulus\n" +
							"8    Maximum of Two\n" +
							"9    Minimum of Two");
					i = in.nextInt();
					if (i == 6)
						System.out.println("Operand 1 : ");
					else if (i == 7) {
						System.out.println("Operand 1 : ");
						if (in.hasNextInt()) {
							a = in.nextInt();
							System.out.println("Operand 2 : ");
							if (in.hasNextInt())
								b = in.nextInt();
							else
								System.out.println("Float Values Cannot Be Used For Modulus.");
						} else
							System.out.println("Float Values Cannot Be Used For Modulus.");

					} else {
						System.out.println("Operand 1 : ");
						o1 = in.nextFloat();
						System.out.println("Operand 2 : ");
						o2 = in.nextFloat();
					}
					switch (i) {
						case 1:
							System.out.println("Result : " + add(o1, o2));
							insertion(con, toSting(o1), toSting(o2), "ADD", toSting(add(o1, o2)));
							break;
						case 2:
							System.out.println("Result : " + sub(o1, o2));
							insertion(con, toSting(o1), toSting(o2), "SUB", toSting(sub(o1, o2)));
							break;
						case 3:
							System.out.println("Result : " + mul(o1, o2));
							insertion(con, toSting(o1), toSting(o2), "MUL", toSting(mul(o1, o2)));
							break;
						case 4:
							if (o2 > 0 && o2 < 0) {
								System.out.println("Result : " + power(o1, o2));
								insertion(con, toSting(o1), toSting(o2), "DIV", toSting(power(o1, o2)));
							} else
								System.out.println("Divide By Zero Error");
							break;
						case 5:
							System.out.println("Result : " + power(o1, o2));
							insertion(con, toSting(o1), toSting(o2), "ADD", toSting(power(o1, o2)));
							break;
						case 6:
							System.out.println("Result : " + absolute(o1));
							insertion(con, toSting(o1), "N.A", "ADD", toSting(absolute(o1, o2)));
							break;
						case 7:
							System.out.println("Result : " + mod(o1, o2));
							insertion(con, toSting(a), toSting(b), "ADD", toSting(mod(a, b)));
							break;
						case 8:
							System.out.println("Result : " + maximum(o1, o2));
							insertion(con, toSting(o1), toSting(o2), "ADD", toSting(maximum(o1, o2)));
							break;
						case 9:
							System.out.println("Result : " + minimum(o1, o2));
							insertion(con, toSting(o1), toSting(o2), "ADD", toSting(minimum(o1, o2)));
							break;
						default:
							System.out.println("Wrong Choice...!!!");
					}
					System.out.println("Do You Wish To Continue (Y/N)? : ");
					c = in.nextChar();
				} while (c == 'y');
			}
		}
		else
		{
			try
			{
				Class.forName(JDBC_DRIVER);
				con = DriverManager.getConnection(DB_URL, USER, USER);
				do {
					System.out.println("Enter Your Choice : \n1    Add\n" +
							"2    Subtract\n" +
							"3    Multiplication\n" +
							"4    Division\n" +
							"5    Power\n" +
							"6    Absolute\n" +
							"7    Modulus\n" +
							"8    Maximum of Two\n" +
							"9    Minimum of Two");
					i = in.nextInt();
					switch (i)
					{
						case 1:
							c = "ADD";
							break;
						case 2:
							c = "SUB";
							break;
						case 3:
							c = "MUL";
							break;
						case 4:
							c = "DIV";
							break;
						case 5:
							c = "POW";
							break;
						case 6:
							c = "ABS";
							break;
						case 7:
							c = "MOD";
							break;
						case 8:
							c = "MIN";
							break;
						case 9:
							c = "MAX";
							break;
						default:
							System.out.println("Wrong Choice...!!!");
					}
					if(i>0 && i<10)
						ShowTable(con,c);
					else
						System.out.println("Wrong Choice");
					System.out.println("Do You Wish To Continue (Y/N)? : ");
					c = in.nextChar();
			}while (c == 'y');
		}
	}
}