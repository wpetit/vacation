var app = angular.module('vacations', [ 'ngGrid', 'ui.bootstrap' ]);

// Create a controller with name VacationsCtrl to bind to the html page.
app.controller('VacationsCtrl', function($scope, vacationsService) {
	// Refresh the grid, calling the appropriate service method.
	$scope.refreshGrid = function(page) {
		vacationsService
				.getAll(($scope.pagingOptions.pageSize * (page - 1)), 5)
				.success(function(data) {
					$scope.modelList = data.modelList;
					$scope.nbResults = data.total;
				});
	};

	$scope.pagingOptions = {
		pageSize : 5,
		currentPage : 1
	}

	$scope.gridOptions = {
		data : 'modelList',
		enablePaging : true,
		pagingOptions : $scope.pagingOptions
	};

	$scope.refreshGrid(1);
});

// Service that provides vacations operations
app.service('vacationsService', function($http) {
	// Makes the REST request to get the data to populate the grid.
	this.getAll = function(startIndex, pageSize) {
		return $http.get('rest/vacation/list', {
			params : {
				startIndex : startIndex,
				pageSize : pageSize
			}
		});
	}
});

// Create a controller with name vacationsController to bind to the html page.
app.controller('AuthenticationCtrl', function($scope, $log,
		authenticationService) {

	$scope.hasNotification = false;
	$scope.notification = "";
	// Call the authentication service.
	$scope.login = function() {
		authenticationService.authenticate($scope.username, $scope.password)
				.success(function(authenticationResult) {
					$scope.hasNotification = true;
					$log.log("Authentication result: " + authenticationResult);
					if (authenticationResult == "true") {
						$scope.notification = "Authentication succeed.";
					} else {
						$scope.notification = "Authentication failed.";
					}
				});
	};

});

// Service that provides authentication operations
app.service('authenticationService', function($http) {
	this.authenticate = function(login, password) {
		// var xsrf = $.param({username: login, password : password});
		// return $http({
		// method: 'POST',
		// url: 'rest/authentication',
		// data: xsrf,
		// headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		// });
		return $http.post('rest/authentication', $.param({
			username : login,
			password : password
		}), {
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		});
	}
});
