$.fn.modal.defaults.manager = 'form';

function updateBodyClassesForBootstrapModal() {
	if (typeof updateBodyClassesForBootstrapModalRegistered === 'undefined') {
		window.updateBodyClassesForBootstrapModalRegistered = true;

		function updateBodyClassesForBootstrapModalImpl() {
			var modalClasses = 'modal-open page-overflow';
			if ($('.modal:visible').length) {
				$('body').addClass(modalClasses);
			} else {
				$('body').removeClass(modalClasses);
				// ModalContainer disables scroll right before a modal is
				// closed, so that the scroll position is not changed; need to
				// undo that here
				$(window).disablescroll('undo');
			}
			$('form').removeClass(modalClasses);
		}

		updateBodyClassesForBootstrapModalImpl();
		Wicket.Event.add(window, 'shown.bs.modal', function(e) {
			updateBodyClassesForBootstrapModalImpl();
		});
		Wicket.Event.add(window, 'hidden.bs.modal', function(e) {
			updateBodyClassesForBootstrapModalImpl();
		});
	}
}