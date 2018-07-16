package com.wazeedigital.subtitle;

public class Time {

	/** Format like 01:02:03,456 (SRT) */
	public static final String FORMAT_HH_MM_SS_COMMA_MS = "hh:mm:ss,ms";
	/** Format like 01:02:03.456 */
	public static final String FORMAT_HH_MM_SS_DOT_MS = "hh:mm:ss.ms";
	/** Format like 01:02:03.45 (ASS/SSA) */
	public static final String FORMAT_HH_MM_SS_CS = "hh:mm:ss.cs";
	/** Format like 01020309 (EBU STL) */
	public static final String FORMAT_HHMMSSFF = "hhmmssff";
	/** Format like 1:2:3:9 (EBU STL) */
	public static final String FORMAT_H_M_S_F = "h:m:s:f";
	/** Format like 01:02:03:09 (SCC) */
	public static final String FORMAT_HH_MM_SS_FF = "hh:mm:ss:ff";

	/**
	 * Constructor to create a time object.
	 * 
	 * @param format Format of the time string
	 * @param value Time in the format specified
	 * @param fps Frames per second if the format has frames
	 */
	protected Time(String format, String value, double fps) {
		if (format.equals(FORMAT_HH_MM_SS_COMMA_MS) || format.equals(FORMAT_HH_MM_SS_DOT_MS)) {
			// this type of format: 01:02:22,501 (used in .SRT)
			int h, m, s, ms;
			h = Integer.parseInt(value.substring(0, 2));
			m = Integer.parseInt(value.substring(3, 5));
			s = Integer.parseInt(value.substring(6, 8));
			ms = Integer.parseInt(value.substring(9, 12));

			mseconds = ms + s * 1000 + m * 60000 + h * 3600000;

		}
		else if (format.equals(FORMAT_HH_MM_SS_CS)) {
			// this type of format: 1:02:22.51 (used in .ASS/.SSA)
			int h, m, s, cs;
			String[] hms = value.split(":");
			h = Integer.parseInt(hms[0]);
			m = Integer.parseInt(hms[1]);
			s = Integer.parseInt(hms[2].substring(0, 2));
			cs = Integer.parseInt(hms[2].substring(3, 5));

			mseconds = cs * 10 + s * 1000 + m * 60000 + h * 3600000;
		}
		else if (format.equals(FORMAT_H_M_S_F)) {
			int h, m, s, f;
			String[] args = value.split("[:;]");
			h = Integer.parseInt(args[0]);
			m = Integer.parseInt(args[1]);
			s = Integer.parseInt(args[2]);
			f = Integer.parseInt(args[3]);

			mseconds = (int) (f * 1000 / fps) + s * 1000 + m * 60000 + h * 3600000;
		}
	}

	// in an integer we can store 24 days worth of milliseconds, no need for a long
	protected int mseconds;

	public int getMseconds() {
		return mseconds;
	}

	public void setMseconds(int mseconds) {
		this.mseconds = mseconds;
	}

	/* METHODS */

	/**
	 * Method to return a formatted value of the time stored
	 * 
	 * @param format How to format the time
	 * @param fps Frames per second for formats that include frames. Ignored for non-frame formats
	 * @return formatted time in a string
	 */
	public String getTime(String format, double fps) {
		// we use string builder for efficiency
		StringBuilder time = new StringBuilder();
		String aux;
		if (format.equals(FORMAT_HH_MM_SS_COMMA_MS) || format.equals(FORMAT_HH_MM_SS_DOT_MS)) {
			// this type of format: 01:02:22,501 (used in .SRT)
			int h, m, s, ms;
			h = mseconds / 3600000;
			aux = String.valueOf(h);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append(':');
			m = (mseconds / 60000) % 60;
			aux = String.valueOf(m);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append(':');
			s = (mseconds / 1000) % 60;
			aux = String.valueOf(s);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append(format.equals(FORMAT_HH_MM_SS_COMMA_MS) ? ',' : '.');
			ms = mseconds % 1000;
			aux = String.valueOf(ms);
			if (aux.length() == 1) time.append("00");
			else if (aux.length() == 2) time.append('0');
			time.append(aux);

		}
		else if (format.equals(FORMAT_HH_MM_SS_CS)) {
			// this type of format: 1:02:22.51 (used in .ASS/.SSA)
			int h, m, s, cs;
			h = mseconds / 3600000;
			aux = String.valueOf(h);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append(':');
			m = (mseconds / 60000) % 60;
			aux = String.valueOf(m);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append(':');
			s = (mseconds / 1000) % 60;
			aux = String.valueOf(s);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append('.');
			cs = (mseconds / 10) % 100;
			aux = String.valueOf(cs);
			if (aux.length() == 1) time.append('0');
			time.append(aux);

		}
		else if (format.equals(FORMAT_HHMMSSFF)) {
			// this format is used in EBU's STL
			int h, m, s, f;
			h = mseconds / 3600000;
			aux = String.valueOf(h);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			m = (mseconds / 60000) % 60;
			aux = String.valueOf(m);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			s = (mseconds / 1000) % 60;
			aux = String.valueOf(s);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			f = (mseconds % 1000) * (int) fps / 1000;
			aux = String.valueOf(f);
			if (aux.length() == 1) time.append('0');
			time.append(aux);

		}
		else if (format.equals(FORMAT_H_M_S_F)) {
			// this format is used in EBU's STL
			int h, m, s, f;
			h = mseconds / 3600000;
			aux = String.valueOf(h);
			time.append(aux);
			time.append(':');
			m = (mseconds / 60000) % 60;
			aux = String.valueOf(m);
			time.append(aux);
			time.append(':');
			s = (mseconds / 1000) % 60;
			aux = String.valueOf(s);
			time.append(aux);
			time.append(':');
			f = (mseconds % 1000) * (int) fps / 1000;
			aux = String.valueOf(f);
			time.append(aux);
		}
		else if (format.equals(FORMAT_HH_MM_SS_FF)) {
			// this format is used in SCC
			int h, m, s, f;
			h = mseconds / 3600000;
			aux = String.valueOf(h);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append(':');
			m = (mseconds / 60000) % 60;
			aux = String.valueOf(m);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append(':');
			s = (mseconds / 1000) % 60;
			aux = String.valueOf(s);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
			time.append(':');
			f = (mseconds % 1000) * (int) fps / 1000;
			aux = String.valueOf(f);
			if (aux.length() == 1) time.append('0');
			time.append(aux);
		}

		return time.toString();
	}

	/**
	 * ASS/SSA time format.
	 * 
	 * @return ASS/SSA time format.
	 */
	@Override
	public String toString() {
		return getTime(FORMAT_HH_MM_SS_DOT_MS, 0);
	}

}
