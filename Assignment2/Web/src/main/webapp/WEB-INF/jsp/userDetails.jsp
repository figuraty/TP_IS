<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userDetails.css"/>
    <script>
        function setPassword() {
            document.querySelector("#password").value = "password";
        }

        function unReadOnly(){
            document.querySelector("#name").disabled = false;
            document.querySelector("#email").disabled = false;
            document.querySelector("#password").disabled = false;
            document.querySelector("#password").value = "";
            document.querySelector("#country").disabled = false;
            document.querySelector("#save").disabled = false;
            document.querySelector("#save").autofocus = true;
            document.querySelector("#edit").disabled = true;
        }
    </script>
    <title>MyBay</title>
</head>

<body onload="setPassword()">
<form class="form-userDetails" action="${pageContext.request.contextPath}/userDetails" method="post" >
    <div class="userDetails-container">
        <div class="userDetails-account-container">
            <div class="userDetails-account-info-container">
                <div class="userDetails-account-info-username-container">
                    <a href="${pageContext.request.contextPath}/userDetails"
                       style="text-decoration: none; color: black">${userName}</a>
                </div>
                <div class="userDetails-account-info-avatar-container">
                    <a href="${pageContext.request.contextPath}/userDetails"><img class="mb-4" style="height: 25px"
                                                                                  src="images/avatar.png"></a>
                </div>
                <div class="userDetails-account-info-logout-container">
                    <input type="hidden" name="logout" value="logout">
                    <input type="image" name="logout" src="images/logout.png" border="0" alt="Submit"
                           style="height: 25px;"/>
                </div>
            </div>
        </div>
        <div class="logo-container">
            <a href="${pageContext.request.contextPath}/home"><img class="mb-4" src="images/logo.png"></a>
        </div>
        <div class="userDetails-form-container">
            <div class="userDetails-text-container">
                <h1 align="center">User Details</h1>
            </div>

            <div class="userDetails-form-fields-container">
                <div class="userDetails-fields-container">
                    <input disabled type="text" id="name" name="name" placeholder="Name" maxlength="150" value="${user.name}"
                           required>
                </div>
                <div class="userDetails-fields-container">
                    <input disabled type="email" id="email" name="email" placeholder="Email" maxlength="150"
                           value="${user.email}" required>
                </div>

                <div class="userDetails-fields-container">
                    <input disabled type="password" id="password" name="password" placeholder="Password" maxlength="15" required>
                </div>
                <div class="userDetails-fields-container">
                    <select disabled id="country" name="country">
                        <option value="${user.country}" selected hidden>${user.country}</option>
                        <option value="Alemanha">Alemanha</option>
                        <option value="Austria">Aústria</option>
                        <option value="Belgica">Bélgica</option>
                        <option value="Bulgaria">Bulgária</option>
                        <option value="Chipre">Chipre</option>
                        <option value="Croacia">Croácia</option>
                        <option value="Dinamarca">Dinamarca</option>
                        <option value="Eslovaquia">Eslováquia</option>
                        <option value="Eslovenia">Eslovénia</option>
                        <option value="Espanha">Espanha</option>
                        <option value="Estonia">Estónia</option>
                        <option value="Finlandia">Finlândia</option>
                        <option value="Franca">França</option>
                        <option value="Grecia">Grécia</option>
                        <option value="Hungria">Hungria</option>
                        <option value="Irlanda">Irlanda</option>
                        <option value="Islandia">Islândia</option>
                        <option value="Italia">Itália</option>
                        <option value="Luxemburgo">Luxemburgo</option>
                        <option value="Noruega">Noruega</option>
                        <option value="PaisesBaixos">Países Baixos</option>
                        <option value="Polonia">Polónia</option>
                        <option value="Portugal">Portugal</option>
                        <option value="ReinoUnido">Reino Unido</option>
                        <option value="RepublicaCheca">República Checa</option>
                        <option value="Romenia">Roménia</option>
                        <option value="Russia">Rússia</option>
                        <option value="Servia">Sérvia</option>
                        <option value="Suecia">Suécia</option>
                        <option value="Suica">Suéca</option>
                        <option value="Ucrania">Ucrânia</option>
                    </select>
                </div>
                <c:if test="${errorMsg}">
                    <div class="invalid-userDetails-message">
                        <h4 align="center">Email is already used</h4>
                    </div>
                </c:if>
                <div class="userDetails-button-container">
                    <input type="submit" class="userDetails-button" name="delete" style="background-color: #A9062D" value="Delete">
                    <input class="userDetails-button" name="edit" id="edit" value="Edit" style="background-color: #3D6BAB" onclick="unReadOnly()">
                    <input type="submit" class="userDetails-button" style="background-color: #00B658" id="save" name="save" value="Save" disabled>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
