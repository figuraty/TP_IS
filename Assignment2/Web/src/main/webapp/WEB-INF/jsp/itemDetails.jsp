<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/itemDetails.css"/>
    <title>MyBay</title>
</head>

<body>
<form class="form-itemDetails" style="width: 100%" action="${pageContext.request.contextPath}/itemDetails" method="post">
    <div class="itemDetails-container">
        <div class="itemDetails-account-container">
            <div class="itemDetails-account-info-container">
                <div class="itemDetails-account-info-username-container">
                    <a href="${pageContext.request.contextPath}/userDetails" style="text-decoration: none; color: black">${userName}</a>
                </div>
                <div class="itemDetails-account-info-avatar-container">
                    <a href="${pageContext.request.contextPath}/userDetails"><img class="mb-4" style="height: 25px" src="images/avatar.png"></a>
                </div>
                <div class="itemDetails-account-info-logout-container">
                    <input type="hidden" name="logout" value="logout">
                    <input type="image" name="logout" src="images/logout.png" border="0" alt="Submit" style="height: 25px;" />
                </div>
            </div>
        </div>
        <div class="logo-container">
            <a href="${pageContext.request.contextPath}/home"><img class="mb-4" src="images/logo.png"></a>
        </div>
        <div class="itemDetails-form-container">
            <div class="itemDetails-text-container">
                <h1 align="center">Item Details</h1>
            </div>
                <div class="itemDetails-form-fields-container">
                    <div class="itemDetails-fields-container">
                        <h5 style="margin-right: 4px">Name</h5>
                        <input type="text" id="name" name="name" placeholder="Name" maxlength="150"
                        <c:if test="${editItem == null || editItem == false}"> disabled </c:if> value="${item.name}" required>
                    </div>
                    <div class="itemDetails-fields-container">
                        <h5 style="margin-right: 4px">Category</h5>
                        <select id="category" name="category" <c:if test="${editItem == null || editItem == false}"> disabled </c:if>>
                            <option value="${item.category}" selected hidden>${item.category}</option>
                            <option value="Art">Art</option>
                            <option value="House">House</option>
                            <option value="Sport">Sport</option>
                            <option value="Technology">Technology</option>
                            <option value="Toys">Toys</option>
                            <option value="Vehicle Pieces">Vehicle Pieces</option>
                            <option value="Fashion">Fashion</option>
                            <option value="Music">Music</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                    <div class="itemDetails-fields-container">
                        <h5 style="margin-right: 4px">Country</h5>
                        <select id="country" name="country" <c:if test="${editItem == null || editItem == false}"> disabled </c:if>>
                            <option value="${item.country}" selected hidden>${item.country}</option>
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
                    <div class="itemDetails-fields-container">
                        <h5 style="margin-right: 4px">Insertion Date</h5>
                        <input type="date" name="initialInsertionDate" value="${itemDate}" <c:if test="${editItem == null || editItem == false}"> disabled </c:if>>
                    </div>

                    <div class="itemDetails-fields-container">
                        <h5 style="margin-right: 4px">Price</h5>
                        <input type="text" name="price" value="${item.price}" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${editItem == null || editItem == false}"> disabled </c:if>/>
                    </div>
                    <div class="itemDetails-button-container">
                        <c:if test="${itemUserID == user.id}">
                            <c:choose>
                                <c:when test="${editItem == null || editItem == false}">
                                    <div class="itemDetails-edit-button-container">
                                        <input type="submit" class="itemDetails-edit-button" name="edit" value="Edit">
                                    </div>
                                    <div class="itemDetails-delete-button-container">
                                        <input type="submit" class="itemDetails-delete-button" name="delete" value="Delete">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="itemDetails-cancel-button-container">
                                        <input type="submit" class="itemDetails-cancel-button" name="cancel" value="Cancel">
                                    </div>
                                    <div class="itemDetails-save-button-container">
                                        <input type="submit" class="itemDetails-button" name="save" value="Save">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
        </div>
    </div>
</form>
</body>
</html>
