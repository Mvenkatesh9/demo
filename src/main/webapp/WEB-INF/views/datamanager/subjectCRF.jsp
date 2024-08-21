
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<style>
.questions {
	float: right;
}
</style>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Subject CRF - ${patientId}</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Subject CRF</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">CRF Visit List</li>
				</ul>
				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Subject CRF </span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Title</th>
												<th>Query Status</th>
												<th>Filled Date</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="dataList" items="${dataList}">
												<tr>
													<td>${dataList.visitName}<input type="button"
														class="questions"
														id="${dataList.sectionId}|${dataList.categoryId}"
														class="btn " value="VIEW FIELDS"></td>
													<td class="queryOption"><input
														value="${dataList.doctorStatus}" class="qStatus"
														style="display: none"> ${dataList.doctorStatus}</td>
													<td>${dataList.createdDate }</td>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>
					<form action="../datamanager/subjectFields" id="questions"
						method="get" enctype="multipart/form-data">
						<input type="hidden" id="sectionId" name="sectionId" value="" />
						<input type="hidden" id="categoryId" name="categoryId" value="" />
						<input type="hidden" id="patientId" name="patientId"
							value="${patientId}" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(".questions").click(function() {
			var id = $(this).attr("id");
			if (id == '' || id === undefined) {
				alert("Please select a Section Id");
			} else {
				var res = id.split("|");
				var secId = res[0];
				var catId = res[1];
				$("#sectionId").val(secId);
				$("#categoryId").val(catId);
				$("#questions").submit();
			}
		});
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			var queryVal = document.getElementsByClassName("qStatus");
			var queryOption = document.getElementsByClassName("queryOption");
			for (var i = 0; i < queryVal.length; i++) {
				if (queryVal[i].value == "Query Raised") {
					queryOption[i].style.backgroundColor = "#ff0000";
					queryOption[i].style.color = "#fff";
				}
			}
			$('#dataTableId').dataTable({
				"order" : []
			});
		});
	</script>
</div>

