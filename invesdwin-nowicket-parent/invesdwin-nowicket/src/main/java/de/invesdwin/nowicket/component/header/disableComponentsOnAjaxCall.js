function undisableComponentsAfterAjaxCall(){
	$(':input:disabled[data-disableComponentsOnAjaxCall=true]').each(function(){
		var tag = $(this);
		tag.prop('disabled', false);
		tag.removeAttr('data-disableComponentsOnAjaxCall');
	});
	window.ajaxCallRunning = false;
	if (!(typeof disableComponentsOnAjaxCall_activeElement === 'undefined')) {
		$(window.disableComponentsOnAjaxCall_activeElement).focus();
		window.disableComponentsOnAjaxCall_activeElement = null;
	}
}
function disableComponentsOnAjaxCall() {
	
	if (typeof disableComponentsOnAjaxCallRegistered === 'undefined') {
		window.disableComponentsOnAjaxCallRegistered = true;
		window.ajaxCallRunning = false;
		// see https://cwiki.apache.org/confluence/pages/viewpage.action?pageId=87917 http://wicket.apache.org/guide/guide/ajax.html#ajax_3
		//execute disable when ajax call starts
		Wicket.Event.subscribe('/ajax/call/after', function(attributes,
				jqXHR, settings) {
			window.ajaxCallRunning = true;
			setTimeout(function(){ 
				window.disableComponentsOnAjaxCall_activeElement = OptimalSelect.select(document.activeElement); 
				// do not disable file inputs, or their upload will fail!
				$(':input:not(:disabled)').each(function(){
					var tag = $(this);
					tag.attr('data-disableComponentsOnAjaxCall', 'true')
					tag.prop('disabled', true);
				});
			}, 1); //process after the focus might have traversed
		});
		//see https://github.com/hubspot/offline
		Offline.on("up", function(e) {
			location.reload();
		});
	}
	//execute undisable on any ondomready event
	undisableComponentsAfterAjaxCall();
}