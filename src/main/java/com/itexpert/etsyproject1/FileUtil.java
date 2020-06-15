/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpert.etsyproject1;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileUtil {
    public static LoginVO getLoginData(){
        LoginVO login = null;
    try{
     FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Vitali\\Documents\\EtsyProjectData.xlsx"));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        //getting first worksheet
        Sheet firstSheet = workbook.getSheetAt(0);
        //get first row
        Row r =  firstSheet.getRow(1);
        Cell c = (Cell) r.getCell(0); //username value
        String username = c.getStringCellValue();
        c = (Cell) r.getCell(1); //username value
        String password = c.getStringCellValue();
        
        c = (Cell) r.getCell(2);
        String currency = c.getStringCellValue();
        c= (Cell) r.getCell(3);
        String region = c.getStringCellValue();
        
        c = (Cell) r.getCell(4);
        String regionVerf = c.getStringCellValue();
        c =(Cell) r.getCell(5);
        String newPass3 = c.getStringCellValue();
        
        c= (Cell) r.getCell(6);
        String newPass3Invalid = c.getStringCellValue();
        
        c = (Cell) r.getCell(7);
        String errorMesg3 = c.getStringCellValue();
        c =(Cell) r.getCell(8);
        String currentPassInvalid = c.getStringCellValue();
        c= (Cell) r.getCell(9);
        String errorMesg4 = c.getStringCellValue();
        
        c= (Cell) r.getCell(11);
        String newPassword = c.getStringCellValue();
        
        c = (Cell) r.getCell(12);
        String errorMesg5 = c.getStringCellValue();
        
        c= (Cell) r.getCell(13);
        String errorMesg6 = c.getStringCellValue();
        
        
        login = new LoginVO(username, password,currency,region,regionVerf,newPass3,newPass3Invalid,errorMesg3,
                currentPassInvalid, errorMesg4,newPassword,errorMesg5,errorMesg6);
        
        System.out.println(login);
        inputStream.close();
    }catch (Exception e){
        e.printStackTrace();
    }
    return login;
    }
    
    public static void main(String args[]){
     FileUtil.getLoginData();
    
    }
    
}
