$(function() { 
	//https://stackoverflow.com/questions/13119912/disable-bootstraps-collapse-open-close-animation
	$.fn.collapse.Constructor.TRANSITION_DURATION = 0; 
	//$.support.transition = false;
})