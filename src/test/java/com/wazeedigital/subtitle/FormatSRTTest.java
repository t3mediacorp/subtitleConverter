package com.wazeedigital.subtitle;

import static org.fest.assertions.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

public class FormatSRTTest {

	private FormatSRT sut;

	@Before
	public void beforeEachTest() {
		sut = new FormatSRT();
	}

	@Test
	public void subRip() throws IOException {
		try (InputStream is = this.getClass().getResourceAsStream("SRT/Avengers.2012.Eng.Subs.srt")) {
			TimedTextObject tto = sut.parseFile("Avengers.2012.Eng.Subs.srt", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("Avengers.2012.Eng.Subs.srt"));
			assertThat(tto.warnings).isEqualTo(("List of non fatal errors produced during parsing:\n\n"));

			// System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(1760);

			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.getTime("h:mm:ss.cs")).isEqualTo("00:00:27.16");
			assertThat(caption.end.getTime("h:mm:ss.cs")).isEqualTo("00:00:30.00");
			assertThat(caption.content).isEqualTo("<i>The Tesseract has awakened.</i><br />");

			// 3463280=Caption{00:57:43.28..00:57:45.63, null, null: So you're saying that the Hulk...<br />}
			caption = tto.captions.get(3463280);
			assertThat(caption.start.getTime("h:mm:ss.cs")).isEqualTo("00:57:43.28");
			assertThat(caption.end.getTime("h:mm:ss.cs")).isEqualTo("00:57:45.63");
			assertThat(caption.content).isEqualTo("So you're saying that the Hulk...<br />");
		}
	}

	@Test
	public void subRipWithWarning() throws IOException {
		try (InputStream is = this.getClass().getResourceAsStream("SRT/Dirty Harry VOST - Clint Eastwood.srt")) {
			TimedTextObject tto = sut.parseFile("Dirty Harry VOST - Clint Eastwood.srt", is);
			assertThat(tto).isNotNull();
			assertThat(tto.captions).hasSize(792);
			assertThat(tto.fileName).isEqualTo(("Dirty Harry VOST - Clint Eastwood.srt"));
			assertThat(tto.warnings).contains(("793 expected "));
		}
	}

}
