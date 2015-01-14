package org.gver.poi;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-15.
 */
public interface TypeHandler<T> {

    <T> T getValue(String input);

}
