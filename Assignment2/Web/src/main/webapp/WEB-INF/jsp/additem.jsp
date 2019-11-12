<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/additem.css"/>
    <title>MyBay</title>
</head>

<body>
<div class="additem-container">
    <div class="logo-container">
        <a href="${pageContext.request.contextPath}/login"><img class="mb-4" src="images/logo.png"></a>
    </div>
    <div class="additem-form-container">
        <div class="additem-text-container">
            <h1 align="center">Add Item</h1>
        </div>
        <form class="form-additem" action="${pageContext.request.contextPath}/additem" method="post" enctype="multipart/form-data">
            <div class="additem-form-fields-container">
                <div class="additem-fields-container">
                    Select File to Upload:<input type="file" name="fileName">
                </div>
                <div class="additem-fields-container">
                    <input type="text" id="name" name="name" placeholder="Name" maxlength="150" required autofocus>
                </div>
                <div class="additem-fields-container">
                    <input type="text" id="price" name="price" placeholder="Price (EUR)"
                           oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');"/>
                </div>
                <div class="additem-fields-container">
                    <select id="country" name="country" required>
                        <option value="" selected disabled hidden>Country</option>
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

                <div class="additem-fields-container">
                    <select id="category" name="category" required>
                        <option value="" selected disabled hidden>Category</option>
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
                <div class="additem-button-container">
                    <input type="submit" class="additem-button" value="Add Item">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
