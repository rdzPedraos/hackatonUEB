//almacena el id y si el valor fue deligenciado correctamente.
const validate = {};
//Almacena los inputs que tendremos.
var data = [];
$(document).on('click', 'a[href="#finish"]', submitBtn);


$(document).ready(function()
{
    //Cargamos información al div de departamentos:
    loadUbication('departments', 170, '#departments');
    //----------------------------

    //Evitamos que cierre el formulario.
    /*$('#modalSubmit').modal({
        backdrop: 'static',
        keyboard: false  // prevenimos que pueda cerrar el modal de observaciones.
    })*/


    const mapRgx = {
        'email': /^[A-Za-z0-9-_]+@[A-Za-z0-9-_]+(\.[A-Za-z0-9]+)+$/,
        'pass': /^[A-Za-z0-9_*-]{5,}$/,
        'name': /^([A-Za-záéíóúÁÉÍÓÚ]{3,15} ?){1,2}$/,
        'address': /^[A-Za-záéíóúÁÉÍÓÚ0-9-#. ]{1,45}$/,
        'number': /^[1-9][0-9]?$/,
        'zipCode': /^[0-9]{6}$/,
        'text': /^[A-Za-z0-9áéíóúÁÉÍÓÚ \n,.:;]{0,255}$/,
    }

    //mapa de datos para validar:
    data = [
        ['email', 'email'],
        ['password', null],
        ['password2', null],
        ['name', 'name'],
        ['last_name', 'name'],
        ['age', 'number'],
        ['address', 'address'],
        ['municipality', null],
        ['zip_code', 'zipCode'],
        ['message', 'text']
    ];

    //Generamos los eventos para los campos:
    data.forEach( (value)=>{
        validate[value[0]] = false;
        if( value[1] != null ){
            let input = $('#'+value[0]);
            input.on('keyup click blur', function(){
                validateFields(value[0], mapRgx[value[1]] );
            });
        }
    });

    //por defecto no requiere escribir un mensaje:
    validate['message'] = true;
    validate['municipality'] = false;

    //Añadimos el evento:
    $('#municipality').on(
        'keyup blur click', ()=>{ 
            validateInputList('municipality', 'municipalities-dl');
        }
    );

    $('#password2').on('keyup click blur', function(){
        let password = $('#password').val();
        validate['password2'] = $(this).val() == password;
        addClass($(this), validate['password2']);
    });

    $('#password').on('keyup click blur', function(){
        validateFields('password', mapRgx['pass']);
        $('#password2').trigger('click');
    });
});


/// --------------------- VALIDACIONES ---------------------


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
 * Evalua si el campo ingresado existe una lista de opciones.
 * Añade clases para informar si el campo es correcto o no.
 * @param {String} idInput id del campo
 * @param {String} idList id de la lista.
 */
function validateInputList(idInput, idList){
    //Input y lista:
    let input = $('#'+idInput);
    let list = $('#'+idList);

    //me dice si es correcto o no el valor.
    let correct = false;
    
    let value = input.val().toUpperCase();

    if( value ){
        let code = list.find("option[value="+value+"]").data('value');
        //Si encontró algo:
        if( code ){
            input.parent().find('input[type="hidden"]').val(code);
            correct = true;
        }
    }

    if(!correct) input.parent().find('input[type="hidden"]').val('');
    validate[idInput] = correct;
    addClass(input, correct);
}


/**
 * Adiciona clases al input enviado, clases para explicar si un campo es correcto o no.
 * @param {DocumetnsObject} input 
 * @param {Boolean} value 
 */
function addClass(input, value){
    field = input;
    field.removeClass("error success");
    //Si el valor es cierto, entonces:
    if (value) {
        field.addClass("success");
    } else {
        field.addClass("error");
    }
}


/**
 * Evalua si todos los valores de una mapa de valores tiene como valor true.
 * @param {object} objValidate objeto a evaluar. 
 * @returns 
 */
function isAllCorrect(objValidate){
    //Retorno:
    let returnBool = true;

    //Verifique que todos los campos están en "true", que todo es correcto.
    for(value in objValidate) 
    if(!objValidate[value]){returnBool = false; break};

    return returnBool;
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



/// --------------------- AJAX ---------------------

/**
 * Función para cargar lista de departamentos (opciones) en un lugar.
 * @param {String} type_ubication tipo de busqueda (departments, municiapalities)
 * @param {String} value valor de busqueda.
 * @param {String} idList id de lista donde se insertará.
 * @returns 
 */
function loadUbication(type_ubication, value, idList){
    //Validamos que los valores sean correctos:
    if( !/[(departments)|(municipalities)]/.test(type_ubication) 
        || !$.isNumeric(value) || value < 1
    ) return;

    let list = $(idList);
    $.ajax({
        type: 'GET',
        url: URL_REQUEST+type_ubication+"/list/"+value,

        success: function(response){
            if(response){
                let template = '';
                let etq = 'li';
                if(type_ubication == "municipalities") etq = "option";

                response.forEach( (department)=>{
                    template += `
                        <${etq} data-value="${department.code}" value="${department.name.toUpperCase()}">${department.name}</${etq}>
                    `;
                });

                list.html(template);

                //Generemamos eventos para municipios:
                if(type_ubication == "departments"){
                    $("#departments li").on('click', eventMunicipalities);
                }
            }
        },

        error: function(jqXHR, textStatus, errorThrown){
            console.log('error');
        }
    });
}

function eventMunicipalities(){
    let action = $(this);
    let parent = action.parent().parent();

    //---reiniciamos el input de municipios.
    $('#municipality').text('');
    $('#municipalityInput').val('');
    validate['municipality'] = false;
    //-------

    //Muestro lo seleccionado:
    parent.find('.form-holder div').text( action.text() );
    
    //Oculto la ventana emergente:
    parent.find('.dropdown').hide();


    loadUbication(
        'municipalities', $(this).data('value'), '#municipalities-dl'
    );
}


/**
 * Función ejecutada al enviar el formulario.
 */
function submitBtn(){
    if( !$('#conditions').prop('checked') ) showModal("Oops", "Lo sentimos, no puedes ingresar si no aceptas los terminos y condiciones", false);
    else if( isAllCorrect(validate) )
    {
        let data = {
            'user':{
                'email': $('#email').val(),
                'password': $('#password').val()
            },

            'info':{
                'code_municipality': $('#municipalityInput').val(),
                'name': $('#name').val(),
                'last_name': $('#last_name').val(),
                'age': $('#age').val(),
                'address': $('#address').val(),
                'zip_code': $('#zip_code').val(),
                'message': $('#message').val()
            }
        };
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: URL_REQUEST+"users/create",
            data: JSON.stringify(data),
            dataType: 'json',

            success: function(response){
                let title = response.success ? "Registro creado" : "ERROR";
                showModal(title, response.msj, response.success);

                if( response.success ){
                    setTimeout(function(){
                        $(location).attr('href','../');
                    }, 2000);
                }
            }

        });
    }
    else{
        data.forEach( (value)=>{
            input = $('#'+value[0]);
            addClass(input, validate[value[0]]);
        });

        showModal("Oops", "Tienes campos vacios o mal deligenciados! Revisa.", false);
    }
}