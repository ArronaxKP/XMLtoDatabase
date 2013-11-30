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
	private VTDNav vn;
	private String ROOT = "";

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
			if(ROOT != null){
				AutoPilot rootPilot = new AutoPilot();
				rootPilot.selectXPath(xpath);
				rootPilot.bind(vn);
				vn.toElement(rootPilot.evalXPath());
				rootPilot.resetXPath();
			} else {
				vn.toElement(VTDNav.ROOT);
			}
			
			AutoPilot pilot = new AutoPilot();
			pilot.selectXPath(xpath);
			pilot.bind(vn);
			result = pilot.evalXPathToString();
			pilot.resetXPath();
		} catch (XPathParseException | NavException | XPathEvalException e) {
			System.err.println("Error with the xpath parsing: "+xpath);
			e.printStackTrace();
		}
		return result;

	}

	public void setRoot(String ROOT) {
		this.ROOT = ROOT;
	}
}
