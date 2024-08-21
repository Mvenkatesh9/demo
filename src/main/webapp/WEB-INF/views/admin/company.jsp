<<<<<<< HEAD
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
   <div class="notificationmsg">${deviceMsg}</div> 
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Company</span> PAGE
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Company</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">Companies List</li>
					<li id="vscrollTable">Create Company</li>
 					
			</ul>			
		
		
		<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Company</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Select</th>
												<th>Company Id</th>
												<th>Company Name</th>
												<th>Contact Email Id</th>
												<th>contact Mobile Number</th>	
												<th>Company Logo URL</th>											
												<th>Company Website URL</th>
												<th>Created Date</th>	
												<!-- <th>View Details</th> --> 
											</tr>
										</thead>
										<tbody>
										<c:forEach var="companysList" items="${companysList}">
												<tr>
													<td>
														<div class="checkbox">
															<label><input type="radio" name="srado"
																value="${devicesObj.id}"></label>
														</div>
													</td>

														
																																			
													<td>${companysList.id}</td>
													<td>${companysList.companyName}</td>
													<td>${companysList.contactEmail}</td>	
													<td>${companysList.contactNumber}</td>													
													<td>${companysList.companyLogo}</td>	
													<td>${companysList.companyUrl }</td>
													<td>${companysList.createdDate }</td>
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
                  <h2 class="l-box-title"><span>Create</span> Company</h2></div>
                  <section class="l-box-body l-spaced">
                  <form id="createCompany" class="form-horizontal validate" role="form" action="../admin/createCompany.do" method="post" enctype="multipart/form-data">
                  <div class="form-group row">
                    <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Company Name<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="company_name" id="company_name" ></div>
                    </div>
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Company Description<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="company_desc" id="company_desc" ></div>
                    </div>                   
                    </div>
                    <div class="form-group row">
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Contact Email Id<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="contact_email" id="contact_email" ></div>
                        </div>
                    
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v"> ContactMoblie Number<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="contact_number" id="contact_number" ></div>
                        </div>
                    </div>
                    <div class="form-group row">
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Contact Name<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="contact_name" id="contact_name" ></div>
                        </div>
                    
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Company Location<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="company_location" id="company_location" ></div>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                    <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Company URL<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="company_url" id="company_url" placeholder="eg. http://www.cliniv.in"></div>
                        </div> 
                    <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Company Logo<font color="red">*</font></label>
                        <div class="col-sm-8">
                          <div class="input-group"><span class="input-group-btn"><span class="btn btn-primary btn-file">Upload File...
                            <input type="file" required="required"   id="logo" name="logo" size="50"></span></span>
                            <input type="text" class="form-control" readonly name="logo" placeholder="(JPG size up to 250kb)">
                          </div>
                        </div>
                    </div>
                    </div>
                    
                    <div>
                     
                    </div>
                 

                    <div class="form-group row">
                
                  <div class="col-sm-6">
                  <label class="col-sm-4 control-label" for="basicSelect"></label>
                  <div class="col-sm-8">
                   <button type="submit" class="btn btn-green" name="buttonName" value="Create"  onclick= "return fnSubmit(this);" form="createCompany">Create</button>
                  </div>
                </div>
                </div>
               
              </form>
              </section>
                    </div>
                </div>
                <div>               
                </div>

</div>

</div>
</div>
</div>
</div>
=======
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
   <div class="notificationmsg">${deviceMsg}</div> 
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Company</span> PAGE
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Company</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">Companies List</li>
					<li id="vscrollTable">Create Company</li>
 					
			</ul>			
		
		
		<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Company</span> Table
									</h2>
								</div>
								<div class="l-box-body">
								<%--  <form action="../admin/directors.do" method="post" id="directorsRemoveUpdate"> --%>
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Select</th>
												<th>Company Id</th>
												<th>Company Name</th>
												<th>Contact Email Id</th>
												<th>contact Mobile Number</th>	
												<th>Company Logo URL</th>											
												<th>Company Website URL</th>
												<th>Created Date</th>	
												<!-- <th>View Details</th> --> 
											</tr>
										</thead>
										<tbody>
										<c:forEach var="companyList" items="${companyList}">
												<tr>
													<td>
														<div class="checkbox">
															<label><input type="radio" name="srado"
																value="${devicesObj.id}"></label>
														</div>
													</td>

														
																																			
													<td>${companyList.id}</td>
													<td>${companyList.companyName}</td>
													<td>${companyList.contactEmail}</td>	
													<td>${companyList.contactNumber}</td>													
													<td>${companyList.companyLogo}</td>	
													<td>${companyList.comapanyUrl }</td>
													<td>${companyList.createdDate }</td>
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
                  <h2 class="l-box-title"><span>Create</span> Company</h2></div>
                  <section class="l-box-body l-spaced">
                  <form id="createDirectors" class="form-horizontal validate" role="form" action="../admin/createCompany.do" method="post" enctype="multipart/form-data">
                  <div class="form-group row">
                    <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Company Name<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="company_name" id="company_name" ></div>
                    </div>
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Company Logo<font color="red">*</font></label>
                        <div class="col-sm-8">
                          <div class="input-group"><span class="input-group-btn"><span class="btn btn-primary btn-file">Upload File...
                            <input type="file" required="required"   id="logo" name="logo" size="50"></span></span>
                            <input type="text" class="form-control" readonly name="logo" placeholder="(JPG size up to 250kb)">
                          </div>
                        </div>
                    </div>
                     
                   
                    </div>
                    <div class="form-group row">
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Conatact Email Id<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="email_id" id="email_id" ></div>
                        </div>
                    
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v"> ConatactMoblie Number<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="mobile_number" id="mobile_number" ></div>
                        </div>
                    </div>
                    
                    
                    <div class="form-group row">
                    <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Company URL<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="url" id="url" placeholder="eg. http://www.medrep.in"></div>
                        </div> 
                    </div>
                    <div>
                     
                    </div>
                 

                    <div class="form-group row">
                
                  <div class="col-sm-6">
                  <label class="col-sm-4 control-label" for="basicSelect"></label>
                  <div class="col-sm-8">
                   <button type="submit" class="btn btn-green" name="buttonName" value="Create"  onclick= "return fnSubmit(this);" form="createDirectors">Create</button>
                  </div>
                </div>
                </div>
               
              </form>
              </section>
                    </div>
                </div>
                <div>               
                </div>

</div>

>>>>>>> branch 'master' of https://venkatnuthanapati@bitbucket.org/venkatnuthanapati/clinivbackendmvc.git
