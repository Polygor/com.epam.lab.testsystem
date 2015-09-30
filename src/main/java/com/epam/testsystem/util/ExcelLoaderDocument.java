package com.epam.testsystem.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;;
import java.util.List;

public class ExcelLoaderDocument {
    // This class parses xls/xlsx file
    public List<String> correctAnswers = new ArrayList<>();
    public List<String> getQuestionsData() {
        List<String> questions = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File("filename.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(1);

            //Iterate through each rows one by one
            for (Row row : sheet) {
                Cell question = row.getCell(0);
                String questionValue = question.getStringCellValue();
                questions.add(questionValue);
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    public List<String> getAnswersData() {
        List<String> answers = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(1);

            //Iterate through each rows one by one
            for (Row row : sheet) {
                Cell answer1 = row.getCell(1);
                Cell answer2 = row.getCell(2);
                Cell answer3 = row.getCell(3);
                Cell answer4 = row.getCell(4);
                Cell answer5 = row.getCell(5);
                String answer1val = answer1.getStringCellValue();
                String answer2val = answer2.getStringCellValue();
                String answer3val = answer3.getStringCellValue();
                String answer4val = answer4.getStringCellValue();
                String answer5val = answer5.getStringCellValue();
                answers.add(answer1val);
                answers.add(answer2val);
                answers.add(answer3val);
                answers.add(answer4val);
                answers.add(answer5val);
                correctAnswers.add(answer1val);
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return answers;
        }



public List<String> getCorrectAnswers(){
    try {
        FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        HSSFWorkbook workbook = new HSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(1);

        //Iterate through each rows one by one
        for (Row row : sheet) {
            Cell answer1 = row.getCell(1);
            String answer1val = answer1.getStringCellValue();
            correctAnswers.add(answer1val);
        }
        file.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return correctAnswers;
}

}
