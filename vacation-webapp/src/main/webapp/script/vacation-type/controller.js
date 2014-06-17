// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers.controller('VacationTypeCtrl', function($scope, vacationTypeService) {
	// Refresh the grid, calling the appropriate service method.
	$scope.save = function() {
		vacationTypeService
				.saveVacationType($scope.name, $scope.beginDay, $scope.beginMonth, $scope.endDay,$scope.endMonth, $scope.numberOfDays)
				.success(function(data) {
					//nothing to do for the moment
				});
	};

});

