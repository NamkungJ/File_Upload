<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<button id="uploadBtn">Submit</button>
	
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; // 5MB
		
		function checkExtension(fileName, fileSize) {
			
			if(fileSize >= maxSize){
				alert("5MB 이상의 파일을 업로드할 수 없습니다.");
				return false;
			}
			if(regex.test(fileName)) {
				alert(".exe, .sh, .zip, .alz형식의 파일을 업로드할 수 없습니다.");
				return false;
			}
			return true;
		}
		
		$('#uploadBtn').on("click", function(e){
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			
			for(var i=0; i<files.length; i++){
				
				if(!checkExtension(files[i].name, files[i].size)) {
					return false;	
				}
				
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url: '/fileupload/uploadAjaxAction',
					processData: false,
					contentType: false,
					data: formData,
					type: 'POST',
					success: function(result){
						alert("uploaded");
					}
			});
			
		});
	});
</script>
</html>
