package com.wazeedigital.subtitle;

import static org.fest.assertions.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FormatSCCTest {
	private FormatSCC sut;
	private boolean verbose = false;

	@Before
	public void beforeEachTest() {
		sut = new FormatSCC();
	}

	@Test
	public void scenarist1() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/sccTest.scc")) {
			TimedTextObject tto = sut.parseFile("sccTest.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("sccTest.scc"));
			assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(2);

			// 3773533=Caption{01:02:53.53..01:02:55.46, null, null: ( horn honking )
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("01:02:53.533");
			assertThat(caption.end.toString()).isEqualTo("01:02:55.467");
			assertThat(caption.content).isEqualTo("( horn honking )");

			// 3808033=Caption{01:03:28.03..99:59:59.96, null, null: HEY, THERE.}
			caption = tto.captions.get(3808033);
			assertThat(caption.start.toString()).isEqualTo("01:03:28.033");
			assertThat(caption.end.toString()).isEqualTo("99:59:59.967");
			assertThat(caption.content).isEqualTo("HEY, THERE.");
		}
	}

	@Ignore // TODO make this pass
	@Test
	public void scenarist2() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/test2.scc")) {
			TimedTextObject tto = sut.parseFile("test2.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("test2.scc"));
			assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(2);

			// 3773533=Caption{01:02:53.53..01:02:55.46, null, null: ( horn honking )
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("01:02:53.530");
			assertThat(caption.end.toString()).isEqualTo("01:02:55.460");
			assertThat(caption.content).isEqualTo("( horn honking )");

			// 3808033=Caption{01:03:28.03..99:59:59.96, null, null: HEY, THERE.}
			caption = tto.captions.get(3808033);
			assertThat(caption.start.toString()).isEqualTo("01:03:28.030");
			assertThat(caption.end.toString()).isEqualTo("99:59:59.960");
			assertThat(caption.content).isEqualTo("HEY, THERE.");
		}
	}

	@Test
	public void scenarist3() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/test3.scc")) {
			TimedTextObject tto = sut.parseFile("test3.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("test3.scc"));
			assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(11);

			// 3601300=Caption{01:00:01.30..01:00:05.03, null, null: }
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("01:00:01.300");
			assertThat(caption.end.toString()).isEqualTo("01:00:05.033");
			assertThat(caption.content).isEqualTo("");
		}
	}

	@Test
	public void fullMovie() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/bliss.scc")) {
			TimedTextObject tto = sut.parseFile("bliss.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("bliss.scc"));
			assertThat(tto.title).isEqualTo(("bliss.scc"));
			assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println(tto.captions);

			assertThat(tto.captions).hasSize(1983);

			// 3635199=Caption{01:00:35.19..01:00:37.43, null, null: ( woman )<br />All right, I want you to have<br />a
			// couple bites, all right ?}
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("01:00:35.199");
			assertThat(caption.end.toString()).isEqualTo("01:00:37.433");
			assertThat(caption.content)
					.isEqualTo("( woman )<br />All right, I want you to have<br />a couple bites, all right ?");

			// 9091266=Caption{02:31:31.26..02:31:34.13, null, null: ♪ Love ♪}
			caption = tto.captions.get(9091266);
			assertThat(caption.start.toString()).isEqualTo("02:31:31.266");
			assertThat(caption.end.toString()).isEqualTo("02:31:34.133");
			assertThat(caption.content).isEqualTo("♪ Love ♪");
		}
	}

}
