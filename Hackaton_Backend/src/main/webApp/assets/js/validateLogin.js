//Functions:
//code elaborated in : https://stackoverflow.com/questions/901115/how-can-i-get-query-string-values-in-javascript
/**
 * @param String name
 * @return String
 */
 function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

//---- Variables globals.
var code_role_user;
var code;
var email_;
console.log(getParameterByName('code'));
console.log(getParameterByName('email'));
//Validación de datos.
$.ajax({
    type: 'GET',
    url: URL_REQUEST+"users/search/"+getParameterByName('code')+"/"+getParameterByName('email'),
    success: function(response){
        //Si no existe el usuario:
        if(!response.code) $(location).attr('href', '../');
        else{
            code_role_user = response.code_role_user;
            code = response.code;
            email_ = response.email;
            window.history.replaceState({}, document.title, "/"+NAME_DOMINIO);
        }
    },

    error: function(jqXHR, textStatus, errorThrown){
        alert("Error al ingresar.");
        $(location).attr('href', '../');
    }
});

