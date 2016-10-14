package NLPbot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.TokenizerME;

public class TokenInitialzer {
    static InputStream modelInToken;
    static TokenizerModel modelToken;

    public InputStream getSourceModel() {
        try {
            modelInToken = new FileInputStream("src/resources/input/en-token.bin");

        }
        catch (FileNotFoundException e) {
            System.out.println("something went wrong" + e);
            e.printStackTrace();
        }
        return modelInToken;
    }

    public TokenizerModel createTokenModel() {
        try {
            modelToken = new TokenizerModel(modelInToken);
            System.out.println(modelToken);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (modelInToken != null) {
                try {
                    modelInToken.close();
                }
                catch (IOException e) {
                }
            }
        }
        return modelToken;
    }
}