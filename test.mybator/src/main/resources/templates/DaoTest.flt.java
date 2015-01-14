/*
 * Copyright (c) 2014 杭州端点网络科技有限公司
 */
package ${package}.dao;

import io.terminus.common.model.Paging;
import ${package}.model.${model};
import org.joda.time.DateTime;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对 `${tableName}` CRUD 测试<P>
 * created by wanggen ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${model}DaoTest extends BaseDaoTest {
    <#assign ali="${model?uncap_first}"/>

    @Autowired
    private ${model}Dao ${ali}Dao;

    private Long createdId = null;

    private ${model} ${ali} = null;

    @Before
    public void setUp() throws Exception {
        ${ali} = new ${model}();
        <#list properties as p>
        <#assign t><#if "${p.javaType}"=="String">"${p.comment}-1"</#if><#if "${p.javaType}"=="Long">1l</#if><#if "${p.javaType}"=="Integer">1</#if><#if "${p.javaType}"=="Date">DateTime.now().toDate()</#if></#assign>
        ${ali}.set${p.javaName?cap_first}(${t}); //${p.comment}
        </#list>
        createdId = ${ali}Dao.create(${ali});
    }

    @Test
    public void testCreate() throws Exception {
        createdId = ${ali}Dao.create(${ali});
        Assert.assertTrue("One record must be created", createdId>=1);
    }

    @Test
    public void testFindById() throws Exception {
        ${model} byId = ${ali}Dao.findById(createdId);
        Assert.assertTrue("The requery result's id must equals:"+createdId, byId!=null && byId.getId().equals(createdId));
    }


    @Test
    public void testFindByIds() throws Exception {
        List<${model}> byIds = ${ali}Dao.findByIds(ImmutableList.of(createdId));
        Assert.assertTrue("One record must be selectd"+createdId, byIds!=null && byIds.size()==1);
    }



    @Test
    public void testFindByPaging() throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(){
            {
                <#list properties as p>
                <#assign t><#if "${p.javaType}"=="String">"${p.comment}-1"</#if><#if "${p.javaType}"=="Long">1l</#if><#if "${p.javaType}"=="Integer">1</#if><#if "${p.javaType}"=="Date">DateTime.now().toDate()</#if></#assign>
                put("${p.javaName}", ${t}); //${p.comment}
                </#list>
                put("offset",0);
                put("limit",10000);
            }
        };
        Paging<${model}> byPaging = ${ali}Dao.findByPaging(param);
        Assert.assertTrue("At least one record must be found",
                byPaging.getData()!=null && byPaging.getData().size()>=1);
    }

    @Test
    public void testFindAllBy() throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(){
            {
                <#list properties as p>
                <#assign t><#if "${p.javaType}"=="String">"${p.comment}-1"</#if><#if "${p.javaType}"=="Long">1l</#if><#if "${p.javaType}"=="Integer">1</#if><#if "${p.javaType}"=="Date">DateTime.now().toDate()</#if></#assign>
                put("${p.javaName}", ${t}); //${p.comment}
                </#list>
            }
        };
        List<${model}> allBy = ${ali}Dao.findAllBy(param);
        Assert.assertTrue("At least one record must be found", allBy.size()>=0);
    }

    <#if addQueryConds??>
    <#list addQueryConds as fds>
    <#assign mn><#list fds as fd>${fd.javaName?cap_first}<#if fd_has_next>And</#if></#list></#assign>
    @Test
    public void testFindBy${mn}(){

        <#list fds as fd>
        <#assign t><#if "${fd.javaType}"=="String">"${fd.comment}-1"</#if><#if "${fd.javaType}"=="Long">1l</#if><#if "${fd.javaType}"=="Integer">1</#if><#if "${fd.javaType}"=="Date">DateTime.now().toDate()</#if></#assign>
        ${fd.javaType} ${fd.javaName} = ${t}; ${"//"+fd.comment}
        </#list>

        Integer offset=0, limit=1000;

        Paging<${model}> ${ali}List = ${ali}Dao.findBy${mn}(<#list fds as fd>${fd.javaName}<#if fd_has_next>, </#if></#list>, offset, limit);

        Assert.assertTrue("At least one record must be found", ${ali}List.getData()!=null);

    }


    </#list>
    </#if>
    @Test
    public void testUpdate() throws Exception {
        ${model} ${ali} = new ${model}();
        ${ali}.setId(createdId);
        <#list properties as p>
        <#assign t><#if "${p.javaName}"!="id"><#if "${p.javaType}"=="String">"${p.comment}-1-new"</#if><#if "${p.javaType}"=="Long">1001l</#if><#if "${p.javaType}"=="Integer">1001</#if><#if "${p.javaType}"=="Date">DateTime.now().toDate()</#if></#if></#assign>
        <#if "${p.javaName}"!="id">${ali}.set${p.javaName?cap_first}(${t}); //${p.comment}</#if>
        </#list>
        int updated = ${ali}Dao.update(${ali});
        Assert.assertTrue("One record must be updated", updated==1);
    }


    @Test
    public void testDeleteByIds() throws Exception {
        int deleted = ${ali}Dao.deleteByIds(ImmutableList.of(createdId));
        Assert.assertTrue("One record must be deleted", deleted==1);
    }

}