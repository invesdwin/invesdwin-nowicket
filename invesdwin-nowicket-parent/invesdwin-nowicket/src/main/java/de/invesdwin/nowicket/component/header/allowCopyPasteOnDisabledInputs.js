function allowCopyPasteOnDisabledInputs() {

	function replaceWithReadonlyInput(tag, value) {
		var fixedTag = getAlreadyFixedTagFor(tag);
		if (fixedTag.length) {
			// update existing fix
			fixedTag.attr("type", tag.attr("type"));
			fixedTag.attr("class", tag.attr("class"));
			fixedTag.attr("value", value);
			fixedTag.attr("title", tag.attr("title"));
			fixedTag.attr("id", tag.attr("id")
					+ "_allowCopyPasteOnDisabledInputs");

			return;
		} else {
			// replace tag with readonly input and make tag invisible
			tag.attr("style", "display:none");
			var input = $("<input>");

			input.attr("data-allowCopyPasteOnDisabledInputs", tag.attr("id"));
			input.attr("readonly", "readonly");

			// copy important attributes
			input.attr("type", tag.attr("type"));
			input.attr("class", tag.attr("class"));
			input.attr("value", value);
			input.attr("title", tag.attr("title"));
			input
					.attr("id", tag.attr("id")
							+ "_allowCopyPasteOnDisabledInputs");

			return input;
		}
	}

	function getAlreadyFixedTagFor(tag) {
		return $('[data-allowCopyPasteOnDisabledInputs=' + tag.attr("id") + ']')
	}

	$('select:disabled:visible').filter(function() {
		var size = $(this).attr("size");
		return !(size > 1);
	}).after(function(e) {
		var tag = $(this);
		var value = tag.find("option:selected").text();
		var input = replaceWithReadonlyInput(tag, value);
		return input;
	});
	$(
			'input:disabled:not([type=radio]):not([type=checkbox]):not([type=file]):visible')
			.after(function(e) {
				var tag = $(this);
				var value = tag.val();
				var input = replaceWithReadonlyInput(tag, value);
				return input;
			});

}
