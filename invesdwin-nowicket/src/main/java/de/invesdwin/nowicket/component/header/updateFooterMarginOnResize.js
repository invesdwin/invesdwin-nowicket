function updateFooterMarginOnResize() {
	if (typeof updateFooterMarginOnResizeRegistered === 'undefined') {
		window.updateFooterMarginOnResizeRegistered = true;

		var bodyTag = $('body');
		var footerTag = $('.footer');

		if (footerTag.length == 0) {
			bodyTag.css('margin-bottom', '0px');
		} else {
			// http://blog.mojotech.com/responsive-dynamic-height-sticky-footers/
			var bumpIt = function() {
				bodyTag.css('margin-bottom', footerTag.height());
			}, didResize = false;

			bumpIt();

			$(window).resize(function() {
				didResize = true;
			});
			setInterval(function() {
				if (didResize) {
					didResize = false;
					bumpIt();
				}
			}, 250);
		}
	}
}