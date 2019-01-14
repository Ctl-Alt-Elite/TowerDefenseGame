package firstday;

public class JavaTutorial1 {
	
	static double hours = 35.0;
	static double wage = 8.50;
	static int a, b, c, d;
	static double x, w, z;
	
	//Main method
	public static void main(String[] args) {
			
	}
	
	//Methods that prints given text but with a shorter method name.
	public static void print(String y) {
		System.out.println(y);
	}
	public static void print(int y) {
		System.out.println(y);
	}
	public static void print(Boolean y) {
		System.out.println(y);
	}
	public static void print(Double y) {
		System.out.println(y);
	}
	public static void print(Float y) {
		System.out.println(y);
	}
	public static void print(Long y) {
		System.out.println(y);
	}
	
	//Method to add two given integers.
	public static int addInt(int x, int y) {
		return (x+y);
	}
	public static double addDouble(double x, double y) {
		return (x+y);
	}
	
	//Calculate the paycheck of an employee.
	public static double calculatePaycheck(double hours, double wage) {
		double paycheck;
		if (hours>40) {paycheck = ((wage * 40)+((hours-40)*wage*1.5));}
		else if (hours<40&&hours>0) {paycheck = wage*hours;}
		else {paycheck = 0.0;}
		return paycheck;
	}
	
	//Convert double to US currency.
	public static double convertToDollars(double x) {
		return Math.round(x*100.0)/100.0;
	}
	
	//Change wage and hours worked.
	public void changeHours(double hours) {
		firstday.JavaTutorial1.hours = hours;
	}
	public void changeWage(double wage) {
		firstday.JavaTutorial1.wage = wage;
	}
}
