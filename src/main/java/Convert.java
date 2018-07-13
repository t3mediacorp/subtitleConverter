import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.wazeedigital.subtitle.FormatASS;
import com.wazeedigital.subtitle.FormatSCC;
import com.wazeedigital.subtitle.FormatSRT;
import com.wazeedigital.subtitle.FormatSTL;
import com.wazeedigital.subtitle.FormatTTML;
import com.wazeedigital.subtitle.TimedTextFileFormat;
import com.wazeedigital.subtitle.TimedTextObject;


public class Convert {

	private Convert() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TimedTextObject tto;
		TimedTextFileFormat ttff;
		OutputStream output;

		//this is in case anyone may want to use this as stand alone java executable
		if (args != null && args.length == 4){

			try {

				String inputFile = args[0];
				String inputFormat = args[1];
				String outputFormat = args[2];
				String outputFile = args[3];

				if ("SRT".equalsIgnoreCase(inputFormat)){
					ttff = new FormatSRT();
				} else if ("STL".equalsIgnoreCase(inputFormat)){
					ttff = new FormatSTL();
				} else if ("SCC".equalsIgnoreCase(inputFormat)){
					ttff = new FormatSCC();
				} else if ("XML".equalsIgnoreCase(inputFormat)){
					ttff = new FormatTTML();
				} else if ("ASS".equalsIgnoreCase(inputFormat)){
					ttff = new FormatASS();
				} else {
					throw new Exception("Unrecognized input format: "+inputFormat+" only [SRT,STL,SCC,XML,ASS] are possible");
				}

                File file = new File(inputFile);
				InputStream is = new FileInputStream(file);
				tto = ttff.parseFile(file.getName(), is);

				if ("SRT".equalsIgnoreCase(outputFormat)){
					IOClass.writeFileTxt(outputFile, tto.toSRT());
				} else if ("STL".equalsIgnoreCase(outputFormat)){
					output = new BufferedOutputStream(new FileOutputStream(outputFile));
					output.write(tto.toSTL());
                    output.close();
				} else if ("SCC".equalsIgnoreCase(outputFormat)){
					IOClass.writeFileTxt(outputFile, tto.toSCC());
				} else if ("XML".equalsIgnoreCase(outputFormat)){
					IOClass.writeFileTxt(outputFile, tto.toTTML());
				} else if ("ASS".equalsIgnoreCase(outputFormat)){
					IOClass.writeFileTxt(outputFile, tto.toASS());
				} else {
					throw new Exception("Unrecognized input format: "+outputFormat+" only [SRT,STL,SCC,XML,ASS] are possible");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// normal test use
		} else {
                System.out.println("Usage: java Convert input-file input-format output-format output-file");
		}

	}
}
