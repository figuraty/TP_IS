<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <a href="${pageContext.request.contextPath}/userDetails" style="text-decoration: none; color: black">${userName}</a>
            </div>
            <div class="home-account-info-avatar-container">
                <a href="${pageContext.request.contextPath}/userDetails"><img class="mb-4" style="height: 25px" src="images/avatar.png"></a>
            </div>
            <div class="home-account-info-logout-container">
                <form action="${pageContext.request.contextPath}/home" method="post">
                    <input type="hidden" name="logout" value="logout">
                    <input type="image" name="logout" src="images/logout.png" border="0" alt="Submit" style="height: 25px;" />
                </form>
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
                    <input type="submit" name="category" value="All"/>
                </div>
                <div class="home-items-categories-art-container">
                    <input type="submit" name="category" value="Art"/>
                </div>
                <div class="home-items-categories-house-container">
                    <input type="submit" name="category" value="House"/>
                </div>
                <div class="home-items-categories-sport-container">
                    <input type="submit" name="category" value="Sport"/>
                </div>
                <div class="home-items-categories-technology-container">
                    <input type="submit" name="category" value="Technology"/>
                </div>
                <div class="home-items-categories-toys-container">
                    <input type="submit" name="category" value="Toys"/>
                </div>
                <div class="home-items-categories-vehicle-pieces-container">
                    <input type="submit" name="category" value="Vehicle Pieces"/>
                </div>
                <div class="home-items-categories-fashion-container">
                    <input type="submit" name="category" value="Fashion"/>
                </div>
                <div class="home-items-categories-music-container">
                    <input type="submit" name="category" value="Music"/>
                </div>
                <div class="home-items-categories-other-container">
                    <input type="submit" name="category" value="Other"/>
                </div>
            </div>
        </form>
        <div class="separator-container">
            <hr>
        </div>

        <div class="home-items-products-container">
            <div class="home-items-products-filter-container">
                <div class="filters-text">
                    <h3>Filters</h3>
                </div>
                <div class="filters-checkbox-field">
                    <input type="checkbox" name="myItems" value="myItems"> Only my items
                </div>
                <div class="filters-checkbox-field">
                    <input type="checkbox" name="myCountry" value="myCountry"> Only on my Country
                </div>
                <div class="filters-date-field" style="flex-direction: column">
                    <div class="text">Initial Insertion Date</div>
                    <input type="date" name="initialInsertionDate">
                </div>
                <div class="filters-date-field" style="flex-direction: column">
                    <div class="text">Final Insertion Date</div>
                    <input type="date" name="finalInsertionDate">
                </div>
                <div class="filters-price-field">
                    <div class="text">Price Range</div>
                    <div class="range">
                        <div class="money-text">EUR</div>
                        <input type="text" name="initialPriceRange" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" />
                        <div class="money-text" style="margin-left: 8px">EUR</div>
                        <input type="text" name="finalPriceRange" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" />
                    </div>

                </div>
                <div class="filters-search-button">
                    <input type="submit" name="searchFilters" value="Apply Filters"/>
                </div>
            </div>
            <div class="home-items-products-list-container">
                <c:forEach var = "i" begin = "0" end = "${fn:length(items)/3}">
                    <div class="home-items-line-container">
                        <c:forEach var = "j" begin = "${i*3}" end = "${((i + 1)*3 - 1)}">
                            <c:if test = "${j < fn:length(items)}">
                                <div class="home-items-line-item-container">

                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</div>
</body>
</html>
