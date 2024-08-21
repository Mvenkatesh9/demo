
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Investigators</span> 
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Investigators</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">List of Investigators</li>
					<li id="vscrollTable">Create Investigator</li>
					<li id="vscrollTable">Upload Multiple Investigators</li>
				</ul>


				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Investigators</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Name</th>
												<th>Mobile</th>
												<th>Email Id</th>
												<th>Hospital</th>
												<th> Enrolled Date </th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="doctorsList" items="${doctorsList}">
												<tr>
													<td>${doctorsList.name}</td>
													<td>${doctorsList.mobile}</td>
													<td>${doctorsList.emailId}</td>
													<td>ClinIV Hospital </td>
													<td>${doctorsList.createdDate }</td>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>
					<!-- Vertical Scroll Table-->
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Create</span> Investigator
								</h2>
							</div>
							<div class="l-box-body l-spaced">
								<form id="create" class="form-horizontal validate" role="form"
									action="../phcompanyHead/createDoctor.do" method="post"
									enctype="multipart/form-data">

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">First
												Name<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="firstName" id="firstName">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Last
												Name<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="lastName" id="lastName">
											</div>
										</div>

									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Title<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="title" id="title">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Gender<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<select name="gender" id="gender" size="5" tabindex="1"
													style="width: 18.6em;" required="required">
													<option value="Male">Male</option>
													<option value="Female">Female</option>
												</select>
											</div>
										</div>

									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Date
												of Birth<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="date" class="form-control" required="required"
													name="dob" id="dob">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Locality<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="locality" id="locality">
											</div>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Hospital/Clinic
												Name<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="hospital" id="hospital">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">City<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="city" id="city">
											</div>
										</div>

									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">State<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="state" id="state">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Pin
												Code<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="number" class="form-control"
													required="required" name="pincode" id=""pincode"" >
											</div>
										</div>

									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Mobile
												Number<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="mobileNumber" id="mobileNumber">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Email
												Id<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="emailId" id="emailId">
											</div>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="basicSelect"></label>
											<div class="col-sm-8">
												<button type="submit" class="btn btn-green"
													name="buttonName" value="Create"
													onclick="return fnSubmit(this);" form="create">Create</button>
											</div>
										</div>
									</div>


								</form>

							</div>

						</div>
					</div>

					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Upload</span> Investigators
								</h2>
							</div>
							<div class="l-box-body l-spaced">
								<form id="upload" class="form-horizontal validate" role="form"
									action="../phcompanyHead/openword.do" method="post"
									enctype="multipart/form-data">


									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="userName-v">Investigator
												Excel File<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<div class="input-group">
													<span class="input-group-btn"><span
														class="btn btn-primary btn-file">Upload File... <input
															type="file" required="required" id="docExcel"
															name="docExcel" size="50"></span></span> <input type="text"
														class="form-control" readonly name="image"
														placeholder="(XLS, XLSX size up to 250kb)">
												</div>
											</div>
										</div>

									</div>



									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="basicSelect"></label>
											<div class="col-sm-8">
												<button type="submit" class="btn btn-green"
													name="buttonName" value="upload"
													onclick="return fnSubmit(this);" form="upload">Create</button>
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
</div>
