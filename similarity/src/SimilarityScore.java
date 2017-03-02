
import java.util.ArrayList;
import java.util.List;
import com.ue.maxdata.tool.WordSequenceAligner.Alignment;

public class SimilarityScore {

	public static void main(String[] args) {
		String hyp = "1 “ 李丹 ” 投资 北京中 金鑫盛 投资 中心 （ 有限 合伙 ） 1000万元";
		String ref = "5 “ 刘飞 ” 投资 天津 争锋 投资 中心 （ 有限 合伙 ） 700万元 ";
		similarityScore(hyp,ref,"CHINESE");
	}

	public static float fscore (List<String> hypl,List<String> refl){		
		
		List<String> common= new ArrayList<String>();
		
		for (String h:hypl){
			for(int i=0;i<refl.size();i++){
				if(h.equals(refl.get(i))) {
					common.add(h);
					refl.remove(i);
				}
					
			}
		}
		int tp = common.size();
		int fp = hypl.size() - tp;
		int fn = refl.size();
		//测试用打印
//		System.out.println("hypl:"+hypl);
//		System.out.println("refl:"+refl);
//		System.out.println("common:"+common);
//		System.out.println("fp:"+fp);
//		System.out.println("fn:"+fn);
//		System.out.println("hlength:"+hypl.size());
//		System.out.println("rlength:"+refl.size());
		if (tp >0){
			float precision = tp/(float)(tp+fp);
			float recall = tp/(float)(tp+fn);
			return 2*((precision*recall)/(precision+recall));
			
		} else {
			return 0;
		}
	}
	
	public static float similarityScore(String hyp,String ref,String lang) {
		float score = (float) 0.0;
		//lowercase input
		String hypc = hyp.toLowerCase();
		String refc = ref.toLowerCase();
		WordSequenceAligner werE = new WordSequenceAligner();
		
		//process tree
		Treeinfo t1 = new Treeinfo();
		Treeinfo t2 = new Treeinfo();
		t1.getTreeinfo(lang.toUpperCase(), hypc);
		t2.getTreeinfo(lang.toUpperCase(), refc);
		float treescore = fscore(t1.TreeNodeList,t2.TreeNodeList);
		
		//process pos
		Alignment pos = werE.align(t1.Pos.split(" "), t2.Pos.split(" "));
		float wer = pos.getWordErrorRate();
		
		//process word
		float wordscore = fscore(t1.WordList,t2.WordList);
		
		//language specific weight
		if (lang.equals("ENGLISH")) {
			score = (float) ((0.35*treescore+0.35*wer+0.3*wordscore)/1);
		} else if (lang.equals("CHINESE")) {
			score = (float) ((0.35*treescore+0.35*wer+0.3*wordscore)/1);
		} else {
			score = (float) ((0.35*treescore+0.35*wer+0.3*wordscore)/1);
		}
		
		//print alignment
		Alignment a = werE.align(hyp.split(" "), ref.split(" "));
		String sref = String.join(" ", a.reference);
		String shyp = String.join(" ", a.hypothesis);
		System.out.println(sref);
		System.out.println(shyp);
		System.out.println("final: "+score);
		System.out.println("tree: "+treescore);
		System.out.println("pos: "+wer);
		System.out.println("word: "+wordscore);
		return score;
	}

}