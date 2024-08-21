       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- Page Summary Widget-->
    <c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Study FAQ</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Study FAQ</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">
    
    <div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">Study FAQ</li>
					<li id="vscrollTable">Add Study FAQ</li>
				</ul>

				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Study FAQ</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>FAQ Title</th>
												<th>Answer</th>
											
											</tr>
										</thead>
										<tbody>
											<c:forEach var="faqsList" items="${faqsList}">
												<tr>
													<td>${faqsList.question}</td>
													<td>${faqsList.answer}</td>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>
					
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Add </span> Study FAQ
								</h2>
							</div>
							<div class="l-box-body l-spaced">
								<form id="createDocument" class="form-horizontal validate"
									role="form" action="#"
									method="post" enctype="multipart/form-data">

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">FAQ
												Title<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="title" id="title">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">FAQ
												Answer<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="answer" id="answer">
											</div>
										</div>
									</div>
									
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="basicSelect"></label>
											<div class="col-sm-8">
												<button type="submit" class="btn btn-green"
													name="buttonName" value="Create"
													onclick="return fnSubmit(this);" form="createDocument">Create</button>
											</div>
										</div>
									</div>


								</form>

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
        <!-- slider -->
      <!-- Row 5 - Gender, Age-->
        

