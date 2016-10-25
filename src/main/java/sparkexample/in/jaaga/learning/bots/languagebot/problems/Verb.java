package in.jaaga.learning.bots.languagebot.problems;

import java.util.*;

import in.jaaga.learning.bots.skillbot.Problem;
import in.jaaga.learning.bots.skillbot.problems.SimpleProblem;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import NLPbot.TokenInitialzer;
import NLPbot.PosTaggerInitializer;
import penntree.PennTree;

public class Verb {
	String sentence;
    static String prompt = "";

    public static HashMap<String, String> createVerbProblem(String sentence1, Tokenizer tokenizer, POSTaggerME tagger) {
    	HashMap problemMap = new HashMap();
        List pos_list = new ArrayList();
        ArrayList answer_list = new ArrayList();
        List not_answer_list = new ArrayList();
        //get tokens and tags
        String[] tokens = tokenizer.tokenize(sentence1);
        String tags[] = tagger.tag(tokens);
        //get penn tree hashmaps
        PennTree penntree = new PennTree();
        HashMap penntree_hashmap = penntree.getPennTreeHashMap();
        HashMap verb_hashmap = penntree.getPennTreeVerbs();
        prompt += "Which is the verb?";
        //iterate over the tokens
        for (int i = 0; i<tokens.length; i++)
        {
            HashMap mMap = new HashMap();
            mMap.put("token", tokens[i]);
            mMap.put("tag", tags[i]);
            if (null != penntree_hashmap.get(tags[i])) {
                if (null != verb_hashmap.get(tags[i])) {
                    if (answer_list.size() == 0) {
                        answer_list.add(tokens[i]);
                    }
                }
                else {
                    if (not_answer_list == null) {
                        not_answer_list.add(tokens[i]);
                    }
                    else if (not_answer_list.size() < 3) {
                        not_answer_list.add(tokens[i]);
                    }
                }
                mMap.put("pos", penntree_hashmap.get(tags[i]));
                //check if the tag is a noun
            }
            else {
                mMap.put("pos", "null1");    
            }
            pos_list.add(mMap);
        }

        problemMap.put("prompt", prompt);
        problemMap.put("answer_list", answer_list.get(0));
        problemMap.put("not_answer_list", not_answer_list);
        return problemMap;
        //return prompt; //TODO add answer list 
    }
}