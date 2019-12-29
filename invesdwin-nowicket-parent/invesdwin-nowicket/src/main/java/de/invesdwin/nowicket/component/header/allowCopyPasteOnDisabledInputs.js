function allowCopyPasteOnDisabledInputs() {
	if (typeof allowCopyPasteOnDisabledInputsRegistered === 'undefined') {
		window.allowCopyPasteOnDisabledInputsRegistered = true;

		function triggerAllowCopyPasteOnDisabledInputs() {

			function replaceWithReadonlyInput(tag, replaceWithTag, value) {
				if (tag.attr("id").indexOf("_allowCopyPasteOnDisabledInputs") > -1) {
					return;
				}

				var fixedTag = getAlreadyFixedTagFor(tag);
				if (fixedTag.length) {
					// update existing fix
					fixedTag.attr("type", tag.attr("type"));
					fixedTag.attr("class", tag.attr("class"));
					fixedTag.attr("value", value);
					fixedTag.attr("title", tag.attr("title"));
					fixedTag.attr("rows", tag.attr("rows"));
					fixedTag.attr("id", tag.attr("id")
							+ "_allowCopyPasteOnDisabledInputs");
					
					tag.attr("style", "display:none");

					return;
				} else {
					// replace tag with readonly input and make tag invisible
					var input = $(replaceWithTag);

					input.attr("data-allowCopyPasteOnDisabledInputs", tag
							.attr("id"));
					input.attr("readonly", "readonly");

					// copy important attributes
					input.attr("type", tag.attr("type"));
					input.attr("class", tag.attr("class"));
					input.attr("value", value);
					input.attr("title", tag.attr("title"));
					input.attr("rows", tag.attr("rows"));
					input.attr("id", tag.attr("id")
							+ "_allowCopyPasteOnDisabledInputs");

					tag.attr("style", "display:none");

					return input;
				}
			}

			function getAlreadyFixedTagFor(tag) {
				return $('[data-allowCopyPasteOnDisabledInputs='
						+ tag.attr("id") + ']')
			}

			$('textarea:disabled').after(function(e) {
				var tag = $(this);
				var value = tag.val();
				var input = replaceWithReadonlyInput(tag, "<textarea>", value);
				return input;
			});
			$('select:disabled').filter(function() {
				var size = $(this).attr("size");
				return !(size > 1);
			}).after(function(e) {
				var tag = $(this);
				var value = tag.find("option:selected").text();
				var input = replaceWithReadonlyInput(tag, "<input>", value);
				return input;
			});
			$(
					'input:disabled:not([type=radio]):not([type=checkbox]):not([type=file])')
					.after(
							function(e) {
								var tag = $(this);
								var value = tag.val();
								var input = replaceWithReadonlyInput(tag,
										"<input>", value);
								return input;
							});

		}

		triggerAllowCopyPasteOnDisabledInputs();
		Wicket.Event.add(window, 'shown.bs.modal', function(e) {
			triggerAllowCopyPasteOnDisabledInputs();
		});
		Wicket.Event.subscribe('/ajax/call/success', function(attributes,
				jqXHR, settings) {
			triggerAllowCopyPasteOnDisabledInputs();
		});
	}
}
