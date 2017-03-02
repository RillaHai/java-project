

import java.net.URL;
import java.util.ArrayList;

import edu.cmu.meteor.scorer.MeteorConfiguration;
import edu.cmu.meteor.scorer.MeteorScorer;
import edu.cmu.meteor.util.Constants;

public class Eval {
	
	public static MeteorConfiguration config = new MeteorConfiguration();

	public static ArrayList<Double> para = new ArrayList<Double>();
	
	public static void main(String[] args) {
		double score = eval("en", "Yesterday the weather was very nice.", "The weather is fine Yesterday.");
		System.out.println("score   :    " + score);
	}

	public static double eval(String lang,String test, String ref) {
		loadConfig(lang);
		double score = score(test, ref);
		return score;
	}
	
	private static double score (String test, String ref) {
		MeteorScorer scorer = new MeteorScorer(config);
		double score = scorer.getMeteorStats(test, ref).score;
		return score;
	}
	
	public static void loadConfig(String lang) {

		switch (lang){
		case "en":
			// English
			para.add(0.75);
			para.add(1.0);
			para.add(0.5);
			para.add(0.75);

			config.setLanguage(lang);
			config.setNormalization(Constants.NORMALIZE_KEEP_PUNCT);
			config.setParameters(para);
			URL u = Eval.class.getClassLoader().getResource("data/paraphrase-en.gz");
			config.setParaFileURL(u);
			break;
		case "zh":
			para.add(0.75);
			para.add(1.4);
			para.add(0.7);
			para.add(0.75);

			config.setLanguage("other");
			config.setNormalization(Constants.NO_NORMALIZE);
			URL uzh = Eval.class.getClassLoader().getResource("data/paraphrase-zh.gz");
			config.setParaFileURL(uzh);
			config.setParameters(para);
			break;
		case "cz":
			URL ucz = Eval.class.getClassLoader().getResource("data/paraphrase-cz.gz");
			config.setParaFileURL(ucz);
			config.setLanguage(lang);
			config.setNormalization(Constants.NORMALIZE_KEEP_PUNCT);
			break;
		case "de":
			URL ude = Eval.class.getClassLoader().getResource("data/paraphrase-de.gz");
			config.setParaFileURL(ude);
			config.setLanguage(lang);
			config.setNormalization(Constants.NORMALIZE_KEEP_PUNCT);
			break;
		case "es":
			URL ues = Eval.class.getClassLoader().getResource("data/paraphrase-es.gz");
			config.setParaFileURL(ues);
			config.setLanguage(lang);
			config.setNormalization(Constants.NORMALIZE_KEEP_PUNCT);
			break;
		case "fr":
			URL ufr = Eval.class.getClassLoader().getResource("data/paraphrase-fr.gz");
			config.setParaFileURL(ufr);
			config.setLanguage(lang);
			config.setNormalization(Constants.NORMALIZE_KEEP_PUNCT);
			break;
		case "ar":
			config.setLanguage(lang);
			config.setNormalization(Constants.NORMALIZE_KEEP_PUNCT);
			break;
		case "da":
		case "fi":
		case "hu":
		case "it":
		case "nl":
		case "no":
		case "pt":
		case "ro":
		case "ru":
		case "se":
		case "tr":
			config.setLanguage(lang);
			config.setNormalization(Constants.NORMALIZE_KEEP_PUNCT);
			break;
		default:
			config.setLanguage("other");
			config.setNormalization(Constants.NO_NORMALIZE);
		}
	}
}
