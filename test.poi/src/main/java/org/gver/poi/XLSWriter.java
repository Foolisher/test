package org.gver.poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gver._;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-21.
 */
public class XLSWriter {


    @Test
    public void write() throws IOException {

        _.jdbcTemplate.query("select * from snz_users limit 1", new RowMapper<Object>() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println(rs.getObject("NAME"));
                return null;
            }
        });

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("demo");

        Row row_0 = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0, 3, 6));
        CellBuilder.start(row_0, 3).alignHori(2).value("类型").borderThin();
        CellBuilder.start(row_0, 6).borderThin();
        Row row_1 = sheet.createRow(1);
        CellBuilder.start(row_1, 3).value("类型1").alignHori(2).borderThin();
        CellBuilder.start(row_1, 4).value("类型1").alignHori(2).borderThin();
        CellBuilder.start(row_1, 5).value("类型1").alignHori(2).borderThin();
        CellBuilder.start(row_1, 6).value("类型1").alignHori(0x2).borderThin();

        workbook.write(new FileOutputStream("test.xls"));

    }


}
