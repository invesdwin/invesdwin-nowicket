function enableTableFixedHead() {
	if (typeof enableTableFixedHeadRegistered === 'undefined') {
		window.enableTableFixedHeadRegistered = true;
		window.enableTableFixedHeadDisabled = false;

		function triggerEnableTableFixedHead() {
			if (!window.enableTableFixedHeadDisabled) {
				$('.table-fixed-head:not(.floatThead-table)').each(function() {
					const thisElement = $(this)
					const updatedThead = thisElement.find('thead');
					if (!thisElement.hasClass('table-fixed-head-enabled') || updatedThead.length == 0) {
						if (thisElement.data('floatThead-attached')) {
							thisElement.removeClass('table-fixed-head-enabled').floatThead('destroy');
							//thisElement.find('thead').not(':first').remove();
						}
						thisElement.addClass('table-fixed-head-enabled');
						thisElement.siblings('.floatThead-container').remove();
						if (thisElement.closest(".table-responsive").length == 0) {
							thisElement.floatThead({
								zIndex: 999,
							});
						} else {
							thisElement.floatThead({
								zIndex: 999,
								responsiveContainer: function($table) {
									return $table.closest(".table-responsive");
								}
							});
						}
					}
					const container = thisElement.siblings('.floatThead-container');
					if (updatedThead.length > 0) {
						const cols = updatedThead.find('.floatThead-col');
						if (cols.length == 0) {
							const fixedThead = container.find('thead');
							if (fixedThead.length == 1) {
								fixedThead.replaceWith(updatedThead.clone());
							}
						}
					}
					thisElement.find('thead').not(':first').remove();
					container.find('thead').not(':first').remove();
				});
				//$('.table-fixed-head').each(function() {
				//	$(this).find('thead').not(':first').remove();
				//});
			}
		}
		window.triggerEnableTableFixedHead = triggerEnableTableFixedHead;

		function triggerDisableTableFixedHead() {
			$('.table-fixed-head-enabled').removeClass('table-fixed-head-enabled').floatThead('destroy');
		}
		window.triggerDisableTableFixedHead = triggerDisableTableFixedHead;

		triggerEnableTableFixedHead();
		Wicket.Event.add(window, 'shown.bs.modal', function(e) {
			triggerEnableTableFixedHead();
		});
		Wicket.Event.add(window, 'show.bs.collapse', function(e) {
			enableTableFixedHeadDisabled = true;
			triggerDisableTableFixedHead();
		});
		Wicket.Event.add(window, 'shown.bs.collapse', function(e) {
			enableTableFixedHeadDisabled = false;
			triggerEnableTableFixedHead();
		});
		Wicket.Event.add(window, 'hide.bs.collapse', function(e) {
			enableTableFixedHeadDisabled = true;
			triggerDisableTableFixedHead();
		});
		Wicket.Event.add(window, 'hidden.bs.collapse', function(e) {
			enableTableFixedHeadDisabled = false;
			triggerEnableTableFixedHead();
		});
		Wicket.Event.subscribe('/ajax/call/success', function(attributes, jqXHR, settings) {
			triggerEnableTableFixedHead();
		});
		Wicket.Event.subscribe('/ajax/call/failure', function(jqEvent, attributes, jqXHR, errorThrown, textStatus) {
			triggerEnableTableFixedHead();
		});

		var didResize = false;
		$(window).resize(function() {
			didResize = true;
		});
		setInterval(function() {
			if (didResize) {
				didResize = false;
				$('.table-fixed-head').find('thead').not(':first').remove();
			}
		}, 250);
	}
}
