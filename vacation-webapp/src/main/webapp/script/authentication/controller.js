// Create a controller with name vacationsController to bind to the html page.
app
		.controller(
				'AuthenticationCtrl',
				function($scope, $log, authenticationService) {

					$scope.authenticationFailed = false;
					$scope.notification = "";
					// Call the authentication service.
					$scope.login = function() {
						authenticationService
								.authenticate($scope.username, $scope.password)
								.success(
										function(authenticationResult) {
											$scope.authenticationFailed = (authenticationResult == "false");
											$log
													.log("Authentication result: "
															+ $scope.authenticationFailed);
											if ($scope.authenticationFailed) {
												$scope.notification = "Authentication failed.";
											}
										});
					};

				});
