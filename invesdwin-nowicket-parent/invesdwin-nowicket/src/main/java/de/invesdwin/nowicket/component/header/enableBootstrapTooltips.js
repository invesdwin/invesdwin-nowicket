function enableBootstrapTooltips() {
	if (typeof enableBootstrapTooltipsRegistered === 'undefined') {
		window.enableBootstrapTooltipsRegistered = true;
		window.enableBootstrapTooltipsShown = null;

		function triggerEnableBootstrapTooltips() {
			$('[data-bs-toggle="tooltip"]').each(function() {
				var tag = $(this);
				if (tag.attr('data-original-title') === undefined) {
					var tooltip = bootstrap.Tooltip.getInstance(this);
					if (!tooltip) {
						const initialTitle = tag.attr('title');
						tooltip = new bootstrap.Tooltip(this, { "animation": false });
						tag.attr('data-original-title', initialTitle);
						return;
					}
				}
				const title = tag.attr('title');
				if (title !== undefined && title.length > 0) {
					const prevTitle = tag.attr('data-original-title');
					if (prevTitle !== title) {
						tag.attr('data-original-title', title);
						const tooltip = bootstrap.Tooltip.getInstance(this);
						const tooltipShowing = window.enableBootstrapTooltipsShown == tooltip;

						//simply updating tooltips does not work in bootstrap 5.2.1 and upwads, instead we have to recreate them
						//tooltip.setContent({ '.tooltip-inner': title });
						//tooltip.update();
						
						if (tooltip) {
							tooltip.hide();
							tooltip.dispose();
							tag.attr('title', title);
						}
						const newTooltip = new bootstrap.Tooltip(this, { "animation": false });
						if (tooltipShowing) {
							newTooltip.show();
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
		Wicket.Event.add(window, 'shown.bs.tooltip', function(e) {
			//allow only one tooltip to be shown at a time: https://stackoverflow.com/a/29950663
			const thisElement = e.target;
			const thisTooltip = bootstrap.Tooltip.getInstance(thisElement);
			const existingTooltip = window.enableBootstrapTooltipsShown;
			if (thisTooltip != existingTooltip) {
				if (existingTooltip) {
					existingTooltip.hide();
				}
				window.enableBootstrapTooltipsShown = thisTooltip;
			}
		});
		Wicket.Event.add(window, 'hidden.bs.tooltip', function(e) {
			//allow only one tooltip to be shown at a time: https://stackoverflow.com/a/29950663
			const thisElement = e.target;
			const thisTooltip = bootstrap.Tooltip.getInstance(thisElement);
			const existingTooltip = window.enableBootstrapTooltipsShown;
			if (thisTooltip == existingTooltip) {
				window.enableBootstrapTooltipsShown = undefined;
			}
		});
	}
}
