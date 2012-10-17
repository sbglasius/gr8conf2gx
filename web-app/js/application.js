if(typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
                    $(this).fadeOut();
                });
        $('#cameraButton').click(function () {
            $('input[name="file"]').click()
        })
        $('input[name="file"]').ajaxfileupload({
            action:$('input[name="upload"]').val(),
            responseType:'json',
            onComplete:function (response) {
                console.log('custom handler for file:', response, $('#maincontent'));
                $('#maincontent').replaceWith(response);
            },
            onStart:function () {

            },
            onCancel:function () {
                console.log('no file selected');
            }
        });

    })(jQuery);
}
