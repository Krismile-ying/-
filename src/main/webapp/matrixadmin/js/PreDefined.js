 /**
 * 
 */
// ��ȡjsp�����get�Ͳ���
function getUrlParam(url, name) {
	var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g"); // ��ȡҳ���url����
	var matcher = pattern.exec(url);
	var items = null;
	if (matcher != null) {
		try {
			items = decodeURIComponent(decodeURIComponent(matcher[1]));
		} catch (e) {
			try {
				items = decodeURIComponent(matcher[1]);
			} catch (e) {
				items = matcher[1];
			}
		}
	}
	return items;
}

// ֮��ѧԺ����վ����ʽ���ߺ�Ҫ�ĳ�https://sdsy.zzjc.edu.cn
var HostExcelFilePathUrl = "http://127.0.0.1:8080/SDSYwa/excleFile/";
