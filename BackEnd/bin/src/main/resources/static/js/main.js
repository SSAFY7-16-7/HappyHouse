(function ($) {
  'use strict';

  $.fn.scrollingTo = function (opts) {
    var defaults = {
      animationTime: 1000,
      easing: '',
      callbackBeforeTransition: function () {},
      callbackAfterTransition: function () {},
    };

    var config = $.extend({}, defaults, opts);

    $(this).click(function (e) {
      var eventVal = e;
      e.preventDefault();

      var $section = $(document).find($(this).data('section'));
      if ($section.length < 1) {
        return false;
      }

      if ($('html, body').is(':animated')) {
        $('html, body').stop(true, true);
      }

      var scrollPos = $section.offset().top;

      if ($(window).scrollTop() == scrollPos) {
        return false;
      }

      config.callbackBeforeTransition(eventVal, $section);

      $('html, body').animate(
        {
          scrollTop: scrollPos + 'px',
        },
        config.animationTime,
        config.easing,
        function () {
          config.callbackAfterTransition(eventVal, $section);
        }
      );
    });
  };

  /* ========================================================================= */
  /*   Contact Form Validating
    /* ========================================================================= */

  $('#contact-form').validate({
    rules: {
      name: {
        required: true,
        minlength: 4,
      },
      email: {
        required: true,
        email: true,
      },
      subject: {
        required: false,
      },
      message: {
        required: true,
      },
    },
    messages: {
      user_name: {
        required: "Come on, you have a name don't you?",
        minlength: 'Your name must consist of at least 2 characters',
      },
      email: {
        required: 'Please put your email address',
      },
      message: {
        required: 'Put some messages here?',
        minlength: 'Your name must consist of at least 2 characters',
      },
    },
    submitHandler: function (form) {
      $(form).ajaxSubmit({
        type: 'POST',
        data: $(form).serialize(),
        url: 'sendmail.php',
        success: function () {
          $('#contact-form #success').fadeIn();
        },
        error: function () {
          $('#contact-form #error').fadeIn();
        },
      });
    },
  });
})(jQuery);

jQuery(document).ready(function () {
  'use strict';
  new WOW().init();

  (function () {
    jQuery('.smooth-scroll').scrollingTo();
  })();
});

$(document).ready(function () {
  $(window).scroll(function () {
    if ($(window).scrollTop() > 50) {
      $('.navbar-brand a').css('color', '#fff');
      $('.top-bar').removeClass('animated-header');
    } else {
      $('.navbar-brand a').css('color', 'inherit');
      $('.top-bar').addClass('animated-header');
    }
  });

  $('.clients-logo-slider').slick({
    dots: false,
    infinite: true,
    speed: 300,
    slidesToShow: 5,
    slidesToScroll: 1,
    arrows: false,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
        },
      },
    ],
  });
});

// fancybox
$('.fancybox').fancybox({
  padding: 0,

  openEffect: 'elastic',
  openSpeed: 450,

  closeEffect: 'elastic',
  closeSpeed: 350,

  closeClick: true,
  helpers: {
    title: {
      type: 'inside',
    },
    overlay: {
      css: {
        background: 'rgba(0,0,0,0.8)',
      },
    },
  },
});

function regist() {
  let id = document.getElementById('id').value;
  let password = document.getElementById('password').value;
  let name = document.getElementById('name').value;
  let email = document.getElementById('email').value;
  let age = document.getElementById('age').value;

  if (!id || !password || !name || !email || !age) {
    alert('빈칸이 없도록 입력해주세요.');
    return;
  } else {
    const user = {
      id: id,
      password: password,
      name: name,
      email: email,
      age: age,
    };

    localStorage.setItem('user', JSON.stringify(user));
    console.log(user);
    alert('사용자 등록 성공!');
    window.location.replace('login.html');
  }
}

function login() {
  let id = document.getElementById('id').value;
  let password = document.getElementById('password').value;

  const user = JSON.parse(localStorage.getItem('user'));

  if (
    user.id &&
    user.password &&
    user.id === id &&
    user.password === password
  ) {
    alert('로그인 성공 !');
    console.log('login');
    console.log('id : ' + id + ' password : ' + password);
    window.location.replace('index logout.html');
  } else {
    alert('로그인 실패 !');
  }
}

function remove() {
  alert('삭제 완료 !');
}

function logout_console() {
  console.log('logout');
}





