
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Study Subjects</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Report Analytics</a></li>
		<li class="active">Study Subjects</li>
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
									<span>Study Subjects</span> Table
								</h2>
							</div>
							<div class="l-box-body">
								<table id="dataTableId" cellspacing="0" width="100%"
									class="display">
									<thead>
										<tr>
											<th>Subject Id</th>
											<th>Hospital</th>
											<th>Place</th>
											<th>Doctor Name</th>
											<th>Enrolled Date</th>
											<th>Completed Visits</th>
											<!-- <th>View Details</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach var="patientsList" items="${patientsList}">
											<tr>
												<td>${patientsList.patientId}</td>
												<td>${patientsList.hospital}</td>
												<td>${patientsList.location}</td>
												<td>${patientsList.doctorName}</td>
												<td>${patientsList.joinedDate}</td>
												<td>${patientsList.completedVisits}</td>
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
