package rs.ac.uns.ftn.jaxb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Konverter klasa za java.utilDate, parse odnosno 
 * print metode moraju biti statičke.
 *
 */
public class MyDatatypeConverter {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Metoda parsira tekstualnu vrednost datuma.
	 * @param value tekstualni reprezent datuma
	 * @return Vraća datumsku vrednost tekstualnog reprezenta.
	 */
	public static Date parseDate(String value) {
		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metoda štampa datum u definisanom formatu.
	 * @param value datumska vrednost
	 * @return Vraća tekstualni reprezent datuma.
	 */
	public static String printDate(Date value) {
		if (value != null)
			return dateFormat.format(value);
		else
			return null;

	}
}
