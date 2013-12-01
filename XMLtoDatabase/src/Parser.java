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
	private int i = 0;

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
	
	
	public String getValue(String xpath){
		String result = null;
	    try {
	    	vn.toElement(VTDNav.ROOT);
	    	
		    AutoPilot ap = new AutoPilot();
		    ap.selectXPath(this.ROOT);
		    
		    AutoPilot ap2 = new AutoPilot();
			ap2.selectXPath(xpath);
			
	        ap.bind(vn);
	        ap2.bind(vn);
	        //XPath eval returns one node at a time
	        while (ap.evalXPath() != -1)
	        {
	        //ap.evalXPath();
	        	i++;
	            vn.push();
	            result = ap2.evalXPathToString();
	            System.out.println(ROOT);
	            System.out.println(xpath);
	            System.out.println(result);
	            // doesn't forget this since the next iteration will reuse
	            // ap2's XPath!!! 
	            ap2.resetXPath();
	            vn.pop();
	        }
	        ap.resetXPath();
        } catch (XPathParseException | XPathEvalException | NavException e) {
			System.err.println("Error with the xpath parsing: "+xpath);
			e.printStackTrace();
		}
	    return result;
	}

	public String getValueFromRoot(String xpath) {
		String result = null;
		try {
			vn.toElement(VTDNav.ROOT);
			AutoPilot pilot = new AutoPilot();
			pilot.selectXPath(xpath);
			pilot.bind(vn);
			result = pilot.evalXPathToString();
			pilot.resetXPath();
		} catch (XPathParseException | NavException e) {
			System.err.println("Error with the xpath parsing: "+xpath);
			e.printStackTrace();
		}
		return result;

	}

	public void setRoot(String ROOT) {
		this.ROOT = ROOT;
	}
}
