function logViewer_init(highlight) {
	if (typeof logViewerRegistered === 'undefined') {
		window.logViewerRegistered = true;

		// data
		window.logViewer_rowCount = 0;
		window.logViewer_supressHighlightCount = 0;
		
		// reset
		window.logViewer_reset = function(){
			$('#logData').empty();
			window.logViewer_rowCount = 0;
			$('#evictedLogData').text('');
		};
		
		window.logViewer_append = function(time, error, message){
			window.logViewer_rowCount++;
			var attributes = "";
			if(error){
				attributes += ' style="color: red"';
			}
			var logData = $('#logData').prepend('<div '+attributes+'><b>'+time+':</b> '+message);
			if(window.logViewer_supressHighlightCount === 0){
				logData.find(':first').effect('highlight', {}, 1000);
			}else{
				window.logViewer_supressHighlightCount--;
			}
		};
		
	}
}
