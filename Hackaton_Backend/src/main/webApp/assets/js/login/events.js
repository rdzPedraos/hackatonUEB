//ids----
const form = 'login'
const btn = 'btn';
const email = 'email';
const password = 'password';
//obj con los valores validados---
var validate;

$(document).ready( function() {
    const rgx = {
        email: /^[A-Za-z0-9-_]+@[A-Za-z0-9-_]+(\.[A-Za-z0-9]+)+$/,
        password: /^[A-Za-z0-9_*-]{5,}$/
    }

    validate = {email: false, password: false}

    //Validar los campos:
    $('#'+email+', #'+password).on('keyup click blur', function(){
        let id = $(this).attr('id');
        validateFields(id, rgx[id]);

        //Se activa en caso de tener ambos campos bien ingresados.
        $('#'+btn).prop('disabled', !validate[email] || !validate[password]);
    });

    $('#'+form).submit( login );
});


/**
 * Adiciona clases al input enviado, clases para explicar si un campo es correcto o no.
 * @param {DocumetnsObject} input 
 * @param {Boolean} value 
 */
 function addClass(input, value){
    field = input.parent();
    field.removeClass("error success");
    //Si el valor es cierto, entonces:
    if (value) {
        field.addClass("success");
        field.next().hide();
    } else {
        field.addClass("error");
        field.next().show();
    }
}



/**
 * Valida el valor de un campo y adiciona clases al campo para ver si hubo error o es correcto.
 * @param {String} id id del input a evaluar.
 * @param {RegExp} rgx expresión regular con la que evaluaremos.
 */
function validateFields(id, rgx){
    let input = $('#'+id);
    let text = input.val();

    validate[id] = rgx.test(text);
    addClass(input, validate[id]);

}

/**
 * Muestra un modal con un color (verdadero o falso, éxito o fracaso)
 * @param {String} title titulo del modal.
 * @param {String} description descripción del modal
 * @param {Boolean} success éxito o fracaso (cambiará los colores del modal)
 */
function showModal(title, description, success){
    let modal  = $('#modalSubmit');
    modal.find('.modal-header h5').text(title);
    modal.find('.modal-body p').text(description);

    modal.removeClass('succes error');

    if(success) modal.addClass('success');
    else modal.addClass('error');

    modal.modal('show');
};


/**
 * Envia formulario, en caso de error muestra modal, si tuvo éxito redirecciona
 * ¿Cómo redirecciona por post? Elimina el evento y activa nuevamente el submit del form.
 */
function login(e){
    e.preventDefault();
    let form = this;
    if( validate[email] && validate[password] )
    {
        $.ajax({
            type: 'POST',
            url: URL_REQUEST+"users/search",
            data: new FormData(form),
            contentType: false,
            cache: false,
            processData: false,
    
            success: function(response){
                if(response.code){
                    //Hariamos el envio por post para validar el usuario:
                    /*$(form).unbind('submit', login);
                    $(form).trigger('submit');*/

                    $(location).attr('href', 'pages/main.html?code='+response.code+"&email="+response.email);
                }
                else showModal("Oops", "El usuario y/o contraseña son inválidos.", false);
            },
    
            error: function(jqXHR, textStatus, errorThrown){
                showModal("Oops", "Ha ocurrido un error desconocido, prueba más tarde", false);
            }
        });
    }
    else{
        showModal("Oops", "Faltan campos o están mal deligenciados.", false);
        $('#'+btn).prop('disabled', true);
    }
};