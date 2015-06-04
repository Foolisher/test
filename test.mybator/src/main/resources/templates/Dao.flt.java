/*
 * Copyright (c) 2015
 */
/*
 *
 */
package ${package}.dao;

import io.terminus.common.model.Paging;
import com.google.common.collect.Maps;
import ${package}.model.${model};
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 提供对表 `${tableName}`  的增删改查操作<P>
 *
 * Created by wanggen ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Repository
public class ${model}Dao extends SqlSessionDaoSupport {

    private static final String NAMESPACE = "${namespace}.";
    <#assign ali="${model?uncap_first}"/>
    /**
     * 新增
     *
     * @param ${ali} add bean
     * @return 新增后的自增序列号
     */
    public long create(${model} ${ali}) {
        getSqlSession().insert(NAMESPACE + "create", ${ali});
        return ${ali}.getId();
    }


    /**
     * 根据ID查询单条记录
     *
     * @return 返回的 ${ali}
     */
    public ${model} findById(Long id) {
        return getSqlSession().selectOne(NAMESPACE + "findById", id);
    }


    /**
     * 根据 id 列表查询多个结果集
     * @param ids 多个id
     * @return ${model} 列表
     */
    public List<${model}> findByIds(List<Long> ids){
        return getSqlSession().selectList(NAMESPACE + "findByIds", ids);
    }


    /**
     * 分页查询
     *
     * @param param 查询参数
     * @return 分页查询结果
     */
    public Paging<${model}> findByPaging(Map<String, Object> param) {
        Integer total = getSqlSession().selectOne(NAMESPACE + "countBy", param);
        if (total == null || total == 0)
            return new Paging<${model}>(0L, Collections.<${model}>emptyList());
        if (param.get("offset")==null)
            param.put("offset", 0);
        if (param.get("limit")==null)
            param.put("limit", total);
        List<${model}> ${ali}List = getSqlSession().selectList(NAMESPACE + "findBy", param);
        return new Paging<${model}>(total.longValue(), ${ali}List);
    }


    /**
     * 根据条件无分页查询
     *
     * @param param 查询条件
     * @return 结果集
     */
    public List<${model}> findAllBy(Map<String, Object> param) {
        return getSqlSession().selectList(NAMESPACE + "findBy", param);
    }

    <#if addQueryConds??>
    <#list addQueryConds as fds>
    <#assign joinedFields><#list fds as fd>${fd.javaName?cap_first}<#if fd_has_next>And</#if></#list></#assign>
    /**
     * 根据 <#list fds as fd>${fd.javaName}<#if fd_has_next>,</#if></#list> 查询 ${model} 列表
     *
     *<#list fds as fd> @param ${fd.javaName}   ${fd.comment}
     *</#list> @return 结果列
     */
    public Paging<${model}> findBy${joinedFields}(<#list fds as fd>${fd.javaType} ${fd.javaName}<#if fd_has_next>, </#if></#list>, Integer offset, Integer limit){

        Map<String, Object> param = Maps.newHashMap();
        <#list fds as fd>
        param.put("${fd.javaName}", ${fd.javaName});
        </#list>
        Integer total = getSqlSession().selectOne(NAMESPACE + "countBy${joinedFields}", param);
        if (total == null || total == 0)
            return new Paging<${model}>(0L, Collections.<${model}>emptyList());

        param.put("offset", offset==null?0:offset);
        param.put("limit", limit==null?100000:limit);

        List<${model}>${ali}List=getSqlSession().selectList(NAMESPACE+"findBy${joinedFields}",param);
        return new Paging<${model}>(total.longValue(),${ali}List);

    }


    </#list>
    </#if>
    /**
     * 更新操作
     *
     * @param ${ali} 更新操作参数
     * @return 影响行数
     */
    public int update(${model} ${ali}) {
        return getSqlSession().update(NAMESPACE + "update", ${ali});
    }


    /**
     * 根据序列 id 删除记录
     *
     * @param ids 序列 id 列表
     * @return 删除行数
     */
    public int deleteByIds(List<Long> ids) {
        return getSqlSession().delete(NAMESPACE + "deleteByIds", ids);
    }


}
