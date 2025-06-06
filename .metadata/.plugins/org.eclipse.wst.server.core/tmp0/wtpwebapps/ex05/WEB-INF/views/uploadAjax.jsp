<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
	.uploadResult {
	    width: 100%;
	    background-color: gray;
	}

	.uploadResult ul {
	    display: flex;
	    flex-flow: row;
	    justify-content: center;
	    align-items: center;
	}
	
	.uploadResult ul li {
	    list-style: none;
	    padding: 10px;
	    align-content: center;
	    text-align: center;
	}
	
	.uploadResult ul li img {
	    width: 100px;
	}
	
	.uploadResult ul li img span{
	    color: white;
	}
	
	.bigPictureWrapper {
		position: absolute;
		display: none;
		justify-content: center;
		align-items: center;
		top: 0%;
		width: 100%;
		height: 100%;
		background-color: gray;
		z-index: 100;
		background: rgba(255, 255, 255, 0.5);
	}
	
	.bigPicture {
		position: relative;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.bigPicture img {
		width: 600px;
	}
</style>
</head>
<body>
	<h1>Upload with Ajax</h1>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple="multiple">
	</div>
	
	<div class="uploadResult">
		<ul>
		</ul>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<div class='bigPictureWrapper'>
	  <div class='bigPicture'>
	  </div>
	</div>
	
<script type="text/javascript">

	function showImage(fileCallPath) {
		$(".bigPictureWrapper").css("display", "flex").show();
		$(".bigPicture")
		  .html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>")
		  .animate({width: '100%', height: '100%'}, 1000);
	}; // end showImage

	$(document).ready(function() {
		
		let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$")
		let maxSize = 5242880; // 5MB
		
		$(".uploadResult").on("click", "span", function(e) {
			let targetFile = $(this).data("file");
			let type = $(this).data("type");
			
			$.ajax({
				url: '/deleteFile',
				data: {fileName: targetFile, type: type},
				dataType: 'text',
				type: 'post',
				
				success: function(result) {
					alert(result);
				}
			});
		});
		
		// 원본 이미지가 전체 화면을 차지하기 때문에 이미지 클릭시 사라지도록 하는 이벤트
		$(".bigPictureWrapper").on("click", function(e) {
			$(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
			setTimeout(()=>{
				$(this).hide();
			}, 1000);
		}); // end 원본 이미지가 전체 화면을 차지하기 때문에 이미지 클릭시 사라지도록 하는 이벤트
		// 파일 크기 초과, 등록 불가한 확장자를 올릴 경우 노출되는 문구
		function checkExtension(fileName, fileSize) {
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드 할 수 없습니다.");
				return false;
			}
			
			return true;
		}
		
		// 함수 만들기
		let uploadResult = $(".uploadResult ul");
		
		function showUploadFile(uploadResultArr) {
			let str = "";
			
			$(uploadResultArr).each(function(i, obj) {
				
				if(!obj.image){
					
					let fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName); 
					// 이미지 삭제
					let fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
		               
					str += "<li><div><a href='/download?fileName=" + fileCallPath + "'>"
					     + "<img src='/resources/img/attach.png'>" + obj.fileName + "</a>"
					     + "<span data-file=\'" + fileCallPath + "\' data-type='file'> x </span>" + "</div></li>";

				}else{
					let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
		               
		               let orginPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
		               
		               orginPath = orginPath.replace(new RegExp(/\\/g), "/");
		               
		               str += "<li><a href=\"javascript:showImage(\'" + orginPath + "\')\">" + 
			               "<img src='display?fileName=" + fileCallPath + "'></a>" +
			               "<span data-file=\'" + fileCallPath + "\' data-type='image'> x </span>"
			               + "</li>";
//					str += "<li>" + obj.fileName + "</li>"	
				}
			});
			
			uploadResult.append(str);
		} // end showUploadFile
		
		let cloneObj = $(".uploadDiv").clone(); // 파일 업로드 영역을 동적으로 추가
		
		$("#uploadBtn").on("click", function(e) { // 버튼을 클릭하면 동작
			let formData = new FormData();
			let inputFile = $("input[name='uploadFile']"); // inputFile : 업로드한 파일 정보를 가지고 있음
			
			let files = inputFile[0].files;
//			console.log(inputFile)
//			console.log("----------")
//			console.log(files)

			// 사진 저장 경로
			for(let i=0; i<files.length; i++){
				
				if(!checkExtension(files[i].name, files[i].size)){
					return false;
				}
				
				formData.append("uploadFile", files[i]);
			}
			console.log("formData");
			console.log(formData);
			
			$.ajax({
				url : "/uploadAjaxAction",
				type : "post",
				processData : false, // 필수 -> 데이터를 문자열로 변환하지 말라
				contentType : false, // contentType 설정하지 않음(자동으로 enctype="multipart/form-data" 변경해줌)
				data : formData,
				dataType : 'json', // 전달 받은 데이터 형식이 json
				success : function(result) {
					console.log(result);
					
					showUploadFile(result); // 호출
					$(".uploadDiv").html(cloneObj.html());
				}
			});
		});
	});
</script>
</body>
</html>