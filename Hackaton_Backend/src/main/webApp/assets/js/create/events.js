$(document).ready(function(){
    $('#contact').submit( createProduct );
    $('#code_user').val(code);
    $('#toMain').click( ()=>{$(location).attr('href', 'pages/main.html?code='+code+"&email="+email_);})
});


function createProduct(e){
    e.preventDefault();
    let formData = $(this)[0];

    $.ajax({
        type: 'POST',
        url: "/products/create",
        data: new FormData(formData),
        contentType: false,
        cache: false,
        processData: false,

        success: function(response){
            //console.log(response);
            $(location).attr('href', 'pages/main.html?code='+code+"&email="+email_);
        },

        error: function(jqXHR, textStatus, errorThrown){
            alert("Ocurrió un error.");
        }

    });
}