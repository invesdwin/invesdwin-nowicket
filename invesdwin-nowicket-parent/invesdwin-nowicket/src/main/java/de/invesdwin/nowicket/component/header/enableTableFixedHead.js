function enableTableFixedHead() {
	if (typeof enableTableFixedHeadRegistered === 'undefined') {
		window.enableTableFixedHeadRegistered = true;

		function triggerEnableTableFixedHead() {
			$(() => $('.table-fixed-head').floatThead({
				autoReflow: true,
				responsiveContainer: function($table){
		            return $table.closest(".table-responsive");
		        }
			}));
		}
		
		function triggerDisableTableFixedHead() {
			$(() => $('.table-fixed-head').floatThead('destroy'));
		}

		triggerEnableTableFixedHead();
		Wicket.Event.add(window, 'shown.bs.modal', function(e) {
			triggerEnableTableFixedHead();
		});
		Wicket.Event.add(window, 'show.bs.collapse', function(e) {
			triggerDisableTableFixedHead();
		});
		Wicket.Event.add(window, 'shown.bs.collapse', function(e) {
			triggerEnableTableFixedHead();
		});
		Wicket.Event.add(window, 'hide.bs.collapse', function(e) {
			triggerDisableTableFixedHead();
		});
		Wicket.Event.add(window, 'hidden.bs.collapse', function(e) {
			triggerEnableTableFixedHead();
		});
		Wicket.Event.subscribe('/ajax/call/success', function(attributes,
				jqXHR, settings) {
			triggerEnableTableFixedHead();
		});
	}
}
