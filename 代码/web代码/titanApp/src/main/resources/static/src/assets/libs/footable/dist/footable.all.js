/*!
 * FooTable - Awesome Responsive Tables
 * Version : 2.0.3
 * http://fooplugins.com/plugins/footable-jquery/
 *
 * Requires jQuery - http://jquery.com/
 *
 * Copyright 2014 Steven Usher & Brad Vincent
 * Released under the MIT license
 * You are free to use FooTable in commercial projects as long as this copyright header is left intact.
 *
 * Date: 11 Nov 2014
 */
(function ($, w, undefined) {
    w.footable = {
        options: {
            delay: 100, // The number of millseconds to wait before triggering the react event
            breakpoints: { // The different screen resolution breakpoints
                phone: 480,
                tablet: 1024
            },
            parsers: {  // The default parser to parse the value out of a cell (values are used in building up row detail)
                alpha: function (cell) {
                    return $(cell).data('value') || $.trim($(cell).text());
                },
                numeric: function (cell) {
                    var val = $(cell).data('value') || $(cell).text().replace(/[^0-9.\-]/g, '');
                    val = parseFloat(val);
                    if (isNaN(val)) val = 0;
                    return val;
                }
            },
            addRowToggle: true,
            calculateWidthOverride: null,
            toggleSelector: ' > tbody > tr:not(.footable-row-detail)', //the selector to show/hide the detail row
            columnDataSelector: '> thead > tr:last-child > th, > thead > tr:last-child > td', //the selector used to find the column data in the thead
            detailSeparator: ':', //the separator character used when building up the detail row
            toggleHTMLElement: '<span />', // override this if you want to insert a click target rather than use a background image.
            createGroupedDetail: function (data) {
                var groups = { '_none': { 'name': null, 'data': [] } };
                for (var i = 0; i < data.length; i++) {
                    var groupid = data[i].group;
                    if (groupid !== null) {
                        if (!(groupid in groups))
                            groups[groupid] = { 'name': data[i].groupName || data[i].group, 'data': [] };

                        groups[groupid].data.push(data[i]);
                    } else {
                        groups._none.data.push(data[i]);
                    }
                }
                return groups;
            },
            createDetail: function (element, data, createGroupedDetail, separatorChar, classes) {
                /// <summary>This function is used by FooTable to generate the detail view seen when expanding a collapsed row.</summary>
                /// <param name="element">This is the div that contains all the detail row information, anything could be added to it.</param>
                /// <param name="data">
                ///  This is an array of objects containing the cell information for the current row.
                ///  These objects look like the below:
                ///    obj = {
                ///      'name': String, // The name of the column
                ///      'value': Object, // The value parsed from the cell using the parsers. This could be a string, a number or whatever the parser outputs.
                ///      'display': String, // This is the actual HTML from the cell, so if you have images etc you want moved this is the one to use and is the default value used.
                ///      'group': String, // This is the identifier used in the data-group attribute of the column.
                ///      'groupName': String // This is the actual name of the group the column belongs to.
                ///    }
                /// </param>
                /// <param name="createGroupedDetail">The grouping function to group the data</param>
                /// <param name="separatorChar">The separator charactor used</param>
                /// <param name="classes">The array of class names used to build up the detail row</param>

                var groups = createGroupedDetail(data);
                for (var group in groups) {
                    if (groups[group].data.length === 0) continue;
                    if (group !== '_none') element.append('<div class="' + classes.detailInnerGroup + '">' + groups[group].name + '</div>');

                    for (var j = 0; j < groups[group].data.length; j++) {
                        var separator = (groups[group].data[j].name) ? separatorChar : '';
                        element.append($('<div></div>').addClass(classes.detailInnerRow).append($('<div></div>').addClass(classes.detailInnerName)
                                .append(groups[group].data[j].name + separator)).append($('<div></div>').addClass(classes.detailInnerValue)
                                .attr('data-bind-value', groups[group].data[j].bindName).append(groups[group].data[j].display)));
                    }
                }
            },
            classes: {
                main: 'footable',
                loading: 'footable-loading',
                loaded: 'footable-loaded',
                toggle: 'footable-toggle',
                disabled: 'footable-disabled',
                detail: 'footable-row-detail',
                detailCell: 'footable-row-detail-cell',
                detailInner: 'footable-row-detail-inner',
                detailInnerRow: 'footable-row-detail-row',
                detailInnerGroup: 'footable-row-detail-group',
                detailInnerName: 'footable-row-detail-name',
                detailInnerValue: 'footable-row-detail-value',
                detailShow: 'footable-detail-show'
            },
            triggers: {
                initialize: 'footable_initialize',                      //trigger this event to force FooTable to reinitialize
                resize: 'footable_resize',                              //trigger this event to force FooTable to resize
                redraw: 'footable_redraw',                              //trigger this event to force FooTable to redraw
                toggleRow: 'footable_toggle_row',                       //trigger this event to force FooTable to toggle a row
                expandFirstRow: 'footable_expand_first_row',            //trigger this event to force FooTable to expand the first row
                expandAll: 'footable_expand_all',                       //trigger this event to force FooTable to expand all rows
                collapseAll: 'footable_collapse_all'                    //trigger this event to force FooTable to collapse all rows
            },
            events: {
                alreadyInitialized: 'footable_already_initialized',     //fires when the FooTable has already been initialized
                initializing: 'footable_initializing',                  //fires before FooTable starts initializing
                initialized: 'footable_initialized',                    //fires after FooTable has finished initializing
                resizing: 'footable_resizing',                          //fires before FooTable resizes
                resized: 'footable_resized',                            //fires after FooTable has resized
                redrawn: 'footable_redrawn',                            //fires after FooTable has redrawn
                breakpoint: 'footable_breakpoint',                      //fires inside the resize function, when a breakpoint is hit
                columnData: 'footable_column_data',                     //fires when setting up column data. Plugins should use this event to capture their own info about a column
                rowDetailUpdating: 'footable_row_detail_updating',      //fires before a detail row is updated
                rowDetailUpdated: 'footable_row_detail_updated',        //fires when a detail row is being updated
                rowCollapsed: 'footable_row_collapsed',                 //fires when a row is collapsed
                rowExpanded: 'footable_row_expanded',                   //fires when a row is expanded
                rowRemoved: 'footable_row_removed',                     //fires when a row is removed
                reset: 'footable_reset'                                 //fires when FooTable is reset
            },
            debug: false, // Whether or not to log information to the console.
            log: null
        },

        version: {
            major: 0, minor: 5,
            toString: function () {
                return w.footable.version.major + '.' + w.footable.version.minor;
            },
            parse: function (str) {
                var version = /(\d+)\.?(\d+)?\.?(\d+)?/.exec(str);
                return {
                    major: parseInt(version[1], 10) || 0,
                    minor: parseInt(version[2], 10) || 0,
                    patch: parseInt(version[3], 10) || 0
                };
            }
        },

        plugins: {
            _validate: function (plugin) {
                ///<summary>Simple validation of the <paramref name="plugin"/> to make sure any members called by FooTable actually exist.</summary>
                ///<param name="plugin">The object defining the plugin, this should implement a string property called "name" and a function called "init".</param>

                if (!$.isFunction(plugin)) {
                  if (w.footable.options.debug === true) console.error('Validation failed, expected type "function", received type "{0}".', typeof plugin);
                  return false;
                }
                var p = new plugin();
                if (typeof p['name'] !== 'string') {
                    if (w.footable.options.debug === true) console.error('Validation failed, plugin does not implement a string property called "name".', p);
                    return false;
                }
                if (!$.isFunction(p['init'])) {
                    if (w.footable.options.debug === true) console.error('Validation failed, plugin "' + p['name'] + '" does not implement a function called "init".', p);
                    return false;
                }
                if (w.footable.options.debug === true) console.log('Validation succeeded for plugin "' + p['name'] + '".', p);
                return true;
            },
            registered: [], // An array containing all registered plugins.
            register: function (plugin, options) {
                ///<summary>Registers a <paramref name="plugin"/> and its default <paramref name="options"/> with FooTable.</summary>
                ///<param name="plugin">The plugin that should implement a string property called "name" and a function called "init".</param>
                ///<param name="options">The default options to merge with the FooTable's base options.</param>

                if (w.footable.plugins._validate(plugin)) {
                    w.footable.plugins.registered.push(plugin);
                    if (typeof options === 'object') $.extend(true, w.footable.options, options);
                }
            },
            load: function(instance){
              var loaded = [], registered, i;
              for(i = 0; i < w.footable.plugins.registered.length; i++){
                try {
                  registered = w.footable.plugins.registered[i];
                  loaded.push(new registered(instance));
                } catch (err) {
                  if (w.footable.options.debug === true) console.error(err);
                }
              }
              return loaded;
            },
            init: function (instance) {
                ///<summary>Loops through all registered plugins and calls the "init" method supplying the current <paramref name="instance"/> of the FooTable as the first parameter.</summary>
                ///<param name="instance">The current instance of the FooTable that the plugin is being initialized for.</param>

                for (var i = 0; i < instance.plugins.length; i++) {
                    try {
                      instance.plugins[i]['init'](instance);
                    } catch (err) {
                        if (w.footable.options.debug === true) console.error(err);
                    }
                }
            }
        }
    };

    var instanceCount = 0;

    $.fn.footable = function (options) {
        ///<summary>The main constructor call to initialize the plugin using the supplied <paramref name="options"/>.</summary>
        ///<param name="options">
        ///<para>A JSON object containing user defined options for the plugin to use. Any options not supplied will have a default value assigned.</para>
        ///<para>Check the documentation or the default options object above for more information on available options.</para>
        ///</param>

        options = options || {};
        if (options.breakpoints) {  // clear the default breakpoints if the user specifies one
          w.footable.options.breakpoints = {};
        }
        var o = $.extend(true, {}, w.footable.options, options); //merge user and default options
        return this.each(function () {
            instanceCount++;
            var footable = new Footable(this, o, instanceCount);
            $(this).data('footable', footable);
        });
    };

    //helper for using timeouts
    function Timer() {
        ///<summary>Simple timer object created around a timeout.</summary>
        var t = this;
        t.id = null;
        t.busy = false;
        t.start = function (code, milliseconds) {
            ///<summary>Starts the timer and waits the specified amount of <paramref name="milliseconds"/> before executing the supplied <paramref name="code"/>.</summary>
            ///<param name="code">The code to execute once the timer runs out.</param>
            ///<param name="milliseconds">The time in milliseconds to wait before executing the supplied <paramref name="code"/>.</param>

            if (t.busy) {
                return;
            }
            t.stop();
            t.id = setTimeout(function () {
                code();
                t.id = null;
                t.busy = false;
            }, milliseconds);
            t.busy = true;
        };
        t.stop = function () {
            ///<summary>Stops the timer if its runnning and resets it back to its starting state.</summary>

            if (t.id !== null) {
                clearTimeout(t.id);
                t.id = null;
                t.busy = false;
            }
        };
    }

    function Footable(t, o, id) {
        ///<summary>Inits a new instance of the plugin.</summary>
        ///<param name="t">The main table element to apply this plugin to.</param>
        ///<param name="o">The options supplied to the plugin. Check the defaults object to see all available options.</param>
        ///<param name="id">The id to assign to this instance of the plugin.</param>

        var ft = this;
        ft.id = id;
        ft.table = t;
        ft.options = o;
        ft.breakpoints = [];
        ft.breakpointNames = '';
        ft.columns = {};
        ft.plugins = w.footable.plugins.load(ft);

        var opt = ft.options,
            cls = opt.classes,
            evt = opt.events,
            trg = opt.triggers,
            indexOffset = 0;

        // This object simply houses all the timers used in the FooTable.
        ft.timers = {
            resize: new Timer(),
            register: function (name) {
                ft.timers[name] = new Timer();
                return ft.timers[name];
            }
        };

        ft.init = function () {
            var $window = $(w), $table = $(ft.table);

            w.footable.plugins.init(ft);

            if ($table.hasClass(cls.loaded)) {
                //already loaded FooTable for the table, so don't init again
                ft.raise(evt.alreadyInitialized);
                return;
            }

            //raise the initializing event
            ft.raise(evt.initializing);

            $table.addClass(cls.loading);

            // Get the column data once for the life time of the plugin
            $table.find(opt.columnDataSelector).each(function () {
                var data = ft.getColumnData(this);
                ft.columns[data.index] = data;
            });

            // Create a nice friendly array to work with out of the breakpoints object.
            for (var name in opt.breakpoints) {
                ft.breakpoints.push({ 'name': name, 'width': opt.breakpoints[name] });
                ft.breakpointNames += (name + ' ');
            }

            // Sort the breakpoints so the smallest is checked first
            ft.breakpoints.sort(function (a, b) {
                return a['width'] - b['width'];
            });

            $table
                .unbind(trg.initialize)
                //bind to FooTable initialize trigger
                .bind(trg.initialize, function () {
                    //remove previous "state" (to "force" a resize)
                    $table.removeData('footable_info');
                    $table.data('breakpoint', '');

                    //trigger the FooTable resize
                    $table.trigger(trg.resize);

                    //remove the loading class
                    $table.removeClass(cls.loading);

                    //add the FooTable and loaded class
                    $table.addClass(cls.loaded).addClass(cls.main);

                    //raise the initialized event
                    ft.raise(evt.initialized);
                })
                .unbind(trg.redraw)
                //bind to FooTable redraw trigger
                .bind(trg.redraw, function () {
                    ft.redraw();
                })
                .unbind(trg.resize)
                //bind to FooTable resize trigger
                .bind(trg.resize, function () {
                    ft.resize();
                })
                .unbind(trg.expandFirstRow)
                //bind to FooTable expandFirstRow trigger
                .bind(trg.expandFirstRow, function () {
                    $table.find(opt.toggleSelector).first().not('.' + cls.detailShow).trigger(trg.toggleRow);
                })
                .unbind(trg.expandAll)
                //bind to FooTable expandFirstRow trigger
                .bind(trg.expandAll, function () {
                    $table.find(opt.toggleSelector).not('.' + cls.detailShow).trigger(trg.toggleRow);
                })
                .unbind(trg.collapseAll)
                //bind to FooTable expandFirstRow trigger
                .bind(trg.collapseAll, function () {
                    $table.find('.' + cls.detailShow).trigger(trg.toggleRow);
                });

            //trigger a FooTable initialize
            $table.trigger(trg.initialize);

            //bind to window resize
            $window
                .bind('resize.footable', function () {
                    ft.timers.resize.stop();
                    ft.timers.resize.start(function () {
                        ft.raise(trg.resize);
                    }, opt.delay);
                });
        };

        ft.addRowToggle = function () {
            if (!opt.addRowToggle) return;

            var $table = $(ft.table),
                hasToggleColumn = false;

            //first remove all toggle spans
            $table.find('span.' + cls.toggle).remove();

            for (var c in ft.columns) {
                var col = ft.columns[c];
                if (col.toggle) {
                    hasToggleColumn = true;
                    var selector = '> tbody > tr:not(.' + cls.detail + ',.' + cls.disabled + ') > td:nth-child(' + (parseInt(col.index, 10) + 1) + '),' +
                                            '> tbody > tr:not(.' + cls.detail + ',.' + cls.disabled + ') > th:nth-child(' + (parseInt(col.index, 10) + 1) + ')';
                    $table.find(selector).not('.' + cls.detailCell).prepend($(opt.toggleHTMLElement).addClass(cls.toggle));
                    return;
                }
            }
            //check if we have an toggle column. If not then add it to the first column just to be safe
            if (!hasToggleColumn) {
                $table
                    .find('> tbody > tr:not(.' + cls.detail + ',.' + cls.disabled + ') > td:first-child')
                                        .add('> tbody > tr:not(.' + cls.detail + ',.' + cls.disabled + ') > th:first-child')
                    .not('.' + cls.detailCell)
                    .prepend($(opt.toggleHTMLElement).addClass(cls.toggle));
            }
        };

        ft.setColumnClasses = function () {
            var $table = $(ft.table);
            for (var c in ft.columns) {
                var col = ft.columns[c];
                if (col.className !== null) {
                    var selector = '', first = true;
                    $.each(col.matches, function (m, match) { //support for colspans
                        if (!first) selector += ', ';
                        selector += '> tbody > tr:not(.' + cls.detail + ') > td:nth-child(' + (parseInt(match, 10) + 1) + ')';
                        first = false;
                    });
                    //add the className to the cells specified by data-class="blah"
                    $table.find(selector).not('.' + cls.detailCell).addClass(col.className);
                }
            }
        };

        //moved this out into it's own function so that it can be called from other add-ons
        ft.bindToggleSelectors = function () {
            var $table = $(ft.table);

            if (!ft.hasAnyBreakpointColumn()) return;

            $table.find(opt.toggleSelector).unbind(trg.toggleRow).bind(trg.toggleRow, function (e) {
                var $row = $(this).is('tr') ? $(this) : $(this).parents('tr:first');
                ft.toggleDetail($row);
            });

            $table.find(opt.toggleSelector).unbind('click.footable').bind('click.footable', function (e) {
                if ($table.is('.breakpoint') && $(e.target).is('td,th,.'+ cls.toggle)) {
                    $(this).trigger(trg.toggleRow);
                }
            });
        };

        ft.parse = function (cell, column) {
            var parser = opt.parsers[column.type] || opt.parsers.alpha;
            return parser(cell);
        };

        ft.getColumnData = function (th) {
            var $th = $(th), hide = $th.data('hide'), index = $th.index();
            hide = hide || '';
            hide = jQuery.map(hide.split(','), function (a) {
                return jQuery.trim(a);
            });
            var data = {
                'index': index,
                'hide': { },
                'type': $th.data('type') || 'alpha',
                'name': $th.data('name') || $.trim($th.text()),
                'ignore': $th.data('ignore') || false,
                'toggle': $th.data('toggle') || false,
                'className': $th.data('class') || null,
                'matches': [],
                'names': { },
                'group': $th.data('group') || null,
                'groupName': null,
                'isEditable': $th.data('editable')
            };

            if (data.group !== null) {
                var $group = $(ft.table).find('> thead > tr.footable-group-row > th[data-group="' + data.group + '"], > thead > tr.footable-group-row > td[data-group="' + data.group + '"]').first();
                data.groupName = ft.parse($group, { 'type': 'alpha' });
            }

            var pcolspan = parseInt($th.prev().attr('colspan') || 0, 10);
            indexOffset += pcolspan > 1 ? pcolspan - 1 : 0;
            var colspan = parseInt($th.attr('colspan') || 0, 10), curindex = data.index + indexOffset;
            if (colspan > 1) {
                var names = $th.data('names');
                names = names || '';
                names = names.split(',');
                for (var i = 0; i < colspan; i++) {
                    data.matches.push(i + curindex);
                    if (i < names.length) data.names[i + curindex] = names[i];
                }
            } else {
                data.matches.push(curindex);
            }

            data.hide['default'] = ($th.data('hide') === "all") || ($.inArray('default', hide) >= 0);

            var hasBreakpoint = false;
            for (var name in opt.breakpoints) {
                data.hide[name] = ($th.data('hide') === "all") || ($.inArray(name, hide) >= 0);
                hasBreakpoint = hasBreakpoint || data.hide[name];
            }
            data.hasBreakpoint = hasBreakpoint;
            var e = ft.raise(evt.columnData, { 'column': { 'data': data, 'th': th } });
            return e.column.data;
        };

        ft.getViewportWidth = function () {
            return window.innerWidth || (document.body ? document.body.offsetWidth : 0);
        };

        ft.calculateWidth = function ($table, info) {
            if (jQuery.isFunction(opt.calculateWidthOverride)) {
                return opt.calculateWidthOverride($table, info);
            }
            if (info.viewportWidth < info.width) info.width = info.viewportWidth;
            if (info.parentWidth < info.width) info.width = info.parentWidth;
            return info;
        };

        ft.hasBreakpointColumn = function (breakpoint) {
            for (var c in ft.columns) {
                if (ft.columns[c].hide[breakpoint]) {
                    if (ft.columns[c].ignore) {
                        continue;
                    }
                    return true;
                }
            }
            return false;
        };

        ft.hasAnyBreakpointColumn = function () {
            for (var c in ft.columns) {
                if (ft.columns[c].hasBreakpoint) {
                    return true;
                }
            }
            return false;
        };

        ft.resize = function () {
            var $table = $(ft.table);

            if (!$table.is(':visible')) {
                return;
            } //we only care about FooTables that are visible

            if (!ft.hasAnyBreakpointColumn()) {
				$table.trigger(trg.redraw);
				return;
            } //we only care about FooTables that have breakpoints

            var info = {
                'width': $table.width(),                  //the table width
                'viewportWidth': ft.getViewportWidth(),   //the width of the viewport
                'parentWidth': $table.parent().width()    //the width of the parent
            };

            info = ft.calculateWidth($table, info);

            var pinfo = $table.data('footable_info');
            $table.data('footable_info', info);
            ft.raise(evt.resizing, { 'old': pinfo, 'info': info });

            // This (if) statement is here purely to make sure events aren't raised twice as mobile safari seems to do
            if (!pinfo || (pinfo && pinfo.width && pinfo.width !== info.width)) {

                var current = null, breakpoint;
                for (var i = 0; i < ft.breakpoints.length; i++) {
                    breakpoint = ft.breakpoints[i];
                    if (breakpoint && breakpoint.width && info.width <= breakpoint.width) {
                        current = breakpoint;
                        break;
                    }
                }

                var breakpointName = (current === null ? 'default' : current['name']),
                    hasBreakpointFired = ft.hasBreakpointColumn(breakpointName),
                    previousBreakpoint = $table.data('breakpoint');

                $table
                    .data('breakpoint', breakpointName)
                    .removeClass('default breakpoint').removeClass(ft.breakpointNames)
                    .addClass(breakpointName + (hasBreakpointFired ? ' breakpoint' : ''));

                //only do something if the breakpoint has changed
                if (breakpointName !== previousBreakpoint) {
                    //trigger a redraw
                    $table.trigger(trg.redraw);
                    //raise a breakpoint event
                    ft.raise(evt.breakpoint, { 'breakpoint': breakpointName, 'info': info });
                }
            }

            ft.raise(evt.resized, { 'old': pinfo, 'info': info });
        };

        ft.redraw = function () {
            //add the toggler to each row
            ft.addRowToggle();

            //bind the toggle selector click events
            ft.bindToggleSelectors();

            //set any cell classes defined for the columns
            ft.setColumnClasses();

            var $table = $(ft.table),
                breakpointName = $table.data('breakpoint'),
                hasBreakpointFired = ft.hasBreakpointColumn(breakpointName);

            $table
                .find('> tbody > tr:not(.' + cls.detail + ')').data('detail_created', false).end()
                .find('> thead > tr:last-child > th')
                .each(function () {
                    var data = ft.columns[$(this).index()], selector = '', first = true;
                    $.each(data.matches, function (m, match) {
                        if (!first) {
                            selector += ', ';
                        }
                        var count = match + 1;
                        selector += '> tbody > tr:not(.' + cls.detail + ') > td:nth-child(' + count + ')';
                        selector += ', > tfoot > tr:not(.' + cls.detail + ') > td:nth-child(' + count + ')';
                        selector += ', > colgroup > col:nth-child(' + count + ')';
                        first = false;
                    });

                    selector += ', > thead > tr[data-group-row="true"] > th[data-group="' + data.group + '"]';
                    var $column = $table.find(selector).add(this);
                    if (breakpointName !== '') {
                      if (data.hide[breakpointName] === false) $column.addClass('footable-visible').show();
                      else $column.removeClass('footable-visible').hide();
                    }

                    if ($table.find('> thead > tr.footable-group-row').length === 1) {
                        var $groupcols = $table.find('> thead > tr:last-child > th[data-group="' + data.group + '"]:visible, > thead > tr:last-child > th[data-group="' + data.group + '"]:visible'),
                            $group = $table.find('> thead > tr.footable-group-row > th[data-group="' + data.group + '"], > thead > tr.footable-group-row > td[data-group="' + data.group + '"]'),
                            groupspan = 0;

                        $.each($groupcols, function () {
                            groupspan += parseInt($(this).attr('colspan') || 1, 10);
                        });

                        if (groupspan > 0) $group.attr('colspan', groupspan).show();
                        else $group.hide();
                    }
                })
                .end()
                .find('> tbody > tr.' + cls.detailShow).each(function () {
                    ft.createOrUpdateDetailRow(this);
                });

            $table.find("[data-bind-name]").each(function () {
                ft.toggleInput(this);
            });

            $table.find('> tbody > tr.' + cls.detailShow + ':visible').each(function () {
                var $next = $(this).next();
                if ($next.hasClass(cls.detail)) {
                    if (!hasBreakpointFired) $next.hide();
                    else $next.show();
                }
            });

            // adding .footable-first-column and .footable-last-column to the first and last th and td of each row in order to allow
            // for styling if the first or last column is hidden (which won't work using :first-child or :last-child)
            $table.find('> thead > tr > th.footable-last-column, > tbody > tr > td.footable-last-column').removeClass('footable-last-column');
            $table.find('> thead > tr > th.footable-first-column, > tbody > tr > td.footable-first-column').removeClass('footable-first-column');
            $table.find('> thead > tr, > tbody > tr')
                .find('> th.footable-visible:last, > td.footable-visible:last')
                .addClass('footable-last-column')
                .end()
                .find('> th.footable-visible:first, > td.footable-visible:first')
                .addClass('footable-first-column');

            ft.raise(evt.redrawn);
        };

        ft.toggleDetail = function (row) {
            var $row = (row.jquery) ? row : $(row),
                $next = $row.next();

            //check if the row is already expanded
            if ($row.hasClass(cls.detailShow)) {
                $row.removeClass(cls.detailShow);

                //only hide the next row if it's a detail row
                if ($next.hasClass(cls.detail)) $next.hide();

                ft.raise(evt.rowCollapsed, { 'row': $row[0] });

            } else {
                ft.createOrUpdateDetailRow($row[0]);
                $row.addClass(cls.detailShow)
                    .next().show();

                ft.raise(evt.rowExpanded, { 'row': $row[0] });
            }
        };

        ft.removeRow = function (row) {
            var $row = (row.jquery) ? row : $(row);
            if ($row.hasClass(cls.detail)) {
                $row = $row.prev();
            }
            var $next = $row.next();
            if ($row.data('detail_created') === true) {
                //remove the detail row
                $next.remove();
            }
            $row.remove();

            //raise event
            ft.raise(evt.rowRemoved);
        };

        ft.appendRow = function (row) {
            var $row = (row.jquery) ? row : $(row);
            $(ft.table).find('tbody').append($row);

            //redraw the table
            ft.redraw();
        };

        ft.getColumnFromTdIndex = function (index) {
            /// <summary>Returns the correct column data for the supplied index taking into account colspans.</summary>
            /// <param name="index">The index to retrieve the column data for.</param>
            /// <returns type="json">A JSON object containing the column data for the supplied index.</returns>
            var result = null;
            for (var column in ft.columns) {
                if ($.inArray(index, ft.columns[column].matches) >= 0) {
                    result = ft.columns[column];
                    break;
                }
            }
            return result;
        };

        ft.createOrUpdateDetailRow = function (actualRow) {
            var $row = $(actualRow), $next = $row.next(), $detail, values = [];
            if ($row.data('detail_created') === true) return true;

            if ($row.is(':hidden')) return false; //if the row is hidden for some reason (perhaps filtered) then get out of here
            ft.raise(evt.rowDetailUpdating, { 'row': $row, 'detail': $next });
            $row.find('> td:hidden').each(function () {
                var index = $(this).index(), column = ft.getColumnFromTdIndex(index), name = column.name;
                if (column.ignore === true) return true;

                if (index in column.names) name = column.names[index];

                var bindName = $(this).attr("data-bind-name");
                if (bindName != null && $(this).is(':empty')) {
                    var bindValue = $('.' + cls.detailInnerValue + '[' + 'data-bind-value="' + bindName + '"]');
                    $(this).html($(bindValue).contents().detach());
                }
                var display;
                if (column.isEditable !== false && (column.isEditable || $(this).find(":input").length > 0)) {
                    if(bindName == null) {
                        bindName = "bind-" + $.now() + "-" + index;
                        $(this).attr("data-bind-name", bindName);
                    }
                    display = $(this).contents().detach();
                }
                if (!display) display = $(this).contents().clone(true, true);
                values.push({ 'name': name, 'value': ft.parse(this, column), 'display': display, 'group': column.group, 'groupName': column.groupName, 'bindName': bindName });
                return true;
            });
            if (values.length === 0) return false; //return if we don't have any data to show
            var colspan = $row.find('> td:visible').length;
            var exists = $next.hasClass(cls.detail);
            if (!exists) { // Create
                $next = $('<tr class="' + cls.detail + '"><td class="' + cls.detailCell + '"><div class="' + cls.detailInner + '"></div></td></tr>');
                $row.after($next);
            }
            $next.find('> td:first').attr('colspan', colspan);
            $detail = $next.find('.' + cls.detailInner).empty();
            opt.createDetail($detail, values, opt.createGroupedDetail, opt.detailSeparator, cls);
            $row.data('detail_created', true);
            ft.raise(evt.rowDetailUpdated, { 'row': $row, 'detail': $next });
            return !exists;
        };

        ft.raise = function (eventName, args) {

            if (ft.options.debug === true && $.isFunction(ft.options.log)) ft.options.log(eventName, 'event');

            args = args || { };
            var def = { 'ft': ft };
            $.extend(true, def, args);
            var e = $.Event(eventName, def);
            if (!e.ft) {
                $.extend(true, e, def);
            } //pre jQuery 1.6 which did not allow data to be passed to event object constructor
            $(ft.table).trigger(e);
            return e;
        };

        //reset the state of FooTable
        ft.reset = function() {
            var $table = $(ft.table);
            $table.removeData('footable_info')
                .data('breakpoint', '')
                .removeClass(cls.loading)
                .removeClass(cls.loaded);

            $table.find(opt.toggleSelector).unbind(trg.toggleRow).unbind('click.footable');

            $table.find('> tbody > tr').removeClass(cls.detailShow);

            $table.find('> tbody > tr.' + cls.detail).remove();

            ft.raise(evt.reset);
        };

        //Switch between row-detail and detail-show.
        ft.toggleInput = function (column) {
            var bindName = $(column).attr("data-bind-name");
            if(bindName != null) {
                var bindValue = $('.' + cls.detailInnerValue + '[' + 'data-bind-value="' + bindName + '"]');
                if(bindValue != null) {
                    if($(column).is(":visible")) {
                        if(!$(bindValue).is(':empty')) $(column).html($(bindValue).contents().detach());
                    } else if(!$(column).is(':empty')) {
                        $(bindValue).html($(column).contents().detach());
                    }
                }
            }
        };

        ft.init();
        return ft;
    }
})(jQuery, window);
;(function($, w, undefined) {
  if (w.footable === undefined || w.foobox === null) throw new Error('Please check and make sure footable.js is included in the page and is loaded prior to this script.');
  var defaults = {
    grid: {
      enabled: true,
      data: null,
      template: null, //row html template, use for make a row.
      cols: null, //column define
      items: null, //data items
      url: null, //get data from url
      ajax: null, //paramater for $.ajax
      activeClass: 'active', //add to row selected
      multiSelect: false, //allow select multiple row
      showIndex: false, //show row index
      showCheckbox: false, //show checkbox for select
      showEmptyInfo: false, //when that is not data in table, show a info to notify user
      emptyInfo: '<p class="text-center text-warning">No Data</p>',
      pagination: {
        "page-size": 20,
        "pagination-class": "pagination pagination-centered"
      },
      indexFormatter: function(val, $td, index) {
        return index + 1;
      },
      checkboxFormatter: function(isTop) {
        return '<input type="checkbox" class="' + (isTop ? 'checkAll' : 'check') + '">';
      },
      events: {
        loaded: 'footable_grid_loaded',
        created: 'footable_grid_created',
        removed: 'footable_grid_removed',
        updated: 'footable_grid_updated'
      }
    }
  };

  function makeTh(col) {
    var $th = $('<th>' + col.title + '</th>');
    if ($.isPlainObject(col.data)) {
      $th.data(col.data);
    }
    if ($.isPlainObject(col.style)) {
      $th.css(col.style);
    }
    if (col.className) {
      $th.addClass(col.className);
    }
    return $th;
  }

  function initThead($table, options) {
    var $thead = $table.find('thead');
    if ($thead.size() === 0) {
      $thead = $('<thead>').appendTo($table);
    }
    var $row = $('<tr>').appendTo($thead);
    for (var i = 0, len = options.cols.length; i < len; i++) {
      $row.append(makeTh(options.cols[i]));
    }
  }

  function initTBody($table) {
    var $tbody = $table.find('tbody');
    if ($tbody.size() === 0) {
      $tbody = $('<tbody>').appendTo($table);
    }
  }

  function initPagination($table, cols, options) {
    if (options) {
      $table.attr("data-page-size", options['page-size']);
      var $tfoot = $table.find('tfoot');
      if ($tfoot.size() === 0) {
        $tfoot = $('<tfoot class="hide-if-no-paging"></tfoot>').appendTo($table);
      }
      $tfoot.append('<tr><td colspan=' + cols.length + '></td></tr>');
      var $pagination = $("<div>").appendTo($tfoot.find("tr:last-child td"));
      $pagination.addClass(options['pagination-class']);
    }
  }

  function setToggleColumn(cols) {
    var toggleColumn = cols[0];
    for (var i = 0, len = cols.length; i < len; i++) {
      var col = cols[i];
      if (col.data && (col.data.toggle === true || col.data.toggle === "true")) {
        return;
      }
    }
    toggleColumn.data = $.extend(toggleColumn.data, {
      toggle: true
    });
  }

  function makeEmptyInfo($table, cols, emptyInfo) {
    if ($table.find("tr.emptyInfo").size() === 0) {
       $table.find('tbody').append('<tr class="emptyInfo"><td colspan="' + cols.length + '">' + emptyInfo + '</td></tr>');
    }
  }

  function updateRowIndex($tbody, $newRow, detailClass, offset) {
    //update rows index
    $tbody.find('tr:not(.' + detailClass + ')').each(function() {
      var $row = $(this),
        index = $newRow.data('index'),
        oldIndex = parseInt($row.data('index'), 0),
        newIndex = oldIndex + offset;
      if (oldIndex >= index && this !== $newRow.get(0)) {
        $row.attr('data-index', newIndex).data('index', newIndex);
      }
    });
  }

  function Grid() {
    var grid = this;
    grid.name = 'Footable Grid';
    grid.init = function(ft) {
      var toggleClass = ft.options.classes.toggle;
      var detailClass = ft.options.classes.detail;
      var options = ft.options.grid;
      if (!options.cols) return;
      grid.footable = ft;
      var $table = $(ft.table);
      $table.data('grid', grid);
      if ($.isPlainObject(options.data)) {
        $table.data(options.data);
      }
      grid._items = [];
      setToggleColumn(options.cols);
      if (options.showCheckbox) {
        options.multiSelect = true;
        options.cols.unshift({
          title: options.checkboxFormatter(true),
          name: '',
          data: {
            "sort-ignore": true
          },
          formatter: options.checkboxFormatter
        });
      }
      if (options.showIndex) {
        options.cols.unshift({
          title: '#',
          name: 'index',
          data: {
            "sort-ignore": true
          },
          formatter: options.indexFormatter
        });
      }
      initThead($table, options);
      initTBody($table);
      initPagination($table, options.cols, options.pagination);
      $table.off('.grid').on({
        'footable_initialized.grid': function(e) {
          if (options.url || options.ajax) {
            $.ajax(options.ajax || {
              url: options.url
            }).then(function(resp) {
              grid.newItem(resp);
              ft.raise(options.events.loaded);
            }, function(jqXHR) {
              throw 'load data from ' + (options.url || options.ajax.url) + ' fail';
            });
          } else {
            grid.newItem(options.items || []);
            ft.raise(options.events.loaded);
          }
        },
        'footable_sorted.grid footable_grid_created.grid footable_grid_removed.grid': function(event) {
          if (options.showIndex && grid.getItem().length > 0) {
            $table.find('tbody tr:not(.' + detailClass + ')').each(function(index) {
              var $td = $(this).find('td:first');
              $td.html(options.indexFormatter(null, $td, index));
            });
          }
        },
        'footable_redrawn.grid footable_row_removed.grid': function(event) {
          if (grid.getItem().length === 0 && options.showEmptyInfo) {
            makeEmptyInfo($table, options.cols, options.emptyInfo);
          }
        }
      }).on({
        'click.grid': function(event) {
          if ($(event.target).closest('td').find('>.' + toggleClass).size() > 0) {
            return true;
          }
          var $tr = $(event.currentTarget);
          if ($tr.hasClass(detailClass)) {
            return true;
          }
          if (!options.multiSelect && !$tr.hasClass(options.activeClass)) {
            $table.find('tbody tr.' + options.activeClass).removeClass(options.activeClass);
          }
          $tr.toggleClass(options.activeClass);
          if (options.showCheckbox) {
            $tr.find('input:checkbox.check').prop('checked', function(index, val) {
              if (event.target === this) {
                return val;
              }
              return !val;
            });
          }
          ft.toggleDetail($tr);
        }
      }, 'tbody tr').on('click.grid', 'thead input:checkbox.checkAll', function(event) {
        var checked = !! event.currentTarget.checked;
        if (checked) {
          $table.find('tbody tr').addClass(options.activeClass);
        } else {
          $table.find('tbody tr').removeClass(options.activeClass);
        }
        $table.find('tbody input:checkbox.check').prop('checked', checked);
      });
    };
    /**
     * get selected rows index;
     */
    grid.getSelected = function() {
      var options = grid.footable.options.grid,
        $selected = $(grid.footable.table).find('tbody>tr.' + options.activeClass);
      return $selected.map(function() {
        return $(this).data('index');
      });
    };
    /**
     * get row's data by index
     */
    grid.getItem = function(index) {
      if (index !== undefined) {
        if ($.isArray(index)) {
          return $.map(index, function(item) {
            return grid._items[item];
          });
        }
        return grid._items[index];
      }
      return grid._items;
    };

    function makeCell(col, value, index) {
      var $td = $('<td>');
      if (col.formatter) {
        $td.html(col.formatter(value, $td, index));
      } else {
        $td.html(value || '');
      }
      return $td;
    }
    grid._makeRow = function(item, index) {
      var options = grid.footable.options.grid;
      var $row;
      if ($.isFunction(options.template)) {
        $row = $(options.template($.extend({}, {
          __index: index
        }, item)));
      } else {
        $row = $('<tr>');
        for (var i = 0, len = options.cols.length; i < len; i++) {
          var col = options.cols[i];
          $row.append(makeCell(col, item[col.name] || '', index));
        }
      }
      $row.attr('data-index', index);
      return $row;
    };
    /**
     * create rows by js object
     */
    grid.newItem = function(item, index, wait) {
      var $tbody = $(grid.footable.table).find('tbody');
      var detailClass = grid.footable.options.classes.detail;
      $tbody.find('tr.emptyInfo').remove();
      if ($.isArray(item)) {
        for (var atom;
          (atom = item.pop());) {
          grid.newItem(atom, index, true);
        }
        grid.footable.redraw();
        grid.footable.raise(grid.footable.options.grid.events.created, {
          item: item,
          index: index
        });
        return;
      }
      if (!$.isPlainObject(item)) {
        return;
      }
      var $tr, len = grid._items.length;
      if (index === undefined || index < 0 || index > len) {
        $tr = grid._makeRow(item, len++);
        grid._items.push(item);
        $tbody.append($tr);
      } else {
        $tr = grid._makeRow(item, index);
        if (index === 0) {
          grid._items.unshift(item);
          $tbody.prepend($tr);
        } else {
          var $before = $tbody.find('tr[data-index=' + (index - 1) + ']');
          grid._items.splice(index, 0, item);
          if ($before.data('detail_created') === true) {
            $before = $before.next();
          }
          $before.after($tr);
        }
        updateRowIndex($tbody, $tr, detailClass, 1);
      }
      if (!wait) {
        grid.footable.redraw();
        grid.footable.raise(grid.footable.options.grid.events.created, {
          item: item,
          index: index
        });
      }
    };
    /**
     * update row by js object
     */
    grid.setItem = function(item, index) {
      if (!$.isPlainObject(item)) {
        return;
      }
      var $tbody = $(grid.footable.table).find('tbody'),
        $newTr = grid._makeRow(item, index);
      $.extend(grid._items[index], item);
      var $tr = $tbody.find('tr').eq(index);
      $tr.html($newTr.html());
      grid.footable.redraw();
      grid.footable.raise(grid.footable.options.grid.events.updated, {
        item: item,
        index: index
      });
    };
    /**
     * remove rows by index
     */
    grid.removeItem = function(index) {
      var $tbody = $(grid.footable.table).find('tbody');
      var detailClass = grid.footable.options.classes.detail;
      var result = [];
      if ($.isArray(index)) {
        for (var i;
          (i = index.pop());) {
          result.push(grid.removeItem(i));
        }
        grid.footable.raise(grid.footable.options.grid.events.removed, {
          item: result,
          index: index
        });
        return result;
      }
      if (index === undefined) {
        $tbody.find('tr').each(function() {
          result.push(grid._items.shift());
          grid.footable.removeRow(this);
        });
      } else {
        var $tr = $tbody.find('tr[data-index=' + index + ']');
        result = grid._items.splice(index, 1)[0];
        grid.footable.removeRow($tr);
        //update rows index
        updateRowIndex($tbody, $tr, detailClass, -1);
      }
      grid.footable.raise(grid.footable.options.grid.events.removed, {
        item: result,
        index: index
      });
      return result;
    };
  }
  w.footable.plugins.register(Grid, defaults);
})(jQuery, window);
;(function ($, w, undefined) {
    if (w.footable === undefined || w.footable === null)
        throw new Error('Please check and make sure footable.js is included in the page and is loaded prior to this script.');

    var defaults = {
        filter: {
            enabled: true,
            input: '.footable-filter',
            timeout: 300,
            minimum: 2,
            disableEnter: false,
            filterFunction: function(index) {
                var $t = $(this),
                    $table = $t.parents('table:first'),
                    filter = $table.data('current-filter').toUpperCase(),
                    text = $t.find('td').text();
                if (!$table.data('filter-text-only')) {
                    $t.find('td[data-value]').each(function () {
                        text += $(this).data('value');
                    });
                }
                return text.toUpperCase().indexOf(filter) >= 0;
            }
        }
    };

    function Filter() {
        var p = this;
        p.name = 'Footable Filter';
        p.init = function (ft) {
            p.footable = ft;
            if (ft.options.filter.enabled === true) {
                if ($(ft.table).data('filter') === false) return;
                ft.timers.register('filter');
                $(ft.table)
                    .unbind('.filtering')
                    .bind({
                        'footable_initialized.filtering': function (e) {
                            var $table = $(ft.table);
                            var data = {
                                'input': $table.data('filter') || ft.options.filter.input,
                                'timeout': $table.data('filter-timeout') || ft.options.filter.timeout,
                                'minimum': $table.data('filter-minimum') || ft.options.filter.minimum,
                                'disableEnter': $table.data('filter-disable-enter') || ft.options.filter.disableEnter
                            };
                            if (data.disableEnter) {
                                $(data.input).keypress(function (event) {
                                    if (window.event)
                                        return (window.event.keyCode !== 13);
                                    else
                                        return (event.which !== 13);
                                });
                            }
                            $table.bind('footable_clear_filter', function () {
                                $(data.input).val('');
                                p.clearFilter();
                            });
                            $table.bind('footable_filter', function (event, args) {
                                p.filter(args.filter);
                            });
                            $(data.input).keyup(function (eve) {
                                ft.timers.filter.stop();
                                if (eve.which === 27) {
                                    $(data.input).val('');
                                }
                                ft.timers.filter.start(function () {
                                    var val = $(data.input).val() || '';
                                    p.filter(val);
                                }, data.timeout);
                            });
                        },
                        'footable_redrawn.filtering': function (e) {
                            var $table = $(ft.table),
                                filter = $table.data('filter-string');
                            if (filter) {
                                p.filter(filter);
                            }
                        }
                })
                //save the filter object onto the table so we can access it later
                .data('footable-filter', p);
            }
        };

        p.filter = function (filterString) {
            var ft = p.footable,
                $table = $(ft.table),
                minimum = $table.data('filter-minimum') || ft.options.filter.minimum,
                clear = !filterString;

            //raise a pre-filter event so that we can cancel the filtering if needed
            var event = ft.raise('footable_filtering', { filter: filterString, clear: clear });
            if (event && event.result === false) return;
            if (event.filter && event.filter.length < minimum) {
              return; //if we do not have the minimum chars then do nothing
            }

          if (event.clear) {
                p.clearFilter();
            } else {
                var filters = event.filter.split(' ');

                $table.find('> tbody > tr').hide().addClass('footable-filtered');
                var rows = $table.find('> tbody > tr:not(.footable-row-detail)');
                $.each(filters, function (i, f) {
                    if (f && f.length > 0) {
                        $table.data('current-filter', f);
                        rows = rows.filter(ft.options.filter.filterFunction);
                    }
                });
                rows.each(function () {
                    p.showRow(this, ft);
                    $(this).removeClass('footable-filtered');
                });
                $table.data('filter-string', event.filter);
                ft.raise('footable_filtered', { filter: event.filter, clear: false });
            }
        };

        p.clearFilter = function () {
            var ft = p.footable,
                $table = $(ft.table);

            $table.find('> tbody > tr:not(.footable-row-detail)').removeClass('footable-filtered').each(function () {
                p.showRow(this, ft);
            });
            $table.removeData('filter-string');
            ft.raise('footable_filtered', { clear: true });
        };

        p.showRow = function (row, ft) {
            var $row = $(row), $next = $row.next(), $table = $(ft.table);
            if ($table.hasClass('breakpoint') && $row.hasClass('footable-detail-show') && $next.hasClass('footable-row-detail')) {
                $row.add($next).show();
                ft.createOrUpdateDetailRow(row);
            }
            else $row.show();
        };
    }

    w.footable.plugins.register(Filter, defaults);

})(jQuery, window);
;(function ($, w, undefined) {
	if (w.footable === undefined || w.footable === null)
		throw new Error('Please check and make sure footable.js is included in the page and is loaded prior to this script.');

	var defaults = {
		paginate: true,
		pageSize: 10,
		pageNavigation: '.pagination',
		firstText: '&laquo;',
		previousText: '&lsaquo;',
		nextText: '&rsaquo;',
		lastText: '&raquo;',
		limitNavigation: 0,
		limitPreviousText: '...',
		limitNextText: '...'
	};

	function pageInfo(ft) {
		var $table = $(ft.table), data = $table.data();
		this.pageNavigation = data.pageNavigation || ft.options.pageNavigation;
		this.pageSize = data.pageSize || ft.options.pageSize;
		this.firstText = data.firstText || ft.options.firstText;
		this.previousText = data.previousText || ft.options.previousText;
		this.nextText = data.nextText || ft.options.nextText;
		this.lastText = data.lastText || ft.options.lastText;
		this.limitNavigation = parseInt(data.limitNavigation || ft.options.limitNavigation || defaults.limitNavigation, 10);
		this.limitPreviousText = data.limitPreviousText || ft.options.limitPreviousText;
		this.limitNextText = data.limitNextText || ft.options.limitNextText;
		this.limit = this.limitNavigation > 0;
		this.currentPage = data.currentPage || 0;
		this.pages = [];
		this.control = false;
	}

	function Paginate() {
		var p = this;
		p.name = 'Footable Paginate';

		p.init = function (ft) {
			if (ft.options.paginate === true) {
				if ($(ft.table).data('page') === false) return;
				p.footable = ft;
				$(ft.table)
					.unbind('.paging')
					.bind({
						'footable_initialized.paging footable_row_removed.paging footable_redrawn.paging footable_sorted.paging footable_filtered.paging': function () {
							p.setupPaging();
						}
					})
					//save the filter object onto the table so we can access it later
					.data('footable-paging', p);
			}
		};

		p.setupPaging = function () {
			var ft = p.footable,
				$tbody = $(ft.table).find('> tbody');

			ft.pageInfo = new pageInfo(ft);

			p.createPages(ft, $tbody);
			p.createNavigation(ft, $tbody);
			p.fillPage(ft, $tbody, ft.pageInfo.currentPage);
		};

		p.createPages = function (ft, tbody) {
			var pages = 1;
			var info = ft.pageInfo;
			var pageCount = pages * info.pageSize;
			var page = [];
			var lastPage = [];
			info.pages = [];
			var rows = tbody.find('> tr:not(.footable-filtered,.footable-row-detail)');
			rows.each(function (i, row) {
				page.push(row);
				if (i === pageCount - 1) {
					info.pages.push(page);
					pages++;
					pageCount = pages * info.pageSize;
					page = [];
				} else if (i >= rows.length - (rows.length % info.pageSize)) {
					lastPage.push(row);
				}
			});
			if (lastPage.length > 0) info.pages.push(lastPage);
			if (info.currentPage >= info.pages.length) info.currentPage = info.pages.length - 1;
			if (info.currentPage < 0) info.currentPage = 0;
			if (info.pages.length === 1) {
				//we only have a single page
				$(ft.table).addClass('no-paging');
			} else {
				$(ft.table).removeClass('no-paging');
			}
		};

		p.createNavigation = function (ft, tbody) {
			var $nav = $(ft.table).find(ft.pageInfo.pageNavigation);
			//if we cannot find the navigation control within the table, then try find it outside
			if ($nav.length === 0) {
				$nav = $(ft.pageInfo.pageNavigation);
				//if the navigation control is inside another table, then get out
				if ($nav.parents('table:first').length > 0 && $nav.parents('table:first') !== $(ft.table)) return;
				//if we found more than one navigation control, write error to console
				if ($nav.length > 1 && ft.options.debug === true) console.error('More than one pagination control was found!');
			}
			//if we still cannot find the control, then don't do anything
			if ($nav.length === 0) return;
			//if the nav is not a UL, then find or create a UL
			if (!$nav.is('ul')) {
				if ($nav.find('ul:first').length === 0) {
					$nav.append('<ul />');
				}
				$nav = $nav.find('ul');
			}
			$nav.find('li').remove();
			var info = ft.pageInfo;
			info.control = $nav;
			if (info.pages.length > 0) {
				$nav.append('<li class="footable-page-arrow"><a data-page="first" href="#first">' + ft.pageInfo.firstText + '</a>');
				$nav.append('<li class="footable-page-arrow"><a data-page="prev" href="#prev">' + ft.pageInfo.previousText + '</a></li>');
				if (info.limit){
					$nav.append('<li class="footable-page-arrow"><a data-page="limit-prev" href="#limit-prev">' + ft.pageInfo.limitPreviousText + '</a></li>');
				}
				if (!info.limit){
					$.each(info.pages, function (i, page) {
						if (page.length > 0) {
							$nav.append('<li class="footable-page"><a data-page="' + i + '" href="#">' + (i + 1) + '</a></li>');
						}
					});
				}
				if (info.limit){
					$nav.append('<li class="footable-page-arrow"><a data-page="limit-next" href="#limit-next">' + ft.pageInfo.limitNextText + '</a></li>');
					p.createLimited($nav, info, 0);
				}
				$nav.append('<li class="footable-page-arrow"><a data-page="next" href="#next">' + ft.pageInfo.nextText + '</a></li>');
				$nav.append('<li class="footable-page-arrow"><a data-page="last" href="#last">' + ft.pageInfo.lastText + '</a></li>');
			}
			$nav.off('click', 'a[data-page]').on('click', 'a[data-page]', function (e) {
				e.preventDefault();
				var page = $(this).data('page');
				var newPage = info.currentPage;
				if (page === 'first') {
					newPage = 0;
				} else if (page === 'prev') {
					if (newPage > 0) newPage--;
				} else if (page === 'next') {
					if (newPage < info.pages.length - 1) newPage++;
				} else if (page === 'last') {
					newPage = info.pages.length - 1;
				} else if (page === 'limit-prev') {
					newPage = -1;
					var first = $nav.find('.footable-page:first a').data('page');
					p.createLimited($nav, info, first - info.limitNavigation);
					p.setPagingClasses($nav, info.currentPage, info.pages.length);
				} else if (page === 'limit-next') {
					newPage = -1;
					var last = $nav.find('.footable-page:last a').data('page');
					p.createLimited($nav, info, last + 1);
					p.setPagingClasses($nav, info.currentPage, info.pages.length);
				} else {
					newPage = page;
				}
				if (newPage >= 0){
					if (info.limit && info.currentPage != newPage){
						var start = newPage;
						while (start % info.limitNavigation !== 0){ start -= 1; }
						p.createLimited($nav, info, start);
					}
					p.paginate(ft, newPage);
				}
			});
			p.setPagingClasses($nav, info.currentPage, info.pages.length);
		};

		p.createLimited = function(nav, info, start){
			start = start || 0;
			nav.find('li.footable-page').remove();
			var i, page,
				$prev = nav.find('li.footable-page-arrow > a[data-page="limit-prev"]').parent(),
				$next = nav.find('li.footable-page-arrow > a[data-page="limit-next"]').parent();
			for (i = info.pages.length - 1; i >=0 ; i--){
				page = info.pages[i];
				if (i >= start && i < start + info.limitNavigation && page.length > 0) {
					$prev.after('<li class="footable-page"><a data-page="' + i + '" href="#">' + (i + 1) + '</a></li>');
				}
			}
			if (start === 0){ $prev.hide(); }
			else { $prev.show(); }
			if (start + info.limitNavigation >= info.pages.length){ $next.hide(); }
			else { $next.show(); }
		};

		p.paginate = function (ft, newPage) {
			var info = ft.pageInfo;
			if (info.currentPage !== newPage) {
				var $tbody = $(ft.table).find('> tbody');

				//raise a pre-pagin event so that we can cancel the paging if needed
				var event = ft.raise('footable_paging', { page: newPage, size: info.pageSize });
				if (event && event.result === false) return;

				p.fillPage(ft, $tbody, newPage);
				info.control.find('li').removeClass('active disabled');
				p.setPagingClasses(info.control, info.currentPage, info.pages.length);
			}
		};

		p.setPagingClasses = function (nav, currentPage, pageCount) {
			nav.find('li.footable-page > a[data-page=' + currentPage + ']').parent().addClass('active');
			if (currentPage >= pageCount - 1) {
				nav.find('li.footable-page-arrow > a[data-page="next"]').parent().addClass('disabled');
				nav.find('li.footable-page-arrow > a[data-page="last"]').parent().addClass('disabled');
			}
			if (currentPage < 1) {
				nav.find('li.footable-page-arrow > a[data-page="first"]').parent().addClass('disabled');
				nav.find('li.footable-page-arrow > a[data-page="prev"]').parent().addClass('disabled');
			}
		};

		p.fillPage = function (ft, tbody, pageNumber) {
			ft.pageInfo.currentPage = pageNumber;
			$(ft.table).data('currentPage', pageNumber);
			tbody.find('> tr').hide();
			$(ft.pageInfo.pages[pageNumber]).each(function () {
				p.showRow(this, ft);
			});
			ft.raise('footable_page_filled');
		};

		p.showRow = function (row, ft) {
			var $row = $(row), $next = $row.next(), $table = $(ft.table);
			if ($table.hasClass('breakpoint') && $row.hasClass('footable-detail-show') && $next.hasClass('footable-row-detail')) {
				$row.add($next).show();
				ft.createOrUpdateDetailRow(row);
			}
			else $row.show();
		};
	}

	w.footable.plugins.register(Paginate, defaults);

})(jQuery, window);
;(function ($, w, undefined) {
    if (w.footable === undefined || w.footable === null)
        throw new Error('Please check and make sure footable.js is included in the page and is loaded prior to this script.');

    var defaults = {
        sort: true,
        sorters: {
            alpha: function (a, b) {
              if (typeof(a) === 'string') { a = a.toLowerCase(); }
              if (typeof(b) === 'string') { b = b.toLowerCase(); }
              if (a === b) return 0;
              if (a < b) return -1;
              return 1;
            },
            numeric: function (a, b) {
              return a - b;
            }
        },
        classes: {
            sort: {
                sortable: 'footable-sortable',
                sorted: 'footable-sorted',
                descending: 'footable-sorted-desc',
                indicator: 'footable-sort-indicator'
            }
        },
        events: {
            sort: {
                sorting: 'footable_sorting',
                sorted: 'footable_sorted'
            }
        }
    };

    function Sort() {
        var p = this;
        p.name = 'Footable Sortable';
        p.init = function (ft) {
            p.footable = ft;
            if (ft.options.sort === true) {
                $(ft.table)
                    .unbind('.sorting')
                    .bind({
                        'footable_initialized.sorting': function (e) {
                            var $table = $(ft.table),
                                $tbody = $table.find('> tbody'),
                                cls = ft.options.classes.sort,
                                column, $th;

                            if ($table.data('sort') === false) return;

                            $table.find('> thead > tr:last-child > th, > thead > tr:last-child > td').each(function (ec) {
                                var $th = $(this), column = ft.columns[$th.index()];
                                if (column.sort.ignore !== true && !$th.hasClass(cls.sortable)) {
                                    $th.addClass(cls.sortable);
                                    $('<span />').addClass(cls.indicator).appendTo($th);
                                }
                            });

                            $table.find('> thead > tr:last-child > th.' + cls.sortable + ', > thead > tr:last-child > td.' + cls.sortable).unbind('click.footable').bind('click.footable', function (ec) {
                                ec.preventDefault();
                                $th = $(this);
                                var ascending = !$th.hasClass(cls.sorted);
                                p.doSort($th.index(), ascending);
                                return false;
                            });

                            var didSomeSorting = false;
                            for (var c in ft.columns) {
                                column = ft.columns[c];
                                if (column.sort.initial) {
                                    var ascending = (column.sort.initial !== 'descending');
                                    p.doSort(column.index, ascending);
                                    break;
                                }
                            }
                            if (didSomeSorting) {
                                ft.bindToggleSelectors();
                            }
                        },
                        'footable_redrawn.sorting': function(e) {
                            var $table = $(ft.table),
                                cls = ft.options.classes.sort;
                            if ($table.data('sorted') >= 0) {
                                $table.find('> thead > tr:last-child > th').each(function(i){
                                    var $th = $(this);
                                    if ($th.hasClass(cls.sorted) || $th.hasClass(cls.descending)) {
                                        p.doSort(i);
                                        return;
                                    }
                                });
                            }
                        },
                        'footable_column_data.sorting': function (e) {
                            var $th = $(e.column.th);
                            e.column.data.sort = e.column.data.sort || {};
                            e.column.data.sort.initial = $th.data('sort-initial') || false;
                            e.column.data.sort.ignore = $th.data('sort-ignore') || false;
                            e.column.data.sort.selector = $th.data('sort-selector') || null;

                            var match = $th.data('sort-match') || 0;
                            if (match >= e.column.data.matches.length) match = 0;
                            e.column.data.sort.match = e.column.data.matches[match];
                        }
                    })
                //save the sort object onto the table so we can access it later
                .data('footable-sort', p);
            }
        };

        p.doSort = function(columnIndex, ascending) {
            var ft = p.footable;
            if ($(ft.table).data('sort') === false) return;

            var $table = $(ft.table),
                $tbody = $table.find('> tbody'),
                column = ft.columns[columnIndex],
                $th = $table.find('> thead > tr:last-child > th:eq(' + columnIndex + ')'),
                cls = ft.options.classes.sort,
                evt = ft.options.events.sort;

            ascending = (ascending === undefined) ? $th.hasClass(cls.sorted) :
                (ascending === 'toggle') ? !$th.hasClass(cls.sorted) : ascending;

            if (column.sort.ignore === true) return true;

            //raise a pre-sorting event so that we can cancel the sorting if needed
            var event = ft.raise(evt.sorting, { column: column, direction: ascending ? 'ASC' : 'DESC' });
            if (event && event.result === false) return;

            $table.data('sorted', column.index);

            $table.find('> thead > tr:last-child > th, > thead > tr:last-child > td').not($th).removeClass(cls.sorted + ' ' + cls.descending);

            if (ascending === undefined) {
                ascending = $th.hasClass(cls.sorted);
            }

            if (ascending) {
                $th.removeClass(cls.descending).addClass(cls.sorted);
            } else {
                $th.removeClass(cls.sorted).addClass(cls.descending);
            }

            p.sort(ft, $tbody, column, ascending);

            ft.bindToggleSelectors();
            ft.raise(evt.sorted, { column: column, direction: ascending ? 'ASC' : 'DESC' });
        };

        p.rows = function (ft, tbody, column) {
            var rows = [];
            tbody.find('> tr').each(function () {
                var $row = $(this), $next = null;
                if ($row.hasClass(ft.options.classes.detail)) return true;
                if ($row.next().hasClass(ft.options.classes.detail)) {
                    $next = $row.next().get(0);
                }
                var row = { 'row': $row, 'detail': $next };
                if (column !== undefined) {
                    row.value = ft.parse(this.cells[column.sort.match], column);
                }
                rows.push(row);
                return true;
            }).detach();
            return rows;
        };

        p.sort = function (ft, tbody, column, ascending) {
            var rows = p.rows(ft, tbody, column);
            var sorter = ft.options.sorters[column.type] || ft.options.sorters.alpha;
            rows.sort(function (a, b) {
                if (ascending) {
                    return sorter(a.value, b.value);
                } else {
                    return sorter(b.value, a.value);
                }
            });
            for (var j = 0; j < rows.length; j++) {
                tbody.append(rows[j].row);
                if (rows[j].detail !== null) {
                    tbody.append(rows[j].detail);
                }
            }
        };
    }

    w.footable.plugins.register(Sort, defaults);

})(jQuery, window);
;(function ($, w, undefined) {
  if (w.footable === undefined || w.foobox === null)
    throw new Error('Please check and make sure footable.js is included in the page and is loaded prior to this script.');

  var defaults = {
    striping: {
      enabled: true
    },
    classes: {
      striping: {
        odd: 'footable-odd',
        even: 'footable-even'
      }
    }
  };

  function Striping() {
    var p = this;
    p.name = 'Footable Striping';
    p.init = function (ft) {
      p.footable = ft;
      $(ft.table)
        .unbind('striping')
        .bind({
          'footable_initialized.striping footable_row_removed.striping footable_redrawn.striping footable_sorted.striping footable_filtered.striping': function () {
            
            if ($(this).data('striping') === false) return;

            p.setupStriping(ft);
          }
        });
    };

    p.setupStriping = function (ft) {

      var rowIndex = 0;
      $(ft.table).find('> tbody > tr:not(.footable-row-detail)').each(function () {

        var $row = $(this);

        //Clean off old classes
        $row.removeClass(ft.options.classes.striping.even).removeClass(ft.options.classes.striping.odd);

        if (rowIndex % 2 === 0) {
          $row.addClass(ft.options.classes.striping.even);
        } else {
          $row.addClass(ft.options.classes.striping.odd);
        }

        rowIndex++;
      });
    };
  }

  w.footable.plugins.register(Striping, defaults);

})(jQuery, window);
;(function ($, w, undefined) {
    if (w.footable === undefined || w.foobox === null)
        throw new Error('Please check and make sure footable.js is included in the page and is loaded prior to this script.');

    var defaults = {
        bookmarkable: {
            enabled: false
        }
    };

    // see http://www.onlineaspect.com/2009/06/10/reading-get-variables-with-javascript/
    function $_HASH(q,s) {
        s = s ? s : location.hash;
        var re = new RegExp('&'+q+'(?:=([^&]*))?(?=&|$)','i');
        return (s=s.replace(/^\#/,'&').match(re)) ? (typeof s[1] == 'undefined' ? '' : decodeURIComponent(s[1])) : undefined;
    }

    function addFootableStatusData(ft, event) {
        var tbl_total = $(ft.table).find("tbody").find("tr:not(.footable-row-detail, .footable-filtered)").length;
        $(ft.table).data("status_num_total", tbl_total);

        var tbl_num = $(ft.table).find("tbody").find("tr:not(.footable-row-detail)").filter(":visible").length;
        $(ft.table).data("status_num_shown", tbl_num);

        var sort_colnum = $(ft.table).data("sorted");
        var sort_col = $(ft.table).find("th")[sort_colnum];
        var descending = $(sort_col).hasClass("footable-sorted-desc");
        $(ft.table).data("status_descending", descending);
            
        if (ft.pageInfo) {
            var pagenum = ft.pageInfo.currentPage; 
            $(ft.table).data("status_pagenum", pagenum);
        }

        var filter_val = '';
        var filter_field_id = $(ft.table).data('filter');
        if ( $(filter_field_id).length ) {
            filter_val = $(filter_field_id).val();
        }

        $(ft.table).data("status_filter_val", filter_val);

        // manage expanded or collapsed rows:
	var row, rowlist, expanded_rows;
        if (event.type == 'footable_row_expanded') {
            row = event.row;
            if (row) {
                rowlist = $(ft.table).data('expanded_rows');
                expanded_rows = [];
                if (rowlist) {
                    expanded_rows = rowlist.split(',');
                }
                expanded_rows.push(row.rowIndex);
                $(ft.table).data('expanded_rows', expanded_rows.join(','));
            }
        }
        if (event.type == 'footable_row_collapsed') {
            row = event.row;
            if (row) {
                rowlist = $(ft.table).data('expanded_rows');
                expanded_rows = [];
                if (rowlist) {
                    expanded_rows = rowlist.split(',');
                }
                var new_expanded_rows = [];
                for (var i in expanded_rows) {
                    if (expanded_rows[i] == row.rowIndex) {
                        new_expanded_rows = expanded_rows.splice(i, 1);
                        break;
                    }
                }
                $(ft.table).data('expanded_rows', new_expanded_rows.join(','));
            }
        }
    }

 function Bookmarkable() {
     var p = this;
     p.name = 'Footable LucidBookmarkable';
     p.init = function(ft) {
         if (ft.options.bookmarkable.enabled) {
             
             $(ft.table).bind({
                 'footable_initialized': function(){
                     var tbl_id     = ft.table.id;
                     var q_filter   = $_HASH(tbl_id + '_f');
                     var q_page_num = $_HASH(tbl_id + '_p');
                     var q_sorted   = $_HASH(tbl_id + '_s');
                     var q_desc     = $_HASH(tbl_id + '_d');
                     var q_expanded = $_HASH(tbl_id + '_e');

                     if (q_filter) {
                         var filter_field_id = $(ft.table).data('filter');
                         $(filter_field_id).val(q_filter); 
                         $(ft.table).trigger('footable_filter', {filter: q_filter});
                     }
                     if (q_page_num) {
                         $(ft.table).data('currentPage',  q_page_num);
			 // we'll check for sort before triggering pagination, since
			 // sorting triggers pagination. 
                     }
                     if (typeof q_sorted !== 'undefined') {
                         var footableSort = $(ft.table).data('footable-sort');
                         var ascending = true;
                         if (q_desc == 'true') {
                             ascending = false;
                         }
                         footableSort.doSort(q_sorted, ascending);
                     }
                     else {
                         $(ft.table).trigger('footable_setup_paging');
                     }
                     if (q_expanded) {
                         var expanded_rows = q_expanded.split(',');
                         for (var i in expanded_rows) {
                             var row = $(ft.table.rows[expanded_rows[i]]);
                             row.find('> td:first').trigger('footable_toggle_row');
                         }
                     }
                     ft.lucid_bookmark_read = true;
                 },
                 'footable_page_filled footable_redrawn footable_filtered footable_sorted footable_row_expanded footable_row_collapsed': function(e) {
                     addFootableStatusData(ft, e);

                     // update the URL hash
                     // lucid_bookmark_read guards against running this logic before
                     // the "first read" of the location bookmark hash.
                     if (ft.lucid_bookmark_read) {
                         var tbl_id     = ft.table.id;
                         var filter     = tbl_id + '_f';
                         var page_num   = tbl_id + '_p';
                         var sorted     = tbl_id + '_s';
                         var descending = tbl_id + '_d';
                         var expanded   = tbl_id + '_e';
                         
                         var hash = location.hash.replace(/^\#/, '&');
                         var hashkeys = [filter, page_num, sorted, descending, expanded];
                         // trim existing elements out of the hash.
                         for (var i in hashkeys) {
                             var re = new RegExp('&' + hashkeys[i]+'=([^&]*)', 'g');
                             hash = hash.replace(re, '');
                         }

                         var foostate = {};
                         foostate[filter]     = $(ft.table).data('status_filter_val');
                         foostate[page_num]   = $(ft.table).data('status_pagenum');
                         foostate[sorted]     = $(ft.table).data('sorted');
                         foostate[descending] = $(ft.table).data('status_descending');
                         foostate[expanded]   = $(ft.table).data('expanded_rows');

                         var pairs = [];
                         for (var elt in foostate) {
                             if (foostate[elt] !== undefined) {
                                 pairs.push(elt + '=' + encodeURIComponent(foostate[elt]));
                             }
                         }
                         if (hash.length) {
                             pairs.push(hash);
                         }
                         location.hash = pairs.join('&');
                     }
                 }
             });
         }
     };
 }
 
 w.footable.plugins.register(Bookmarkable, defaults);
  
})(jQuery, window);
