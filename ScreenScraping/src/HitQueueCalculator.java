import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/*
 * Small  screen scraper to scrap data from Australian immigration electronic queue calculator for parent 103 visa
 */
public class HitQueueCalculator {

	public void hitMe(String filename) throws IOException {

		int start = Calendar.NOVEMBER;
		int end = Calendar.DECEMBER;
		FileWriter writer = new FileWriter(filename);

		GregorianCalendar startCal = new GregorianCalendar(2011, start, 1);
		GregorianCalendar endCal = new GregorianCalendar(2011, end, 31);

		while (startCal.before(endCal)) {
			startCal.add(Calendar.DATE, 1);

			int dd = startCal.get(Calendar.DATE);
			int mm = startCal.get(Calendar.MONTH) + 1;
			int yy = 2011;
			Document doc = Jsoup
					.connect("https://www.ecom.immi.gov.au/qcalc/QDateCalc.do")
					.timeout(20 * 1000)
					.data("queue_date_day", String.valueOf(dd))
					.data("queue_date_month", String.valueOf(mm))
					.data("queue_date_year", String.valueOf(yy))
					.data("queue_type", "103").data("x", "102").data("y", "16")
					.post();

			Element correctElement = doc.select("b:contains(Approximate)")
					.first();
			if (correctElement != null) {
				// Xpath it is in the <b> tag with the word "Approximate"
				Element d = doc.select("b:contains(Approximate)").first()
						.parent().parent().child(1);
				String num = d.text();
				System.out.println("Entries for " + startCal.getTime()
						+ " is>>>>> " + num);

				writer.append("Entries for " + startCal.getTime() + " is>>>>> "
						+ num);
				writer.append("\n");
			} else {
				System.out.println("Tried >>>>" + startCal.getTime());

				writer.append("Tried >>>>" + startCal.getTime());
				writer.append("\n");

			}

		}
		writer.flush();
		writer.close();

	}

	public static void main(String args[]) {
		if (args.length != 1) {
			System.out
					.println("You need to set the file location where you want output saved");
			System.exit(0);
		}
		HitQueueCalculator c = new HitQueueCalculator();
		try {
			c.hitMe(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
