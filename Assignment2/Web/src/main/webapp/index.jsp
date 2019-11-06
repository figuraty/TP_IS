<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
        <title>MyBay</title>
    </head>

    <body>
        <div class="login-container">
            <div class="logo-container">
                <a href="${pageContext.request.contextPath}/login"><img class="mb-4" src="images/logo.png"></a>
            </div>
            <div class="login-form-container">
                <div class="login-text-container">
                    <h1 align="center">Login</h1>
                </div>
                <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="login-form-fields-container">
                        <div class="login-fields-container">
                            <input type="email" id="email" name="email" placeholder="Email" maxlength="150" required autofocus>
                        </div>

                        <div class="login-fields-container">
                            <input type="password" id="password" name="password" placeholder="Password" maxlength="15" required>
                        </div>
<%--                        <c:if test="${errorMsg}">--%>
<%--                            <div class="invalid-login-message">--%>
<%--                                <h4 align="center">Invalid Credentials</h4>--%>
<%--                            </div>--%>
<%--                        </c:if>--%>
                        <div class="login-button-container">
                            <input type="submit" class="login-button" value="Login">
                        </div>
                    </div>
                </form>
            </div>

            <div class="register-button-container">
                <a href="${pageContext.request.contextPath}/register"><input type="button" class="register-button" value="Register"/></a>
            </div>
        </div>
    </body>
</html>
