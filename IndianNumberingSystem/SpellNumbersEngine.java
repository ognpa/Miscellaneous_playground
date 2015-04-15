import java.util.Scanner;
import java.util.StringJoiner;
//TODO:Do negative numbers/
public class SpellNumbersEngine {
	private static final String SPACE_STR = " ";

	private static final String AND_STR = "and";
	
	public static void main(String args[]) {

		boolean isQuit = false;
		Scanner scanIn = null;

		SpellNumbersEngine m = new SpellNumbersEngine();

		while (!isQuit) {
			scanIn = new Scanner(System.in);

			System.out.println("Enter something here : ");
			String number = scanIn.nextLine();

			String str = m.convertToIndianNumberingSystem(number);
			System.out.println(str);
			System.out.println("Do you want to quit");
			String ans = scanIn.nextLine();
			if (ans.toLowerCase().equals("y")) {
				isQuit = true;
			}

		}
		scanIn.close();

	}

	public String convertToIndianNumberingSystem(String num) {

		// Step 1 divide it by the largest number
		StringJoiner sj = new StringJoiner(SPACE_STR);

		spellNumber(num, sj);
		String speltNumber=sj.toString();
		return speltNumber;

	}

	private static final String[] UNITS = { "one", "two", "three", "four",
			"five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
			"eighteen", "nineteen" };
	/** Holds the tens places **/
	private static final String[] TENS = { "ten", "twenty", "thirty", "forty",
			"fifty", "sixty", "seventy", "eighty", "ninty" };

	private void spellNumber(String num, StringJoiner sb) {
		
		int len = num.length();
		int pow = len - 1;

		
		int rem = 0;
		long num1 = Long.valueOf(num);
		if (pow>=21){
			throw new IllegalArgumentException("We currently do not support numbers with more than 20 digits");
		}
		if(num1>0){
		// The largest number that can be multiplied
		switch (pow) {
		
		case 0: {
			int divisor = (int) Math.pow(10, pow);
			int quo = (int) (num1 / divisor);
			
			rem = (int) (num1 % divisor);

			sb.add(UNITS[(int) (quo - 1)]);
			break;
		}
		case 1: {
			int divisor = (int) Math.pow(10, pow);
			int quo = (int) (num1 / divisor);
			rem = (int) (num1 % divisor);

			if (num1 <= 19) {
				sb.add(UNITS[(int) (num1 - 1)]);
				rem = -1;
			} else {
				sb.add(TENS[quo - 1]);
			}
			break;
		}
		case 2: {
			int divisor = (int) Math.pow(10, pow);
			int quo = (int) (num1 / divisor);
			rem = (int) (num1 % divisor);

			sb.add(UNITS[quo - 1]);
			sb.add("hundred");

			break;
		}
		case 3:
		case 4: {
			int divisor = (int) Math.pow(10, 3);
			int quo = (int) (num1 / divisor);
			rem = (int) (num1 % divisor);

			spellNumber("" + quo, sb);
			sb.add("thousand");

			break;
		}
		case 5:
		case 6: {
			int divisor = (int) Math.pow(10, 5);
			int quo = (int) (num1 / divisor);
			rem = (int) (num1 % divisor);

			spellNumber("" + quo, sb);
			sb.add("lakh");
			break;
		}

		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13: {
			int divisor = (int) Math.pow(10, 7);
			int quo = (int) (num1 / divisor);
			rem = (int) (num1 % divisor);

			spellNumber("" + quo, sb);
			sb.add("crore");

			break;
		}

		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20: {
			int divisor = (int) Math.pow(10, 14);
			int quo = (int) (num1 / divisor);
			rem = (int) (num1 % divisor);

			spellNumber("" + quo, sb);
			sb.add("crore crore");

			break;
		}
		default:{
			sb.add("-1");//Should never gethere since we check for this before
			break;
		}
		}}else{
			sb.add("zero");
		}

		num1 = rem;
		num = "" + num1;
		if (rem > 0) {
			if (num.length() == 2) {
				sb.add(AND_STR);
			}
			spellNumber(num, sb);
		}

	}

}
