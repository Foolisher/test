/*
 * Copyright (c) 2014
 */

package alipay.dto.settlement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * Mail: remindxiao@gmail.com <br>
 * Date: 2014-02-20 10:43 AM  <br>
 * Author: xiao
 */
@Data
public class AlipaySettlementPaging {

    @XStreamAlias("account_log_list")
    private List<AlipaySettlementDto> accountLogList;

    @XStreamAlias("has_next_page")
    private String hasNextPage = "F";

    @XStreamAlias("page_no")
    private String pageNo;

    @XStreamAlias("page_size")
    private String pageSize;
}
