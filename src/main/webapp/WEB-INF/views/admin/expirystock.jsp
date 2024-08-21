					
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
   <div class="notificationmsg">${deviceMsg}</div> 
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Drug Stock</span> PAGE
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
	<li class="active">Drug Stock</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">Available Stock</li>
					<!-- <li id="vscrollTable">Distribute Stock</li>
					<li id="plist">Create Stock</li> -->
 					
			</ul>			
		
		
		<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Drugs</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Select</th>
												<!-- <th>Drug Id</th> -->
												<th>Drug Name</th>
												<th>Available Stock</th>
												<th>Total Stock</th>
												<th>Batch Numaber</th>											
												<th>Company Name</th>
												<th>Expiry Date</th>
												<th>Price Pre Units</th>
												<th>Damaged Units</th>
												<th>Shortage Units</th>
												<th>Damaged Image</th>
												<th>Created Date</th>		
												<!-- <th>View Details</th> --> 
											</tr>
										</thead>
										<tbody>
										<c:forEach var="drugStockList" items="${drugStockList}">
												<tr>
													<td>
														<div class="checkbox">
															<label><input type="radio" name="srado"
																value="${devicesObj.id}"></label>
														</div>
													</td>

														
																																			
													
													<%-- <td>${allDrugStockList.drugId}</td> --%>	
													<td>${drugStockList.drugName}</td>	
													<td>${drugStockList.availStock}</td>
													<td>${drugStockList.totStock}</td>														
													<td>${drugStockList.batchNumber }</td>
													<td>${drugStockList.companyName}</td>
													<td>${drugStockList.expiryDate}</td>	
													<td>${drugStockList.price}</td>	
													<td>${drugStockList.dUnits}</td>
													<td>${drugStockList.sUnits}</td>
													<td>${drugStockList.dImage}</td>																
													<td>${drugStockList.createdDate }</td>
											</c:forEach>
										</tbody>
										</table>
				 </div>

							</div>
						</div>
						</div>
				<!-- Vertical Scroll Table-->
				<%-- <div class="l-row l-spaced-bottom">
					<div class="l-box">
						<div class="l-box-header">
							<h2 class="l-box-title">
								<span>Distribute</span> Stock
							</h2>
						</div>
						<div class="l-box-body l-spaced">
							
							     <form id="createDirectors" class="form-horizontal validate" role="form" action="../admin/createDrug.do" method="post" enctype="multipart/form-data">
                  <div class="form-group row">
                    <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Drug Name<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="drug_name" id="drug_name" ></div>
                    </div>
                     <div class="col-sm-6">
                        <label class="col-sm-4 control-label" for="userName-v">Drug Type<font color="red">*</font></label>
                        <div class="col-sm-8"><input type="text" class="form-control required" name="drug_type" id="drug_type" placeholder="eg. capsule"></div>
                        </div> 
                     
                   
                    </div>
                 
                    <div class="form-group row">
                   
                        <div class="col-sm-6">
                  <label class="col-sm-4 control-label" for="basicMultiSelect">Select Company<font color="red">*</font></label>
                  <div class="col-sm-8">
                  <select name="companyies" id="companyies" size="5" tabindex="1" style="width:18.6em;">
                  <c:forEach var="companyies" items="${companyies}">
			        <option value="${companyies.id},${companyies.companyName}">${companyies.companyName}</option>
			      </c:forEach>
			      </select>
			     </div>
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
							
							
							
						</div>

					</div>
				</div> --%>

				
				</div>
				<!-- Notification Publish end -->
				
			</div>
		</div>
	</div>
</div>
