let grid = 'products-main';

$(document).ready(function() {
    gridDiv = $('#'+grid);

    $.ajax({
        type: 'GET',
        url: URL_REQUEST+'/products/list',
        success: function(response){
            //Si hubo respuesta:
            if(response)
            {
                let template = '';
                response.forEach( (element)=>{
                    let product = element.product;
                    let img = element.imgs[0];

                    template += printProduct(
                        img.url, product.title, product.description, product.price
                    );
                });

                gridDiv.html(template);
            }
        },

        error: function(jqXHR, textStatus, errorThrown){
            console.log("error");
        }
    });
});



function printProduct(url, title, description, price){
    let temp = `
        <div class="product">
            <div class="product-content">
                <div class="product-img">
                    <img src="${url}" alt="${title}">
                </div>

                <div class="product-btns">
                    <button type="button" class="btn-cart"> Tomarlo!
                        <span><i class="fas fa-plus"></i></span>
                    </button>

                    <button type="button" class="btn-buy"> Lo deseo!
                        <span><i class = "fas fa-shopping-cart"></i></span>
                    </button>
                </div>
            </div>

            <div class="product-info">
                <div class="product-info-top">
                    <h2 class="sm-title">Usuario</h2>
                    <div class="rating">
                        <span><i class="fas fa-star"></i></span>
                        <span><i class="fas fa-star"></i></span>
                        <span><i class="fas fa-star"></i></span>
                        <span><i class="fas fa-star"></i></span>
                        <span><i class="far fa-star"></i></span>
                    </div>
                </div>
                <a href="#" class="product-name">${title}</a>
                <span>${description}</span>
                <p class="product-price">$ ${price}</p>
            </div>
    `;

    if(price == 0){
        temp += `
            <div class = "off-info">
                <h2 class = "sm-title">Gratis!</h2>
            </div>
        `
    }

    temp += '</div>'

    return temp;
}