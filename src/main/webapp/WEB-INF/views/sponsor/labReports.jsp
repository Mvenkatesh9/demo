
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Subject Lab Reports</span>
	</h2>
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Subject Lab Reports</li>
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
									<span>Subject Lab Reports</span> Table
								</h2>
							</div>
							<div class="l-box-body">
								<table id="dataTableId" cellspacing="0" width="100%"
									class="display">
									<thead>
										<tr>
											<th>Subject Id</th>
											<th>Test Name</th>
											<th>Test Value</th>
											<th>Test Report</th>
											<th>CreatedDate</th>

											<!-- <th>View Details</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach var="labsList" items="${labsList}">
											<tr>
												<td>${labsList.patientId}</td>
												<td>${labsList.testName}</td>
												<td>${labsList.testValue}</td>
												<td><a class=" view_lab" href="${labsList.fileURL}"
													target="_blank"> VIEW </a></td>
												<td>${labsList.createdDate}</td>
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
<script>
$(document).ready(function() {
	$('#dataTableId').dataTable({
		"order" : []
	});
});</script>
