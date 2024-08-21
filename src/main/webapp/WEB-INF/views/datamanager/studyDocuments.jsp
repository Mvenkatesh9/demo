
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Page Summary Widget-->
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Study Documents</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Study Documents</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div id="tables" class="resp-tabs-skin-1">
			<ul class="resp-tabs-list">
				<li id="nlist">Study Documents</li>
			</ul>

			<div class="resp-tabs-container">
				<!-- Default Table-->
				<div>
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Study Documents</span> Table
								</h2>
							</div>
							<div class="l-box-body">
								<table id="dataTableId" cellspacing="0" width="100%"
									class="display">
									<thead>
										<tr>
											<th>Document Title</th>
											<th>File Type</th>
											<th>View Document</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="docsList" items="${docsList}">
											<tr>
												<td>${docsList.title}</td>
												<td>${docsList.fileType}</td>
												<td><a href="${docsList.fileUrl }" target="_blank">
														OPEN </a></td>
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
          $('#id').on('click', function() {
        	    $('#image').trigger('click');
        	   
        	});
          $(document).ready( function () {
        	  $('#dataTableId').dataTable({
        	    "order": []
        	  });
        	});
          </script>
<!-- slider -->
<!-- Row 5 - Gender, Age-->


