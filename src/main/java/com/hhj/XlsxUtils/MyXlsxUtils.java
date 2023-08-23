package com.hhj.XlsxUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * xlsx/excel 文件 增删改查操作
 */
public class MyXlsxUtils {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\20365\\Desktop" + File.separator + "test.xlsx";
        System.out.println(getExcelMessage(filePath));
        Map<String, List<String>> excelMessage = getExcelMessage(filePath);
        outPutExcel(getMapKeyValue(excelMessage));
    }

    public static Map<String, List<String>> getEncExcelMessage(String filePath,String password) {
        return getEncExcelMessage(filePath,password,0);
    }
    //读取加密excel文件
    public static Map<String, List<String>> getEncExcelMessage(String filePath,String password,int sheetIndex){
        InputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            Workbook workbook = null;
            //解密
            POIFSFileSystem pfs = new POIFSFileSystem(fis);
            EncryptionInfo encInfo = new EncryptionInfo(pfs);
            Decryptor decryptor = new Decryptor(encInfo);
            decryptor.verifyPassword(password);
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(decryptor.getDataStream(pfs));
            } else if (filePath.endsWith(".xls") || filePath.endsWith(".et")) {
                workbook = new HSSFWorkbook(decryptor.getDataStream(pfs));
            }
            fis.close();
            return new MyXlsxUtils().parseExcel(workbook,sheetIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, List<String>> getExcelMessage(String filePath) {
        return getExcelMessage(filePath,0);
    }
    public static Map<String, List<String>> getExcelMessage(String filePath,int sheetIndex) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            Workbook workbook = null;
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls") || filePath.endsWith(".et")) {
                workbook = new HSSFWorkbook(fis);
            }
            fis.close();
            return new MyXlsxUtils().parseExcel(workbook,sheetIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Map<String, List<String>> parseExcel(Workbook workbook,int sheetIndex) {
        Map<String, List<String>> map = new HashMap<>();
        /* 读EXCEL文字内容 */
        // 获取第一个sheet表，也可使用sheet表名获取
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        // 获取行
        int i = 0;
        // 逐行解析
        for (Row row : sheet) {
            List<String> list = new ArrayList<>();
            // 前两行是表头，跳过
            i++;
            // 结束标记，当某一行没有数据时结束（现在时只要有空挡就结束，应该调整到某一行开头的单元格为空——时结束）
            boolean endFlag = false;
            endFlag = parseCellInfo(row, endFlag, list);
            // 如果是空格，直接进入下个循环
            if (endFlag) {
                break;
            }
//            System.out.println(list);
            map.put(String.valueOf(i), list);
        }
        return map;
    }

    private boolean parseCellInfo(Row row, boolean endFlag, List<String> list) {
        // 单元格序号
        int cellIndex = 0;
        for (Cell cell : row) {
            // 获取单元格中内容
            String cellValue = getCellValue(cell);
            if (cellIndex == 0) {
                if (StringUtils.isBlank(cellValue)) {
                    endFlag = true;
                    break;
                }
            }
//            System.out.println(cellIndex+" : "+cellValue);
            list.add(cellValue);
            cellIndex++;
        }
        return endFlag;
    }

    /**
     * 获取cell中的值并返回String类型
     *
     * @param cell
     * @return String类型的cell值
     */
    private String getCellValue(Cell cell) {
        String cellValue = "";
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    if (0 == cell.getCellType()) {// 判断单元格的类型是否则NUMERIC类型
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 判断是否为日期类型
                            Date date = cell.getDateCellValue();
//                      DateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                            cellValue = formater.format(date);
                        } else {
                            // 有些数字过大，直接输出使用的是科学计数法： 2.67458622E8 要进行处理
                            DecimalFormat df = new DecimalFormat("####.####");
                            cellValue = df.format(cell.getNumericCellValue());
                            // cellValue = cell.getNumericCellValue() + "";
                        }
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    try {
                        // 如果公式结果为字符串
                        cellValue = String.valueOf(cell.getStringCellValue());
                    } catch (IllegalStateException e) {
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 判断是否为日期类型
                            Date date = cell.getDateCellValue();
//                      DateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                            cellValue = formater.format(date);
                        } else {
                            FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper()
                                    .createFormulaEvaluator();
                            evaluator.evaluateFormulaCell(cell);
                            // 有些数字过大，直接输出使用的是科学计数法： 2.67458622E8 要进行处理
                            DecimalFormat df = new DecimalFormat("####.####");
                            cellValue = df.format(cell.getNumericCellValue());
//                          cellValue = cell.getNumericCellValue() + "";
                        }
                    }
//              //直接获取公式
//              cellValue = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }

    static void outPutExcel(Object[] [] value) throws IOException {
        String filePath = "D:\\test_out.xlsx";
        OutputStream os = new FileOutputStream(new File(filePath));
        String extString = filePath.substring(filePath.lastIndexOf("."));
        Workbook workbook = null;
        if (extString.equals(".xls")) workbook = new HSSFWorkbook();//一个excel用一个HSSFWorkbook
        else if (extString.equals(".xlsx")) workbook = new XSSFWorkbook();
        else {
            System.out.println("错误的文件类型:" + extString);
            return;
        }
        Sheet sheet = workbook.createSheet("均值");
        for (int i = 0; i < value.length; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < value[i].length; j++) {
                Cell cell = row.createCell(j);
                if (cell == null) System.out.println("cell is null");
                if (value[i][j] != "null") {
                    assert cell != null;
                    if (value[i][j] instanceof String) cell.setCellValue(value[i][j].toString());
                    if (value[i][j] instanceof Double) cell.setCellValue((double) value[i][j]);
                }
            }
        }
        workbook.write(os);
        System.out.println("\n已生成test_out.xlsx,路径：" + filePath);
    }

    /**
     *   将map转换为二维数组
     * @param map
     * @return
     */
    public static Object[][] getMapKeyValue(Map<String,List<String>> map) {
        Object[][] object = null;
        if ((map != null) && (!map.isEmpty())) {
            int size = map.size();
            int size_y = 0;
            if(size>0)
                size_y = map.get("1").size();
            object = new Object[size][size_y];
            Iterator iterator = map.entrySet().iterator();
            for (int i = 0; i < size; i++) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                object[i][0] = key;
                List<String> list = map.get(key);
                for (int j = 0; j < list.size(); j++) {
                    object[i][j] = list.get(j);
                }
            }
        }
        return object;
    }
}
