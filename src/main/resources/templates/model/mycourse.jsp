<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<title>课程</title>
		<link rel="stylesheet" href="<%=basePath %>css/ctdms.css" />
		<jsp:include   page="left.jsp" flush="true"/> 
		<link rel="stylesheet" href="<%=basePath %>css/ctdms_course.css" />
	</head>
	
	<body>
	<div class="container module-frame"  id="iframe">  
			<div class="frame-title">
			<h5>预期课程表</h5>
			</div>
		<table id="table" class="fadeInUp animated" border="1" width="100%" style="border-collapse:collapse">
			<tbody> 
				<tr>
					<td class="cell1"></td>
					<td class="cell1">星期一</td>
					<td class="cell1">星期二</td>
					<td class="cell1">星期三</td>
					<td class="cell1">星期四</td>
					<td class="cell1">星期五</td>
					<td class="cell1">星期六</td>
					<td class="cell1">星期日</td>
				</tr>
				<tr>
					<td width="2%" class="morning">1</td>
					<td rowspan="4"></td>
					<td rowspan="4"></td>
					<td rowspan="4"></td>
					<td rowspan="4"></td>
					<td rowspan="4"></td>
					<td rowspan="4"></td>
					<td rowspan="4"></td>
				</tr>
				<tr>
					<td width="2%" class="morning">2</td>  
				</tr>
				<tr>
					<td width="2%" class="morning">3</td> 
				</tr>
				<tr>
					<td width="2%" class="morning">4</td> 
				</tr>
				<tr>
					<td width="2%" class="afternoon">5</td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
				</tr>
				<tr>
					<td width="2%" class="afternoon">6</td>
				</tr>
				<tr>
					<td width="2%" class="afternoon">7</td>
				</tr>
				<tr>
					<td width="2%" class="night">8</td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
					<td rowspan="3"></td>
				</tr>
				<tr>
					<td width="2%" class="night">9</td>
				</tr>
				<tr>
					<td width="2%" class="night">A</td>
				</tr>
			</tbody>
		</table>
		</div>
	</body>
	<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script> 
	<script type="text/javascript">
		var json ;
		var CLASSNAME_COURSE;
		var dc;
		var t;
		window.onload=function(){ 
			 $.get("mycourse",{},function(data){
   					json = data;
   					CLASSNAME_COURSE = 'course'; 
					dc = [4, 3, 3];		//早上 4 下午 3  晚上3
					t = document.getElementById("table");	//获得课程表element
   					course();
  				});
		};
		function course() {
			var ct = parse(json);
			//console.log(json); 
			sort(ct);
			//console.log(ct);
			insertTD(ct); 
		}
		//转换格式
		function parse(json){
			var ct = new Array(); 
			var k = 0;
			for(var i = 0; i < json.length; i++) {
				for(var j = 0; j < json[i].courseTimes.length; j++) {
					ct[k] = { courseIndex: i, courseTime: json[i].courseTimes[j] };
					k++;
				}
			}
			return ct;
		}
		//排序
		function sort(ct){
			for(var i = 0; i < ct.length - 1; i++) {
				var maxIndex = i;
				for(var j = i + 1; j < ct.length; j++) {
					if(ct[j].courseTime.day < ct[maxIndex].courseTime.day) {
						maxIndex = j;
					} else if(ct[j].courseTime.day == ct[maxIndex].courseTime.day) {
						if(ct[j].courseTime.classIndex < ct[maxIndex].courseTime.classIndex) {
							maxIndex = j;
						}
					}
				}
				if(maxIndex != i) {
					//交换
					var temp = objDeepCopy(ct[maxIndex]);
					ct[maxIndex] = ct[i];
					ct[i] = temp;
				}
			}
		}
		 
		//插入td
		function insertTD(ct){ 
			for(var i = 0; i < ct.length; i++) {  
				var temp = ct[i].courseTime; 
				var td = t.rows[temp.classIndex].cells[temp.day];  
				var start = getOffsetStart(temp.classTime);
				
				if(!td) { // 要插入的td 不存在
					//先尝试找到一个可以可提供插入大小的 td 并将其减小 temp.lastTime
					findOrInsertNewTD(ct,i,start); 
				} else {
					//找到的是 存在的course 就不覆盖 直接跳过.
					if(td.className == CLASSNAME_COURSE) {
						continue;
					}
					//判断 课程是否会超过 该时段的课程量 ,      不超过就得分割出td 填充
					if( temp.classIndex - start + temp.lastTime - 1< dc[temp.classTime] ) {
						var c1 = t.rows[temp.classIndex + temp.lastTime].insertCell(-1);
						c1.rowSpan = dc[temp.classTime] - temp.lastTime ; 
					}
					//修改当前找到的td  修改 
					innerHTML2TD(td,ct[i]);
				}
			}
		}
		function findOrInsertNewTD(ct,i,start){
				var temp = ct[i].courseTime;
				for(var j = 1; j <= dc[temp.classTime]; j++) { 
						var tds = t.rows[j + start].cells[temp.day];
						if(tds) {
							if(tds.className != CLASSNAME_COURSE && tds.rowSpan >=  temp.lastTime) {
								tds.rowSpan = tds.rowSpan -  temp.lastTime;
								break;
							}
						}
					}
					//由上面提供的rowSpan 
					//是否存在能找到的 之前分割的没利用过的td , 找到直接修改它, 没有就插入一个新的
					var isFind = false;
					for(var j = 1; j < t.rows[temp.classIndex].childNodes.length; j++) {
						var fft = t.rows[temp.classIndex].cells[j];
						//这个空的 可能不是 可利用的, 需要判断后一个cell 是否存在.存在即不可使用
						if( t.rows[temp.classIndex].cells[j+1] ){	
							continue;
						}
						if(fft && fft.className != 'course') {
						 	innerHTML2TD(fft,ct[i]);
							isFind = true;
							break;
						}
					}
					if(!isFind) {
						var td3 = t.rows[temp.classIndex].insertCell(-1);
						innerHTML2TD(td3,ct[i]); 
					}
		}
		function getOffsetStart(tlen){
				var start = 0; //起始点	-	
				for(var j = 0; j < tlen; j++) {
					start += dc[j];
				} 
				return start; 
		}
		//数据插入td
		function innerHTML2TD(td,cto){
			var course = json[cto.courseIndex].course ;
			var shift = json[cto.courseIndex].shift ;
			var courseTime = cto.courseTime;
			td.rowSpan = courseTime.lastTime;
			td.className = CLASSNAME_COURSE;
			td.innerHTML = '<span class="caption">课程代码：</span>' +
				'<span class="content">' + course.courseCode + '</span><br>' +
				'<span class="caption">课程名称：</span>' +
				'<span class="content">' + course.courseName + '</span><br>' +
				'<span class="caption">班号：</span>' +
				'<span class="content">' + shift + '</span><br>' +
				'<span class="caption">教室：</span>' +
				'<span class="content">' + courseTime.classRoom + '</span><br>' +
				'<span class="caption">授课校区：</span>' +
				'<span class="content">' + course.district + '</span><br>' +
				'<span class="caption">上课周次：</span>' +
				'<span class="content">' + course.weekly + '</span><br>';
		} 
		//Deep copy
		  function objDeepCopy(source) {
			var sourceCopy = {};
			for(var item in source) 
				sourceCopy[item] = source[item];
			return sourceCopy;
		}
	</script>
</html>