// from https://github.com/jhaygt/bootstrap-multimodal/issues/4#issuecomment-537610995

(function ($, window) {
    var MultiModal = function (element) {
        this.$element = $(element);
        this.modalCount = 0;
    };

    MultiModal.BASE_ZINDEX = 1040;

    MultiModal.prototype.show = function (target) {
        var that = this;
        var $target = $(target);
        if(!($target.is('div') && $target.is('.modal'))){
			return;
		}

        // Bootstrap triggers the show event at the beginning of the show function and before
        // the modal backdrop element has been created. The timeout here allows the modal
        // show function to complete, after which the modal backdrop will have been created
        // and appended to the DOM.
        window.setTimeout(function () {
            that.modalCount = getModalCount() + 1;

            // we only want one backdrop; hide any extras
            if (that.modalCount > 1)
                $('.modal-backdrop').not(':first').addClass('d-none');

            that.adjustModal($target);
            that.adjustBackdrop();
        });
    };

    MultiModal.prototype.hidden = function (target) {
		var that = this;
		var $target = $(target);
        if(!($target.is('div') && $target.is('.modal'))){
			return;
		}
	
        window.setTimeout(function () {
	        that.modalCount = getModalCount();
	
	        if (this.modalCount) {
	            that.adjustBackdrop();
	
	            // bootstrap removes the modal-open class when a modal is closed; add it back
	            $('body').addClass('modal-open');
	        } else {
				that.removeBackdrop();
			}
        });
    };

    function getModalCount () {
        return $('.modal:visible').length;
    }

    MultiModal.prototype.adjustModal = function ($target) {
        const modalIndex = this.modalCount - 1;
        const modalZIndex = MultiModal.BASE_ZINDEX + (modalIndex * 20) + 10;
        $target.css('z-index', modalZIndex);
    };

    MultiModal.prototype.adjustBackdrop = function () {
        const modalIndex = this.modalCount - 1;
        const backdropZIndex = MultiModal.BASE_ZINDEX + (modalIndex * 20);
        $('.modal-backdrop:first').css('z-index', backdropZIndex);
    };
    
    MultiModal.prototype.removeBackdrop = function () {
        $('.modal-backdrop').remove();
    };

    function Plugin(method, target) {
        return this.each(function () {
            var $this = $(this);
            var data = $this.data('multi-modal-plugin');

            if (!data)
                $this.data('multi-modal-plugin', (data = new MultiModal(this)));

            if (method)
                data[method](target);
        });
    }

    $.fn.multiModal = Plugin;
    $.fn.multiModal.Constructor = MultiModal;

    $(document).on('show.bs.modal', function (e) {
        $(document).multiModal('show', e.target);
    });

    $(document).on('hidden.bs.modal', function (e) {
        $(document).multiModal('hidden', e.target);
    });
}(jQuery, window));