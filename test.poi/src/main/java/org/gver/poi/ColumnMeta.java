package org.gver.poi;

import lombok.Data;
import org.joda.time.format.DateTimeFormatter;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-15.
 */
@Data
public class ColumnMeta<T> {

    public int index;

    public String column;

    public String type;

    public String datePattern;

    public TypeHandler<T> typeHandler;

    public DateTimeFormatter formatter;

}
