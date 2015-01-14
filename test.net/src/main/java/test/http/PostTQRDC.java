package test.http;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * @author wanggen on 14-7-30.
 * @Desc:
 */
public class PostTQRDC {


    @Test
    public void post(){
        Map params = Maps.newHashMap();
        params.put("companyId", "70987589");
        params.put("supplierCode", "09787679809");
        params.put("supplierName", "98789098");
        params.put("module", "98786");

        HttpRequest req = HttpRequest
                .post("http://www.snz.com/api/tqrdc/put", params, true)
                .header("Request Method", "POST")
                .header("Accept", "*/*")
                .header("Content-Type", "application/x-www-form-urlencoded");

        String response = req.body("UTF-8");

        System.out.println(response);
    }


}



/*
        `user_id`               bigint          NOT NULL    COMMENT '用户id',
        `company_id`            BIGINT          NOT NULL    COMMENT '对应企业信息',
        `special_number`        VARCHAR(20)     NULL        COMMENT '专用号',
        `supplier_code`         VARCHAR(20)     NOT NULL    COMMENT '供应商代码',
        `supplier_name`         VARCHAR(20)     NOT NULL    COMMENT '供应商名称',
        `module`                VARCHAR(50)     NOT NULL    COMMENT '模块',
        `product_line_id`       SMALLINT        NULL        COMMENT '产品线ID',
        `location`              VARCHAR(20)     NOT NULL    COMMENT '区域',
        `rank`                  VARCHAR(20)     NOT NULL    COMMENT '排名',
        `month`                 VARCHAR(8)      NOT NULL    COMMENT '月份',
        `composite_score`       INT             NOT NULL    COMMENT '综合绩效',
        `tech_score`            INT             NOT NULL    COMMENT '技术得分',
        `delay_days`            INT             NOT NULL    COMMENT '拖期天数',
        `new_product_pass`      INT             NOT NULL    COMMENT '新品合格率',
        `quality_score`         INT             NOT NULL    COMMENT '质量得分',
        `live_bad`              INT             NOT NULL    COMMENT '现场不良率',
        `market_bad`            INT             NOT NULL    COMMENT '市场不良率',
        `resp_score`            INT             NOT NULL    COMMENT '响应得分',
        `requirement_resp`      INT             NOT NULL    COMMENT '需求响应度',
        `deliver_score`         INT             NOT NULL    COMMENT '交付得分',
        `deliver_diff`          INT             NOT NULL    COMMENT '交付差异',
        `cost_score`            INT             NOT NULL    COMMENT '成本得分',
        `increment`             INT             NOT NULL    COMMENT '增值',
        `tech_score_rank`       INT             NULL        COMMENT '技术得分排序',
        `quality_score_rank`    INT             NULL        COMMENT '质量得分排序',
        `resp_score_rank`       INT             NULL        COMMENT '响应得分排序',
        `delivery_score_rank`   INT             NULL        COMMENT '交付得分排序',
        `cost_score_rank`       INT             NULL        COMMENT '成本得分排序',

*/