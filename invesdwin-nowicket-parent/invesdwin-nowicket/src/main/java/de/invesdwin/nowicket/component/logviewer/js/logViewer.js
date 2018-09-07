function logViewer_init(highlight) {
	if (typeof logViewerRegistered === 'undefined') {
		window.logViewerRegistered = true;

		// data
		window.logViewer_rowCount = 0;
		window.logViewer_toBeAppendedLogs = new Array();
		window.logViewer_background = true;
		
		// reset
		window.logViewer_reset = function(){
			$('#logData').empty();
			window.logViewer_rowCount = 0;
			window.logViewer_toBeAppendedLogs.length = 0;
			window.logViewer_background = true;
		};
		
		window.logViewer_append = function(time, error, message){
			window.logViewer_rowCount++;
			window.logViewer_toBeAppendedLogs.push({time: time, error: error, message: message});
		};
		
		window.logViewer_update = function(){
			var length = window.logViewer_toBeAppendedLogs.length;
			var supressHighlightCount = 0;
			if(length > 20){
			 	supressHighlightCount = length - 20;
			}
			for (var i = 0; i < length; i++) {
				var log = window.logViewer_toBeAppendedLogs[0];
				var time = log.time;
				var error = log.error;
				var message = log.message;
				
				var attributes = "";
				if(error){
					attributes += 'color: red';
				}
				window.logViewer_background = !window.logViewer_background;
				if(window.logViewer_background){
					if (attributes.length != 0) {
						attributes = attributes+"; ";
					}
					attributes = attributes + "background-color: #F2F2F2";
				}
				
				if (attributes.length != 0) {
					attributes = ' style="'+attributes+'"';
				}
				
				var logData = $('#logData').prepend('<div '+attributes+'><b>'+time+':</b> '+message);
				if(supressHighlightCount === 0){
					logData.find(':first').effect('highlight', {}, 1000);
				}else{
					supressHighlightCount--;
				}
				window.logViewer_toBeAppendedLogs.shift();
				if(i >= 100){
					setTimeout(function(){ window.logViewer_update(); }, 1);
					break;
				}
			}
		}
		
	}
}
