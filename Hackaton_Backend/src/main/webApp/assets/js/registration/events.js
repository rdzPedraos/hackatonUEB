//almacena el id y si el valor fue deligenciado correctamente.
const validate = {};
const btn = $()

$(document).ready(function()
{
    const mapRgx = {
        'email': /^[A-Za-z0-9-_]+@[A-Za-z0-9-_]+(\.[A-Za-z0-9]+)+$/,
        'pass': /^[A-Za-z0-9_*-]{5,}$/,
        'name': /^([A-Za-záéíóúÁÉÍÓÚ]{3,15} ?){1,2}$/,
        'address': /^[A-Za-záéíóúÁÉÍÓÚ0-9-#. ]{1,45}$/,
        'number': /^[1-9][0-9]?$/,
        'zipCode': /^[0-9]{6}$/,
        'text': /^[A-Za-záéíóúÁÉÍÓÚ ]{0,255}$/,
    }

    //mapa de datos para validar:
    var data = [
        ['email', 'email'],
        ['password', 'pass'],
        ['password2', 'pass'],
        ['name', 'name'],
        ['last_name', 'name'],
        ['age', 'number'],
        ['address', 'address'],
        ['municipalityInput', null],
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

    //Añadimos el evento:
    $('#municipalityInput').on(
        'keyup blur', ()=>{ 
            validateInputList('municipalityInput', '') 
        }
    );
});


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
    btn.prop( 'disabled', !isAllCorrect() );
}


/**
 * Evalua si el campo ingresado existe una lista de opciones.
 * Añade clases para informar si el campo es correcto o no.
 * @param {String} idInput id del campo
 * @param {String} idList id de la lista.
 */
function validateInputList(idInput, idList){

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
 * @param {object} validate objeto a evaluar. 
 * @returns 
 */
function isAllCorrect(validate){
    //Retorno:
    let returnBool = true;

    //Verifique que todos los campos están en "true", que todo es correcto.
    for(value in validate) 
    if(!validate[value]){returnBool = false; break};

    return returnBool;
}