<jsp:include page="header.jsp" />
<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Welcome To Learner Academy Portal!</h1>
                                    </div>
                                    <form class="user" id="userLogin" action = "UserAuthentication" role="form" method="POST" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <input type="text" 
                                                id="userName" name="userName"
                                                placeholder="Enter Username">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" 
                                                id="password" name="password"
                                                placeholder="Password">
                                        </div>
										<div class="form-group">
                                            <input type="submit" id="loginButton" name="loginButton" class="btn btn-primary btn-user btn-block" value="Login">
                                        </div>
                                    </form>
                                    <div id="snackbar"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>	
<jsp:include page="footer.jsp" />

