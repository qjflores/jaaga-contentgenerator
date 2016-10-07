package sparkexample;

import freemarker.template.Configuration;
import freemarker.template.Template;
//import freemarker.template.Version;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.halt;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Hello {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Hello.class, "/");
/*        get("/", (req, res) -> {
            return "hello from sparkjava.com";
        });*/
        get("/", (request, response) -> {
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
            StringWriter writer = new StringWriter();
 
            try {
                String name = request.queryParams("name") != null ? request.queryParams("name") : "anonymous";
                String email = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
 
                Template resultTemplate = configuration.getTemplate("templates/result.ftl");
 
                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("email", email);
 
                resultTemplate.process(map, writer);
            } catch (Exception e) {
                halt(500);
            }
 
            return writer;
        });
    }
}