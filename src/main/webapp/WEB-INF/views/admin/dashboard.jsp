       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- Page Summary Widget-->
        <div class="widget-page-summary">
          <div class="l-col-lg-4" style="width: 69em;">
            <h2 class="page-title">Welcome to <span> ${FirstName}<c:if test="${not empty MiddleName}"> ${MiddleName}</c:if> ${LastName}</span>.</h2>
            <h4 class="page-sub-title">Your <span id="rotating-text">Dashboard</span></h4><a href="#" class="page-summary-info-switcher"><i class="fa fa-bars"></i></a>
          </div>
          <div class="l-col-lg-8 page-summary-info">
            <div class="l-row">
              <!-- Page Summary Clock-->
              <div class="summary-time-status clock-wrapper l-col-md-8 l-col-sm-6">
                <div id="clock"></div>
              </div>
              <!-- Page Summary Weather-->
              <div class="summary-time-status weather-wrapper l-col-md-4 l-col-sm-6">
                <div id="weather">
                  <div class="l-span-sm-6 l-span-xs-12">
                    <div class="weather-location">India</div>
                    <div class="weather-description">Hyderabad</div>
                  </div>
                  <div class="l-span-sm-3 l-span-xs-9">
                    <div class="weather-temp">65°F</div>
                  </div>
                  <div class="l-span-sm-3 l-span-xs-3">
                    <div class="weather-icon"><i class="ac ac-0"></i></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
         <!-- slider -->
          <div class="l-spaced col-md-12">
            <div class="item col-md-2 dashboard-item">
	            <a href="../admin/company.do">
	            <span class="title">Company's</span>
				</a>
            </div>
            <div class="item col-md-2 dashboard-item">
	            <a href="../admin/company.do">
	            <span class="title">Study's</span>
				</a>
            </div>
          </div>
        <!-- slider -->
      <!-- Row 5 - Gender, Age-->
        
