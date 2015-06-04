/*
 * Copyright (c) 2015 杭州端点网络科技有限公司
 */

package test.guava.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2014-12-21.
 */
@Data
@AllArgsConstructor
public class StudentMsg extends Message {

    public StudentMsg(String msg){
        super(msg);
    }

}
