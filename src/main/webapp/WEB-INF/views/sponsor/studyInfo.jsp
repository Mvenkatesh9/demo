
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Page Summary Widget-->
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td, b {
	padding: 5px;
	text-align: left;
	font-size: 18px;
	font-family: poppins;
	width: 5%;
	line-height: 30px;
}
</style>
	<h2 class="l-page-title">
		<span>Study Information</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Study Information</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div id="tables" class="resp-tabs-skin-1">
			<ul class="resp-tabs-list">
				<li id="nlist">Information</li>
			</ul>

			<div class="resp-tabs-container">
				<!-- Default Table-->
				<div>
					<!-- slider -->
					<div class="l-row l-spaced">
						<div class="row">
							<div class="col-md-12 doctor-dash">
								<div class="col-md-12">
									<table style="width: 100%">
										<caption>Study Information</caption>
										<tr>
											<th>Study Name</th>
											<th><b> ${studyData.studyName} </b></th>
										</tr>
										<tr>
											<td>No.of Sites</td>
											<td><b> 4 </b></td>
										</tr>
										<tr>
											<td>No.of Countries</td>
											<td><b> 1 </b></td>
										</tr>
										<tr>
											<td>Study Start Date</td>
											<td><b> ${studyData.startDate} </b></td>
										</tr>
										<tr>
											<td>Study Approximate End Date</td>
											<td><b> ${studyData.endDate} </b></td>
										</tr>
										<tr>
											<td>Study Status</td>
											<td><b> ${studyData.studyStatus} </b></td>
										</tr>
										<tr>
											<td>Phase of The Study</td>
											<td><b> IV </b></td>
										</tr>
										<tr>
											<td>Therapeutic Area</td>
											<td><b>General Medicine </b></td>
										</tr>
										<tr>
											<td>Subject Treatment Duration</td>
											<td><b> ${studyData.patientDuration} Days</b></td>
										</tr>
										<tr>
											<td>No.of Visits</td>
											<td><b> 5 </b></td>
										</tr>
										<tr>
											<td>Point of Contact Name</td>
											<td><b> Venkat Nuthanapati</b></td>
										</tr>
										<tr>
											<td>Contact Number</td>
											<td><b> +917095486457</b></td>
										</tr>
										<tr>
											<td>Contact Email</td>
											<td><b> venkat@Cliniv.in</b></td>
										</tr>
									</table>

								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<script>
	$('#id').on('click', function() {
		$('#image').trigger('click');

	});
</script>



