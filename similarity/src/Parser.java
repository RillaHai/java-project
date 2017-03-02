

import java.io.StringReader;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;


public class Parser {
	final static String chinesemodel = "edu/stanford/nlp/models/lexparser/chinesePCFG.ser.gz";
	final static String englishmodel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
	final static String germanmodel = "edu/stanford/nlp/models/lexparser/germanPCFG.ser.gz";
	final static String frenchmodel = "edu/stanford/nlp/models/lexparser/frenchFactored.ser.gz";
	final static String spanishmodel = "edu/stanford/nlp/models/lexparser/spanishPCFG.ser.gz";
	final static String arabicmodel = "edu/stanford/nlp/models/lexparser/arabicFactored.ser.gz";

	
			
	public static void main(String[] args) {
		String text = "1 “ 李丹 ” 投资 北京中 金鑫盛 投资 中心 （ 有限 合伙 ） 1000万元";
		//String text= "You're a good investigator. Maybe...";
		setModel("CHINESE",text);
	}
	
	
	public static Tree setModel(String lang,String text){
		String ParseModel = null;
		if (lang == "CHINESE") {
			ParseModel = chinesemodel;
		} else if (lang == "ENGLISH") {
			ParseModel = englishmodel;
		} else if (lang == "FRENCH") {
			ParseModel = frenchmodel;
		} else if (lang == "GERMAN") {
			ParseModel = germanmodel;
		} else if (lang == "SPANISH") {
			ParseModel = spanishmodel;
		} else if (lang == "ARABIC") {
			ParseModel = arabicmodel;
		}
		LexicalizedParser lp = LexicalizedParser.loadModel(ParseModel);
		TokenizerFactory<CoreLabel> tokenizerFactory =
		        PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		    Tokenizer<CoreLabel> tok =
		        tokenizerFactory.getTokenizer(new StringReader(text));
		    List<CoreLabel> rawWords2 = tok.tokenize();
		    //System.out.println(rawWords2);
		    Tree parse = lp.apply(rawWords2);
		    //parse.pennPrint();
		return parse;

	}

}



