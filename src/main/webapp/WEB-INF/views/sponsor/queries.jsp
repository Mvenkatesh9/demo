
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Queries</span>
	</h2>
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">All Queries</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">Queries List</li>
				</ul>

				<div class="resp-tabs-container">
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Queries</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Subject Id</th>
												<th>Form Name</th>
												<th>Question Name</th>
												<th>Query Status</th>
												<th>Created Date</th>
												<th>View Details</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="dataList" items="${dataList}">
												<tr>
													<td>${dataList.patientId}</td>
													<td>${dataList.visitName}</td>
													<td>${dataList.questionName}</td>
													<td>${dataList.status}</td>
													<td>${dataList.createdDate}</td>
													<td><input type="button" class="details"
														id="${dataList.queryId}" class="btn " value="VIEW DETAILS"></td>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>
					<form action="../sponsor/queryDetails" id="details"
						method="get" enctype="multipart/form-data">
						<input type="hidden" id="queryId" name="queryId" value="" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(".details").click(function() {
			var id = $(this).attr("id");
			if (id == '' || id === undefined) {
				alert("Please select a Section Id");
			} else {
				var res = id.split("|");
				var secId = res[0];
				var secName = res[1];
				$("#queryId").val(secId);
				$("#details").submit();
			}
		})
		$(document).ready(function() {
			$('#dataTableId').dataTable({
				"order" : []
			});
		});
	</script>
</div>

