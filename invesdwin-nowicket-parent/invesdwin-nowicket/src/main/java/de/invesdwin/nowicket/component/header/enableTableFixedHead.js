function enableTableFixedHead() {
	if (typeof enableTableFixedHeadRegistered === 'undefined') {
		window.enableTableFixedHeadRegistered = true;
		window.enableTableFixedHeadDisabled = false;

		function triggerEnableTableFixedHead() {
			if (!window.enableTableFixedHeadDisabled) {
				$('.table-fixed-head:not(.floatThead-table):not(.table-fixed-head-enabled)').each(function() {
					const thisElement = $(this)
					thisElement.addClass('table-fixed-head-enabled');
					thisElement.siblings('.floatThead-container').remove();
					if (thisElement.closest(".table-responsive").length == 0) {
						thisElement.floatThead({
							autoReflow: true,
							zIndex: 999,
						});
					} else {
						thisElement.floatThead({
							autoReflow: true,
							zIndex: 999,
							responsiveContainer: function($table) {
								return $table.closest(".table-responsive");
							}
						});
					}
				});
			}
		}

		function triggerDisableTableFixedHead() {
			$('.table-fixed-head-enabled').removeClass('table-fixed-head-enabled').floatThead('destroy');
		}

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
		Wicket.Event.subscribe('/ajax/call/success', function(attributes,
			jqXHR, settings) {
			triggerEnableTableFixedHead();
		});
	}
}
