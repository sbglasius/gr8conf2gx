if(typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
                    $(this).fadeOut();
                });
        $('#cameraButton').click(function () {
            console.debug("Button click!", $('input[name="file"]'));
            $('input[name="file"]').trigger('click')
        });
        $('input[name="file"]').ajaxfileupload({
            action:$('input[name="upload"]').val(),
            responseType:'json',
            onComplete:function (response) {
                $('#maincontent').replaceWith(response);
                $('#spin-overlay').remove()
            },
            onStart:function () {
                var body = $('body')
                var overlay = $('<div/>').attr('id','spin-overlay').appendTo(body);
                overlay.css({
                    position: 'absolute',
                    top: 0,
                    left: 0,
                    backgroundColor: 'rgba(0,0,0,0.5)',
                    width: "100%",
                    height: body.height()
                });
                overlay.spin()
            },
            onCancel:function () {
                console.log('no file selected');
            }
        });
        console.debug("Ready!")
    })(jQuery);
}
