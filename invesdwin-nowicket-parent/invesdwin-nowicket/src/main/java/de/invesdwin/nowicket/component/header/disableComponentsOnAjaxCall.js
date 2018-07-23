function undisableComponentsAfterAjaxCall(){
	$(':input:disabled[data-disableComponentsOnAjaxCall=true]').each(function(){
		var tag = $(this);
		tag.prop('disabled', false);
		tag.removeAttr('data-disableComponentsOnAjaxCall');
	});
	window.ajaxCallRunning = false;
	if (!(typeof disableComponentsOnAjaxCall_activeElement === 'undefined')) {
		var comp = $(window.disableComponentsOnAjaxCall_activeElement);
		if(comp.is(':visible')){
			comp.focus();
		}
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
			// only disable components when we have an ajax indicator activated for this event
			if (jqXHR.i) {
				setTimeout(function(){ 
					window.disableComponentsOnAjaxCall_activeElement = OptimalSelect.select(document.activeElement); 
					// do not disable file inputs, or their upload will fail!
					$(':input:not(:disabled)').each(function(){
						var tag = $(this);
						tag.attr('data-disableComponentsOnAjaxCall', 'true')
						tag.prop('disabled', true);
					});
				}, 1); //process after the focus might have traversed
			}
		});
		//execute undisable when ajax call is finished
		Wicket.Event.subscribe('/ajax/call/success', function(attributes,
				jqXHR, settings) {
			// only undisable components when we had an ajax indicator activated for this event
			if (jqXHR.i) {
				undisableComponentsAfterAjaxCall();
			}
		});
		
		//see https://github.com/hubspot/offline
		Offline.on("up", function(e) {
			location.reload();
		});
	}
	//execute undisable on any ondomready event
	undisableComponentsAfterAjaxCall();
}