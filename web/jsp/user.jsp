<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head> <title>Вхід</title>
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon" />
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
            <div style="text-align: left;"><span style="text-align: center;">Кредитный лимит:</span></div>
            <div style="text-align: left;">Кредитная ставка:</div>
            <div style="text-align: left;">Сума начисленных процентов:</div>
            <div style="text-align: left;">Текущая задолженность:</div>
                </c:when>
                <c:when test="${selAccount.getType()==2}">
            <div style="text-align: left;"><br /></div>
            <div style="text-align: left;">Ставка:</div>
                </c:when>
            </c:choose>
            <div style="text-align: left;"><br /></div>
        </td>    <td>   <ul>
        <li>Перевод 50 грн на карту 5118 7534 2127 7686. Остаток: 430 грн.</li>
        <li>Пополнения 50 грн с карты 5118 7534 2127 7686. Баланс: 480 грн.</li>
        <li>Оплачену комунальные услуги (1500 грн). Остаток: 80 грн.</li>
    </ul><br /><br /></td> <td><div style="text-align: center;">Перевести <input type="text" size="5"> грн. на карту<div></div><input type="text" size="30"><input type="submit" value="Перевести"></div></td>  </tr> </tbody></table></body>
</html>