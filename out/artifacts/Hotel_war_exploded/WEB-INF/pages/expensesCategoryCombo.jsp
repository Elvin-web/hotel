<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/12/2019
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<option selected="selected" id="advExpensesCategoryCmbId">Select Expenses category</option>
<c:forEach items="${expensesCategoryList}" var="ecl">
    <option value="${ecl.id}">${ecl.expensesType}</option>
</c:forEach>
