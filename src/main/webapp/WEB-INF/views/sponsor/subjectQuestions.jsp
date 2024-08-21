
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>${questionsData.title} Form Fields - ${patientId}</span>
	</h2>
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Form Fields</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">${questionsData.title}FormFields</li>
				</ul>

				<div class="resp-tabs-container">
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Form Field</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Field Name</th>
												<th>Value</th>
												<th>Remarks</th>
												<th>Field Type</th>
												<th>Field Length</th>
												<th>Query Status</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="questionsList"
												items="${questionsData.questions}">
												<tr>
													<td>${questionsList.questionTitle}</td>
													<td>${questionsList.answer}</td>
													<td>${questionsList.remarks}</td>
													<td>${questionsList.questionType}</td>
													<td>${questionsList.length}</td>
													<td class="queryOption">${questionsList.options}<input
														class="qStatus" name="" value="${questionsList.options}"
														style="display: none" /> </td>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<form action="../sponsor/subjectQueries" id="viewQueries"
						method="get" enctype="multipart/form-data">
						<input type="hidden" id="questionId" name="questionId" value="" />
						<input type="hidden" id="patientId" name="patientId"
							value="${patientId}" />
					</form>

				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="queryModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-lg"
			style="max-height: -webkit-fill-available; overflow: scroll;">
			<div class="modal-content"
				style="background-color: #a2a2a2 !important">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Raise New Query</h5>
				</div>
				<div class="modal-body">
					<form id="qaiseQueryForm" class="form-horizontal validate"
						role="form" action="../datamanager/createQuery" method="post"
						enctype="multipart/form-data">

						<div class="form-group row">
							<div class="col-sm-6" style="display: none">
								<!-- Patient Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="patientId" id="patientId" value="${patientId}">
								</div>
							</div>
							<div class="col-sm-6" style="display: none">
								<!-- Visit Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="sectionId" id="sectionId" value="${sectionId}">
								</div>
							</div>
							<div class="col-sm-6" style="display: none">
								<!-- Visit Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="categoryId" id="categoryId" value="${categoryId}">
								</div>
							</div>
							<div class="col-sm-6" style="display: none">
								<!-- Question Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="questionId" id="questionIdF" value="">
								</div>
							</div>
							<div class="col-sm-6">
								<label class="col-sm-4 control-label" for="title">Add
									Comments <font color="red">*</font>
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="comments" id="comments">
								</div>
							</div>

						</div>
						<div class="form-group row">

							<div class="col-sm-6">
								<label class="col-sm-4 control-label" for="title">Target
									Role <font color="red">*</font>
								</label> <select id="assignedRole" name="assignedRole" class="col-sm-8"
									required="required">
									<option value="SITE">Site User</option>
								</select>
							</div>

						</div>
						<div class="form-group row">
							<div class="col-sm-6">
								<label class="col-sm-4 control-label" for="basicSelect"></label>
								<div class="col-sm-8">
									<button type="submit" class="btn btn-green" id="rejDoc"
										name="buttonName" value="Create" form="qaiseQueryForm">Submit</button>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="closeDialog btn btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var queryVal = document.getElementsByClassName("qStatus");
		var queryOption = document.getElementsByClassName("queryOption");
		var raiseQuery = document.getElementsByClassName("raiseQuery");
		for (var i = 0; i < queryOption.length; i++) {
			if (queryVal[i].value == "Query Raised") {
				queryOption[i].style.backgroundColor = "#ff0000";
				queryOption[i].style.color = "#fff";
				raiseQuery[i].value = "VIEW QUERY";
				raiseQuery[i].style.color = "#000";
			} else {
				raiseQuery[i].value = "RAISE QUERY";
			}
		}
		$('#dataTableId').dataTable({
			"order" : []
		});
	});
</script>

<script type="text/javascript">
	$(".raiseQuery").click(function() {
		var modal = document.getElementById("queryModal");
		var id = $(this).attr("id");
		if (id == '' || id === undefined) {
			alert("Please select a Site Id");
		} else {
			var res = id.split("|");
			var quesId = res[0];
			var qStatus = res[1];
			if (qStatus == "Query Raised") {
				modal.style.display = "none";
				$("#questionId").val(quesId);
				$("#viewQueries").submit();
			} else {
				modal.style.display = "block";
				$("#questionIdF").val(quesId);
			}
		}
	})
</script>
<script type="text/javascript">
	$(".closeDialog").click(function() {
		var modal = document.getElementById("queryModal");
		modal.style.display = "none";
	})
</script>
