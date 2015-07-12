/*
 * Copyright (c) 2014
 */

package alipay.dto;

import alipay.dto.settlement.AlipaySettlementParam;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-01-13.
 */
public class ParamDeserializer extends JsonDeserializer<List<AlipaySettlementParam>> {
    @Override
    public List<AlipaySettlementParam> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        TreeNode treeNode = jp.readValueAsTree();

        System.out.println(treeNode);

        return null;

    }
}
