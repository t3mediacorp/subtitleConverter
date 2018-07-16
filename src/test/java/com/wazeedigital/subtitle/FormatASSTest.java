package com.wazeedigital.subtitle;

import static org.fest.assertions.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FormatASSTest {

	private FormatASS sut;

	@Before
	public void beforeEachTest() {
		sut = new FormatASS();
	}

	@Test
	public void subStationAlpha() throws IOException {
		try (InputStream is = this.getClass().getResourceAsStream("ASS/test.ssa")) {
			TimedTextObject tto = sut.parseFile("test.ssa", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo("test.ssa");
			assertThat(tto.title).isEqualTo("Wolf's rain 2");
			assertThat(tto.styling).hasSize(1);

			// System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(2);

			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("00:02:40.650");
			assertThat(caption.end.toString()).isEqualTo("00:02:41.790");
			assertThat(caption.content).isEqualTo("Et les enregistrements de ses ondes delta ?");

			// 162420=Caption{00:02:42.42..00:02:44.15, Wolf main, null: Toujours rien.}
			caption = tto.captions.get(162420);
			assertThat(caption.start.toString()).isEqualTo("00:02:42.420");
			assertThat(caption.end.toString()).isEqualTo("00:02:44.150");
			assertThat(caption.content).isEqualTo("Toujours rien.");
		}
	}

	@Ignore // TODO make this pass
	@Test
	public void advancedSubStationAlpha() throws IOException {
		try (InputStream is = this.getClass().getResourceAsStream("ASS/Aqui_no_hay_quien_viva_1.ass")) {
			TimedTextObject tto = sut.parseFile("Aqui_no_hay_quien_viva_1.ass", is);
			assertThat(tto).isNotNull();

			System.out.println(tto.captions);

			// TODO kmurray - FIX
			// assertThat(tto.captions).hasSize(792);
			//
			// // 1781665=Caption{00:29:41.66..00:29:45.13, null, null: Not me. You're the one who<br />wants to get
			// // yourself killed. Not me.<br />}
			// Caption caption = tto.captions.get(1781665);
			// assertThat(caption.start.getTime(Time.FORMAT_HH_MM_SS_DOT_MS,0)).isEqualTo("00:29:41.660");
			// assertThat(caption.end.getTime(Time.FORMAT_HH_MM_SS_DOT_MS,0)).isEqualTo("00:29:45.130");
			// assertThat(caption.content)
			// .isEqualTo("Not me. You're the one who<br />wants to get yourself killed. Not me.<br />");
		}
	}

}
