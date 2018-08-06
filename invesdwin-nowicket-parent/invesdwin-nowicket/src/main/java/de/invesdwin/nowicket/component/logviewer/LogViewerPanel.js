function updateLogViewerSizeOnResize() {
	if (typeof updateLogViewerSizeOnResizeRegistered === 'undefined') {
		window.updateLogViewerSizeOnResizeRegistered = true;

		// http://b.mojotech.com/responsive-dynamic-height-sticky-footers/
		var bumpIt = function() {
			var newHeight = $(window).height() * ${LOG_HEIGHT};
			if (newHeight < 300) {
				newHeight = $(window).height();
			}
			newHeight = newHeight + 'px';
			$('#logData').css('height', newHeight);
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
Wicket.Event.add(window, "load", function(event) {
	updateLogViewerSizeOnResize();
});