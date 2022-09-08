function enableBootstrapTooltips() {
	if (typeof enableBootstrapTooltipsRegistered === 'undefined') {
		window.enableBootstrapTooltipsRegistered = true;

		function triggerEnableBootstrapTooltips() {
			${FUNCTION}
		}

		triggerEnableBootstrapTooltips();
		Wicket.Event.add(window, 'shown.bs.modal', function(e) {
			triggerEnableBootstrapTooltips();
		});
		Wicket.Event.subscribe('/ajax/call/success', function(attributes, jqXHR, settings) {
			triggerEnableBootstrapTooltips();
		});
		Wicket.Event.subscribe('/ajax/call/failure', function(jqEvent, attributes, jqXHR, errorThrown, textStatus) {
			triggerEnableBootstrapTooltips();
		});
	}
}
