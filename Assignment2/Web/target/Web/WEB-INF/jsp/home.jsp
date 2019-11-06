<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css"/>
    <title>MyBay</title>
</head>

<body>
<div class="home-container">
    <div class="home-account-container">
        <div class="home-account-info-container">
            <div class="home-account-info-username-container">
                ${userName}
            </div>
            <div class="home-account-info-avatar-container">

            </div>
        </div>
    </div>
    <div class="home-items-container">
        <div class="home-items-search-bar-container">
            <div class="home-items-search-bar-logo-container">
                <a href="${pageContext.request.contextPath}/home" style="height: 25px"><img class="mb-4" style="height: 30px" src="images/logo-bar.png"></a>
            </div>
            <div class="home-items-search-bar-input-container">
                <input type="text" id="name" name="name" placeholder="Search here" maxlength="150">
            </div>
            <div class="home-items-search-bar-button-container">
                <button class="home-button">Search</button>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/home" method="post">
            <div class="home-items-categories-container">
                <div class="home-items-categories-art-container">
                    <input type="submit" name="category" value="utensilios"/>
                </div>
                <div class="home-items-categories-house-container">
                    <input type="submit" name="category" value="Button 2" />
                </div>
                <div class="home-items-categories-sport-container">
                    <button class="home-button">Search</button>
                </div>
                <div class="home-items-categories-technology-container">
                    <button class="home-button">Search</button>
                </div>
                <div class="home-items-categories-toys-container">
                    <button class="home-button">Search</button>
                </div>
                <div class="home-items-categories-vehicle-pieces-container">
                    <button class="home-button">Search</button>
                </div>
                <div class="home-items-categories-fashion-container">
                    <button class="home-button">Search</button>
                </div>
                <div class="home-items-categories-music-container">
                    <button class="home-button">Search</button>
                </div>
                <div class="home-items-categories-other-container">
                    <button class="home-button">Search</button>
                </div>
            </div>
        </form>

<%--        <%--%>
<%--            List<Item> items=(List<Item>) request.getAttribute("items");--%>
<%--            for (Item item: items) {--%>
<%--        %>--%>
<%--        <tr>--%>
<%--            <h1><%=item.getName()%></h1>--%>
<%--        </tr>--%>
<%--        <%}%>--%>

<%--        <c:forEach var = "i" begin = "1" end = "5">--%>
<%--            Item <c:out value = "${i}"/><p>--%>
<%--        </c:forEach>--%>

        <c:forEach items="${items}" var="item">
            <h1>${item.getName()}</h1>
        </c:forEach>

    </div>



<%--    <div class="logo-container">--%>
<%--        <a href="${pageContext.request.contextPath}/login"><img class="mb-4" src="images/logo.png"></a>--%>
<%--    </div>--%>
<%--    <div class="home-form-container">--%>
<%--        <div class="home-text-container">--%>
<%--            <h1 align="center">${userName}</h1>--%>
<%--        </div>--%>
<%--        <form class="form-home" action="${pageContext.request.contextPath}/home" method="post">--%>
<%--            <div class="home-form-fields-container">--%>
<%--                <div class="home-fields-container">--%>
<%--                    <input type="text" id="name2" name="name" placeholder="Name" maxlength="150" required autofocus>--%>
<%--                </div>--%>
<%--                <div class="home-fields-container">--%>
<%--                    <input type="email" id="email" name="email" placeholder="Email" maxlength="150" required>--%>
<%--                </div>--%>

<%--                <div class="home-fields-container">--%>
<%--                    <input type="password" id="password" name="password" placeholder="Password" maxlength="15" required>--%>
<%--                </div>--%>
<%--                <div class="home-fields-container">--%>
<%--                    <select id="country" name="country">--%>
<%--                        <option value="Alemanha">Alemanha</option>--%>
<%--                        <option value="Austria">Aústria</option>--%>
<%--                        <option value="Belgica">Bélgica</option>--%>
<%--                        <option value="Bulgaria">Bulgária</option>--%>
<%--                        <option value="Chipre">Chipre</option>--%>
<%--                        <option value="Croacia">Croácia</option>--%>
<%--                        <option value="Dinamarca">Dinamarca</option>--%>
<%--                        <option value="Eslovaquia">Eslováquia</option>--%>
<%--                        <option value="Eslovenia">Eslovénia</option>--%>
<%--                        <option value="Espanha">Espanha</option>--%>
<%--                        <option value="Estonia">Estónia</option>--%>
<%--                        <option value="Finlandia">Finlândia</option>--%>
<%--                        <option value="Franca">França</option>--%>
<%--                        <option value="Grecia">Grécia</option>--%>
<%--                        <option value="Hungria">Hungria</option>--%>
<%--                        <option value="Irlanda">Irlanda</option>--%>
<%--                        <option value="Islandia">Islândia</option>--%>
<%--                        <option value="Italia">Itália</option>--%>
<%--                        <option value="Luxemburgo">Luxemburgo</option>--%>
<%--                        <option value="Noruega">Noruega</option>--%>
<%--                        <option value="PaisesBaixos">Países Baixos</option>--%>
<%--                        <option value="Polonia">Polónia</option>--%>
<%--                        <option value="Portugal">Portugal</option>--%>
<%--                        <option value="ReinoUnido">Reino Unido</option>--%>
<%--                        <option value="RepublicaCheca">República Checa</option>--%>
<%--                        <option value="Romenia">Roménia</option>--%>
<%--                        <option value="Russia">Rússia</option>--%>
<%--                        <option value="Servia">Sérvia</option>--%>
<%--                        <option value="Suecia">Suécia</option>--%>
<%--                        <option value="Suica">Suéca</option>--%>
<%--                        <option value="Ucrania">Ucrânia</option>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--&lt;%&ndash;                <c:if test="${errorMsg}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <div class="invalid-home-message">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <h4 align="center">Email is already used</h4>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </c:if>&ndash;%&gt;--%>
<%--                <div class="home-button-container">--%>
<%--                    <input type="submit" class="home-button" value="home">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
</div>
</body>
</html>
