function setDefaultFocusInitially() {
	if (typeof setDefaultFocusInitiallyRegistered === 'undefined') {
		window.setDefaultFocusInitiallyRegistered = true;

		function requestInitialDefaultFocus() {
			//honour autofocus tag, can be used to disable autofocus by making a hidden input have autofocus
			var focusTag = $(':input[autofocus]:enabled:not([readonly]):not(:file):not(:button):first');
			if (focusTag.length == 0) {
				//otherwise focus the first valid input
				focusTag = $(':input:visible:enabled:not([readonly]):not(:file):not(:button):first');
			}
			
			if(focusTag.length == 1){
				focusTag.focus();
				//hide datepicker initially
				if($('.datepicker').length > 0){
					focusTag.datepicker('hide');
					//but show it again on click
					focusTag.one('click', function(){
						$(this).datepicker('show');
					});
				}
			}
		}

		requestInitialDefaultFocus();
		Wicket.Event.add(window, 'shown.bs.modal', function(e) {
			requestInitialDefaultFocus();
		});
	}
}