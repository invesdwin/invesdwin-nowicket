if (Wicket.Ajax.original_ajax == null) {
	Wicket.Ajax.original_ajax = Wicket.Ajax.ajax;

	Wicket.Ajax.ajax = function(attrs) {
		if (attrs.c) {
			const element = document.getElementById(attrs.c);
			if (element) {
				const attrsStr = JSON.stringify(attrs);
				element.wicketAjaxUrls = element.wicketAjaxUrls || [];
				if (!element.wicketAjaxUrls.includes(attrsStr)) {
					element.wicketAjaxUrls.push(attrsStr);
					Wicket.Ajax.original_ajax(attrs);
				}
			}
		} else {
			// ajax timer
			Wicket.Ajax.original_ajax(attrs);
		}
	}
}
