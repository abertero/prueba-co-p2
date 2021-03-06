<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="game.title"/></h1>

<c:choose>
    <c:when test="${notEnoughCards}">
        <div>
            <p><spring:message code="game.no.cards"/></p>
        </div>
    </c:when>
    <c:otherwise>
        <h3>Hand 1</h3>

        <div class="row">
            <c:forEach items="${hand1.cards}" var="card">
                <div class="card col-xs-1"><img src="${ctx}/static/images/cards/${card.toString()}.png"
                                                style="max-width: 100%; max-height: 100%;"/></div>
            </c:forEach>
            <div class="col-xs-4"><spring:message code="game.hand.ranking"/>: <c:out value="${hand1.ranking.display}"/></div>
        </div>


        <h3>Hand 2</h3>

        <div class="row">
            <c:forEach items="${hand2.cards}" var="card">
                <div class="card col-xs-1"><img src="${ctx}/static/images/cards/${card.toString()}.png"
                                                style="max-width: 100%; max-height: 100%;"/></div>
            </c:forEach>
            <div class="col-xs-4"><spring:message code="game.hand.ranking"/>: <c:out value="${hand2.ranking.display}"/></div>
        </div>

        <h3 style="color: brown;">
            <c:choose>
                <c:when test="${comparison==1}">
                    <spring:message code="game.hand1.won"/>
                </c:when>
                <c:when test="${comparison==-1}">
                    <spring:message code="game.hand2.won"/>
                </c:when>
                <c:when test="${comparison==0}">
                    <spring:message code="game.match.tie"/>
                </c:when>
            </c:choose>
        </h3>
    </c:otherwise>
</c:choose>

<h3>Deck</h3>

<div class="row">
    <div class="card col-xs-1"><img src="${ctx}/static/images/cards/back.jpg"
                                    style="max-width: 100%; max-height: 100%;"/>
    </div>
    <div class="col-xs-6">
        <ul>
            <li><spring:message code="game.cards.left"/>: <c:out value="${52-deck.position}"/></li>
        </ul>
    </div>
</div>

<button class="btn btn-primary" onclick="location.href='game'"><spring:message code="game.button.continue"/></button>
<button class="btn btn-primary" onclick="location.href='game?shuffle=true'"><spring:message code="game.button.new"/></button>

</body>
</html>