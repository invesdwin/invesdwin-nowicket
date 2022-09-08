function enableBootstrapTooltips() {
	if (typeof enableBootstrapTooltipsRegistered === 'undefined') {
		window.enableBootstrapTooltipsRegistered = true;

		function triggerEnableBootstrapTooltips() {
			$('[data-toggle="tooltip"]').each(function(){
				var tag = $(this);
				if(!tag.data('bs.tooltip')){
					tag.tooltip(${CONFIG});
				}
				if(tag.is('[title]')){
					tag.attr('data-original-title', tag.attr('title'))
					tag.removeAttr('title');
				}
				if(tag.attr('aria-describedby')){
					tag.tooltip('hide');
					tag.tooltip('show');
				}
			});
			$('[data-original-title]').each(function(){
				var tag = $(this);
			});
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
