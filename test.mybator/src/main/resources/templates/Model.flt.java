/*
 * Copyright (c) 2015
 */
package ${package}.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * ${desc}<P>
 *
 * Created by wanggen ${now?string("yyyy-MM-dd HH:mm:ss")}
 * <PRE>
 * <#list properties as p>${p.javaName}${indent[propertyWidth-p.javaName?length]}${p.javaType}${indent[typeWidth-p.javaType?length]}  ${p.comment}<#if p_has_next>${"\n * "}</#if></#list>
 * </PRE>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${model} implements Serializable {

    <#list properties as p><#assign declare>private ${p.javaType} ${p.javaName};</#assign>
    ${declare}${indent[propertyDeclareWidth-declare?length]} ${"//"+p.comment}

    </#list>

}
