<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!--HEADER-->
        <header class="l-header l-header-1 t-header-1">
          <div class="navbar navbar-ason">
            <div class="container-fluid">
              <div id="ason-navbar-collapse" class="collapse navbar-collapse">
                <ul class="nav navbar-nav" style="width: 90%">
					<!-- Search Widget-->
					<!-- <div class="widget-search search-in-header is-search-right t-search-1">
                      <form role="form" action="">
                        <input type="text" placeholder="Search..." class="form-control">
                        <button type="submit" class="btn"><i class="fa fa-search"></i></button>
                      </form>
                    </div>
                  </li> -->
					<li>
						<p id="time"
							style="color: white; padding: 14px 20px; margin: 0px;">
							Recent Activity at <b
								style="padding: 5px; margin: 0px; text-align: left; font-size: 18px; font-family: poppins; width: 5%; line-height: 30px;">${timeStr}</b>
						</p>
					</li>
					<li style="width: 45%">
					   <h3 style="display: inline-block;text-align: center; color: white; width: 90%; padding: 0px 0px 5px 0px;">Cliniv EDC</h3>
					</li>
				</ul>
                <ul class="nav navbar-nav navbar-right">
                  <li class="hidden-sm">
                    <!-- Full Screen Toggle--><!-- a href="#" class="full-screen-page sidebar-switcher switcher t-switcher-header"><i class="fa fa-expand"></i></a> -->
                  </li>
                  <li>
                    <!-- Profile Widget-->
                    <div class="widget-profile profile-in-header">
                      <button type="button" data-toggle="dropdown" class="btn dropdown-toggle"><span class="">${sessionScope.UserName} </span><img src="${sessionScope.profiePicture.dPicture}" style="border:1px solid #fff;" alt="Profile picture"/></button>
                      <ul role="menu" class="dropdown-menu">
                        <li><a href="../datamanager/changePassword"><i class="fa fa-cog"></i>Change Password</a></li>
                        <li class="lock"><a href="../logout.do"><i class="fa fa-lock"></i>Log Out</a></li>
                      </ul>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          
        </header>