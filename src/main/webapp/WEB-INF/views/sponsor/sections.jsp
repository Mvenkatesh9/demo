
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
		<span>CRF</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">CRF</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">CRF List</li>
				</ul>
				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>CRF Section</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Title</th>
												<th>Description</th>
												<th>Created Date</th>
												<!-- <th>View Details</th> -->
											</tr>
										</thead>
										<tbody>
											<c:forEach var="dataList" items="${dataList}">
												<tr>
													<td>${dataList.title}<input type="button"
														class="questions"
														id="${dataList.sectionId}|${dataList.title}" class="btn "
														value="VIEW FIELDS"></td>
													<td>${dataList.description}</td>
													<td>${dataList.createdDate }</td>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>
					<form action="../sponsor/formFields" id="questions" method="get"
						enctype="multipart/form-data">
						<input type="hidden" id="sectionId" name="sectionId" value="" />
						<input type="hidden" id="sectionName" name="sectionName" value="" />
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
				var secName = res[1];
				$("#sectionId").val(secId);
				$("#sectionName").val(secName);
				$("#questions").submit();
			}
		});
		$(document).ready(function() {
			$('#dataTableId').dataTable({
				"order" : []
			});
		});
	</script>
</div>

