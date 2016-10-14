package NLPbot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class PosTaggerInitializer {
	static InputStream modelInPOStagger;
	static POSModel modelPOStagger;

	public InputStream getSourceModel() {
        try {
            modelInPOStagger = new FileInputStream("src/resources/input/en-pos-maxent.bin");

        }
        catch (FileNotFoundException e) {
            System.out.println("something went wrong" + e);
            e.printStackTrace();
        }
        return modelInPOStagger;
    }

    public POSModel createPosModel() {
    	try {
                modelPOStagger = new POSModel(modelInPOStagger);
            }
            catch (IOException e) {
              // Model loading failed, handle the error
              e.printStackTrace();
            }
            finally {
              if (modelInPOStagger != null) {
                try {
                  modelInPOStagger.close();
                }
                catch (IOException e) {
                }
              }
            }
        return modelPOStagger;
    }
}