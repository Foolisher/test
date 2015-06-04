/*
 * Copyright (c) 2015 杭州端点网络科技有限公司
 */
package ${package}.service;

import io.terminus.common.model.PageInfo;
import io.terminus.common.model.Paging;
import io.terminus.pampas.common.Response;
import io.terminus.snz.user.dao.${model}Dao;
import io.terminus.snz.user.model.${model};
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.*;

/**
 * ${desc}<P>
 *
 * @author wanggen ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service
@Slf4j
public class ${model}ServiceImpl implements ${model}Service {

    <#assign ali="${model?uncap_first}"/>
    @Autowired
    private ${model}Dao ${ali}Dao;

    @Override
    public Response<Long> create(${model} ${ali}) {
        Response<Long> resp = new Response<Long>();
        if (${ali} == null) {
            log.error("param [${ali}] can not be null");
            resp.setError("params.not.null");
            return resp;
        }
        try{
            Long createdId = ${ali}Dao.create(${ali});
            resp.setResult(createdId);
            return resp;
        }catch (Exception e){
            resp.setError("${tableName}.insert.failed");
            log.error("Failed to insert into `${tableName}` with param:{}", ${ali} , e);
            return resp;
        }
    }

    @Override
    public Response<${model}> findById(Long id) {
        Response<${model}> resp = new Response<${model}>();
        if (id == null) {
            log.error("param [id] can not be null when query");
            resp.setError("params.not.null");
            return resp;
        }
        try{
            ${model} ${ali} = ${ali}Dao.findById(id);
            if(${ali} == null){
                log.warn("Failed to findById from `${tableName}` with param:{}", id);
                resp.setError("${tableName}.select.failed");
                return resp;
            }
            resp.setResult(${ali});
            return resp;
        }catch (Exception e){
            resp.setError("${tableName}.select.failed");
            log.error("Failed to findById from `${tableName}` with param:{}", id , e);
            return resp;
        }
    }

    @Override
    public Response<List<${model}>> findByIds(List<Long> ids) {
        Response<List<${model}>> resp = new Response<List<${model}>>();
        if (ids == null) {
            log.error("param [ids] can not be null when findByIds");
            resp.setError("params.not.null");
            return resp;
        }
        try{
            List<${model}> ${ali}s = ${ali}Dao.findByIds(ids);
            if(${ali}s==null || ${ali}s.size()<1){
                log.warn("Failed to findByIds from `${tableName}` with param:{}", ids);
                resp.setError("${tableName}.select.failed");
                return resp;
            }
            resp.setResult(${ali}s);
            return resp;
        }catch (Exception e){
            resp.setError("${tableName}.select.failed");
            log.error("Failed to findByIds from `${tableName}` with param:{}", ids, e);
            return resp;
        }
    }

    @Override
    public Response<Paging<${model}>> findByPaging(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        Response<Paging<${model}>> resp = new Response<Paging<${model}>>();
        if (param == null) {
            log.error("param can not be null when findByPaging");
            resp.setError("params.not.null");
            return resp;
        }
        try{
            PageInfo pageInfo = new PageInfo(pageNo, pageSize);
            param.put("offset", pageInfo.getOffset());
            param.put("limit", pageInfo.getLimit());
            Paging<${model}> ${ali}s = ${ali}Dao.findByPaging(param);
            if(${ali}s.getTotal() < 1){
                log.warn("Failed to findByPaging from `${tableName}` with param:{}", param);
                resp.setError("${tableName}.select.failed");
                return resp;
            }
            resp.setResult(${ali}s);
            return resp;
        }catch (Exception e){
            resp.setError("${tableName}.select.failed");
            log.error("Failed to findByPaging from `${tableName}` with param:{}", param, e);
            return resp;
        }
    }

    @Override
    public Response<List<${model}>> findAllBy(Map<String, Object> param) {
        Response<List<${model}>> resp = new Response<List<${model}>>();
        if (param == null) {
            log.error("param can not be null when query");
            resp.setError("params.not.null");
            return resp;
        }
        try{
            List<${model}> ${ali}s = ${ali}Dao.findAllBy(param);
            if(${ali}s != null && ${ali}s.size()<1){
                log.warn("Failed to findAllBy from `${tableName}` with param:{}", param);
                resp.setError("${tableName}.select.failed");
                return resp;
            }
            resp.setResult(${ali}s);
            return resp;
        }catch (Exception e){
            resp.setError("${tableName}.select.failed");
            log.error("Failed to findAllBy from `${tableName}` with param:{}", param, e);
            return resp;
        }
    }

    <#if addQueryConds??>
    <#list addQueryConds as fds>
    <#assign mn_c><#list fds as fd>"${fd.javaName}"<#if fd_has_next>, </#if></#list></#assign>
    <#assign mn>findBy<#list fds as fd>${fd.javaName?cap_first}<#if fd_has_next>And</#if></#list></#assign>
    <#assign pp><#list fds as fd>${fd.javaName}:{}<#if fd_has_next>, </#if></#list></#assign>
    @Override
    public Response<Paging<${model}>> ${mn}(<#list fds as fd>${fd.javaType} ${fd.javaName}<#if fd_has_next>, </#if></#list>, Integer pageNo, Integer pageSize){
        Response<Paging<${model}>> resp = new Response<Paging<${model}>>();
        try{
            PageInfo pageInfo = new PageInfo(pageNo, pageSize);
            Paging<${model}> ${ali}s = ${ali}Dao.${mn}(<#list fds as fd>${fd.javaName}<#if fd_has_next>, </#if></#list>, pageInfo.getOffset(), pageInfo.getLimit());
            if(${ali}s.getData() == null || ${ali}s.getData().size()<1){
                log.warn("Failed to ${mn} from `${tableName}` with ${pp}", <#list fds as fd>${fd.javaName}<#if fd_has_next>, </#if></#list>);
                resp.setError("${tableName}.select.failed");
                return resp;
            }
            resp.setResult(${ali}s);
            return resp;
        }catch (Exception e){
            resp.setError("${tableName}.select.failed");
            log.error("Failed to ${mn} from `${tableName}` with ${pp}", <#list fds as fd>${fd.javaName}<#if fd_has_next>, </#if></#list>, e);
            return resp;
        }
    }


    </#list>


    </#if>
    @Override
    public Response<Integer> update(${model} ${ali}) {
        Response<Integer> resp = new Response<Integer>();
        if (${ali} == null) {
            log.error("param ${ali} can not be null when update");
            resp.setError("params.not.null");
            return resp;
        }
        try{
            resp.setResult(${ali}Dao.update(${ali}));
            return resp;
        }catch (Exception e){
            resp.setError("${tableName}.update.failed");
            log.error("Failed to update table `${tableName}` with param:{}", ${ali} , e);
            return resp;
        }
    }

    @Override
    public Response<Integer> deleteByIds(List<Long> ids) {
        Response<Integer> resp = new Response<Integer>();
        if (ids == null) {
            log.error("param can not be null when deleteByIds");
            resp.setError("params.not.null");
            return resp;
        }
        try{
            resp.setResult(${ali}Dao.deleteByIds(ids));
            return resp;
        }catch (Exception e){
            resp.setError("${tableName}.delete.failed");
            log.error("Failed to deleteByIds from `${tableName}` with param:{}", ids , e);
            return resp;
        }
    }
}
