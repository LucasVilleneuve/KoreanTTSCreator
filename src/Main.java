import com.voicerss.tts.Languages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main
{
    public static ArrayList<String> getSentencesFromFile(String filePath)
    {
        ArrayList<String> sentences = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null)
            {
                sentences.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sentences;
    }

    public static void main (String[] args) throws Exception
    {
        if (args.length < 1)
        {
            System.out.println("Need to specify api key.");
            return;
        }

        ArrayList<String> sentences = getSentencesFromFile("./data/KoreanQuestions.txt");

        String apiKey = args[0];
        AudioFileCreator audioCreator = new AudioFileCreator(apiKey, "./audioFiles");

        String language = Languages.Korean;

        for (int i = 0; i < sentences.size(); ++i)
        {
            audioCreator.createAudioFile("Question" + (i + 1), sentences.get(i), language);
        }
    }
}