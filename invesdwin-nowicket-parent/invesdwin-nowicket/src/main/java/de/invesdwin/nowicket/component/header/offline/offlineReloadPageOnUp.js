function offlineReloadPageOnUp() {
	if (typeof offlineReloadPageOnUpRegistered === 'undefined') {
		window.offlineReloadPageOnUpRegistered = true;
		//see https://github.com/hubspot/offline
		Offline.on("up", function(e) {
			location.reload();
		});
	}
}