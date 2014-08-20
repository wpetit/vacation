// Create a controller with name VacationsCtrl to bind to the html page.
signUpControllers
		.controller(
				'SignUpCtrl',
				function($scope, $window, $modal, toaster,
						signUpService) {

					// Get vacation balances.
					$scope.createUser = function() {
						signUpService.createUser($scope.username, $scope.password)
								.success(function(data) {
									$window.location.href = $window.location.href.substr(0, $window.location.href.indexOf('signup.html')) + 'login.html';
									});
					};

				});
