


package com.appian.robot.core.template;

import com.novayre.jidoka.client.api.IJidokaServer;
import com.novayre.jidoka.client.api.IRobot;
import com.novayre.jidoka.client.api.JidokaFactory;
import com.novayre.jidoka.client.api.annotations.Robot;
import com.novayre.jidoka.client.api.multios.IClient;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.jna.platform.mac.MacFileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class RobotBlankTemplate.
 */
@Robot
public class RobotBlankTemplate implements IRobot {

    /**
     * The server.
     */
    private IJidokaServer<?> server;

    /**
     * The client.
     */
    private IClient client;
    private String file = "E:/file.csv";
    private String xlsFileLocation="E://New folder/excel.xlsx";
    private ArrayList<String> list;
    private List<List<String>> data;
    private Object FileFormatType;
    public static final int CSV = 0;
    /**
     * Initialize the modules
     */
    public void start() {

        server = JidokaFactory.getServer();
        client = IClient.getInstance(this);

        server.debug("Robot initialized");

    }

    public <LoadOptions> void readCsv() throws Exception {

try {
    data = new ArrayList<List<String>>();
    CSVReader reader = new CSVReader(new FileReader(file));
    server.info("file present");
    for (String[] str : reader) {
        list = new ArrayList<String>();
        for (String s : str) {
            list.add(s);
        }
        data.add(list);

    }
    server.warn(data);
    // LoadOptions loadOptions = new LoadOptions(FileFormatType);
} catch (FileNotFoundException e) {
    e.printStackTrace();
}

    }

    public void writeinExcel() throws Exception {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");
            int rownum = 0;
            int cellnum = 0;
            for (List<String> ls : data) {
                Row row = sheet.createRow(rownum++);
                for (int i = 0; i < 15; i++) {
                    row.createCell(i).setCellValue(ls.get(i));
                }

            }
            FileOutputStream outputStream = new FileOutputStream(xlsFileLocation);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * End.
     */
    public void end() {

    }
}
