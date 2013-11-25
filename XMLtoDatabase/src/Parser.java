import java.io.UnsupportedEncodingException;

import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.ParseException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

public class Parser {

	private VTDGen vg;
	private VTDNav vn;;

	public Parser(String XML) {
		try {
			String withHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + XML;
			byte[] bytes = withHeader.getBytes("UTF-8");
			vg = new VTDGen();
			vg.setDoc(bytes);
			vg.parse(false);
			vn = vg.getNav();
		} catch (UnsupportedEncodingException | ParseException e) {
			System.err.println("UTF-8 is the only encoding supported currently.");
		}
	}

	public String getValue(String xpath) {
		String result = null;
		try {
			AutoPilot ap = new AutoPilot();
			ap.selectXPath(xpath);
			ap.bind(vn);
			while (ap.evalXPath() != -1) {
				int text = vn.getText();
				result = vn.toString(text);
			}
			ap.resetXPath();
			vn.toElement(VTDNav.ROOT);
		} catch (XPathParseException | XPathEvalException | NavException e) {
			System.err.println("Error with the xpath parsing");
			e.printStackTrace();
		}
		return result;

	}

}
