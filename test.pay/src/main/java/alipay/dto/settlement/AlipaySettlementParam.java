/*
 * Copyright (c) 2014
 */

package alipay.dto.settlement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import lombok.Data;

/**
 * Mail: remindxiao@gmail.com <br>
 * Date: 2014-02-20 1:00 PM  <br>
 * Author: xiao
 */
@Data
@XStreamAlias("param")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"value"})
public class AlipaySettlementParam {

    @XStreamAlias("name")
    private String name;



    private String value;

    public AlipaySettlementParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
