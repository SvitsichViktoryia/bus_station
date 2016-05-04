$(document).ready(function() {
           $(".slider2").each(function () { // обрабатываем каждый слайдер
            var obj = $(this);
            $(obj).append("<div class='nav'></div>");
            $(obj).find("li").each(function () {
             $(obj).find(".nav").append("<span rel='"+$(this).index()+"'></span>"); // добавляем блок навигации
             $(this).addClass("slider2"+$(this).index());
            });
            $(obj).find("span").first().addClass("on"); // делаем активным первый элемент меню
           });
          });
          function slider2JS (obj, sl) { // slider function
           var ul = $(sl).find("ul"); // находим блок
           var bl = $(sl).find("li.slider2"+obj); // находим любой из элементов блока
           var step = $(bl).width(); // ширина объекта
           $(ul).animate({marginLeft: "-"+step*obj}, 500); // 500 это скорость перемотки
          }
          $(document).on("click", ".slider2 .nav span", function() { // slider click navigate
           var sl = $(this).closest(".slider2"); // находим, в каком блоке был клик
           $(sl).find("span").removeClass("on"); // убираем активный элемент
           $(this).addClass("on"); // делаем активным текущий
           var obj = $(this).attr("rel"); // узнаем его номер
           slider2JS(obj, sl); // слайдим
           return false;
});
