<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../common/header.jsp"/>
<section>
	<form action="noticeWrite.do" method="post">
		<input type="hidden" name="notice_author" value="${member.name}"/>
		<table>
			<tr>
				<th colspan="2"><h1>공지글 작성</h1></th>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${member.name}</td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>
				<select name="notice_category">
					<option value="공지" selected>공지</option>
					<option value="알림">알림</option>
					<option value="이벤트">이벤트</option>
					<option value="당첨">당첨</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="notice_title"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="notice_content" id="editor"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="작성완료"/></td>
			</tr>
		</table>
	</form>
</section>
 <script src="https://cdn.tiny.cloud/1/obo1ajb9mpoksni5xtbim22h1w9bchmzywkov7jn9ee0ghnl/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
 <script>
 	var plugins = ["textcolor","link","image"];
 	var edit_toolbar = 'link image forecolor backcolor';
    tinymce.init({
      language : "ko_KR",	// 한글로 변경
      width : 600,
      height : 500,
      menubar : false,
      selector: '#editor',
      plugins: plugins,
      toolbar: edit_toolbar,
      toolbar_mode: 'floating',
      tinycomments_mode: 'embedded',
      tinycomments_author: 'Author name',
      /* image-upload */
      image_title : true,
      automatic_uploads : true,
      file_picker_types : "image",
      file_picker_callback : function(cb,value,meta){
    	  var input = document.createElement("input");
    	  input.setAttribute("type","file");
    	  input.setAttribute("accept","image/*");
    	  
    	  input.onchange = function(){
    		  var file = this.files[0];
    		  var reader = new FileReader();
    		  reader.onload = function(){
    			var id = "blobid"+(new Date()).getTime();
    			var blobCache = tinymce.activeEditor.editorUpload.blobCache;
    			var base64 = reader.result.split(',')[1];
    			var blobInfo = blobCache.create(id,file,base64);
    			blobCache.add(blobInfo);
    			cb(blobInfo.blobUri(),{title : file.name});
    		  };
    		  reader.readAsDataURL(file);
    	  };
    	  input.click();
      }
    /* image upload end */
    });
  </script>
<jsp:include page="../../common/footer.jsp"/>
