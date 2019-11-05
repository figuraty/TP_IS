<%-- 
    Document   : login
    Created on : 05/05/2018, 23:08:55
    Author     : Victor de Lucca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Pet Shop</title>

        <!-- Bootstrap core CSS -->
        <link href="tools/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template -->
        <link href="tools/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

        <!-- Plugin CSS -->
        <link href="tools/vendor/magnific-popup/magnific-popup.css" rel="stylesheet" type="text/css">

        <!-- Custom styles for this template -->
        <link href="tools/css/freelancer.min.css" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="tools/css/modificacao.css">

    </head>
    <body id="page-top">

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}/home">Pet Shop</a>
                <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fa fa-bars"></i>
                </button>


            </div>
        </nav>

        <section class="col-md-12">
            <div class="container">
                <div class="row">
                    <div class="col-md-4" style="margin: 0 auto;">
                        <form class="form-signin" style="margin-top: 80px;" action="${pageContext.request.contextPath}/login" method="post">
                            <div class="col-md-6" style="margin: 0 auto;">
                                <img class="mb-4" src="tools/img/Dog-vector-now.jpg" width="130">
                            </div>
                            <h1 class="h3 mb-3 font-weight-normal" align="center">Logar</h1>
                            <label for="inputUser" class="sr-only">Login </label>
                            <input type="text" id="username" name="username" class="form-control" placeholder="Usuario" required autofocus>
                            <label for="inputPassword" class="sr-only" >Senha</label>
                            <input type="password" id="senha" name="senha" class="form-control" placeholder="Senha" required >
                            <div class="checkbox mb-3" style="margin-top:15px;">
                                <label>
                                    <input type="checkbox" value="remember-me"> Lembrar
                                </label>
                            </div>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>

                        </form>
                    </div>
                </div>
            </div>
        </section>          

    </body>
</html>
