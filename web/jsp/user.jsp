<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head> <title>Управление счетами</title>
</head>
<body>
<c:if test="${role=='ADMIN'}">
<p><a href="/admin/">Войти в админ-панель</a><p>
    </c:if>
<div style="text-align: center;"><font size="6" style="font-size: 24pt;">Ваш логин: ${user.getLogin()}</font></div>
<div style="text-align: center;">Выберите счет</div>
<div style="text-align: center;">
    <form action="/pages/user" method="get">
        <p><select name="numberOfAccount">
            <c:if test="${selAccount == null}">
            <option disabled selected value="default">Выберите счет</option>
            </c:if>
            <c:if test="${selAccount != null}">
                <option disabled selected value="${selAccount.getNumber()}">${selAccount.getNumber()}</option>
            </c:if>
            <c:forEach var="account" items="${accounts}">
                <option value="${account.getNumber()}">${account.getNumber()}</option>
            </c:forEach>
        </select>
        <div>
            <input type="submit" value="Выбрать">
    </form></div></div>
<table border="1" width="70%" cellpadding="5" style=" margin:auto; margin-top: 10px;">
    <tbody><tr>    <th>Общая информация</th>    <th>История платежей</th> <th>Операции</th>   </tr>   <tr>
        <td><div style="text-align: center;">
            <span style="text-align: center;">Тип : <c:choose><c:when test="${selAccount.getType()==0}">
                <p style="color:green;">Обычный счет</p>
            </c:when>
                <c:when test="${selAccount.getType()==1}">
                    <p style="color:blue;">Кредитный счет</p>
                </c:when>
                <c:when test="${selAccount.getType()==2}">
                    <p style="color:blue;">Депозитный счет</p>
                </c:when>
</c:choose>
            </span></div>
            <div style="text-align: center;"><span style="text-align: center;">Номер счета: ${selAccount.getNumber()}</span></div>
            <div style="text-align: left;"><span style="text-align: center;">Баланс: ${selAccount.getBalance()} грн.</span></div>
            <div style="text-align: left;"><span style="text-align: center;">Срок действия: ${selAccount.getValidity()} </span></div>
            <c:choose>
                <c:when test="${selAccount.getType()==1}">
            <div style="text-align: left;"><span style="text-align: center;"><br /></span></div>
            <div style="text-align: left;"><span style="text-align: center;">Кредитный лимит: ${credite.getLimit()} грн.</span></div>
            <div style="text-align: left;">Кредитная ставка: ${credite.getRate()}%</div>
            <div style="text-align: left;">Сума начисленных процентов: ${credite.getAmount_interest()} грн.</div>
            <div style="text-align: left;">Текущая задолженность: ${credite.getDebt()} грн.</div>
                </c:when>
                <c:when test="${selAccount.getType()==2}">
            <div style="text-align: left;"><br /></div>
            <div style="text-align: left;">Ставка: ${deposite.getRate()} грн.</div>
                </c:when>
            </c:choose>
            <div style="text-align: left;"><br /></div>
        </td>    <td>   <ul>

        <c:forEach var="payment" items="${payments}">
        <li>Перевод ${payment.getAmount()} грн. c карты ${payment.getAccount_number()} ---> на карту ${payment.getTo()}. ${payment.getDescription()}. ${payment.getDate()}</li>
        </c:forEach>
    </ul><br /><br /></td> <td><div style="text-align: center;"><form action="/pages/transaction" method="post">Перевести <input name="amount" type="text" size="10"> грн. на карту<div></div><input name="account_number" type="text" size="30"><input type="submit" value="Перевести"></form></div></td>  </tr> </tbody></table></body>
</html>