/*
 * Copyright (c) 2014
 */

package datetime;

import org.joda.time.DateTime;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-06-17.
 */
public class DateTimeTest {


    public static void main(String[] args){

        System.out.println(DateTime.now().weekOfWeekyear().getAsString());

    }


}
