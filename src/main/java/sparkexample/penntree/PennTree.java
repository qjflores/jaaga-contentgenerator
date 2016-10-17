package penntree;

import java.util.HashMap;
import java.util.Map;

public class PennTree {
    HashMap<String, String> pennTreeMap;

    public HashMap getPennTreeHashMap() {
        HashMap<String, String> pennTreeMap = new HashMap<String, String>();
        pennTreeMap.put("CC", "Coordinating conjunction");
        pennTreeMap.put("CD", "Cardinal number");
        pennTreeMap.put("DT", "Determiner");
        pennTreeMap.put("EX", "Existential there");
        pennTreeMap.put("FW", "Foreign word");
        pennTreeMap.put("IN", "Preposition or subordinating conjunction");
        pennTreeMap.put("JJ", "Adjective");
        pennTreeMap.put("JJR", "Adjective, comparative");
        pennTreeMap.put("JJS", "Adjective, superlative");
        pennTreeMap.put("LS", "List item marker");
        pennTreeMap.put("MD", "Modal");
        pennTreeMap.put("NN", "Noun, singular or mass");
        pennTreeMap.put("NNS", "Noun, plural");
        pennTreeMap.put("NNP", "Proper noun, singular");
        pennTreeMap.put("NNPS", "Proper noun, plural");
        pennTreeMap.put("PDT", "Predeterminer");
        pennTreeMap.put("POS", "Possessive ending");
        pennTreeMap.put("PRP", "Personal pronoun");
        pennTreeMap.put("PRP$", "Possessive pronoun");
        pennTreeMap.put("RB",   "Adverb");
        pennTreeMap.put("RBR", "Adverb, comparative");
        pennTreeMap.put("RBS", "Adverb, superlative");
        pennTreeMap.put("RP", "Particle");
        pennTreeMap.put("SYM", "Symbol");
        pennTreeMap.put("TO", "to");
        pennTreeMap.put("UH", "Interjection");
        pennTreeMap.put("VB", "Verb, base form");
        pennTreeMap.put("VBD", "Verb, past tense");
        pennTreeMap.put("VBG", "Verb, gerund or present participle");
        pennTreeMap.put("VBN", "Verb, past participle");
        pennTreeMap.put("VBP", "Verb, non-3rd person singular present");
        pennTreeMap.put("VBZ", "Verb, 3rd person singular present");
        pennTreeMap.put("WDT", "Wh-determiner");
        pennTreeMap.put("WP", "Wh-pronoun");
        pennTreeMap.put("WP$", "Possessive wh-pronoun");
        pennTreeMap.put("WRB", "Wh-adverb");
        return pennTreeMap;
    }

    public HashMap getPennTreeNouns() {
        HashMap<String, String> pennTreeNounMap = new HashMap<String, String>();
        pennTreeNounMap.put("NN", "Noun, singular or mass");
        pennTreeNounMap.put("NNS", "Noun, plural");
        pennTreeNounMap.put("NNP", "Proper noun, singular");
        pennTreeNounMap.put("NNPS", "Proper noun, plural");
        return pennTreeNounMap;
    }

    public HashMap getPennTreeVerbs() {
        HashMap<String, String> pennTreeVerbMap = new HashMap<String, String>();
        pennTreeVerbMap.put("VB", "Verb, base form");
        pennTreeVerbMap.put("VBD", "Verb, past tense");
        pennTreeVerbMap.put("VBG", "Verb, gerund or present participle");
        pennTreeVerbMap.put("VBN", "Verb, past participle");
        pennTreeVerbMap.put("VBP", "Verb, non-3rd person singular present");
        pennTreeVerbMap.put("VBZ", "Verb, 3rd person singular present");
        return pennTreeVerbMap;
    }

    public HashMap getPennTreeAdjectives() {
        HashMap<String, String> pennTreeAdjectiveMap = new HashMap<String, String>();
        pennTreeAdjectiveMap.put("JJ", "Adjective");
        pennTreeAdjectiveMap.put("JJR", "Adjective, comparative");
        pennTreeAdjectiveMap.put("JJS", "Adjective, superlative");
        return pennTreeAdjectiveMap;
    }

    public HashMap getPennTreeAdverbs() {
        HashMap<String, String> pennTreeAdverbMap = new HashMap<String, String>();
        pennTreeAdverbMap.put("RB",   "Adverb");
        pennTreeAdverbMap.put("RBR", "Adverb, comparative");
        pennTreeAdverbMap.put("RBS", "Adverb, superlative");
        pennTreeAdverbMap.put("WRB", "Wh-adverb");
        return pennTreeAdverbMap;
    }

}