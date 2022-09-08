function enableBootstrapTooltips() {
	if (typeof enableBootstrapTooltipsRegistered === 'undefined') {
		window.enableBootstrapTooltipsRegistered = true;

		function triggerEnableBootstrapTooltips() {
			$('[data-toggle="tooltip"]').each(function(){
				var tag = $(this);
				if(tag.attr('data-original-title') === undefined){
					tag.tooltip(${CONFIG});
				}
				const title = tag.attr('title');
				if(title !== undefined && title.length > 0){
					const prevTitle = tag.attr('data-original-title');
					if(prevTitle !== title) {
						tag.attr('data-original-title', title)
						if(tag.attr('aria-describedby')){
							tag.tooltip('hide');
							tag.tooltip('show');
						}
					}
					tag.removeAttr('title');
				}
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
