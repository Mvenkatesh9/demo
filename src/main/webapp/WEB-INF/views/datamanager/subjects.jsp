
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Subjects</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Subjects List</li>
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
									<span>Subjects</span> Table
								</h2>
							</div>
							<div class="l-box-body">
								<table id="dataTableId" cellspacing="0" width="100%"
									class="display">
									<thead>
										<tr>

											<th>Subject Id</th>
											<th>Status</th>
											<th>Registered Date Time</th>
											<th>Site Name</th>
											<th>VIEW CRF</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="subjectsList" items="${subjectsList}">
											<tr>
												<td>${subjectsList.patientId}</td>
												<td>${subjectsList.status}</td>
												<td>${subjectsList.registeredDate}</td>
												<td>${subjectsList.siteName}</td>
												<td><input type="button" class="questions"
													id="${subjectsList.patientId}" class="btn "
													value="VIEW CRF"></td>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<form action="../datamanager/subjectCrf" id="questions"
								method="get" enctype="multipart/form-data">
								<input type="hidden" id="patientId" name="patientId" value="" />

							</form>
						</div>
					</div>
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
				$("#patientId").val(id);
				$("#questions").submit();
			}

		})

		$(document).ready(function() {
			$('#dataTableId').dataTable({
				"order" : []
			});
		});
	</script>
</div>
