package net.cbaines.suo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import net.fortuna.ical4j.data.CalendarParser;
import net.fortuna.ical4j.data.ContentHandler;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

public class SotonCalendarParser implements CalendarParser {

	public void parse(InputStream in, ContentHandler handler) throws IOException, ParserException {
		parse(new InputStreamReader(in), handler);
	}

	public void parse(Reader in, ContentHandler handler) throws IOException, ParserException {

		BufferedReader reader = new BufferedReader(in);

		handler.startCalendar();

		String line;
		while ((line = reader.readLine()) != null) {

			handler.startComponent(Component.VEVENT);
			handler.startProperty(Property.NAME);
			// handler.parameter(arg0, arg1)

		}
	}
}
