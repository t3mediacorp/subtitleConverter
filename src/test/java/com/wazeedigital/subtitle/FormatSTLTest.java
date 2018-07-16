package com.wazeedigital.subtitle;

import static org.fest.assertions.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FormatSTLTest {

	private FormatSTL sut;
	private String dir = "STL/";

	@Before
	public void beforeEachTest() {
		sut = new FormatSTL();
	}

	@Test
	public void colorTests() throws IOException, FatalParsingException {
		String file = "Alsalirdeclasebien.stl";

		try (InputStream is = this.getClass().getResourceAsStream(dir + file)) {
			TimedTextObject tto = sut.parseFile(file, is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(file);
			assertThat(tto.styling).hasSize(6);

			// System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(38);

			Iterator<Caption> iter = tto.captions.values().iterator();

			Caption caption = iter.next();
			assertThat(caption.style.iD).isEqualTo("white");
			assertThat(caption.style.color).isEqualTo("ffffffff");

			caption = iter.next();
			assertThat(caption.style.iD).isEqualTo("black");
			assertThat(caption.style.color).isEqualTo("000000ff");

			caption = iter.next();
			assertThat(caption.style.iD).isEqualTo("yellow");
			assertThat(caption.style.color).isEqualTo("ffff00ff");

			caption = iter.next();
			assertThat(caption.style.iD).isEqualTo("green");
			assertThat(caption.style.color).isEqualTo("008000ff");

			caption = iter.next();
			assertThat(caption.style.iD).isEqualTo("cyan");
			assertThat(caption.style.color).isEqualTo("00ffffff");

			caption = iter.next();
			assertThat(caption.style.iD).isEqualTo("magenta");
			assertThat(caption.style.color).isEqualTo("ff00ffff");
		}
	}

	@Ignore // TODO - fix test
	@Test
	public void bbcTestSubtitles() throws IOException, FatalParsingException {
		String file = "BBCTest.stl";

		try (InputStream is = this.getClass().getResourceAsStream(dir + file)) {
			TimedTextObject tto = sut.parseFile(file, is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(file);

			// System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(166);

			// -60000=Caption{00:-1:00.00..00:-1:00.00, white, null: }
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("00:-1:00.000");
			assertThat(caption.end.toString()).isEqualTo("00:-1:00.000");
			assertThat(caption.content).isEqualTo("");

			// 36343000=Caption{10:05:43.00..10:05:59.00, white, null: Simple Snake Subtitle test one two<br /> three
			// four five six seven eight nine}
			caption = tto.captions.get(36343000);
			assertThat(caption.start.toString()).isEqualTo("10:05:43.000");
			assertThat(caption.end.toString()).isEqualTo("10:05:59.000");
			assertThat(caption.content)
					.isEqualTo("   Simple Snake Subtitle test one two<br />   three four five six seven eight nine");
		}
	}

	@Test
	public void ebu() throws IOException, FatalParsingException {
		String file = "EBU.stl";

		try (InputStream is = this.getClass().getResourceAsStream(dir + file)) {
			TimedTextObject tto = sut.parseFile(file, is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(file);

			// System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(27);

			// 0=Caption{00:00:00.00..00:00:00.00, white, null: }
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("00:00:00.000");
			assertThat(caption.end.toString()).isEqualTo("00:00:00.000");
			assertThat(caption.content).isEqualTo("");

			// 3410280=Caption{00:56:50.28..00:56:53.28, black, null: No puede ser. Por qué<br />nos iban a abandonar?}
			caption = tto.captions.get(3410280);
			assertThat(caption.start.toString()).isEqualTo("00:56:50.280");
			assertThat(caption.end.toString()).isEqualTo("00:56:53.280");
			assertThat(caption.content).isEqualTo("No puede ser. Por qué<br />nos iban a abandonar?");
		}
	}

	@Test
	public void debate() throws IOException, FatalParsingException {
		String file = "debate1_03-03-08.stl";

		try (InputStream is = this.getClass().getResourceAsStream(dir + file)) {
			TimedTextObject tto = sut.parseFile(file, is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(file);

			// System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(598);

			// 0=Caption{00:00:00.00..00:00:00.00, white, null: }
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("00:00:00.000");
			assertThat(caption.end.toString()).isEqualTo("00:00:00.000");
			assertThat(caption.content).isEqualTo("");

			// 2982400=Caption{00:49:42.40..00:49:45.72, white, null: ...espanoles es usted; pero por sus<br />
			// multiples mentiras. Le he...}
			caption = tto.captions.get(2982400);
			assertThat(caption.start.toString()).isEqualTo("00:49:42.400");
			assertThat(caption.end.toString()).isEqualTo("00:49:45.720");
			assertThat(caption.content)
					.isEqualTo("...espanoles es usted; pero por sus<br />   multiples mentiras. Le he...");
		}
	}

}
