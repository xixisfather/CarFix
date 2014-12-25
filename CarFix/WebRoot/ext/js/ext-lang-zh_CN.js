/*
 * Simplified Chinese translation
 * By DavidHu
 * 09 April 2007
 */

Ext.UpdateManager.defaults.indicatorText = '<div class="loading-indicator">\u52a0\u8f7d\u4e2d...</div>';

if(Ext.View){
   Ext.View.prototype.emptyText = "";
}

if(Ext.grid.Grid){
   Ext.grid.Grid.prototype.ddText = "{0} \u9009\u62e9\u884c";
}

if(Ext.TabPanelItem){
   Ext.TabPanelItem.prototype.closeText = "\u5173\u95ed";
}

if(Ext.form.Field){
   Ext.form.Field.prototype.invalidText = "\u8f93\u5165\u503c\u975e\u6cd5";
}

Date.monthNames = [
   "\u4e00\u6708",
   "\u4e8c\u6708",
   "\u4e09\u6708",
   "\u56db\u6708",
   "\u4e94\u6708",
   "\u516d\u6708",
   "\u4e03\u6708",
   "\u516b\u6708",
   "\u4e5d\u6708",
   "\u5341\u6708",
   "\u5341\u4e00\u6708",
   "\u5341\u4e8c\u6708"
];

Date.dayNames = [
   "\u65e5",
   "\u4e00",
   "\u4e8c",
   "\u4e09",
   "\u56db",
   "\u4e94",
   "\u516d"
];

if(Ext.MessageBox){
   Ext.MessageBox.buttonText = {
      ok     : "\u786e\u5b9a",
      cancel : "\u53d6\u6d88",
      yes    : "\u662f",
      no     : "\u5426"
   };
}

if(Ext.util.Format){
   Ext.util.Format.date = function(v, format){
      if(!v) return "";
      if(!(v instanceof Date)) v = new Date(Date.parse(v));
      return v.dateFormat(format || "y\u5e74m\u6708d\u65e5");
   };
}

if(Ext.DatePicker){
   Ext.apply(Ext.DatePicker.prototype, {
      todayText         : "\u4eca\u5929",
      minText           : "\u65e5\u671f\u5728\u6700\u5c0f\u65e5\u671f\u4e4b\u524d",
      maxText           : "\u65e5\u671f\u5728\u6700\u5927\u65e5\u671f\u4e4b\u540e",
      disabledDaysText  : "",
      disabledDatesText : "",
      monthNames	: Date.monthNames,
      dayNames		: Date.dayNames,      
      nextText          : '\u4e0b\u6708 (Control+Right)',
      prevText          : '\u4e0a\u6708 (Control+Left)',
      monthYearText     : '\u9009\u62e9\u4e00\u4e2a\u6708 (Control+Up/Down \u6765\u6539\u53d8\u5e74)',
      todayTip          : "{0} (\u7a7a\u683c\u952e\u9009\u62e9)",
      format            : "y\u5e74m\u6708d\u65e5"
   });
}

if(Ext.PagingToolbar){
   Ext.apply(Ext.PagingToolbar.prototype, {
      beforePageText : "\u9875",
   	  afterPageText  : "\u9875\u5171 {0} \u9875", 
      firstText      : "\u7b2c\u4e00\u9875",
      prevText       : "\u524d\u4e00\u9875",
      nextText       : "\u4e0b\u4e00\u9875",
      lastText       : "\u6700\u540e\u9875",
      refreshText    : "\u5237\u65b0",
	  displayMsg     : "\u663e\u793a {0} - {1}\uff0c\u5171 {2} \u6761",
      emptyMsg       : '\u6ca1\u6709\u6570\u636e\u9700\u8981\u663e\u793a'
   });
}

if(Ext.form.TextField){
   Ext.apply(Ext.form.TextField.prototype, {
      minLengthText : "\u8be5\u8f93\u5165\u9879\u7684\u6700\u5c0f\u957f\u5ea6\u662f {0}",
      maxLengthText : "\u8be5\u8f93\u5165\u9879\u7684\u6700\u5927\u957f\u5ea6\u662f {0}",
      blankText     : "\u8be5\u8f93\u5165\u9879\u4e3a\u5fc5\u8f93\u9879",
      regexText     : "",
      emptyText     : null
   });
}

if(Ext.form.NumberField){
   Ext.apply(Ext.form.NumberField.prototype, {
      minText : "\u8be5\u8f93\u5165\u9879\u7684\u6700\u5c0f\u503c\u662f {0}",
      maxText : "\u8be5\u8f93\u5165\u9879\u7684\u6700\u5927\u503c\u662f {0}",
      nanText : "{0} \u4e0d\u662f\u6709\u6548\u6570\u503c"
   });
}

if(Ext.form.DateField){
   Ext.apply(Ext.form.DateField.prototype, {
      disabledDaysText  : "\u7981\u7528",
      disabledDatesText : "\u7981\u7528",
      minText           : "\u8be5\u8f93\u5165\u9879\u7684\u65e5\u671f\u5fc5\u987b\u5728 {0} \u4e4b\u540e",
      maxText           : "\u8be5\u8f93\u5165\u9879\u7684\u65e5\u671f\u5fc5\u987b\u5728 {0} \u4e4b\u524d",
      invalidText       : "{0} \u662f\u65e0\u6548\u7684\u65e5\u671f - \u5fc5\u987b\u7b26\u5408\u683c\u5f0f\uff1a {1}",
      format            : "y\u5e74m\u6708d\u65e5"
   });
}

if(Ext.form.ComboBox){
   Ext.apply(Ext.form.ComboBox.prototype, {
      loadingText       : "\u52a0\u8f7d...",
      valueNotFoundText : undefined
   });
}

if(Ext.form.VTypes){
   Ext.apply(Ext.form.VTypes, {
      emailText    : '\u8be5\u8f93\u5165\u9879\u5fc5\u987b\u662f\u7535\u5b50\u90ae\u4ef6\u5730\u5740\uff0c\u683c\u5f0f\u5982\uff1a "user@domain.com"',
      urlText      : '\u8be5\u8f93\u5165\u9879\u5fc5\u987b\u662fURL\u5730\u5740\uff0c\u683c\u5f0f\u5982\uff1a "http:/'+'/www.domain.com"',
      alphaText    : '\u8be5\u8f93\u5165\u9879\u53ea\u80fd\u5305\u542b\u5b57\u7b26\u548c_',
      alphanumText : '\u8be5\u8f93\u5165\u9879\u53ea\u80fd\u5305\u542b\u5b57\u7b26,\u6570\u5b57\u548c_'
   });
}

if(Ext.grid.GridView){
   Ext.apply(Ext.grid.GridView.prototype, {
      sortAscText  : "\u6b63\u5e8f",
      sortDescText : "\u9006\u5e8f",
      lockText     : "\u9501\u5217",
      unlockText   : "\u89e3\u9501\u5217",
      columnsText  : "\u5217"
   });
}

if(Ext.grid.PropertyColumnModel){
   Ext.apply(Ext.grid.PropertyColumnModel.prototype, {
      nameText   : "\u540d\u79f0",
      valueText  : "\u503c",
      dateFormat : "y\u5e74m\u6708d\u65e5"
   });
}

if(Ext.layout.BorderLayout.SplitRegion){
   Ext.apply(Ext.layout.BorderLayout.SplitRegion.prototype, {
      splitTip            : "\u62d6\u52a8\u6765\u6539\u53d8\u5c3a\u5bf8.",
      collapsibleSplitTip : "\u62d6\u52a8\u6765\u6539\u53d8\u5c3a\u5bf8. \u53cc\u51fb\u9690\u85cf."
   });
}
