package com.mycompany.reader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.rule.Rule;

import com.mycompany.modal.Rules;
import com.mycompnay.dbmanager.DBController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ReadExcelFileExample {

    private static final String FILE_PATH = "C:\\Users\\ravindra_r.HCLCA\\Desktop\\nearGroup\\RuleList.xlsx";

    public static void main(String args[]) {

        List<Rules> studentList = getStudentsListFromExcel();

        System.out.println("SIZE :"+studentList.size());
        System.out.println(studentList);
        DBController.insertIntodb(studentList);
    }

    private static List getStudentsListFromExcel() {
        List<Rules> ruleList = new ArrayList<Rules>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(FILE_PATH);

            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);

            int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.iterator();

                //iterating over each row
                while (rowIterator.hasNext()) {

                    Rules rule = new Rules();
                    Row row = (Row) rowIterator.next();
                    Iterator cellIterator = row.cellIterator();

                    //Iterating over each cell (column wise)  in a particular row.
                    while (cellIterator.hasNext()) {

                        Cell cell = (Cell) cellIterator.next();
                        //The Cell Containing String will is name.
                        if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                        	
                        	if(cell.getColumnIndex() == 0)
                        	{
                        		System.out.println(cell.getStringCellValue() +" -");
                        		rule.setGroupName(cell.getStringCellValue());
                        	}
                        	else if(cell.getColumnIndex() == 1)
                        	{
                        		System.out.println(cell.getStringCellValue()+" \n");
                        		rule.setRule(cell.getStringCellValue());
                        	}
                            //student.setName(cell.getStringCellValue());

                            //The Cell Containing numeric value will contain marks
                        } /*else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {

                            //Cell with index 1 contains marks in Maths
                            if (cell.getColumnIndex() == 1) {
                                student.setMaths(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 2 contains marks in Science
                            else if (cell.getColumnIndex() == 2) {
                                student.setScience(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 3 contains marks in English
                            else if (cell.getColumnIndex() == 3) {
                                student.setEnglish(String.valueOf(cell.getNumericCellValue()));
                            }
                        }*/
                    }
                    //end iterating a row, add all the elements of a row in list
                    if(rule != null && rule.getGroupName() != null && rule.getRule() != null)
                    ruleList.add(rule);
                }
            }

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruleList;
    }


}