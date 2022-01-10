<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"/>
<section>
	<div>
		<div id="result"
		style="border:1px solid black;padding:10px;margin-top:10px;">
			<table border=1>
				<tr>
					<th>이름</th>
					<th>나이</th>
				</tr>
			</table>
		</div>
		<div>
			<input type="text" id="name"/> <br/>
			<input type="number" id="age"/> 
			<input type="button" id="get" value="get"/>
			<input type="button" id="post" value="post"/>
			<input type="button" id="gsonTest" value="gsonTest"/>
			<input type="button" id="xmlGet" value="xmlGet"/>
		</div>
	</div>
</section>
<script src="http://code.jquery.com/jquery-latest.min.js">
</script>
<script>
	$("#get").click(function(){
		var input_name = $("#name").val();
		var input_age = $("#age").val();
		/*
		$.get("user.test",	// url
				{name : input_name,age : input_age},	//parameter
				function(data){						    // callback
			console.log(data);
		});
		*/
		
		$.ajax({
			async : false,		// 동기 & 비동기 여부
			type : 'GET',		// 전송방식
			url : 'user.test',	// 요청ㅇ URl
			data : {			// 전달할 parameter
				name : input_name,
				age : input_age
			},
			// 요청 처리 결과 성공 시
			success : function(data){
				console.log(data);
				print(data);
			},
			// 요청 처리 결과 실패 시
			error : function(xhr,status,errorThrown){
				console.log(xhr);
			}
		});	// ajax 종료
		console.log("get click 종료");
	});	// click 종료
	
	// post click
	$("#post").click(function(){
		var input_name = $("#name").val();
		var input_age = $("#age").val();
		
		$.ajax({
			type : 'POST',
			url : "userList.test",
			data : {
				name : input_name,
				age : input_age
			},
			dataType : 'json',	// 서버에서 전달되는 데이터 타입
			success : function(result){
				console.log(result);
				for(var i=0; i<result.length; i++){
					print(result[i]);
				}
			}
		});
	});
	
	// gsonTest btn click
	$("#gsonTest").click(function(){
		$.ajax({
			type : 'post',
			url : 'gsontest.test',
			data : {
				name : $("#name").val(),
				age : $("#age").val()
			},
			dataType : 'json',
			success : function(result){
				console.log(result);
				$(result).each(function(){
					print(this);
				});
				//print(result);
			}
		});
	});
	
	
	function print(obj){
		let html = "<tr>";
		html+="<td>";
		html+= obj.name;
		html+="</td>";
		html+="<td>";
		html+= obj.age;
		html+="</td>";
		html+="<tr/>";
		$("#result table").append(html);
	}
	
	$("#xmlGet").click(function(){
		$.ajax({
			type : "GET",
			url : "userXML.test",
			data : {
				name : $("#name").val(),
				age : $("#age").val()
			},
			dataType : "xml",
			success : function(data){
				console.log(data);	
				var name = $(data).find("name").html();
				var age = $(data).find("age").html();
				var result = "<tr>";
				result += "<td>";
				result += name;
				result += "</td>";
				result += "<td>";
				result += age;
				result += "</td>";
				result += "</tr>";
				$("#result table").append(result);
			}
		});
	});
	
</script>
<jsp:include page="../common/footer.jsp"/>








