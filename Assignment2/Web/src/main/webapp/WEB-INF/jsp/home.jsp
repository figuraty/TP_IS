<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css"/>
    <title>MyBay</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/home" method="post">
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
                    <input type="hidden" name="logout" value="logout">
                    <input type="image" name="logout" src="images/logout.png" border="0" alt="Submit" style="height: 25px;" />
            </div>
        </div>
    </div>
    <div class="home-items-container">
        <div class="home-items-search-bar-container">
            <div class="home-items-search-bar-logo-container">
                <a href="${pageContext.request.contextPath}/home" style="height: 25px"><img class="mb-4" style="height: 30px" src="images/logo-bar.png"></a>
            </div>
            <div class="home-items-search-bar-input-container">
                <input type="text" id="name" name="name" placeholder="Search here" maxlength="150" <c:if test="${filter != null && filter.name != null}">value="${filter.name}" </c:if>>
            </div>
            <div class="home-items-search-bar-button-container">
                <input type="submit" class="home-button" style="padding: 8px 12px;" name="search" value="Search"/>
            </div>
        </div>
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
        <div class="separator-container">
            <hr>
        </div>

        <div class="home-items-products-container">
            <div class="home-items-products-filter-container">
                <div class="filters-text">
                    <h3>Filters</h3>
                </div>
                <div class="filters-checkbox-field">
                    <input type="checkbox" name="myItems" value="myItems" <c:if test="${filter != null && filter.myItems != false}">checked</c:if>> Only my items
                </div>
                <div class="filters-checkbox-field">
                    <input type="checkbox" name="myCountry" value="myCountry" <c:if test="${filter != null &&  filter.country != null}">checked</c:if>> Only on my Country
                </div>
                <div class="filters-date-field" style="flex-direction: column">
                    <div class="text">Initial Insertion Date</div>
                    <input type="date" name="initialInsertionDate" <c:if test="${filter != null &&  filter.intialInsertionDate != null}"> value="${initialDate}"</c:if>>
                </div>
                <div class="filters-date-field" style="flex-direction: column">
                    <div class="text">Final Insertion Date</div>
                    <input type="date" name="finalInsertionDate" <c:if test="${filter != null &&  filter.intialInsertionDate != null}"> value="${finalDate}"</c:if>>
                </div>
                <div class="filters-price-field">
                    <div class="text">Price Range</div>
                    <div class="range">
                        <div class="money-text">EUR</div>
                        <input type="text" name="initialPriceRange" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                               <c:if test="${filter != null &&  filter.intitialPriceRange > 0}">value="${filter.intitialPriceRange}" </c:if>/>
                        <div class="money-text" style="margin-left: 8px">EUR</div>
                        <input type="text" name="finalPriceRange" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                               <c:if test="${filter != null &&  filter.finalPriceRange > 0}">value="${filter.finalPriceRange}" </c:if>/>
                    </div>

                </div>
                <div class="filters-search-button">
                    <input type="submit" name="searchFilters" value="Apply Filters"/>
                </div>
                <div class="filters-search-button">
                    <input type="submit" name="resetFilters" style="background-color: #999999;" value="Reset Filters"/>
                </div>
            </div>
            <div class="home-items-products-list-container">
                <div class="home-items-line-container">
                    <div class="home-items-line-item-container">
                        <a href="${pageContext.request.contextPath}/addItem" style="width: 100%; height: 100%; border-style: dotted; border-width: 2px; border-color: #999999;"><img style="width: 100%; height: 100%;" src="images/addItem.png"></a>
                    </div>
                    <c:if test = "${fn:length(items) > 0}">
                        <div class="home-items-line-item-container">
                            <button class="item-details-button" name="itemID" value="${items[0].id}" type="submit"><img src="images/logout.png" style="width: 100%; height: 100%"></button>
                        </div>
                    </c:if>
                    <c:if test = "${fn:length(items) > 1}">
                        <div class="home-items-line-item-container">
                            <button class="item-details-button" name="itemID" value="${items[1].id}" type="submit"><img src="images/logout.png" style="width: 100%; height: 100%"></button>
                        </div>
                    </c:if>
                </div>
                <c:if test = "${fn:length(items) > 2}">
                    <c:forEach var = "i" begin = "1" end = "${((fn:length(items) - 2 )/3) + 1}">
                        <div class="home-items-line-container">
                            <c:forEach var = "j" begin = "${(i*3) - 1}" end = "${((i *3) + 1)}">
                                <c:if test = "${j < fn:length(items)}">
                                    <div class="home-items-line-item-container">
                                        <button class="item-details-button" name="itemID" value="${items[j].id}" type="submit"><img src="images/logout.png" style="width: 100%; height: 100%"></button>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>
</form>
</body>
</html>
