		   $(document).keypress(function(e) {  
			    // 回车键事件  
			       if(e.which == 13) {  
			    	   search();
			       }  
		   }); 	
 
		var jsonData;  
		function ajax(_url,_tab,complete){
			var op={
					url:_url,
					data:{tab:_tab},
				    type:'GET', //GET  
				    timeout:10000,    //超时时间
				    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
				    beforeSend:function(xhr){ 
				    },
				    success:function(data,textStatus,jqXHR){ 
				    	jsonData = data;
				    	//console.log(data);
				    	var s = document.getElementById('teach-s'); 
				    	setMaxColspan(s,data);
				     
				    	completeProByState(0);
						soft(0);   
						complete();
				    },
				    error:function(xhr,textStatus){ 
				    },
				    complete:function(){  
				    }
				};
				$.ajax(op);
		}
		function setMaxColspan(s,data){
			var max = 1;
			for(var i=0;i<data.length;i++){
				if(data[i].schedules.length>max){
					max = data[i].schedules.length;
				}
			} 
			s.setAttribute("colspan",max); 
		}
		$("#select").on("change",function(){
			document.getElementById('tbody').innerHTML='';		//clear
			completeProByState($('#select').prop('selectedIndex'));
			soft($('#select2').prop('selectedIndex')); 
			setTBody(sortArr);  
		});
		
		function completeProByState(index){ 
			for(var i=0;i<jsonData.length;i++){
				//jsonData[i].textBookStr = getStrByState(jsonData[i].textBookState,index);
				jsonData[i].cssClassName = getStrByState(jsonData[i].state,index);
				setScheduleListStr(jsonData[i].schedules,index);
			} 
		}
		function setScheduleListStr(list,index){
			for(var i=0;i<list.length;i++){ 
				list[i].cssClassName = getStrByState(list[i].state,index); 
			} 
		}
		/**
		 * @param docState
		 * @param index
		 * @returns {String}
		 */
		function getStrByState(docState,index ) {
			var str;
			switch (index) {
			case 0: 
				str=getStrByDocState(4,docState);
				break;
			case 1:
				str=getStrByDocState(19,docState); 
				break;
			case 2:
				str=getStrByDocState(7,docState); 
				break;
			default:
				str="remove";
				break;
			}
			return str;
		}
		function getStrByDocState(state,docState){
			if( docState >= state  && docState <= 19 ) 
				return "ok";
			else
				return "remove";
		}
		
		 
		function search(){
			 var type=$(".search-button").data('type');
			 var keyWord = document.getElementById('search-query').value;
			 if(keyWord==""){ 
				 setQueryResult(type,jsonData);
				 return;
			 }
			 var arr = getQueryResult(keyWord);
			 setQueryResult(type,arr);
		}
		 
		 function getQueryResult(key){ 
			 if(jsonData=== undefined )
				 return;
			 var arr=[]; 
			 for(var i=0;i<jsonData.length;i++){
				 if(jsonData[i].name.indexOf(key)>=0){
					 arr[arr.length]=jsonData[i];  
				 }
			 }
			 return (arr);
		 }
		 
		 function setQueryResult(type,arr){ 
			 document.getElementById('tbody').innerHTML=''; 
			 if(type=='teach'){
				 insertClassTbody(arr);
			 }else if(type='syllabus'){
				 insertUserTbody(arr);
			 }
		 }
		 
		 function insertClassTbody(data){   
		    var tb = document.getElementById('tbody');
			 for(var i =0; i<data.length;i++){
		    		var row = tb.insertRow(tb.rows.length);
		    		var c1 = row.insertCell(0);
		    		c1.innerHTML =Number(i+1);
		    		c1.style="vertical-align: middle; width: 50px;";
		    		c1.setAttribute("rowspan",2); 
		    		var c2 = row.insertCell(1);  
		    		c2.innerHTML =data[i].name;
		    		c2.style="vertical-align: middle; width: 50px;";
		    		c2.setAttribute("rowspan",2); 

		    		var c3 = row.insertCell(2);
		    		c3.innerHTML ='<td>教材</td>';
		    		
		    		var c4 = row.insertCell(3);
		    		c4.innerHTML ='<td>教学大纲</td>';

		    		for(var j=0; j<data[i].schedules.length;j++){ 
		    			var cn = row.insertCell(row.length);
		    			cn.innerHTML ='<td>'+data[i].schedules[j].shift+'</td>';
		    		} 
		    		var row2=tb.insertRow(tb.rows.length);
		    		 
		    		var r2c1 = row2.insertCell(0);
		    		r2c1.innerHTML='<td><span class="glyphicon glyphicon-remove"></span></td>';
		    		 
		    		var r2c2 = row2.insertCell(1);
		    		r2c2.innerHTML='<td><span class="glyphicon glyphicon-'+data[i].cssClassName+'"></span></td>';

		    		
		    		for(var j=0; j<data[i].schedules.length;j++){ 
		    			var r2cn = row2.insertCell(row2.length);
		    			r2cn.innerHTML ='<td><span class="glyphicon glyphicon-'+data[i].schedules[j].cssClassName+'" ></span></td>';
		    			r2cn.setAttribute("class",'schedule'); 
		    			r2cn.setAttribute("title",data[i].schedules[j].teacherName);  
		    		} 
		    	} 
		 }
		  
			function insertUserTbody(data){
			    var tb = document.getElementById('tbody');
				for(var i =0; i<data.length;i++){
			    		var row = tb.insertRow(tb.rows.length);
			    		var c1 = row.insertCell(0);
			    		c1.innerHTML =Number(i+1);
			    		c1.style="vertical-align: middle; width: 50px;";
			    		c1.setAttribute("rowspan",2); 
			    		var c2 = row.insertCell(1);  
			    		c2.innerHTML =data[i].name;
			    		c2.style="vertical-align: middle; width: 50px;";
			    		c2.setAttribute("rowspan",2); 
			    		/**
			    		 * teacherName 在这里是 课程名
			    		 */
			    		for(var j=0; j<data[i].schedules.length;j++){
			    			var index = Number(j)+2;
			    			var c4 = row.insertCell(index);
			    			var name = data[i].schedules[j].teacherName;
			    			if(name.length>4){
			    				name = name.substring(0,4);
			    			}
			    			c4.innerHTML ='<td><abbr title="'+data[i].schedules[j].teacherName+'">'+name+'-'+data[i].schedules[j].shift+'</abbr></td>';
			    		} 
			    		var row2=tb.insertRow(tb.rows.length);
			    		for(var j=0; j<data[i].schedules.length;j++){ 
			    			var c4 = row2.insertCell(j);
			    			c4.innerHTML ='<td><span class="glyphicon glyphicon-'+data[i].schedules[j].cssClassName+'" title="'+data[i].schedules[j].teacherName+'" ></span></td>';
			    		} 
			    	}
			}
			
			/**
			 * 排序
			 * soft
			 */
			$("#select2").on("change",function(){
				soft($('#select2').prop('selectedIndex')); 
			}); 
			
			var sortArr;
			function soft(typeIndex){
				sortArr =objArrDeepCopy(jsonData); 
				if(typeIndex==0){ 
					sortArr.sort(function(a,b){ 
					    return a.name.localeCompare(b.name); 
					});  
				}else if(typeIndex==1){
					sortBody(sortArr , sortByCompleteCount);
				}else if(typeIndex==2){
					sortBody(sortArr , sortByUnCompleteCount);
				}else if(typeIndex==3){
					sortBody(sortArr , sortByTotleCount);
				}  
				setTBody(sortArr);
			}
				
			
			 function setTBody(arr){
				 var type=$(".search-button").data('type');
				 setQueryResult(type,arr);
			 }
			
			function sortBody(arr,func){
				var maxIndex = 0;
				for(var i=0;i<arr.length - 1 ;i++){
					for(var j=i+1;j<arr.length;j++){
						if(func(arr[j],arr[maxIndex])){
							maxIndex = j ;
						}
					}
					if(i != maxIndex){
						var temp = objDeepCopy(arr[maxIndex]);
						arr[maxIndex] = arr[i];
						arr[i] = temp ;
					}
				}	
			}
			function sortByCompleteCount(a,b){
				if(getCountByCom(a,'ok')>getCountByCom(b,'ok'))
					return true;
				else
					return false;
			}
			function sortByUnCompleteCount(a,b){
				if(getCountByCom(a,'remove')>getCountByCom(b,'remove'))
					return true;
				else
					return false;
			}
			
			function getCountByCom(a,Tstr){
				var count = 0 ;
				var sch = a.schedules;
				for(var i=0;i < sch.length;i++){
					if(sch[i].cssClassName==Tstr){
						count ++;
					}
				} 
				if(a.cssClassName==Tstr){
					count ++;
				} 
				return count;
			}
			
			function sortByTotleCount(a,b){
				if(a.schedules.length>b.schedules.length)
					return true;
				else
					return false;
			} 
			
			//排序颠倒
			function sortTurn(){ 
				if(sortArr=== undefined ){
					sortArr = objArrDeepCopy(jsonData); 
				} 
				setTBody(sortArr.reverse());
			}
			
			
			/**
			 * 深拷贝
			 */
			//对象
			var objDeepCopy = function(source){
			    var sourceCopy = {};
			    for (var item in source) sourceCopy[item] = source[item];
			    return sourceCopy;
			}
			//对象数组
			var objArrDeepCopy = function (source) {
			    var sourceCopy = source instanceof Array ? [] : {};
			    for (var item in source) {
			        sourceCopy[item] = typeof source[item] === 'object' ? objDeepCopy(source[item]) : source[item];
			    }
			    return sourceCopy;
			}
			