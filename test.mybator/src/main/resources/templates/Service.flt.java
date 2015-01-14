/*
 * Copyright (c) 2014 杭州端点网络科技有限公司
 */
package ${package}.service;

import io.terminus.common.model.Paging;
import io.terminus.pampas.client.Export;
import io.terminus.pampas.common.Response;
import ${package}.model.${model};

import java.util.List;
import java.util.Map;


/**
 * ${desc}<P>
 *
 * @author wanggen ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${model}Service {
    <#assign ali="${model?uncap_first}"/>

    /**
     * 新增
     *
     * @param ${ali} add bean
     * @return 新增后的自增序列号
     */
    @Export(paramNames = {"${ali}"})
    public Response<Long> create(@NotNull(message = "param.null.error") ${model} ${ali});


    /**
     * 根据ID查询单条记录
     *
     * @return 返回的 ${ali}
     */
    @Export(paramNames = {"id"})
    public Response<${model}> findById(@NotNull(message = "param.null.error") Long id);


    /**
     * 根据 id 列表查询多个结果集
     * @param ids 多个id
     * @return ${model} 列表
     */
    @Export(paramNames = {"ids"})
    public Response<List<${model}>> findByIds(@NotNull(message = "param.null.error") List<Long> ids);


    /**
     * 分页查询
     * <PRE>
     * 查询接口
     * <#list properties as p>${p.javaName}${indent[propertyWidth-p.javaName?length]}${p.javaType}${indent[typeWidth-p.javaType?length]}  ${p.comment}<#if p_has_next>${"\n     * "}</#if></#list>
     * </PRE>
     * @param param     可选查询参数
     * @param pageNo    页码
     * @param pageSize  每页数量
     * @return          分页查询结果
     */
    @Export(paramNames = {"param", "pageNo", "pageSize"})
    Response<Paging<${model}>> findByPaging(Map<String, Object> param, Integer pageNo, Integer pageSize);


    /**
     * 根据查询条件查询
     * <PRE>
     * 查询接口
     * <#list properties as p>${p.javaName}${indent[propertyWidth-p.javaName?length]}${p.javaType}${indent[typeWidth-p.javaType?length]}  ${p.comment}<#if p_has_next>${"\n     * "}</#if></#list>
     * </PRE>
     * @param param 可选查询参数
     * @return 分页查询结果
     */
    @Export(paramNames = {"param"})
    Response<List<${model}>> findAllBy(Map<String, Object> param);


    <#if addQueryConds??>
    <#list addQueryConds as fds>
    <#assign mn_c><#list fds as fd>"${fd.javaName}"<#if fd_has_next>, </#if></#list></#assign>
    <#assign mn>findBy<#list fds as fd>${fd.javaName?cap_first}<#if fd_has_next>And</#if></#list></#assign>


    /**
     * 根据 <#list fds as fd>${fd.javaName}<#if fd_has_next>,</#if></#list> 查询 ${model} 列表
     *
     *<#list fds as fd> @param ${fd.javaName}   ${fd.comment}
     *</#list> @return 结果列
     */
    @Export(paramNames = {${mn_c}, "pageNo", "pageSize"})
    Response<Paging<${model}>> ${mn}(<#list fds as fd>${fd.javaType} ${fd.javaName}<#if fd_has_next>, </#if></#list>, Integer pageNo, Integer pageSize);
    </#list>


    </#if>

    /**
     * 更新操作
     *
     * @param ${ali} 更新操作参数
     * @return 影响行数
     */
    public Response<Integer> update(${model} ${ali});


    /**
     * 根据序列 id 删除记录
     *
     * @param ids 序列 id 列表
     * @return 删除行数
     */
    public Response<Integer> deleteByIds(List<Long> ids);


}
