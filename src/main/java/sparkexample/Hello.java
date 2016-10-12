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
import java.util.ArrayList;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;

public class Hello {

    static SentenceModel model;
    static InputStream modelIn;

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
    }
}