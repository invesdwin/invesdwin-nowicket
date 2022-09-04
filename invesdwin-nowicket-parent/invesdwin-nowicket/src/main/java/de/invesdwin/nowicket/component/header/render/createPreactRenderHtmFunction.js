import { h, render } from '${PREACT_RESOURCE}';
import htm from '${HTM_RESOURCE}';

const html = htm.bind(h);

window.preactRenderHtm = function preactRenderHtm(htm, markupId) {
	const element = document.getElementById(markupId);
	// reset handlers or else they will be duplicated by wicket: https://stackoverflow.com/a/39026635
	element.outerHTML = element.outerHTML;
	const updatedElement = document.getElementById(markupId);
	//https://stackoverflow.com/questions/29182244/convert-a-string-to-a-template-string
	render(eval('html`'+htm+'`'), updatedElement.parentElement, updatedElement);
}


