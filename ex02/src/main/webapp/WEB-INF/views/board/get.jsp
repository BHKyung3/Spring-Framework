<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Tables</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Read Page
            </div>
            <!-- /.panel-heading -->
          <div class="panel-body">
              
              <div class="form-group">
              	<label>Bno</label><input class="form-control" name="bno"
              			value="<c:out value='${board.bno}'/>" readonly="readonly">
              </div>
              <div class="form-group">
              	<label>Title</label><input class="form-control" name="title"
              			value="<c:out value='${board.title}'/>" readonly="readonly"> 
              </div>
              <div class="form-group">
              	<label>Text area</label>
              	<textarea rows="3" class="form-control" name="content" readonly="readonly">
              		<c:out value='${board.content}'/>
              	</textarea>
              </div>
              <div class="form-group">
              	<label>Writer</label><input class="form-control" name="writer"
              			value="<c:out value='${board.title}'/>" readonly="readonly">
              </div>
              	<button data-oper="modify" class="btn btn-info">Modify</button>
              	<button data-oper="list" class="btn btn-default">List</button>
              	
              	<form id="openForm" action="/board/modify" method="get">
              		<input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno}"/>'>
              		<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
              		<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
              		<input type="hidden" name="keyword" value=<c:out value="${cri.keyword}"/>>
              		<input type="hidden" name="type" value=<c:out value="${cri.type}"/>>
              	</form>
          </div>
          <!-- /.panel-body -->
      </div>
      <!-- /.panel -->
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- 이 코드는 동적 폼 처리 + 조건 분기를 가능 / 버튼 클릭에 따라 폼의 action을 동적으로 바꾸고 submit하는 기능을 만들기 위한 것 -->
<script type="text/javascript">
	$(document).ready(function(){
		let openForm = $("#openForm");
		
		$("button[data-oper='modify']").on("click", function(e){
			openForm.attr("action","/board/modify").submit();
		});
		$("button[data-oper='list']").on("click", function(e){
			openForm.find("#bno").remove();
			openForm.attr("action","/board/list").submit();
		});
	});
</script>

<%@ include file="../includes/footer.jsp" %>