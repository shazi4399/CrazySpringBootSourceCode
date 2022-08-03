// 展示loading
function g_showLoading() {
	return layer.msg('处理中...', {
		icon: 16, shade: [0.5, '#f5f5f5'],
		scrollbar: false, offset: '0px', time: 100000
	});
}

// 获取url参数
function g_getQueryString(name) {
	let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	let r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}

// 获取url参数
function g_secs2hour(secs) {
	let hours = Math.floor(secs / 3600);
	let mins = Math.floor((secs % 3600) / 60);
	let left = secs % 60;
	return hours + "小时" + mins + "分" + left + "秒";
}

// 设定时间格式化函数，使用new Date().format("yyyyMMddhhmmss");
Date.prototype.format = function (format) {
	let args = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear()
			+ "").substr(4 - RegExp.$1.length));
	for (let i in args) {
		let n = args[i];
		if (new RegExp("(" + i + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n :
				("00" + n).substr(("" + n).length));
	}
	return format;
};