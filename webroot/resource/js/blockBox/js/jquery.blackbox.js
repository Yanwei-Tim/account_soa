(function () {
    var root = this,
        box_id = 0,
        overlay_list = [],
        load_array = {},
        $W = $(window);
    var BlackBox = function () {
        this.init.apply(this, arguments);
    };
    BlackBox.fn = BlackBox.prototype;
    BlackBox.fn.init = function (config) {
        var default_config = {
            'clickOverlayEffect': 'shake',
            'overlayColor': '#000',
            'overlayOpacity': .6,
            'allowPromptBlank': false,
            'displayClose': false,
            'enableKeyPress': true
        };

        if (config && Object.prototype.toString.call(config) == '[object Object]') {
            var this_config = {};
            jQuery.each(default_config,
                function (item, value) {
                    this_config[item] = config[item] || value;
                });
            this.config = this_config;
        } else {
            this.config = default_config;
        }
        if (this.config.enableKeyPress) _setKeyShort.call(this);
    };
    BlackBox.fn.load = function (item, callback, _delay_appear) {
        if (arguments.length === 0) return;
        callback = callback || $.noop;
        if (!_delay_appear) {
            if (Object.prototype.toString.call(load_array[item]) == '[object Array]') {
                load_array[item].push(callback);
                return;
            }
            load_array[item] = [callback];
            for (var key in load_array) {
                if (key !== item) return;
            }
        } else {
            if ((function () {
                for (var key in load_array) {
                    if (load_array.hasOwnProperty(key)) return false;
                }
                return true;
            })()) {
                _clearOverlay.call(this);
                return;
            }
        }
        if (arguments.length === 1) {
            Array.prototype.push.call(arguments, callback);
        }
        if (!_setOverlay.call(this, 'load', arguments, _delay_appear) && !_delay_appear) return;
        $("#BlackBox").append('<div id="BlackBoxLoad">载入中</div>');
        var $BoxLoad = $("#BlackBoxLoad").fadeTo(0, 0).fadeTo(400, 1),
            resize = function () {
                $BoxLoad.css({
                    left: ($W.width() - $BoxLoad.width()) / 2,
                    top: ($W.height() - $BoxLoad.height()) / 2
                })
            };
        resize.call(this);
        $W.resize(resize);
    };
    BlackBox.fn.ready = function (item) {
        if (arguments.length === 0) return;
        var func_list = load_array[item];
        if (func_list) {
            for (var i = 0; i < func_list.length; i++) {
                var func = func_list[i];
                func.call(this);
            }
        }
        delete load_array[item];
        for (var key in load_array) {
            if (load_array.hasOwnProperty(key)) return;
        }
        if (overlay_list[0].type !== "load")return;
        var _this = this;
        $("#BlackBoxLoad").fadeTo(400, 0, function () {
            $(this).remove();
            _clearOverlay.call(_this);
        });
    };
    BlackBox.fn.popup = function (html, callback,_delay_appear) {
    	var args = _getArgs.apply(this, arguments);
        if (arguments.length === 0) return;
        callback = callback || $.noop;
        if (arguments.length === 1) {
            Array.prototype.push.call(arguments, callback);
        }
        if (!_setOverlay.call(this, 'popup', arguments, _delay_appear) && !_delay_appear)return;
        var $BlackBox = $("#BlackBox");
        $BoxLoad = $("#BlackBoxLoad");
        $BlackBox.append('<div class="normal" id="popup' + _getNowID.call(this) + '">' + html + '</div>');
        _boxWrap.call(this, $("#popup" + _getNowID.call(this)), {});
        _setOverlayAttr.call(this);
        if (this.config.displayClose) {
            _setClose.call(this, args[1]);
        }
        callback.call(this,$BlackBox);
    };
    BlackBox.fn.boxClose = function (callback) {
        var $BlackBoxContent = $("#" + _getNowID.call(this)),
            _this = this;
        if (!$BlackBoxContent[0]) return;
        $BlackBoxContent.fadeOut(400,
            function () {
                $(this).remove();
                _clearOverlay.call(_this, callback);
            });
    };
    var _getArgs = function (text, callback, options, _delay_appear) {
        callback = callback || $.noop;
        if (!$.isFunction(callback)) {
            options = callback;
            callback = $.noop;
        }
        options = options || {};
        if (arguments.length === 1) {
            Array.prototype.push.call(arguments, callback, options);
        }
        if (arguments.length === 2) {
            Array.prototype.push.call(arguments, options);
        }
        return arguments;
    };
    var _boxWrap = function ($target, options) {
        $target.wrap('<div class="BlackBoxContent" id="' + _getNowID.call(this) + '"></div>');
        var $BlackBoxContent = $("#" + _getNowID.call(this)).fadeTo(0, 0).fadeTo(400, 1),
            box_width = $BlackBoxContent.width(),
            box_height = $BlackBoxContent.height(),
            resize = function () {
                $BlackBoxContent.css({
                    left: ($W.width() - box_width) / 2 + 'px',
                    top: ($W.height() - box_height) / 2 + 'px'
                })
            };
        if (options.title) {
            $BlackBoxContent.prepend('<p class="title">' + options.title + '</p>');
        }
        $W.resize(resize);
        resize.call(this);
    };
    var _setKeyShort = function () {
        var _this = this;
        $(document).bind('keydown.fb',
            function (e) {
                var $BlackBoxContent = $(".BlackBoxContent");
                if (!$BlackBoxContent[0])return;
                if (e.keyCode == 13) {
                    $BlackBoxContent.find(".submit").click();
                } else if (e.keyCode == 27) {
                    var button = $BlackBoxContent.find(".close")[0] || $BlackBoxContent.find(".cancel")[0] || $BlackBoxContent.find(".submit")[0];
                    if (button) {
                        $(button).click();
                    } else {
                        _this.boxClose.call(_this);
                    }
                }
            });
    };
    var _setOverlay = function (type, args, delay_appear) {
        box_id += 1;
        if (!delay_appear) {
            overlay_list.push({
                id: box_id,
                type: type,
                args: args
            });
        }
        if (overlay_list.length !== 1) {
            return false;
        }
        if (!$("#BlackBox")[0]) {
            $("body").append('<div id="BlackBox"><div id="BBOverlay"></div></div>');
        }
        var $BBOverlay = $("#BBOverlay"),
            config = this.config;
        if (!delay_appear) {
            $BBOverlay.css({
                background_color: config.overlayColor
            }).fadeTo(0, 0).fadeTo(400, config.overlayOpacity);
        }
        var resize = function () {
            $BBOverlay.width($W.width() + "px").height($W.height() + "px");
        };
        resize.call(this);
        $W.resize(resize);
        return true;
    };
    var _clearOverlay = function (callback, force) {
        if (callback) callback.call(this);
        if (force) {
            overlay_list.length = 0;
        } else {
            overlay_list.shift();
        }
        $("#BBOverlay").unbind("click");
        if (overlay_list.length == 0) {
            var $BlackBox = $("#BlackBox");
            if (!$BlackBox[0]) return;
            $BlackBox.fadeOut(400,
                function () {
                    $(this).remove();
                });
        } else {
            //执行队列中下一个内容
            Array.prototype.push.call(overlay_list[0].args, true);
            this[overlay_list[0].type].apply(this, overlay_list[0].args);
        }
    };
    var _setOverlayAttr = function () {
        var click_effect = this.config.clickOverlayEffect,
            $BlackBoxContent = $("#" + _getNowID.call(this)),
            $BBOverlay = $("#BBOverlay"),
            _this = this;
        if (click_effect === 'close') {
            $BBOverlay.click(function () {
                var button = $BlackBoxContent.find(".close")[0] || $BlackBoxContent.find(".cancel")[0] || $BlackBoxContent.find(".submit")[0];
                if (button) {
                    $(button).click();
                } else {
                    _this.boxClose.call(_this);
                }
            });
            return;
        }
        $BBOverlay.click(function () {
            var $input = $BlackBoxContent.find("input");
            if ($input.length === 1) {
                $input.focus();
            }
            _this.boxShake.call(_this);
        })
    };
    var _setClose = function (onCancel) {
        var $BlackBox = $("#BlackBox"),
            _this = this;
        $BlackBox.append('<div class="close">Close</div>');
        $BlackBox.find(".close").click(function () {
            _this.boxClose.call(_this, onCancel);
        })
    };

    var _getNowID = function () {
        var id = overlay_list[0].id;
        return "_box_" + id;
    };

    root.BlackBox = BlackBox;

}).call(window);