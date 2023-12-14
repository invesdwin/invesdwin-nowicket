function createPreactRenderHtmlFunction() {
	if (typeof preactRenderHtml === 'undefined') {
		const { h, render } = preact;

		//https://dirask.com/posts/Preact-convert-HTML-to-Preact-nodes-VNode-s-pJ3Boj
		const parseHtml = function(html) {
			const hRoot = document.createElement('div');
			hRoot.innerHTML = html;

			return hRoot.childNodes;
		};

		const createType = function(tag) {
			return tag.toLowerCase();
		};

		const createProperties = function(attributes) {
			const properties = {};
			for (let i = 0; i < attributes.length; ++i) {
				const attribute = attributes[i];
				properties[attribute.name] = attribute.value;
			}
			return properties;
		};

		const convertElement = function(hElement) {
			const type = createType(hElement.tagName);
			const properties = createProperties(hElement.attributes);
			const children = convertNodes(hElement.childNodes);
			return h(type, properties, children);
		};

		const convertText = function(hText) {
			return hText.data;
		};

		const convertNode = function(hNode) {
			if (hNode.nodeType === 1) { // Node.ELEMENT_NODE
				return convertElement(hNode);
			}
			if (hNode.nodeType === 3) { // Node.TEXT_NODE
				return convertText(hNode);
			}
			return null;
		};

		const convertNodes = function(hNodes) {
			if (hNodes.length > 0) {
				const nodes = [];
				for (let i = 0; i < hNodes.length; ++i) {
					const hNode = convertNode(hNodes[i]);
					if (hNode == null) {
						continue;
					}
					nodes.push(hNode);
				}
				return nodes;
			}
			return null; // we want to get self-closed elements in rendering to string on the server when elements are ampty (it means: `render` function returns `<br></br>` instread of `<br />` when empty array is returned)
		};

		/**
		 * Converts HTML to preact nodes.
		 *
		 * @param html input html
		 * @returns preact element created from input HTML or null
		 */
		const convertHtml = function(html) {
			if (html) {
				const hChildren = parseHtml(html);
				return convertNodes(hChildren);
			}
			return null;
		};

		window.preactRenderHtml = function preactRenderHtml(html, markupId) {
			const element = document.getElementById(markupId);
			render(convertHtml(html), element.parentElement);
		}
	}

}