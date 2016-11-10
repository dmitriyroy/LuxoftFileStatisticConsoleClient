package dmroy.luxoft.client.service;

import dmroy.luxoft.client.Main;
import dmroy.luxoft.client.been.Line;
import dmroy.luxoft.client.util.FileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmitriyroy
 */
public class FileParser implements FileParserInterface{

    private File file;
    Set<String> globalWordSet = new HashSet<>();
    private int globalMinWordLength = Integer.MAX_VALUE;
    private int globalMaxWordLength = -1;
    private String globalMinWord;
    private String globalMaxWord;
    private int lineNumber = 0;

    public FileParser() {
        this.file = FileUtils.getFile("Выберите файл для разбора статистики");
    }

    public FileParser(File file) {
        this.file = file;
    }

    @Override
    public List<Line> parseFile() {
        System.out.println("parseFile()");
        List<Line> outList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file) , "cp1251"/*StandardCharsets.UTF_8*/ ))) {
            String fileLline;
            // ID файла, для ключа в базе
            Long fileId = new Date().getTime();
            while ((fileLline = reader.readLine()) != null) {
                lineNumber++;
                int lineLength = fileLline.length();
                // берем только непустые строки
                if(lineLength > 0){
                    String[] words = fileLline.split(" ");
                    Set<String> wordSet = new HashSet<>();
                    int minWordLength = Integer.MAX_VALUE;
                    int maxWordLength = -1;
                    String minWord = null;
                    String maxWord = null;
                    for (String word:words) {
                        if(word.length() > 0){
                            // START: Line-block
                            if(word.length() < minWordLength){
                                minWordLength = word.length();
                                minWord = word;
                            }
                            if(word.length() > maxWordLength){
                                maxWordLength = word.length();
                                maxWord = word;
                            }
                            wordSet.add(word);
                            // STOP: Line-block

                            // START: Global-block
                            if(word.length() < globalMinWordLength){
                                globalMinWordLength = word.length();
                                globalMinWord = word;
                            }
                            if(word.length() > globalMaxWordLength){
                                globalMaxWordLength = word.length();
                                globalMaxWord = word;
                            }
                            globalWordSet.add(word);
                            // STOP: Global-block

                        }
                    }
                    long wordsLength = 0L;
                    for(String s:wordSet){
                        wordsLength += s.length();
                    }
                    int averageWordLength = 0;
                    averageWordLength = (int)wordsLength/wordSet.size();
                    Line line = new Line();
                    line.setFileId(fileId);
                    line.setFileName(file.getAbsolutePath());
                    line.setLineNumber(lineNumber);
                    line.setMinWord(minWord);
                    line.setMaxWord(maxWord);
                    line.setMinWordLength(minWordLength);
                    line.setMaxWordLength(maxWordLength);
                    line.setAverageWordLength(averageWordLength);
                    line.setAllWordsLength(wordsLength);
                    line.setWordsCount(wordSet.size());
                    outList.add(line);

                }
            }

        } catch (IOException e) {
            // log error
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
            return outList;
    }

    public Set<String> getGlobalWordSet() {
        return globalWordSet;
    }

    public void setGlobalWordSet(Set<String> globalWordSet) {
        this.globalWordSet = globalWordSet;
    }

    public int getGlobalMinWordLength() {
        return globalMinWordLength;
    }

    public void setGlobalMinWordLength(int globalMinWordLength) {
        this.globalMinWordLength = globalMinWordLength;
    }

    public int getGlobalMaxWordLength() {
        return globalMaxWordLength;
    }

    public void setGlobalMaxWordLength(int globalMaxWordLength) {
        this.globalMaxWordLength = globalMaxWordLength;
    }

    public String getGlobalMinWord() {
        return globalMinWord;
    }

    public void setGlobalMinWord(String globalMinWord) {
        this.globalMinWord = globalMinWord;
    }

    public String getGlobalMaxWord() {
        return globalMaxWord;
    }

    public void setGlobalMaxWord(String globalMaxWord) {
        this.globalMaxWord = globalMaxWord;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }


}
