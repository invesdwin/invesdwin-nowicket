import { h, render } from '${PREACT_RESOURCE}';
import htm from '${HTM_RESOURCE}';

const html = htm.bind(h);

window.preactRenderHtm = function preactRenderHtm(htm, markupId) {
	const element = document.getElementById(markupId);
	//https://stackoverflow.com/questions/29182244/convert-a-string-to-a-template-string
	render(eval('html`'+htm+'`'), element.parentElement, element);
}


