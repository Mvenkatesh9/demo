
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Query Details</span>
	</h2>
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Query Details</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">Query Details</li>
				</ul>

				<div class="resp-tabs-container">
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Query Details List</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Subject Id</th>
												<th>Question Name</th>
												<th>Original Answer</th>
												<th>Modified Answer</th>
												<th>Comments</th>
												<th>From User Role</th>
												<th>Status</th>
												<th>Created Date</th>
												<th>Update Status</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="dataList" items="${dataList}">
												<tr>
													<td>${dataList.patientId}</td>
													<td>${dataList.questionName}</td>
													<td>${dataList.originalAnswer}</td>
													<td>${dataList.modifiedAnswer}</td>
													<td>${dataList.comments}</td>
													<td>${dataList.updatedFrom}<input class="updatedFromF"
														name="" value="${dataList.updatedFrom}"
														style="display: none" />
													</td>
													<td>${dataList.status}<input class="statusF" name=""
														value="${dataList.status}" style="display: none" /></td>
													<td>${dataList.createdDate}</td>
													<td><input type="button" class="closeQuery"
														id="${dataList.detailId}|${dataList.queryId}" class="btn "
														value="CLOSE QUERY"> <input type="button"
														class="raiseQuery"
														id="${dataList.detailId}|${dataList.queryId}" class="btn "
														value="RAISE QUERY"></td>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>

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
						role="form" action="../datamanager/updateQuery" method="post"
						enctype="multipart/form-data">

						<div class="form-group row">
							<div class="col-sm-6" style="display: none">
								<!-- Patient Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="queryDetailId" id="queryDetailIdR" value="">
								</div>
							</div>
							<div class="col-sm-6" style="display: none">
								<!-- Visit Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="queryId" id="queryIdR" value="">
								</div>
							</div>
							<div class="col-sm-6" style="display: none">
								<!-- Question Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="status" id="status" value="OPEN">
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
					<button type="button" class="closeQDialog btn btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal" id="queryCloseModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-lg"
			style="max-height: -webkit-fill-available; overflow: scroll;">
			<div class="modal-content"
				style="background-color: #a2a2a2 !important">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Close Query</h5>
				</div>
				<div class="modal-body">
					<form id="closeQueryForm" class="form-horizontal validate"
						role="form" action="../datamanager/updateQuery" method="post"
						enctype="multipart/form-data">

						<div class="form-group row">
							<div class="col-sm-6" style="display: none">
								<!-- Patient Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="queryDetailId" id="queryDetailId" value="">
								</div>
							</div>
							<div class="col-sm-6" style="display: none">
								<!-- Visit Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="queryId" id="queryId" value="">
								</div>
							</div>
							<div class="col-sm-6" style="display: none">
								<!-- Question Id -->
								<div class="col-sm-8">
									<input type="text" class="form-control" required="required"
										name="status" id="status" value="CLOSED">
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
								<label class="col-sm-4 control-label" for="basicSelect"></label>
								<div class="col-sm-8">
									<button type="submit" class="btn btn-green" id="rejDoc"
										name="buttonName" value="Create" form="closeQueryForm">Submit</button>
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
		var updatedFrom = document.getElementsByClassName("updatedFromF");
		var status = document.getElementsByClassName("statusF");
		var raiseQuery = document.getElementsByClassName("raiseQuery");
		var closeQuery = document.getElementsByClassName("closeQuery");
		for (var i = 0; i < updatedFrom.length; i++) {
			if (status[i].value == "OPEN") {
				if (updatedFrom[i].value != "ROLE_DATA_MANAGER") {
					raiseQuery[i].style.display = "block";
					closeQuery[i].style.display = "block";
				} else {
					raiseQuery[i].style.display = "none";
					closeQuery[i].style.display = "none";
				}
			} else {
				raiseQuery[i].style.display = "none";
				closeQuery[i].style.display = "none";
			}
		}
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
			var detId = res[0];
			var queId = res[1];
			var detailId = document.getElementById("queryDetailIdR");
			var queryId = document.getElementById("queryIdR");
			detailId.value = detId;
			queryId.value = queId;
			modal.style.display = "block";
		}
	})
</script>
<script type="text/javascript">
	$(".closeQDialog").click(function() {
		var modal = document.getElementById("queryModal");
		modal.style.display = "none";
	})
</script>

<script type="text/javascript">
	$(".closeQuery").click(function() {
		var modal = document.getElementById("queryCloseModal");
		var id = $(this).attr("id");
		if (id == '' || id === undefined) {
			alert("Please select a Site Id");
		} else {
			var res = id.split("|");
			var detId = res[0];
			var queId = res[1];
			var detailId = document.getElementById("queryDetailId");
			var queryId = document.getElementById("queryId");
			detailId.value = detId;
			queryId.value = queId;
			modal.style.display = "block";
		}
	})
</script>
<script type="text/javascript">
	$(".closeDialog").click(function() {
		var modal = document.getElementById("queryCloseModal");
		modal.style.display = "none";
	})
	$(document).ready(function() {
		$('#dataTableId').dataTable({
			"order" : []
		});
	});
</script>