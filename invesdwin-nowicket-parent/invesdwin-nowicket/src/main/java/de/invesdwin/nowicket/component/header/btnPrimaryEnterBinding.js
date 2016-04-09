function btnPrimaryEnterBinding() {
	if (typeof btnPrimaryEnterBindingRegistered === 'undefined') {
		window.btnPrimaryEnterBindingRegistered = true;
		$(window).keydown(function(e) {
			if (e.which == 13) {
				// disable key down so that the default button is pressed instead of form submitted
				e.preventDefault();
				return false;
			}
		});
		$(window).keypress(function(e) {
			if (e.which == 13) {
				// disable key down so that the default button is pressed instead of form submitted
				e.preventDefault();
				return false;
			}
		});
		$(window).keyup(function(e) {
			if (e.which == 13) {
				// invoke button on key up
				e.preventDefault();
				var btnPrimaryTag = $('button.btn-primary:not(:disabled):visible');
				if (btnPrimaryTag.length == 1) {
					btnPrimaryTag.click();
				}
				return false;
			}
		});
	}
}