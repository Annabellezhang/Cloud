package TwitterStream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KeyWordprocesser {
	    String regex = "[fF][rR][iI][dD][aA][yY]|[bB][eE][aA][uU][tT][yY]|[mM][oO][vV][iI][eE]|[fF][oO][oO][dD]|game";
	    Pattern p = Pattern.compile(regex);
	    Pattern p1 = Pattern.compile("[fF][rR][iI][dD][aA][yY]");
	    Pattern p2 = Pattern.compile("[bB][eE][aA][uU][tT][yY]");
	    Pattern p3 = Pattern.compile("[mM][oO][vV][iI][eE]");
	    Pattern p4 = Pattern.compile("[fF][oO][oO][dD]");
	    public String iskeyword(String text) {
	    	String ret = new String();
	    	Matcher m = p.matcher(text);
			if (m.find()) {
				if (p1.matcher(text).find()) {
					ret = "Friday";
					return ret;
				} else if (p2.matcher(text).find()) {
					ret = "beauty";
					return ret;
				} else if (p3.matcher(text).find()) {
					ret = "movie";
					return ret;
				} else if (p4.matcher(text).find()) {
					ret = "food";
					return ret;
				} else {
					ret = "game";
					return ret;
				}
			}
			ret = "none";
			return ret;
		}
}
