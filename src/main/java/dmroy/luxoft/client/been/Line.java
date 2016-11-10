package dmroy.luxoft.client.been;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

/**
 *
 * @author dmitriyroy
 */
@Component
@XmlRootElement(name = "line")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Line {
    private long fileId;
    private String fileName;
    private int lineNumber;
    private String maxWord;
    private String minWord;
    private int minWordLength;
    private int maxWordLength;
    private int averageWordLength;
    private long allWordsLength;
    private int wordsCount;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMaxWord() {
        return maxWord;
    }

    public void setMaxWord(String maxWord) {
        this.maxWord = maxWord;
    }

    public String getMinWord() {
        return minWord;
    }

    public void setMinWord(String minWord) {
        this.minWord = minWord;
    }

    public int getMinWordLength() {
        return minWordLength;
    }

    public void setMinWordLength(int minWordLength) {
        this.minWordLength = minWordLength;
    }

    public int getMaxWordLength() {
        return maxWordLength;
    }

    public void setMaxWordLength(int maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    public int getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(int averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public long getAllWordsLength() {
        return allWordsLength;
    }

    public void setAllWordsLength(long allWordsLength) {
        this.allWordsLength = allWordsLength;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Line other = (Line) obj;
        if (this.fileId != other.fileId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Line{" + "fileId=" + fileId + ", fileName=" + fileName + ", lineNumber=" + lineNumber + ", maxWord=" + maxWord + ", minWord=" + minWord + ", minWordLength=" + minWordLength + ", maxWordLength=" + maxWordLength + ", averageWordLength=" + averageWordLength + ", allWordsLength=" + allWordsLength + ", wordsCount=" + wordsCount + '}';
    }




}
