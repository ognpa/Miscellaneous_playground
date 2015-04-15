import static org.junit.Assert.*;

import org.junit.Test;


public class IndianNumberingSystemTestCase {

	private static final String[] UNITS = { "one", "two", "three", "four",
		"five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
		"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
		"eighteen", "nineteen" };
	@Test
	public void testZero() {
		SpellNumbersEngine sne=new SpellNumbersEngine();
		String zeroString=sne.convertToIndianNumberingSystem("0");
		assertEquals(zeroString,"zero");
	}
	
	@Test
	public void testTwoDigitNumber(){
		SpellNumbersEngine sne=new SpellNumbersEngine();
		for (int i=1;i<20;i++){
			String spelling=sne.convertToIndianNumberingSystem(""+i);
			assertEquals(spelling,UNITS[i-1]);
		}
		
	}
	@Test
	public void testNumberGreaterThanTwentyLessThanHundred(){
		SpellNumbersEngine sne=new SpellNumbersEngine();
		String spell=sne.convertToIndianNumberingSystem("22");
		assertEquals("twenty two",spell);
		
		spell=sne.convertToIndianNumberingSystem("96");
		assertEquals("ninty six",spell);
		spell=sne.convertToIndianNumberingSystem("20");
		assertEquals("twenty",spell);
		
	}
	@Test
	public void testNumberGreaterThanHundred(){
		SpellNumbersEngine sne=new SpellNumbersEngine();
		String spell=sne.convertToIndianNumberingSystem("223");
		assertEquals("two hundred and twenty three",spell);
		spell=sne.convertToIndianNumberingSystem("2223");
		assertEquals("two thousand two hundred and twenty three",spell);
		spell=sne.convertToIndianNumberingSystem("22333");
		assertEquals("twenty two thousand three hundred and thirty three",spell);
		spell=sne.convertToIndianNumberingSystem("222333");
		assertEquals("two lakh twenty two thousand three hundred and thirty three",spell);
		spell=sne.convertToIndianNumberingSystem("2222333");
		assertEquals("twenty two lakh twenty two thousand three hundred and thirty three",spell);
		spell=sne.convertToIndianNumberingSystem("22222333");
		assertEquals("two crore twenty two lakh twenty two thousand three hundred and thirty three",spell);
		
		
		
		
		
		
	
		
	}

}
