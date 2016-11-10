package dmroy.luxoft.client.dao;

import static dmroy.luxoft.client.Main.SERVER_URL;
import dmroy.luxoft.client.been.Line;
import dmroy.luxoft.client.util.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author dmitriyroy
 */
@Repository
public class FileStatisticDao implements FileStatisticDaoInterface{

//    private static JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public FileStatisticDao() {
    }

    @Override
    public void writeIntoFile(List<Line> lineList, String inFile) {
        boolean goodFile = true;
        do{
            File file = FileUtils.getFile("Введите название файл для вывода результата.");
            if(!file.getAbsolutePath().equals(inFile)){
                writeIntoFile(lineList,inFile, file);
                goodFile = false;
            }else{
                JOptionPane.showMessageDialog(null,
                                            "Нельзя вывести лог в самого себя.",
                                            "Внимание!",
                                            JOptionPane.WARNING_MESSAGE);
            }
        }while(goodFile);
    }

    @Override
    public void writeIntoFile(List<Line> lineList, String inFile, String outFileName) {
        if(!outFileName.equals(inFile)){
            writeIntoFile(lineList, inFile, new File(outFileName));
        }
    }

    @Override
    public void writeIntoFile(List<Line> lineList,String inFile ,File outFile) {
        try {
            FileOutputStream os = new FileOutputStream(outFile);
            os.write("\n".getBytes());
            for(Line line:lineList){
                os.write(line.toString().getBytes());
                os.write("\n".getBytes());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileStatisticDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileStatisticDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeIntoDB(List<Line> lineList) {
        for (Line line:lineList) {
            System.out.println("Отправляем запрос:line.toString() =  " + line.toString());
//            String req = SERVER_URL + "/fileparser/addFile/"+line.getFileId()
//                    +"/"+line.getFileName()
//                    +"/"+line.getLineNumber()
//                    +"/"+line.getMinWord()
//                    +"/"+line.getMaxWord()
//                    +"/"+line.getMinWordLength()
//                    +"/"+line.getMaxWordLength()
//                    +"/"+line.getAverageWordLength()
//                    +"/"+line.getAllWordsLength()
//                    +"/"+line.getWordsCount();
            String req = SERVER_URL + "/fileparser/test/"+line;
            System.out.println("Такой запрос: " + req);
            Map<String,Object> mapObject = new HashMap<>();
            mapObject.put("fileId",line.getFileId());
            mapObject.put("fileName",line.getFileName());
            mapObject.put("lineNumber",line.getLineNumber());
            mapObject.put("minWord",line.getMinWord());
            mapObject.put("maxWord",line.getMaxWord());
            mapObject.put("minWordLength",line.getMinWordLength());
            mapObject.put("maxWordLength",line.getMaxWordLength());
            mapObject.put("averageWordLength",line.getAverageWordLength());
            mapObject.put("allWordsLength",line.getAllWordsLength());
            mapObject.put("wordsCount",line.getWordsCount());

            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.getForObject(SERVER_URL + "/fileparser/test/{requestData}",Line.class,line);
            restTemplate.getForObject(SERVER_URL + "/fileparser/addFile/{fileId}/{fileName}/{lineNumber}/{minWord}"
//            restTemplate.getForEntity(SERVER_URL + "/fileparser/addFile/{fileId}/{fileName}/{lineNumber}/{minWord}"
                    + "/{maxWord}/{minWordLength}/{maxWordLength}"
                    + "/{averageWordLength}/{allWordsLength}/{wordsCount}", Line.class,
                                            line.getFileId(), line.getFileName(), line.getLineNumber(), line.getMinWord(),
                                            line.getMaxWord(), line.getMinWordLength(), line.getMaxWordLength(),
                                            line.getAverageWordLength(), line.getAllWordsLength(), line.getWordsCount());
//            restTemplate.getForObject(SERVER_URL + "/fileparser/addFile/{fileId}/{fileName}/{lineNumber}/{minWord}"
//                    + "/{maxWord}/{minWordLength}/{maxWordLength}"
//                    + "/{averageWordLength}/{allWordsLength}/{wordsCount}", Line.class, mapObject);
        }
    }

}
