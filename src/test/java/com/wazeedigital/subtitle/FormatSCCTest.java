package com.wazeedigital.subtitle;

import static org.fest.assertions.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FormatSCCTest {
	private FormatSCC sut;
	private boolean verbose = true;

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

			if (verbose) System.out.println("1" + tto.captions);

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

	@Test
	public void scenarist4() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/test4.scc")) {
			TimedTextObject tto = sut.parseFile("test4.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("test4.scc"));
			// assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println("4" + tto.captions);

			assertThat(tto.captions).hasSize(4);

			// 4048500=Caption{01:07:28.500..01:07:29.433, white, null: RIGHT BACK WITH MORE "FEUD,"}
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("01:07:28.500");
			assertThat(caption.end.toString()).isEqualTo("01:07:29.433");
			assertThat(caption.content).isEqualTo("RIGHT BACK WITH MORE \"FEUD,\"");

			// 4148367=Caption{01:09:08.367..99:59:59.967, null, null: "FEUD," EVERYBODY. MARSHALLS}
			caption = tto.captions.get(4148367);
			assertThat(caption.start.toString()).isEqualTo("01:09:08.367");
			assertThat(caption.end.toString()).isEqualTo("99:59:59.967");
			assertThat(caption.content).isEqualTo("\"FEUD,\" EVERYBODY. MARSHALLS");
		}
	}

	@Test
	public void scenarist5() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/test5.scc")) {
			TimedTextObject tto = sut.parseFile("test5.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("test5.scc"));
			// assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println("5" + tto.captions);

			assertThat(tto.captions).hasSize(634);

			// 3599834=Caption{00:59:59.834..01:00:00.200, null, null: }
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("00:59:59.834");
			assertThat(caption.end.toString()).isEqualTo("01:00:00.200");
			assertThat(caption.content).isEmpty();

			// 4919400=Caption{01:21:59.400..01:22:03.400, null, null: a Mark Goodson-Bill Todman<br />production.}
			caption = tto.captions.get(4919400);
			assertThat(caption.start.toString()).isEqualTo("01:21:59.400");
			assertThat(caption.end.toString()).isEqualTo("01:22:03.400");
			assertThat(caption.content).isEqualTo("a Mark Goodson-Bill Todman<br />production.");
		}
	}

	@Test
	public void scenarist6() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/test6.scc")) {
			TimedTextObject tto = sut.parseFile("test6.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("test6.scc"));
			// assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println("6" + tto.captions);

			assertThat(tto.captions).hasSize(629);

			// 3600433=Caption{01:00:00.433..01:00:02.200, null, null: -Get ready to match the stars--}
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("01:00:00.433");
			assertThat(caption.end.toString()).isEqualTo("01:00:02.200");
			assertThat(caption.content).isEqualTo("-Get ready to match the stars--");

			// 4890934=Caption{01:21:30.934..01:21:33.834, null, null: Gene Rayburn here.<br />Tally-ho.}
			caption = tto.captions.get(4890934);
			assertThat(caption.start.toString()).isEqualTo("01:21:30.934");
			assertThat(caption.end.toString()).isEqualTo("01:21:33.834");
			assertThat(caption.content).isEqualTo("Gene Rayburn here.<br />Tally-ho.");
		}
	}

	@Test
	public void scenarist7() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/test7.scc")) {
			TimedTextObject tto = sut.parseFile("test7.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("test7.scc"));
			// assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println("7" + tto.captions);

			assertThat(tto.captions).hasSize(646);

			// 3600533=Caption{01:00:00.533..01:00:02.700, null, null: -Get ready to match the stars:}
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("01:00:00.533");
			assertThat(caption.end.toString()).isEqualTo("01:00:02.700");
			assertThat(caption.content).isEqualTo("-Get ready to match the stars:");

			// 4912066=Caption{01:21:52.066..01:21:55.533, null, null: (applause)}
			caption = tto.captions.get(4912066);
			assertThat(caption.start.toString()).isEqualTo("01:21:52.066");
			assertThat(caption.end.toString()).isEqualTo("01:21:55.533");
			assertThat(caption.content).isEqualTo("(applause)");
		}
	}

	@Test
	public void scenarist8() throws IOException, FatalParsingException {
		try (InputStream is = this.getClass().getResourceAsStream("SCC/test8.scc")) {
			TimedTextObject tto = sut.parseFile("test8.scc", is);
			assertThat(tto).isNotNull();
			assertThat(tto.fileName).isEqualTo(("test8.scc"));
			// assertThat(tto.warnings).contains(("Only data from CC channel 1 will be extracted."));

			if (verbose) System.out.println("7" + tto.captions);

			assertThat(tto.captions).hasSize(497);

			// 3599867=Caption{00:59:59.867..01:00:03.700, null, null: - It's time for the all-star<br />Family Feud
			// Grammy}
			Caption caption = tto.captions.firstEntry().getValue();
			assertThat(caption.start.toString()).isEqualTo("00:59:59.867");
			assertThat(caption.end.toString()).isEqualTo("01:00:03.700");
			assertThat(caption.content).isEqualTo("- It's time for the all-star<br />Family Feud Grammy");

			// 4871333=Caption{01:21:11.333..01:21:15.567, null, null: A Mark Goodson<br />television production.}
			caption = tto.captions.get(4871333);
			assertThat(caption.start.toString()).isEqualTo("01:21:11.333");
			assertThat(caption.end.toString()).isEqualTo("01:21:15.567");
			assertThat(caption.content).isEqualTo("A Mark Goodson<br />television production.");
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

			if (verbose) System.out.println("3" + tto.captions);

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

			if (verbose) System.out.println("full" + tto.captions);

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
