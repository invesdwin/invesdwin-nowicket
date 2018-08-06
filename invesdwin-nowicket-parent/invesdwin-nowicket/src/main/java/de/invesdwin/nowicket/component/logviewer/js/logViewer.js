function logViewer_init() {
	if (typeof logViewerRegistered === 'undefined') {
		window.logViewerRegistered = true;

		// data
		window.logViewer_rowCount = 0;
		
		// reset
		window.logViewer_reset = function(){
			$('#logData').empty();
			window.logViewer_rowCount = 0;
		};
		
		window.logViewer_append = function(time, error, message){
			window.logViewer_rowCount++;
			var attributes = "";
			if(window.logViewer_rowCount % 2 == 0){
				attributes += ' class="even"';
			}
			if(error){
				attributes += ' style="color: red"';
			}
			$('#logData').prepend('<tr '+attributes+'><td>'+time+'</td><td>'+message+'</td></tr>').find(':first').effect('highlight', {}, 1000);
		};
		
	}
}
