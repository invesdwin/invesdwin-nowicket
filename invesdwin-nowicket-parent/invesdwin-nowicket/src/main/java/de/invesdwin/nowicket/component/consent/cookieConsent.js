// adapted from here: https://github.com/sfreytag/bootstrap-cookie-consent

// Creare's 'Implied Consent' EU Cookie Law Banner v:2.4
// Conceived by Robert Kent, James Bavington & Tom Foyster
// Modified by Simon Freytag for syntax, namespace, jQuery and Bootstrap


C = {
    // Number of days before the cookie expires, and the banner reappears
    cookieDuration : "[COOKIE_DURATION]",

    // Name of our cookie
    cookieName: '[COOKIE_NAME]',

    // Value of cookie
    cookieValue: '[COOKIE_VALUE]',
    
    // Message banner title
    bannerTitle: "[BANNER_TITLE]",

    // Message banner message
    bannerMessage: "[BANNER_MESSAGE]",

    // Message banner dismiss button
    bannerButton: "[BANNER_BUTTON_TEXT]",

    // Link to your cookie policy.
    bannerLinkURL: "[BANNER_LINK_URL]",

    // Link text
    bannerLinkText: "[BANNER_LINK_TEXT]",

    // Button css class
    buttonClass: "[BUTTON_CLASS]", 
    
    // Alert css class
    alertClass: "[ALERT_CLASS]",
    
    // Alert position
    position: "[POSITION]",

    createDiv: function () {
        var banner = $(
            '<div class="alert ' + this.alertClass + ' alert-dismissible fade in" ' +
            'role="alert" style="position: fixed; ' + this.position + ': 0; width: 100%; ' +
            'margin-bottom: 0"><strong>' + this.bannerTitle + '</strong> ' +
            this.bannerMessage + '&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="' + this.bannerLinkURL + '">' +
            this.bannerLinkText + '</a>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn ' +
             this.buttonClass + '" onclick="C.createCookie(C.cookieName, C.cookieValue' +
            ', C.cookieDuration)" data-dismiss="alert" aria-label="Close">' +
            this.bannerButton + '</button></div>'
        )
        $("body").append(banner)
    },

    createCookie: function(name, value, days) {
        //console.log("Create cookie")
        var expires = ""
        if (days) {
            var date = new Date()
            date.setTime(date.getTime() + (days*24*60*60*1000))
            expires = "; expires=" + date.toGMTString()
        }
        document.cookie = name + "=" + value + expires + "; path=/";
    },

    checkCookie: function(name) {
        var nameEQ = name + "="
        var ca = document.cookie.split(';')
        for(var i = 0; i < ca.length; i++) {
            var c = ca[i]
            while (c.charAt(0)==' ')
                c = c.substring(1, c.length)
            if (c.indexOf(nameEQ) == 0) 
                return c.substring(nameEQ.length, c.length)
        }
        return null
    },

    init: function() {
        if (this.checkCookie(this.cookieName) != this.cookieValue)
            this.createDiv()
    }
}

$(document).ready(function() {
    C.init()
})
