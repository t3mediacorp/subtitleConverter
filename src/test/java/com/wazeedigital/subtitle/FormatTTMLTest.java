package com.wazeedigital.subtitle;

import static org.fest.assertions.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FormatTTMLTest {

	private FormatSTL sut;
	private String dir = "TTML/";

	@Before
	public void beforeEachTest() {
		sut = new FormatSTL();
	}

	@Ignore // TODO make this pass
	@Test
	public void ttmlDebate() throws IOException, FatalParsingException {
		String file = "Debate0_03-03-08.dfxp.xml.ttml";

		try (InputStream is = this.getClass().getResourceAsStream(dir + file)) {
			TimedTextObject tto = sut.parseFile(file, is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(file);

			// System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(166);

			// -60000=Caption{00:-1:00.00..00:-1:00.00, white, null: }
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.getTime("h:mm:ss.cs")).isEqualTo("00:-1:00.00");
			assertThat(caption.end.getTime("h:mm:ss.cs")).isEqualTo("00:-1:00.00");
			assertThat(caption.content).isEqualTo("");

			// 36343000=Caption{10:05:43.00..10:05:59.00, white, null: Simple Snake Subtitle test one two<br /> three
			// four five six seven eight nine}
			caption = tto.captions.get(36343000);
			assertThat(caption.start.getTime("h:mm:ss.cs")).isEqualTo("10:05:43.00");
			assertThat(caption.end.getTime("h:mm:ss.cs")).isEqualTo("10:05:59.00");
			assertThat(caption.content)
					.isEqualTo("   Simple Snake Subtitle test one two<br />   three four five six seven eight nine");
		}
	}

	@Ignore // TODO make this pass
	@Test
	public void ttml() throws IOException, FatalParsingException {
		String file = "prueba_angel2.xml.ttml";

		try (InputStream is = this.getClass().getResourceAsStream(dir + file)) {
			TimedTextObject tto = sut.parseFile(file, is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(file);

			// System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(166);

			// -60000=Caption{00:-1:00.00..00:-1:00.00, white, null: }
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.getTime("h:mm:ss.cs")).isEqualTo("00:-1:00.00");
			assertThat(caption.end.getTime("h:mm:ss.cs")).isEqualTo("00:-1:00.00");
			assertThat(caption.content).isEqualTo("");

			// 36343000=Caption{10:05:43.00..10:05:59.00, white, null: Simple Snake Subtitle test one two<br /> three
			// four five six seven eight nine}
			caption = tto.captions.get(36343000);
			assertThat(caption.start.getTime("h:mm:ss.cs")).isEqualTo("10:05:43.00");
			assertThat(caption.end.getTime("h:mm:ss.cs")).isEqualTo("10:05:59.00");
			assertThat(caption.content)
					.isEqualTo("   Simple Snake Subtitle test one two<br />   three four five six seven eight nine");
		}
	}

}
