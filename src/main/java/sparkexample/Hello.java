package sparkexample;

import freemarker.template.Configuration;
import freemarker.template.Template;
//import freemarker.template.Version;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.halt;

import java.io.StringWriter;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import NLPbot.TokenInitialzer;
import NLPbot.PosTaggerInitializer;
import penntree.PennTree;

import in.jaaga.learning.bots.languagebot.problems.Noun;
import in.jaaga.learning.bots.languagebot.problems.Verb;


public class Hello {

    static SentenceModel model;
    static InputStream modelIn;
    static InputStream modelInToken;
    static InputStream modelInPOStagger;
    static TokenizerModel modelToken;
    static POSModel modelPOStagger;
    static TokenInitialzer tokenInitialzer;
    static PosTaggerInitializer posTaggerInitializer;


    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Hello.class, "/");
/*        get("/", (req, res) -> {
            return "hello from sparkjava.com";
        });*/
        get("/", (request, response) -> {
            System.out.println("main.get./");
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/form.ftl");
 
                formTemplate.process(null, writer);
            } catch (Exception e) {
                System.out.println("something went wrong" + e);
                halt(500);
            }
 
            return writer;
        });

        post("/sait", (request, response) -> {
            System.out.println("main.post./sayit");
            StringWriter writer = new StringWriter();
            //get the models from source, used to detect sentences
            try {
                System.out.println("trying to get the InputStream");
                modelIn = new FileInputStream("src/resources/input/en-sent.bin");
                System.out.println(modelIn);

            }
            catch (FileNotFoundException e) {
                System.out.println("something went wrong" + e);
                e.printStackTrace();
            }
            //create a new model
            try {
                model = new SentenceModel(modelIn);
                System.out.println(model);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (modelIn != null) {
                    try {
                        modelIn.close();
                    }
                    catch (IOException e) {
                    }
                }
            }
            //from the user input, split the paragraph into sentences
            try {
                String user_input_text = request.queryParams("user_input_text") != null ? request.queryParams("user_input_text") : "anonymous";
                System.out.println(user_input_text);
                SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
     
                String[] sentences = sentenceDetector.sentDetect(user_input_text);

                System.out.println(sentences.getClass());
                for (String sentence : sentences) {
                    System.out.println(sentence);
                }

                Template resultTemplate = configuration.getTemplate("templates/result.ftl");
 
                Map<String, Object> map = new HashMap<>();
                map.put("user_input_text", user_input_text);
                map.put("sentences", sentences);
                resultTemplate.process(map, writer);
            } catch (Exception e) {
                System.out.println(e);
                halt(500);
            }
            return writer;
        });

        get("/opennlp/token", (request, response) -> {
            System.out.println("main.get./opennlp/token");
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/token_form.ftl");
 
                formTemplate.process(null, writer);
            } catch (Exception e) {
                System.out.println("something went wrong" + e);
                halt(500);
            }
 
            return writer;
        });

        post("/token/result", (request, response) -> {
            System.out.println("main.post./token/result");
            StringWriter writer = new StringWriter();
            //get the models from source, used to detect sentences
            tokenInitialzer = new TokenInitialzer();
            modelInToken = tokenInitialzer.getSourceModel();
            //create a new model
            modelToken = tokenInitialzer.createTokenModel();
            //from the user input, split the paragraph into tokens
            try {
                String user_input_text = request.queryParams("user_input_text") != null ? request.queryParams("user_input_text") : "anonymous";
                System.out.println(user_input_text);
                Tokenizer tokenizer = new TokenizerME(modelToken);
     
                String[] tokens = tokenizer.tokenize(user_input_text);

                Template resultTemplate = configuration.getTemplate("templates/token_result.ftl");
 
                Map<String, Object> map = new HashMap<>();
                map.put("user_input_text", user_input_text);
                map.put("tokens", tokens);
                resultTemplate.process(map, writer);
            } catch (Exception e) {
                System.out.println(e);
                halt(500);
            }
            return writer;
        });

        get("/opennlp/part-of-speech/noun/", (request, response) -> {
            System.out.println("main.get./opennlp/part-of-speech");
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/noun/part_of_speech_form.ftl");
 
                formTemplate.process(null, writer);
            } catch (Exception e) {
                System.out.println("something went wrong" + e);
                halt(500);
            }

            return writer;
        });

        post("/opennlp/part-of-speech/noun/result", (request, response) -> {
            System.out.println("main.post./opennlp/part-of-speech/noun/result");
            StringWriter writer = new StringWriter();
            // setup token model and part of speech model
            tokenInitialzer = new TokenInitialzer();
            modelInToken = tokenInitialzer.getSourceModel();
            //create a new model
            modelToken = tokenInitialzer.createTokenModel();

            // setup part of speech model and part of speech model

            posTaggerInitializer = new PosTaggerInitializer();
            modelInPOStagger = posTaggerInitializer.getSourceModel();

            // cerate new part of speech model
            modelPOStagger = posTaggerInitializer.createPosModel();
            Tokenizer tokenizer = new TokenizerME(modelToken);
            POSTaggerME tagger = new POSTaggerME(modelPOStagger);

            //from the user input, split the paragraph into tokens, then part of speech it
            try {
                String user_input_text = request.queryParams("user_input_text") != null ? request.queryParams("user_input_text") : "anonymous";
                System.out.println(user_input_text);

                Noun noun = new Noun();
                HashMap noun_problem = noun.createNounProblem(user_input_text, tokenizer, tagger); 

                Template resultTemplate = configuration.getTemplate("templates/noun/part_of_speech_result.ftl");
 
                Map<String, Object> map = new HashMap<>();
                map.put("user_input_text", user_input_text);
                //map.put("tokens", tokens);
                //map.put("tags", tags);
                //map.put("pos_list", pos_list);
                map.put("prompt", noun_problem.get("prompt"));
                map.put("answer_list", noun_problem.get("answer_list").toString());
                map.put("not_answer_list", noun_problem.get("not_answer_list"));
                
                System.out.println(noun_problem);
                resultTemplate.process(map, writer);
            } catch (Exception e) {
                System.out.println(e);
                halt(500);
            }
            return writer;

        });

        get("/opennlp/part-of-speech/verb/", (request, response) -> {
            System.out.println("main.get./opennlp/part-of-speech/verb");
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/verb/part_of_speech_form.ftl");
 
                formTemplate.process(null, writer);
            } catch (Exception e) {
                System.out.println("something went wrong" + e);
                halt(500);
            }

            return writer;
        });

        post("/opennlp/part-of-speech/verb/result", (request, response) -> {
            System.out.println("main.post./opennlp/part-of-speech/verb/result");
            StringWriter writer = new StringWriter();
            // setup token model and part of speech model
            tokenInitialzer = new TokenInitialzer();
            modelInToken = tokenInitialzer.getSourceModel();
            //create a new model
            modelToken = tokenInitialzer.createTokenModel();

            // setup part of speech model and part of speech model

            posTaggerInitializer = new PosTaggerInitializer();
            modelInPOStagger = posTaggerInitializer.getSourceModel();

            // cerate new part of speech model
            modelPOStagger = posTaggerInitializer.createPosModel();
            Tokenizer tokenizer = new TokenizerME(modelToken);
            POSTaggerME tagger = new POSTaggerME(modelPOStagger);

            //from the user input, split the paragraph into tokens, then part of speech it
            try {
                String user_input_text = request.queryParams("user_input_text") != null ? request.queryParams("user_input_text") : "anonymous";
                System.out.println(user_input_text);

                Verb verb = new Verb();
                HashMap verb_problem = verb.createVerbProblem(user_input_text, tokenizer, tagger); 

                Template resultTemplate = configuration.getTemplate("templates/verb/part_of_speech_result.ftl");
 
                Map<String, Object> map = new HashMap<>();
                map.put("user_input_text", user_input_text);
                //map.put("tokens", tokens);
                //map.put("tags", tags);
                //map.put("pos_list", pos_list);
                map.put("prompt", verb_problem.get("prompt"));
                map.put("answer_list", verb_problem.get("answer_list").toString());
                map.put("not_answer_list", verb_problem.get("not_answer_list"));
                
                System.out.println(verb_problem);
                resultTemplate.process(map, writer);
            } catch (Exception e) {
                System.out.println(e);
                halt(500);
            }
            return writer;

        });

        get("/opennlp/part-of-speech", (request, response) -> {
            System.out.println("main.get./opennlp/part-of-speech");
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/part_of_speech_form.ftl");
 
                formTemplate.process(null, writer);
            } catch (Exception e) {
                System.out.println("something went wrong" + e);
                halt(500);
            }
 
            return writer;
        });

        post("/part-of-speech/result", (request, response) -> {
            System.out.println("main.post./opennlp/part-of-speech/result");
            StringWriter writer = new StringWriter();
            // setup token model and part of speech model
            tokenInitialzer = new TokenInitialzer();
            modelInToken = tokenInitialzer.getSourceModel();
            //create a new model
            modelToken = tokenInitialzer.createTokenModel();

            // setup part of speech model and part of speech model

            posTaggerInitializer = new PosTaggerInitializer();
            modelInPOStagger = posTaggerInitializer.getSourceModel();

            // cerate new part of speech model
            modelPOStagger = posTaggerInitializer.createPosModel();
            Tokenizer tokenizer = new TokenizerME(modelToken);
            POSTaggerME tagger = new POSTaggerME(modelPOStagger);

            //from the user input, split the paragraph into tokens, then part of speech it
            try {
                String user_input_text = request.queryParams("user_input_text") != null ? request.queryParams("user_input_text") : "anonymous";
                System.out.println(user_input_text);

                Noun noun = new Noun();
                HashMap noun_problem = noun.createNounProblem(user_input_text, tokenizer, tagger); 

                Template resultTemplate = configuration.getTemplate("templates/part_of_speech_result.ftl");
 
                Map<String, Object> map = new HashMap<>();
                map.put("user_input_text", user_input_text);
                //map.put("tokens", tokens);
                //map.put("tags", tags);
                //map.put("pos_list", pos_list);
                map.put("prompt", noun_problem.get("prompt"));
                map.put("answer_list", noun_problem.get("answer_list").toString());
                map.put("not_answer_list", noun_problem.get("not_answer_list"));
                
                System.out.println(noun_problem);
                resultTemplate.process(map, writer);
            } catch (Exception e) {
                System.out.println(e);
                halt(500);
            }
            System.out.println("here's teh querymap");
            System.out.println(request.queryMap());
            return writer;

        });

        post("/part-of-speech/answer-check", (request, response) -> {

            String answer_check_response;
            StringWriter writer = new StringWriter();
            System.out.println("post./part-of-speech/answer");
            String user_input_answer = request.queryParams("user_input_answer") != null ? request.queryParams("user_input_answer") : "anonymous";
            //clean user input data
            String answer_list = request.queryParams("answer_list") != null ? request.queryParams("answer_list") : "anonymous";
            String[] cleaned_input_answer = answer_list.split("/");
            String cleaned_answer = cleaned_input_answer[0];
            String prompt = request.queryParams("prompt") != null ? request.queryParams("prompt") : "anonymous";

            Map<String, Object> map = new HashMap<>();
            if (user_input_answer.equals(cleaned_answer)) {
                answer_check_response = "Correct, move onto the next Problem";
            }
            else {
                answer_check_response = "sorry, try again";
            }
            map.put("answer_check_response", answer_check_response);
            try{
                Template answerCheckTemplate = configuration.getTemplate("templates/noun_answer_check.ftl");
                answerCheckTemplate.process(map, writer);
            } catch (Exception e) {
                System.out.println(e);
            }
            return writer;
        });

    }
}