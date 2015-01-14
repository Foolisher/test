
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="/tld/c.tld" %>
<html>

<body>

    <c:forEach items="${pages}" var="page">
        <p>
            <div style="border: #1306ff solid 1px; border-radius: 5px; width: 500px; margin-left: 30px; height: 100px; overflow: scroll; padding: 8px;">
                <c:out value="${page}" escapeXml="false"/>
            </div>
        </p>
    </c:forEach>


</body>
</html>
