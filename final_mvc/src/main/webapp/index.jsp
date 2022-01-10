<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="test">test</a>
<textarea id="editor"></textarea>
<input type="button" id="btn" value="확인"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
<%-- <jsp:forward page="test"/>
<a href="test1">test1</a>  --%>
<script>
	$(function(){
		$('#btn').click(function(){
			var content = tinymce.activeEditor.getContent();
			console.log(content);
		});
	});
</script>
</body>
</html>