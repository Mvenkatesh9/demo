
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Adverse Events</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Adverse Events</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">



			<div class="resp-tabs-container">
				<!-- Default Table-->
				<div>
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Adverse Events</span> Table
								</h2>
							</div>
							<div class="l-box-body">
								<table id="dataTableId" cellspacing="0" width="100%"
									class="display">
									<thead>
										<tr>
											<th>Subject Id</th>
											<th>Adverse Event Created Date Time</th>
											<th>View Details</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="advList" items="${advList}">
											<tr>
												<td>${advList.title}</td>
												<td>${advList.createdDate}</td>
												<td><input type="button" class="events"
													id="${advList.title}|${advList.sectionId}" class="btn "
													value="VIEW"></td>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>
				<form action="../sponsor/adverseEvents" id="events" method="get"
					enctype="multipart/form-data">
					<input type="hidden" id="categoryId" name="categoryId" value="" />
					<input type="hidden" id="patientId" name="patientId" value="" />
				</form>
			</div>
		</div>
	</div>
</div>
<script>
	$(".events").click(function() {
		var id = $(this).attr("id");
		if (id == '' || id === undefined) {
			alert("Please select a Section Id");
		} else {
			var res = id.split("|");
			var secId = res[0];
			var catId = res[1];
			$("#patientId").val(secId);
			$("#categoryId").val(catId);
			$("#events").submit();
		}
	});
</script>
<script>
$(document).ready(function() {
	$('#dataTableId').dataTable({
		"order" : []
	});
});</script>