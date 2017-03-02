

import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.trees.Tree;

public class Treeinfo {
	public String TreeNode;
	public String Pos;
	public String Word;
	public List<String> TreeNodeList = new ArrayList<String>();
	public List<String> PosList = new ArrayList<String>();
	public List<String> WordList = new ArrayList<String>();
	
	public static void main(String[] args) {
		//String test = "1 “ 李丹 ” 投资 北京中 金鑫盛 投资 中心 （ 有限 合伙 ） 1000万元";
		//getTreeinfo("CHINESE",test);
	}

	public void getTreeinfo(String lang,String text){
		Tree parse = Parser.setModel(lang, text);
		for (Tree p : parse) {
			if (p.depth() > 1) {
				TreeNodeList.add(p.label().toString());
				//System.out.println(p.label());
			} else if (p.depth() == 1) {
				PosList.add(p.label().toString());
			} else if (p.depth() == 0){
				//System.out.println(p);
				WordList.add(p.toString());
			}
		}
		TreeNode = String.join(" ",TreeNodeList);
		Pos = String.join(" ",PosList);
		Word = String.join(" ",WordList);
		//System.out.println(TreeNode);
		//System.out.println(Pos);
		//System.out.println(Word);
	}
}
