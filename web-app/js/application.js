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
        var overlay;
        $('input[name="file"]','#ajaxForm').ajaxfileupload({
            action:$('input[name="upload"]').val(),
            responseType:'json',
            onComplete:function (response) {
                if(overlay) {
                    overlay.remove();
                    overlay = undefined;
                }
                $('#maincontent').replaceWith(response);
            },
            onStart:function () {
                var body = $('body')
                overlay = $('<div/>').attr('id','spin-overlay').appendTo(body);
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
        $('input[name="file"]','#mobileForm').change(function() {
            $('#mobileForm').submit();
        });
        console.debug("Ready!")
    })(jQuery);
}
