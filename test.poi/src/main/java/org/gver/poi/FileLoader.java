package org.gver.poi;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkState;

/**
 * 功能描述: <p/>
 *
 * @author wanggen on 14-9-12.
 *
 */
public class FileLoader {


    public static Workbook load(String path) throws IOException {
        checkState(new File(path).exists(), "文件 %s 不存在", path);
        Workbook workbook=null;
        if(path.endsWith("xls"))
            workbook = new HSSFWorkbook(new FileInputStream(path));
        else if(path.endsWith("xlsx"))
            workbook = new XSSFWorkbook(new FileInputStream(path));
        return workbook;
    }

    @Test
    public void test(){

        Workbook workbook = null;
        try {
            workbook = FileLoader.load("/Users/wanggen/Downloads/供应商联系方式（删除已优化供应商）.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<workbook.getNumberOfSheets();i++){
            Sheet sheet = workbook.getSheetAt(i);
            if(sheet.getSheetName().endsWith("(T)")){
                String tableName = sheet.getSheetName().replaceAll("\\([Tt]\\)", "");
                Map<Integer, ColumnMeta> columnMetaMap = Maps.newTreeMap();
                int maxRow=1000, maxCol=256;

                for(int row=sheet.getFirstRowNum();row<=maxRow;row++){

                    if(row==sheet.getFirstRowNum()){
                        Row cells = sheet.getRow(row);
                        for(int col=cells.getFirstCellNum();col<=maxCol;col++){
                            Cell cell = cells.getCell(col);
                            String text = cell.getStringCellValue();
                            if(text.trim().equals("")){
                                if(cells.getCell(col+1)==null || "".equals(cells.getCell(col+1).getStringCellValue())){
                                    maxCol = col-1;
                                    break;
                                }
                            }
                            if(text.indexOf('(')==-1)
                                continue;
                            String columnDesc = text.substring(text.indexOf('(')+1, text.indexOf(')'));
                            List<String> infos = Splitter.on(',').trimResults().splitToList(columnDesc.replaceAll("\\s+", ""));
                            ColumnMeta columnMeta = new ColumnMeta();
                            for(String _v: infos){
                                if(_v.startsWith("column")){
                                    String colum = _v.split("=")[1];
                                    columnMeta.setColumn(colum);
                                    columnMeta.setIndex(col);
                                    columnMetaMap.put(col, columnMeta);
                                }else if(_v.startsWith("type")){
                                    String[] props = _v.split("=");
                                    if(props[0].equals("date")){
                                        if(Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", props[1]))
                                            columnMeta.formatter = DateTimeFormat.forPattern("YYYY-MM-dd");
                                        else if(Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", props[1]))
                                            columnMeta.formatter = DateTimeFormat.forPattern("YYYY/MM/dd");
                                        else if (Pattern.matches("^\\d{4}\\d{2}\\d{2}$", props[1]))
                                            columnMeta.formatter = DateTimeFormat.forPattern("YYYYMMdd");
                                        else if (Pattern.matches("^\\d{2}\\d{2}\\d{2}$", props[1]))
                                            columnMeta.formatter = DateTimeFormat.forPattern("YYMMdd");
                                    }else if(props[0].equals("datetime")){

                                    }
                                }
                            }
                        }
                        continue;
                    }

                    int maxBlank = 0;
                    Row cells = sheet.getRow(row);
                    if(cells==null) break;
                    Map<Integer, String> rowData = Maps.newTreeMap();
                    for(int col=cells.getFirstCellNum()+1;col<=maxCol;col++){

                        Cell cell = cells.getCell(col);
                        int type = cell.getCellType();
                        String text = "";

                        switch (type){
                            case Cell.CELL_TYPE_BLANK:
                                text = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                text = cell.getBooleanCellValue()+"";
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                text = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                text = new BigDecimal(cell.getNumericCellValue()).toPlainString();
                                break;
                            default:
                                text = cell.getStringCellValue();
                        }

                        if(columnMetaMap.containsKey(col))
                            rowData.put(columnMetaMap.get(col).getIndex(), text.equals("")?null:text);

                    }

                    String insertSQL = buildInsertSQL(tableName, columnMetaMap);
                    String valueSQL = buildValueSQL(rowData, columnMetaMap);
                    System.out.println(insertSQL+valueSQL);

                    if(maxBlank>=30)
                        break;

                }


            }
        }
    }

    private String buildValueSQL(Map<Integer, String> rowData, Map<Integer, ColumnMeta> columnMetaMap) {
        StringBuilder valueSQL = new StringBuilder();
        valueSQL.append("\n\t VALUES(");
        for(Map.Entry<Integer, String> entry: rowData.entrySet()){
            DateTimeFormatter formatter=null;
            if((formatter=columnMetaMap.get(entry.getKey()).formatter)!=null && !Strings.isNullOrEmpty(entry.getValue())){
                DateTime dateTime = formatter.parseDateTime(entry.getValue());
                String dateString = formatter.print(dateTime);
                entry.setValue(dateString);
            }
            if(entry.getValue()==null)
                valueSQL.append(entry.getValue());
            else
                valueSQL.append("'").append(entry.getValue()).append("',");
        }
        valueSQL.deleteCharAt(valueSQL.length()-1).append(")");
        return valueSQL.toString();
    }

    private String buildInsertSQL(String tableName, Map<Integer, ColumnMeta> columnMetaMap) {
        StringBuilder sql = new StringBuilder(" INSERT INTO ");
        sql.append(tableName).append("(");
        for(Map.Entry<Integer, ColumnMeta> entry: columnMetaMap.entrySet()){
            sql.append(entry.getValue().getColumn()).append(",");
        }
        sql.deleteCharAt(sql.length()-1)
                .append(")");
        return sql.toString();
    }


}
