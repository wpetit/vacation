// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers.controller('VacationTypeCtrl', function($scope, vacationTypeService) {
	// Refresh the grid, calling the appropriate service method.
	$scope.save = function() {
		vacationTypeService
				.saveVacationType($scope.name, $scope.beginDay, $scope.beginMonth, $scope.endDay,$scope.endMonth, $scope.numberOfDays)
				.success(function(data) {
					$scope.name = null;
					$scope.beginDay = null;
					$scope.beginMonth = null;
					$scope.endDay = null;
					$scope.endMonth = null;
					$scope.numberOfDays = null;
				});
	};

});

